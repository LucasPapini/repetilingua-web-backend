package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.TextPartProgress;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class    TextPartProgressListDTO {
    private static final int MAX_POR_ATIVIDADE = 20;

    private Long id;
    private Integer partTexto;
    private LocalDateTime lastActivetyAt;
    private String titlePart;
    private int     progress;
    private boolean complet;

    public TextPartProgressListDTO() {
    }

    public TextPartProgressListDTO(TextPartProgress entity) {
        id = entity.getId();
        partTexto = entity.getTextPart().getPartNumber();
        lastActivetyAt = entity.getLastActivity();
        titlePart = entity.getTextPart().getText().getTitle();
        progress = calcProgress(entity);
        complet = entity.getComplete();
    }

    private int calcProgress(TextPartProgress entity) {
        if(entity.getComplete()){
            return 100;
        }

        List<Integer> valores = Arrays.asList(
                entity.getDay1ReadAndListen(),
                entity.getDay1OnlyListen(),
                entity.getDay1ReadAndListenFinalCheck(),
                entity.getDay2ReadAndListen(),
                entity.getDay2OnlyListen(),
                entity.getDay2ReadAndListenFinalCheck()
        );

        int totalAtual = valores.stream()
                .mapToInt(x -> x == null ? 0 : x)
                .sum();

        int totalMax = valores.size() * MAX_POR_ATIVIDADE;
        return (totalAtual * 100) /totalMax;
    }

    private int nvl(Integer value) {
        return value == null ? 0 : value;
    }

    public int getProgress() {
        return progress;
    }

    public Integer getPartTexto() {
        return partTexto;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getLastActivetyAt() {
        return lastActivetyAt;
    }

    public String getTitlePart() {
        return titlePart;
    }

    public boolean isComplet() {
        return complet;
    }
}
