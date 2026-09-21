package br.com.lucaspapini.repetilingua.controllers;

import br.com.lucaspapini.repetilingua.controllers.docs.TextPartProgressControllerDocs;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartProgresstCreateDTO;
import br.com.lucaspapini.repetilingua.entities.dto.TextPartProgresstDTO;
import br.com.lucaspapini.repetilingua.services.TextPartProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/progress")
public class TextPartProgressController implements TextPartProgressControllerDocs {
    @Autowired
    private TextPartProgressService service;

    @GetMapping
    public ResponseEntity<Page<TextPartProgresstDTO>> findeAll(Pageable pageable){
        Page<TextPartProgresstDTO> list = service.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/id/{id}/text-part/{idTextPart}")
    public ResponseEntity<TextPartProgresstDTO> findByIdAndIdPartText(@PathVariable("id") Long id, @PathVariable("idTextPart") Long idTextPart ){
        TextPartProgresstDTO dto = service.findByIdAndByIdTextPart(id, idTextPart);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/text-part/{idTextPart}")
    public ResponseEntity<TextPartProgresstCreateDTO> update(@RequestBody TextPartProgresstCreateDTO dto){
        TextPartProgresstCreateDTO result  = service.update(dto);
        return ResponseEntity.ok(result);
    }
}
