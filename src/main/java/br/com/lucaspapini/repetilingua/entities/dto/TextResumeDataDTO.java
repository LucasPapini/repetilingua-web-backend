package br.com.lucaspapini.repetilingua.entities.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public class TextResumeDataDTO {
    private Long textId;
    private String title;
    private Integer partNumber;
    private String audioPath;
    private Instant moment;

    private Integer d1ReadListen;
    private Integer d1ListenOnly;
    private Integer d1FinalCheck;

    private Integer d2ReadListen;
    private Integer d2ListenOnly;
    private Integer d2FinalCheck;

    public TextResumeDataDTO() {
    }

    public TextResumeDataDTO(Long textId, String title, Integer partNumber, String audioPath, Instant moment, Integer d1ReadListen, Integer d1ListenOnly, Integer d1FinalCheck, Integer d2ReadListen, Integer d2ListenOnly, Integer d2FinalCheck) {
        this.textId = textId;
        this.title = title;
        this.partNumber = partNumber;
        this.audioPath = audioPath;
        this.moment = moment;
        this.d1ReadListen = d1ReadListen;
        this.d1ListenOnly = d1ListenOnly;
        this.d1FinalCheck = d1FinalCheck;
        this.d2ReadListen = d2ReadListen;
        this.d2ListenOnly = d2ListenOnly;
        this.d2FinalCheck = d2FinalCheck;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Long getTextId() {
        return textId;
    }

    public void setTextId(Long textId) {
        this.textId = textId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
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
}
