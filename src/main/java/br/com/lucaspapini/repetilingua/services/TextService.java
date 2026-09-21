package br.com.lucaspapini.repetilingua.services;

import br.com.lucaspapini.repetilingua.entities.Text;
import br.com.lucaspapini.repetilingua.entities.TextPart;
import br.com.lucaspapini.repetilingua.entities.TextPartProgress;
import br.com.lucaspapini.repetilingua.entities.User;
import br.com.lucaspapini.repetilingua.entities.dto.*;
import br.com.lucaspapini.repetilingua.repositories.TextPartProgressRepository;
import br.com.lucaspapini.repetilingua.repositories.TextPartRepository;
import br.com.lucaspapini.repetilingua.repositories.TextRepository;
import br.com.lucaspapini.repetilingua.repositories.UserRepository;
import br.com.lucaspapini.repetilingua.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TextService {
    @Autowired
    private TextRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TextPartProgressRepository textPartProgressRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private TextPartRepository textPartRepository;

    @Transactional(readOnly = true)
    public Page<TextListDTO> findAll(Pageable pageable) {
        User me = userService.authenticated();
        Page<Text> result = repository.findByUserId(me.getId(), pageable);
        return result.map(x -> new TextListDTO(x));
    }

    @Transactional(readOnly = true)
    public TextResponseDTO findById(Long id) {
        Text result = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possivel localizar o texto informado com id:  " + id));
        //// Se o usuario não for ADMIN e não for o dono do texto lança 403
        authService.validateSelfOrAdmin(result.getUser().getId());
        return new TextResponseDTO(result);
    }

    @Transactional(readOnly = false)
    public TextCreateRequestDTO insert(TextCreateRequestDTO dto) {
        User me = userService.authenticated();

        Text entity = new Text();

        // Verifica se o Usuario existe
        User user = userRepository.findById(me.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado"));

        // Envia os dados do dto para a entidade
        copyDtoToEntity(dto, entity, user);

        entity = repository.save(entity);

        return new TextCreateRequestDTO(entity);
    }

    public TextUpdateRequestDTO update(Long id, TextUpdateRequestDTO dto) {
        Text entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não localizado"));

        entity.setTitle(dto.getTitle());
        entity.setModule(dto.getModule());
        entity.setCompleted(dto.getCompleted());

        entity = repository.save(entity);

        return new TextUpdateRequestDTO(entity);
    }

    @Transactional(readOnly = true)
    public StatsDTO getAllStats() {
        User me = userService.authenticated();

        List<Text> texts = repository.findByUserId(me.getId());
        List<TextPartProgress> partProgress = textPartProgressRepository.findByUserId(me.getId());

        Long totalTextos = (long) texts.size();

        Long totalTextoPartes = texts.stream().mapToLong(item -> item.getTextParts().size()).sum();

        Long totalCompletedStudies = partProgress.stream().filter(TextPartProgress::getComplete).count();

        Long totalEscutaMaisLeitura = partProgress.stream().mapToLong(item -> item.getDay1ReadAndListen() + item.getDay2ReadAndListen()).sum();

        Long totalSoEsculta = partProgress.stream().mapToLong(item -> item.getDay1OnlyListen() + item.getDay2OnlyListen()).sum();

        Long totalRepetitions = partProgress
                .stream()
                .mapToLong(item ->
                        item.getDay1ReadAndListen() +
                                item.getDay1OnlyListen() +
                                item.getDay1ReadAndListenFinalCheck() +
                                item.getDay1ReadAndListenFinalCheck() +
                                item.getDay2ReadAndListen() +
                                item.getDay2OnlyListen() +
                                item.getDay2ReadAndListenFinalCheck()
                ).sum();

        return new StatsDTO(totalTextos, totalTextoPartes, totalCompletedStudies, totalEscutaMaisLeitura, totalSoEsculta, totalRepetitions);
    }

    @Transactional(readOnly = true)
    public Page<TextResumeResponseDTO> findResumeTexts(Pageable pageable) {
        User me = userService.authenticated();

        List<TextResumeDataDTO> dataDTOS = repository.findAllTextByUserId(me.getId());

        Map<Long, List<TextResumeDataDTO>> agroup = dataDTOS.stream()
                .collect(Collectors.groupingBy(TextResumeDataDTO::getTextId));

        List<TextResumeResponseDTO> response = new ArrayList<>();

        for (Map.Entry<Long, List<TextResumeDataDTO>> entry : agroup.entrySet()) {
            List<TextResumeDataDTO> items = entry.getValue();
            TextResumeDataDTO first = items.get(0);
            long idText = items.stream()
                    .map(TextResumeDataDTO::getTextId)
                    .findFirst()
                    .orElse(null);

            long totalPartes = items.size();
            long totalAudios = items.stream()
                    .filter(i -> i.getAudioPath() != null)
                    .count();
            long repetitions = items.stream()
                    .mapToLong(i -> i.getD1ReadListen()
                            + i.getD1ReadListen()
                            + i.getD1ListenOnly()
                            + i.getD2ReadListen()
                            + i.getD2ListenOnly()
                            + i.getD2FinalCheck()
                    )
                    .sum();
            response.add(
                    new TextResumeResponseDTO(
                            idText,
                            first.getTitle(),
                            totalPartes,
                            totalAudios,
                            repetitions,
                            first.getMoment()
                    )
            );
        }

        // Páginação manual
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), response.size());

        return new PageImpl<>(
                response.subList(start, end),
                pageable,
                response.size()
        );
    }

    @Transactional(readOnly = true)
    public TextStatsResponseDTO getTextStats(Long id) {
        User me = userService.authenticated();

        Text dataDTOS = repository.findAllTextByUserIdAndByTextId(me.getId(), id);

        if(dataDTOS == null){
            throw new  ResourceNotFoundException("Parte do Texto informada não localizada!");
        }

        TextStatsResponseDTO response = new TextStatsResponseDTO();

        Long totalParts = (long) dataDTOS.getTextParts()
                .size();

        Long completedParts = dataDTOS.getTextParts().stream()
                .flatMap(x -> x.getPartProgresses().stream())
                .filter(TextPartProgress::getComplete)
                .count();

        Double overallProgress = dataDTOS.getTextParts().stream()
                .flatMap(x -> x.getPartProgresses().stream())
                .mapToLong(i ->
                        i.getDay1ReadAndListen()
                                + i.getDay1OnlyListen()
                                + i.getDay1ReadAndListenFinalCheck()
                                + i.getDay2ReadAndListen()
                                + i.getDay2OnlyListen()
                                + i.getDay2ReadAndListenFinalCheck()
                )
                .average()
                .orElse(0.0);

        long repetitions = dataDTOS.getTextParts().stream()
                .flatMap(x -> x.getPartProgresses().stream())
                .mapToLong(i ->
                        i.getDay1ReadAndListen()
                                + +i.getDay1OnlyListen()
                                + i.getDay1ReadAndListenFinalCheck()
                                + i.getDay2ReadAndListen()
                                + i.getDay2OnlyListen()
                                + i.getDay2ReadAndListenFinalCheck()
                ).sum();

        LocalDateTime lastActivity = dataDTOS.getTextParts().stream()
                .flatMap(x -> x.getPartProgresses().stream())
                .map(i -> i.getLastActivity())
                .filter(Objects::nonNull)
                .max(Comparator.naturalOrder())
                .orElse(null);

        response.setTextId(dataDTOS.getId());
        response.setTitle(dataDTOS.getTitle());
        response.setModulo(dataDTOS.getModule());
        response.setTotalParts(totalParts);
        response.setCompletedParts(completedParts);
        response.setOverallProgress(overallProgress);
        response.setTotalRepetitions(repetitions);
        response.setLastActivity(lastActivity);

        return response;
    }

    public void delete(Long id) {
        User me = userService.authenticated();
        // 1. Garante que o texto existe e pertence ao usuário
        Text text = repository.findByIdAndUserId(id, me.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Texto não encontrado"));

        // 2. Apaga todos os progressos atrelados às partes deste texto em uma só query
        textPartProgressRepository.deleteAllByTextPartTextIdAndUserId(text.getId(), me.getId());

        // 3. Apaga todas as partes deste texto em uma só query
        textPartRepository.deleteAllByTextId(text.getId());

        // 4. Apaga o texto
        repository.delete(text);
    }

    private void copyDtoToEntity(TextCreateRequestDTO dto, Text entity, User user) {
        entity.setTitle(dto.getTitle());
        entity.setModule(dto.getModule());
        entity.setUser(user);
        entity.setCompleted(Boolean.FALSE);
        entity.setMoment(Instant.now());
    }
}
