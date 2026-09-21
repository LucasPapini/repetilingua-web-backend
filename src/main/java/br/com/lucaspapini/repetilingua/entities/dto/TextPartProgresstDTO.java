package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.TextPartProgress;

import java.util.Arrays;
import java.util.List;

public class TextPartProgresstDTO {
    private static final int MAX_POR_ATIVIDADE = 20;

    private Long idTextoPartProgress;
    private Integer partTexto;
    private int     progress;
    private boolean complet;
    // private String title;

    public TextPartProgresstDTO() {
    }

    public TextPartProgresstDTO(TextPartProgress entity) {
        idTextoPartProgress = entity.getId();
        partTexto = entity.getTextPart().getPartNumber();
        //title = entity.getTextPart().getText().getTitle();
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

    public Long getIdTextoPartProgress() {
        return idTextoPartProgress;
    }

//    // public String getTitle() {
//        return title;
//    }

    public boolean isComplet() {
        return complet;
    }

    public Integer getPartTexto() {
        return partTexto;
    }

    public int getProgress() {
        return progress;
    }
}
