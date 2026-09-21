package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.TextPart;

public class TextPartListDTO {
    private Long id;
    private Integer  partNumber;
    private String content;

    public TextPartListDTO() {
    }

    public TextPartListDTO(TextPart entity) {
        id = entity.getId();
        partNumber = entity.getPartNumber();;
        content = entity.getContent();
    }

    public Long getId() {
        return id;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public String getContent() {
        return content;
    }
}
