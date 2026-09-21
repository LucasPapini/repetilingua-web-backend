package br.com.lucaspapini.repetilingua.controllers;

import br.com.lucaspapini.repetilingua.controllers.docs.TextControllerDocs;
import br.com.lucaspapini.repetilingua.entities.dto.*;
import br.com.lucaspapini.repetilingua.services.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/texts")
public class TextController implements TextControllerDocs {
    @Autowired
    private TextService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<Page<TextListDTO>> findAll(Pageable pageable) {
        Page<TextListDTO> list = service.findAll(pageable);

        return ResponseEntity.ok(list);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TextResponseDTO> findById(@PathVariable("id") Long id) {
        TextResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR')")
    @PostMapping
    public ResponseEntity<TextCreateRequestDTO> create(@RequestBody TextCreateRequestDTO dto) {
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TextUpdateRequestDTO> update(@PathVariable("id") Long id, @RequestBody TextUpdateRequestDTO dto) {
        TextUpdateRequestDTO result = service.update(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @GetMapping(value = "/resume")
    public ResponseEntity<Page<TextResumeResponseDTO>> resumeText(Pageable pageable){
        Page<TextResumeResponseDTO> dto = service.findResumeTexts(pageable);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}/stats")
    public ResponseEntity<TextStatsResponseDTO> getTextStat(@PathVariable("id") Long id){
        TextStatsResponseDTO dto = service.getTextStats(id);
        return ResponseEntity.ok(dto);
    }
}
