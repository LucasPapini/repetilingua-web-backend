package br.com.lucaspapini.repetilingua.entities.dto;

import java.time.LocalDateTime;

public class TextStatsResponseDTO {
    private Long textId;
    private String title;
    private String Modulo;
    private Long totalParts;
    private Long completedParts;
    private Double overallProgress;
    private Long totalRepetitions;
    private LocalDateTime lastActivity;

    public TextStatsResponseDTO() {
    }

    public TextStatsResponseDTO(Long textId, String title, String modulo, Long totalParts, Long completedParts, Double overallProgress, Long totalRepetitions, LocalDateTime lastActivity) {
        this.textId = textId;
        this.title = title;
        Modulo = modulo;
        this.totalParts = totalParts;
        this.completedParts = completedParts;
        this.overallProgress = overallProgress;
        this.totalRepetitions = totalRepetitions;
        this.lastActivity = lastActivity;
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

    public String getModulo() {
        return Modulo;
    }

    public void setModulo(String modulo) {
        Modulo = modulo;
    }

    public Long getTotalParts() {
        return totalParts;
    }

    public void setTotalParts(Long totalParts) {
        this.totalParts = totalParts;
    }

    public Long getCompletedParts() {
        return completedParts;
    }

    public void setCompletedParts(Long completedParts) {
        this.completedParts = completedParts;
    }

    public Double getOverallProgress() {
        return overallProgress;
    }

    public void setOverallProgress(Double overallProgress) {
        this.overallProgress = overallProgress;
    }

    public Long getTotalRepetitions() {
        return totalRepetitions;
    }

    public void setTotalRepetitions(Long totalRepetitions) {
        this.totalRepetitions = totalRepetitions;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }
}
