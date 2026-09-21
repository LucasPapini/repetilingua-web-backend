package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.TextPartProgress;

import java.time.LocalDateTime;

public class TextPartProgresstCreateDTO {
    private Long idTextPart;
    private Long idUser;

    // --- DIA 1 ---
    private Integer day1ReadAndListen;
    private Integer day1OnlyListen;
    private Integer day1ReadAndListenFinalCheck;

    // --- DIA 2 ---
    private Integer day2ReadAndListen;
    private Integer day2OnlyListen;
    private Integer day2ReadAndListenFinalCheck;

    private LocalDateTime lastActivity;

    public TextPartProgresstCreateDTO() {
    }

    public TextPartProgresstCreateDTO(TextPartProgress entity) {
      idTextPart = entity.getTextPart().getId();
      idUser = entity.getUser().getId();
      // -- DIA 1 ---
      day1ReadAndListen = entity.getDay1ReadAndListen();
      day1OnlyListen = entity.getDay1OnlyListen();
      day1ReadAndListenFinalCheck = entity.getDay1ReadAndListenFinalCheck();

      // -- DIA 2 ---
      day2ReadAndListen = entity.getDay2ReadAndListen();
      day2OnlyListen = entity.getDay2OnlyListen();
      day2ReadAndListenFinalCheck = entity.getDay2ReadAndListenFinalCheck();
    }

    public Long getIdTextPart() {
        return idTextPart;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Integer getDay1ReadAndListen() {
        return day1ReadAndListen;
    }

    public Integer getDay1OnlyListen() {
        return day1OnlyListen;
    }

    public Integer getDay1ReadAndListenFinalCheck() {
        return day1ReadAndListenFinalCheck;
    }

    public Integer getDay2ReadAndListen() {
        return day2ReadAndListen;
    }

    public Integer getDay2OnlyListen() {
        return day2OnlyListen;
    }

    public Integer getDay2ReadAndListenFinalCheck() {
        return day2ReadAndListenFinalCheck;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }
}
