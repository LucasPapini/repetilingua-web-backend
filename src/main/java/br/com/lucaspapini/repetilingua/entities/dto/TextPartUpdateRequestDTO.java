package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.TextPart;

public class TextPartUpdateRequestDTO {
    private Integer pathNumber;
    private String audioPath;
    private String content;

    public TextPartUpdateRequestDTO() {
    }

    public TextPartUpdateRequestDTO(Integer pathNumber, String audioPath, String content) {
        this.pathNumber = pathNumber;
        this.audioPath = audioPath;
        this.content = content;
    }

    public TextPartUpdateRequestDTO(TextPart entity) {
        pathNumber = entity.getPartNumber();
        audioPath = entity.getAudioPath();
        content = entity.getContent();
    }

    public Integer getPathNumber() {
        return pathNumber;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public String getContent() {
        return content;
    }
}
