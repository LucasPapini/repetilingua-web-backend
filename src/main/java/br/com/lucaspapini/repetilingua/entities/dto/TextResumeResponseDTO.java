package br.com.lucaspapini.repetilingua.entities.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public class TextResumeResponseDTO {
    private Long id;
    private String title;
    private Long partes;
    private Long audiosCadastrados;
    private Long repetions;
    private Instant dataCadastro;

    public TextResumeResponseDTO() {
    }

    public TextResumeResponseDTO(Long id, String title, Long partes, Long audiosCadastrados, Long repetions, Instant dataCadastro) {
        this.id = id;
        this.title = title;
        this.partes = partes;
        this.audiosCadastrados = audiosCadastrados;
        this.repetions = repetions;
        this.dataCadastro = dataCadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPartes() {
        return partes;
    }

    public void setPartes(Long partes) {
        this.partes = partes;
    }

    public Long getAudiosCadastrados() {
        return audiosCadastrados;
    }

    public void setAudiosCadastrados(Long audiosCadastrados) {
        this.audiosCadastrados = audiosCadastrados;
    }

    public Long getRepetions() {
        return repetions;
    }

    public void setRepetions(Long repetions) {
        this.repetions = repetions;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
