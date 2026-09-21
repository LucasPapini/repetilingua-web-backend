package br.com.lucaspapini.repetilingua.services;

import br.com.lucaspapini.repetilingua.entities.TextPart;
import br.com.lucaspapini.repetilingua.entities.TextPartProgress;
import br.com.lucaspapini.repetilingua.entities.User;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartProgresstCreateDTO;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartProgresstDTO;
import br.com.lucaspapini.repetilingua.repositories.TextPartProgressRepository;
import br.com.lucaspapini.repetilingua.repositories.TextPartRepository;
import br.com.lucaspapini.repetilingua.repositories.UserRepository;
import br.com.lucaspapini.repetilingua.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Service
public class TextPartProgressService {
    @Autowired
    private TextPartProgressRepository repository;

    @Autowired
    private TextPartRepository textPartRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<TextPartProgresstDTO> findAll(Pageable pageable) {
        Page<TextPartProgress> result = repository.findAll(pageable);
        return result.map(x -> new TextPartProgresstDTO(x));
    }

    @Transactional(readOnly = true)
    public TextPartProgresstDTO findByIdAndByIdTextPart(Long id, Long idTextPart) {
        TextPartProgress entity = repository.findByIdAndTextPartId(id, idTextPart);
        return new TextPartProgresstDTO(entity);
    }

    @Transactional(readOnly = false)
    public TextPartProgresstCreateDTO update(TextPartProgresstCreateDTO dto) {
        TextPartProgress entity = repository.findById(dto.getIdTextPart())
                .orElseThrow(() -> new ResourceNotFoundException("Parte do Texto informada não localizada!"));

        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new ResourceNotFoundException("O usuário informado não foi localizado!"));

        copyDtoToEntity(entity, user, dto);

        entity = repository.save(entity);

        return new TextPartProgresstCreateDTO(entity);
    }

    private void copyDtoToEntity(TextPartProgress entity, User user, TextPartProgresstCreateDTO dto) {
        entity.setUser(user);

        // --- DIA 1 ---
        setIfNotNull(dto.getDay1ReadAndListen(), entity::setDay1ReadAndListen);
        setIfNotNull(dto.getDay1OnlyListen(), entity::setDay1OnlyListen);
        setIfNotNull(dto.getDay1ReadAndListenFinalCheck(), entity::setDay1ReadAndListenFinalCheck);

        // --- DIA 2 ---
        setIfNotNull(dto.getDay2ReadAndListen(), entity::setDay2ReadAndListen);
        setIfNotNull(dto.getDay2OnlyListen(), entity::setDay2OnlyListen);
        setIfNotNull(dto.getDay2ReadAndListenFinalCheck(), entity::setDay2ReadAndListenFinalCheck);
        entity.setLastActivity(LocalDateTime.now());
    }

    private void setIfNotNull(Integer value, Consumer<Integer> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
