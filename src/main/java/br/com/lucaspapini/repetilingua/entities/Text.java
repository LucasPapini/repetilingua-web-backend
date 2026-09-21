package br.com.lucaspapini.repetilingua.entities;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_text")
public class Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String module;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    private Boolean completed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "text", cascade = CascadeType.ALL, orphanRemoval = true) // Refere-se ao campo 'text' em TextPart
    private List<TextPart> textParts = new ArrayList<>();

    public Text() {
    }

    public Text(Long id, String title, String module, Instant moment, Boolean completed, User user, List<TextPart> textParts) {
        this.id = id;
        this.title = title;
        this.module = module;
        this.moment = moment;
        this.completed = completed;
        this.user = user;
        this.textParts = textParts;
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

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TextPart> getTextParts() {
        return textParts;
    }

    public void setTextParts(List<TextPart> textParts) {
        this.textParts = textParts;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Text text = (Text) object;
        return Objects.equals(id, text.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
