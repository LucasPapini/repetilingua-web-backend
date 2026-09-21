package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.Text;

public class TextUpdateRequestDTO {
    private String title;
    private String module;
    private Boolean completed;

    public TextUpdateRequestDTO() {
    }

    public TextUpdateRequestDTO(Text entity) {
        this.title = entity.getTitle();
        this.module = entity.getModule();
        this.completed = entity.getCompleted();
    }

    public String getTitle() {
        return title;
    }

    public String getModule() {
        return module;
    }

    public Boolean getCompleted() {
        return completed;
    }
}
