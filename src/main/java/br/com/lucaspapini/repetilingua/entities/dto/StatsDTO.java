package br.com.lucaspapini.repetilingua.entities.dto;

public class StatsDTO {
    private Long totalTexts;
    private Long totalTextParts;
    private Long completedStudies;
    private Long listenAndReadCount;
    private Long listenOnlyCount;
    private Long totalRepetitions;

    public StatsDTO() {
    }

    public StatsDTO(Long totalTexts, Long totalTextParts, Long completedStudies, Long listenAndReadCount, Long listenOnlyCount, Long totalRepetitions) {
        this.totalTexts = totalTexts;
        this.totalTextParts = totalTextParts;
        this.completedStudies = completedStudies;
        this.listenAndReadCount = listenAndReadCount;
        this.listenOnlyCount = listenOnlyCount;
        this.totalRepetitions = totalRepetitions;
    }

    public void setTotalTexts(Long totalTexts) {
        this.totalTexts = totalTexts;
    }

    public void setTotalTextParts(Long totalTextParts) {
        this.totalTextParts = totalTextParts;
    }

    public void setCompletedStudies(Long completedStudies) {
        this.completedStudies = completedStudies;
    }

    public void setListenAndReadCount(Long listenAndReadCount) {
        this.listenAndReadCount = listenAndReadCount;
    }

    public void setListenOnlyCount(Long listenOnlyCount) {
        this.listenOnlyCount = listenOnlyCount;
    }

    public void setTotalRepetitions(Long totalRepetitions) {
        this.totalRepetitions = totalRepetitions;
    }

    public Long getTotalTexts() {
        return totalTexts;
    }

    public Long getTotalTextParts() {
        return totalTextParts;
    }

    public Long getCompletedStudies() {
        return completedStudies;
    }

    public Long getListenAndReadCount() {
        return listenAndReadCount;
    }

    public Long getListenOnlyCount() {
        return listenOnlyCount;
    }

    public Long getTotalRepetitions() {
        return totalRepetitions;
    }
}
