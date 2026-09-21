package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.TextPart;

public class TextPartResponseDTO {
    private Long id;
    private Integer  partNumber;
    private String content;
    private String audioPath;

    public TextPartResponseDTO() {
    }

    public TextPartResponseDTO(TextPart entity) {
        id = entity.getId();
        partNumber = entity.getPartNumber();
        content = entity.getContent();
        audioPath = entity.getAudioPath();
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

    public String getAudioPath() {
        return audioPath;
    }
}
