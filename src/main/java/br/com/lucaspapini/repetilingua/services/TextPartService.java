package br.com.lucaspapini.repetilingua.services;

import br.com.lucaspapini.repetilingua.enums.TextPartProgressEnum;
import br.com.lucaspapini.repetilingua.entities.Text;
import br.com.lucaspapini.repetilingua.entities.TextPart;
import br.com.lucaspapini.repetilingua.entities.TextPartProgress;
import br.com.lucaspapini.repetilingua.entities.User;
import br.com.lucaspapini.repetilingua.entities.dto.*;
import br.com.lucaspapini.repetilingua.repositories.TextPartProgressRepository;
import br.com.lucaspapini.repetilingua.repositories.TextPartRepository;
import br.com.lucaspapini.repetilingua.repositories.TextRepository;
import br.com.lucaspapini.repetilingua.repositories.UserRepository;
import br.com.lucaspapini.repetilingua.services.exceptions.ForbiddenException;
import br.com.lucaspapini.repetilingua.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TextPartService {
    @Autowired
    private TextPartRepository repository;

    @Autowired
    private TextRepository textRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TextPartProgressRepository textPartProgressRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public Page<TextPartListDTO> findAll(Pageable pageable) {
        Page<TextPart> result = repository.findAll(pageable);
        return result.map(x -> new TextPartListDTO(x));
    }

    @Transactional(readOnly = true)
    public TextPartResponseDTO findById(Long id) {
        User me = userService.authenticated();
        TextPart result = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possivel localiar a parte do texto como o ID: " + id));
        return new TextPartResponseDTO(result);
    }

    @Transactional(readOnly = true)
    public Page<TextPartStatsResponseDTO> getTextPartStat(Long id, Pageable pageable) {
        User me = userService.authenticated();

        List<TextPartStatsDataDTO> dto = repository.findAllTextPartByUserAndTextId(me.getId(), id);

        List<TextPartStatsResponseDTO> response = dto.stream()
                .filter(entry -> entry.getPartNumber() != null) // Ignora registros nulos
                .map(entry -> {
                    long totalRepetitions = Objects.requireNonNullElse(entry.getD1ReadListen(), 0)
                            + Objects.requireNonNullElse(entry.getD1ListenOnly(), 0)
                            + Objects.requireNonNullElse(entry.getD1FinalCheck(), 0)
                            + Objects.requireNonNullElse(entry.getD2ReadListen(), 0)
                            + Objects.requireNonNullElse(entry.getD2ListenOnly(), 0)
                            + Objects.requireNonNullElse(entry.getD2FinalCheck(), 0);

                    return new TextPartStatsResponseDTO(
                            entry.getId(),
                            entry.getPartNumber().longValue(),
                            entry.getContent(),
                            entry.getAudioPath(),
                            Boolean.TRUE.equals(entry.getComplete()) ? 1L : 0L,
                            totalRepetitions);
                })
                .toList();

        // Páginação manual
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), response.size());

        return new PageImpl<>(
                response.subList(start, end),
                pageable,
                response.size());
    }

    @Transactional(readOnly = true)
    public TextPartStudyResponseDTO getTextPartStudy(Long textId, Long partNumber) {
        User me = userService.authenticated();
        return repository.findTextPartStudy(me.getId(), textId, partNumber);
    }

    @Transactional(readOnly = false)
    public TextPartCreateRequestDTO create(TextPartCreateRequestDTO dto) {
        User me = userService.authenticated();

        Text text = textRepository.findById(dto.getTextId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possivel localizar o texto informado! "));

        TextPart entity = new TextPart();

        copyDtoToEntity(dto, entity);

        entity.setText(text);

        // Vereficar se a parte informado para o texto já existe ...
        boolean existeParteTextoJaCadastrada = repository.existsByPartNumberAndTextId(dto.getPathNumber(),
                dto.getTextId());

        if (existeParteTextoJaCadastrada) {
            throw new ResourceNotFoundException("A parte do texto de número: " + dto.getPathNumber()
                    + " já existe para o texto: " + entity.getText().getTitle());
        }

        // salvar a parte do texto e pegar o id dela
        entity = repository.save(entity);

        // Estacia objeto de Progresso do texto
        TextPartProgress textPartProgress = new TextPartProgress();

        salvandoUmProgressoAoCriarUmaParteDoTexto(textPartProgress, entity, me);

        // Salvando o progresso inicial do texto
        textPartProgressRepository.save(textPartProgress);

        return new TextPartCreateRequestDTO(entity);
    }

    @Transactional(readOnly = false)
    public TextPartUpdateRequestDTO update(Long id, TextPartUpdateRequestDTO dto) {
        TextPart entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A parte do texto não foi localizado"));

        // Validação defensiva para o set nos cmapos
        if (dto.getPathNumber() != null) {
            entity.setPartNumber(dto.getPathNumber());
        }
        if (dto.getAudioPath() != null) {
            entity.setAudioPath(dto.getAudioPath());
        }
        if (dto.getContent() != null) {
            entity.setContent(dto.getContent());
        }

        entity = repository.save(entity);
        return new TextPartUpdateRequestDTO(entity);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Mão foi possivel localizar a parte do texto para exclusão!");
        }

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {

        }
    }

    public void setProgressTextPart(Long idTextPart, Long idText, TextPartProgressEnum field) {
        User me = userService.authenticated();

        // 1. Busca o progresso ou instancia um novo registro caso não exista
        TextPartProgress progress = textPartProgressRepository
                .findByTextPartIdAndTextIdAndUserId(idTextPart, idText, me.getId())
                .orElseGet(() -> createInitialProgress(idText, idTextPart, me));

        switch (field) {
            case D1_READ_LISTEN -> progress.setDay1ReadAndListen(increment(progress.getDay1ReadAndListen()));
            case D1_LISTEN_ONLY -> progress.setDay1OnlyListen(increment(progress.getDay1OnlyListen()));
            case D1_FINAL_CHECK ->
                progress.setDay1ReadAndListenFinalCheck(increment(progress.getDay1ReadAndListenFinalCheck()));
            case D2_READ_LISTEN -> progress.setDay2ReadAndListen(increment(progress.getDay2ReadAndListen()));
            case D2_LISTEN_ONLY -> progress.setDay2OnlyListen(increment(progress.getDay2OnlyListen()));
            case D2_FINAL_CHECK ->
                progress.setDay2ReadAndListenFinalCheck(increment(progress.getDay2ReadAndListenFinalCheck()));

        }

        textPartProgressRepository.save(progress);
    }

    private TextPartProgress createInitialProgress(Long idText, Long idTextPart, User me) {
        TextPartProgress newProgress = new TextPartProgress();
        newProgress.setUser(me);
        // --- DIA 1 ---
        newProgress.setDay1ReadAndListen(0);
        newProgress.setDay1OnlyListen(0);
        newProgress.setDay1ReadAndListenFinalCheck(0);

        // --- DIA 2 ---
        newProgress.setDay2ReadAndListen(0);
        newProgress.setDay2OnlyListen(0);
        newProgress.setDay2ReadAndListenFinalCheck(0);

        TextPart textPart = new TextPart();
        textPart.setId(idTextPart);
        newProgress.setTextPart(textPart);

        return newProgress;
    }

    private void salvandoUmProgressoAoCriarUmaParteDoTexto(TextPartProgress textPartProgress, TextPart entity,
            User user) {
        textPartProgress.setTextPart(entity);
        textPartProgress.setUser(user);

        // Dia 1
        textPartProgress.setDay1ReadAndListen(0);
        textPartProgress.setDay1OnlyListen(0);
        textPartProgress.setDay1ReadAndListenFinalCheck(0);

        // Dia 2
        textPartProgress.setDay2ReadAndListen(0);
        textPartProgress.setDay2OnlyListen(0);
        textPartProgress.setDay2ReadAndListenFinalCheck(0);

        textPartProgress.setLastActivity(LocalDateTime.now());
        textPartProgress.setComplete(Boolean.FALSE);
    }

    public String upload(MultipartFile file, Long textId) throws IOException {
        User me = userService.authenticated();
        String directory = "audios/user-" + me.getId() + "/text-" + textId + "/";
        String fileName = file.getOriginalFilename().replaceAll("\\s+", "_");
        fileName.replaceAll("-", "");
        Path path = Paths.get(directory + fileName);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        return directory + fileName;
    }

    private void copyDtoToEntity(TextPartCreateRequestDTO dto, TextPart entity) {
        entity.setContent(dto.getContent());
        entity.setPartNumber(dto.getPathNumber());
        entity.setAudioPath(dto.getAudioPath());
    }

    private int increment(int current) {
        if (current >= 20) {
            throw new ForbiddenException("Meta de 20 repetições já atigida.");
        }
        return current + 1;
    }
}
