package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.Text;

import java.time.Instant;

public class TextListDTO {
    private Long id;
    private String title;
    private String module;
    private Boolean completed;
    private Instant moment;

    public TextListDTO() {
    }

    public TextListDTO(Text entity) {
        id = entity.getId();
        title = entity.getTitle();
        module = entity.getModule();
        completed = entity.getCompleted();
        moment = entity.getMoment();
    }

    public Long getId() {
        return id;
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

    public Instant getMoment() {
        return moment;
    }
}
