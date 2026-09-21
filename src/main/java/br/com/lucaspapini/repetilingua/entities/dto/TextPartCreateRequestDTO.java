package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.TextPart;

public class TextPartCreateRequestDTO {
    private Integer pathNumber;
    private Long textId;
    private String audioPath;
    private String content;

    public TextPartCreateRequestDTO() {
    }

    public TextPartCreateRequestDTO(TextPart entity) {
        pathNumber = entity.getPartNumber();
        textId = entity.getId();
        audioPath = entity.getAudioPath();
        content = entity.getContent();
    }

    public Integer getPathNumber() {
        return pathNumber;
    }

    public Long getTextId() {
        return textId;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public String getContent() {
        return content;
    }
}
