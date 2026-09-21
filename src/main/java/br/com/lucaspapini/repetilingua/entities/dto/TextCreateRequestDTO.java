package br.com.lucaspapini.repetilingua.entities.dto;

import br.com.lucaspapini.repetilingua.entities.Text;

public class TextCreateRequestDTO {
    private Long id;
    private String title;
    private String module;

    public TextCreateRequestDTO() {
    }

    public TextCreateRequestDTO(Text entity) {
        title = entity.getTitle();
        module = entity.getModule();
    }

    public String getTitle() {
        return title;
    }

    public String getModule() {
        return module;
    }

    public Long getId() {
        return id;
    }
}
