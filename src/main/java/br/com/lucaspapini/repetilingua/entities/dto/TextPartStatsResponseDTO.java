package br.com.lucaspapini.repetilingua.entities.dto;

public class TextPartStatsResponseDTO {
    private Long textPartId;
    private Long partNumber;
    private String content;
    private String audioPath;
    private Long completedParts;
    private Long totalRepetitions;

    public TextPartStatsResponseDTO() {
    }

    public TextPartStatsResponseDTO(Long textPartId, Long partNumber, String content, String audioPath, Long completedParts, Long totalRepetitions) {
        this.textPartId = textPartId;
        this.partNumber = partNumber;
        this.content = content;
        this.audioPath = audioPath;
        this.completedParts = completedParts;
        this.totalRepetitions = totalRepetitions;
    }

    public Long getTotalRepetitions() {
        return totalRepetitions;
    }

    public void setTotalRepetitions(Long totalRepetitions) {
        this.totalRepetitions = totalRepetitions;
    }

    public Long getCompletedParts() {
        return completedParts;
    }

    public void setCompletedParts(Long completedParts) {
        this.completedParts = completedParts;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Long partNumber) {
        this.partNumber = partNumber;
    }

    public Long getTextPartId() {
        return textPartId;
    }

    public void setTextPartId(Long textPartId) {
        this.textPartId = textPartId;
    }
}
