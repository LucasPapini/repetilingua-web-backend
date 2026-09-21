package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.Text;
import br.com.lucaspapini.repetilingua.entities.TextPart;

import java.util.ArrayList;
import java.util.List;

public class TextResponseDTO {
    private Long id;
    private String title;
    private String module;
    private List<TextPartListDTO> parts = new ArrayList<>();

    public TextResponseDTO() {
    }

    public TextResponseDTO(Text entity) {
        id = entity.getId();
        title = entity.getTitle();
        module = entity.getModule();
        for(TextPart item : entity.getTextParts()){
            TextPartListDTO dto = new TextPartListDTO(item);
            parts.add(dto);
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getModule() {
        return module;
    }

    public List<TextPartListDTO> getParts() {
        return parts;
    }
}
