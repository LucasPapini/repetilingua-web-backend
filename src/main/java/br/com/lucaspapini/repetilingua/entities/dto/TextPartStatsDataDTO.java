package br.com.lucaspapini.repetilingua.entities.dto;

public class TextPartStatsDataDTO {
    private Long id;
    private Integer partNumber;
    private String content;
    private String audioPath;

    private Integer d1ReadListen;
    private Integer d1ListenOnly;
    private Integer d1FinalCheck;

    private Integer d2ReadListen;
    private Integer d2ListenOnly;
    private Integer d2FinalCheck;

    private Boolean complete;

    public TextPartStatsDataDTO() {
    }

    public TextPartStatsDataDTO(Long id, Integer partNumber, String content, String audioPath, Integer d1ReadListen, Integer d1ListenOnly, Integer d1FinalCheck, Integer d2ReadListen, Integer d2ListenOnly, Integer d2FinalCheck, Boolean complete) {
        this.id = id;
        this.partNumber = partNumber;
        this.content = content;
        this.audioPath = audioPath;
        this.d1ReadListen = d1ReadListen;
        this.d1ListenOnly = d1ListenOnly;
        this.d1FinalCheck = d1FinalCheck;
        this.d2ReadListen = d2ReadListen;
        this.d2ListenOnly = d2ListenOnly;
        this.d2FinalCheck = d2FinalCheck;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public Integer getD1ReadListen() {
        return d1ReadListen;
    }

    public void setD1ReadListen(Integer d1ReadListen) {
        this.d1ReadListen = d1ReadListen;
    }

    public Integer getD1ListenOnly() {
        return d1ListenOnly;
    }

    public void setD1ListenOnly(Integer d1ListenOnly) {
        this.d1ListenOnly = d1ListenOnly;
    }

    public Integer getD1FinalCheck() {
        return d1FinalCheck;
    }

    public void setD1FinalCheck(Integer d1FinalCheck) {
        this.d1FinalCheck = d1FinalCheck;
    }

    public Integer getD2ReadListen() {
        return d2ReadListen;
    }

    public void setD2ReadListen(Integer d2ReadListen) {
        this.d2ReadListen = d2ReadListen;
    }

    public Integer getD2ListenOnly() {
        return d2ListenOnly;
    }

    public void setD2ListenOnly(Integer d2ListenOnly) {
        this.d2ListenOnly = d2ListenOnly;
    }

    public Integer getD2FinalCheck() {
        return d2FinalCheck;
    }

    public void setD2FinalCheck(Integer d2FinalCheck) {
        this.d2FinalCheck = d2FinalCheck;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}
