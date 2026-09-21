package br.com.lucaspapini.repetilingua.controllers;

import br.com.lucaspapini.repetilingua.enums.TextPartProgressEnum;
import br.com.lucaspapini.repetilingua.controllers.docs.TextPartControllerDocs;
import br.com.lucaspapini.repetilingua.entities.dto.*;
import br.com.lucaspapini.repetilingua.services.TextPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping(value = "/texts-parts")
public class TextPartController implements TextPartControllerDocs {
    @Autowired
    private TextPartService service;

    @GetMapping
    public ResponseEntity<Page<TextPartListDTO>> findAll(Pageable pageable) {
        Page<TextPartListDTO> list = service.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<TextPartResponseDTO> findById(@PathVariable("id") Long id) {
        TextPartResponseDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<TextPartCreateRequestDTO> create(@RequestBody TextPartCreateRequestDTO dto) {
        dto = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getTextId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<TextPartUpdateRequestDTO> update(
            @PathVariable("id") Long id,
            @RequestBody TextPartUpdateRequestDTO dto
    ) {
        TextPartUpdateRequestDTO result = service.update(id, dto);
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

    @GetMapping(value = "/{id}/stats")
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<Page<TextPartStatsResponseDTO>> getTextPartStat(@PathVariable("id") Long id, Pageable pageable){
        Page<TextPartStatsResponseDTO> dto = service.getTextPartStat(id, pageable);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{textId}/part/{partNumber}/study")
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<TextPartStudyResponseDTO> getTextPartStudy(@PathVariable("textId") Long textId, @PathVariable("partNumber") Long partNumber){
        TextPartStudyResponseDTO dto = service.getTextPartStudy(textId, partNumber);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/part/{id}/text/{idText}/progress")
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<Void> setProgressTextPart(
            @PathVariable("id") Long idTextPart,
            @PathVariable("idText") Long idText,
            @RequestParam TextPartProgressEnum field
    ){
        service.setProgressTextPart(idTextPart, idText, field);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/upload")
    @PreAuthorize("hasAnyRole('ROLE_OPERATOR', 'ROLE_ADMIN')")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("textId") Long textId
    ) throws IOException {
        String audioPath = service.upload(file, textId);
        return ResponseEntity.ok(audioPath);
    }

}
