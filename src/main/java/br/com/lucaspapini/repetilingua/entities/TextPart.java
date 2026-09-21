package br.com.lucaspapini.repetilingua.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "tb_text_part",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"part_number", "text_id"})
        }
)
public class TextPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer  partNumber;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String audioPath;

    @ManyToOne
    @JoinColumn(name = "text_id")
    private Text text;

    @OneToMany(mappedBy = "textPart", cascade = CascadeType.ALL, orphanRemoval = true) // Refere-se ao campo 'textPart' em TextPartProgress
    private List<TextPartProgress> partProgresses = new ArrayList<>();

    public TextPart() {
    }

    public TextPart(Long id, Integer partNumber, String content, String audioPath, Text text, List<TextPartProgress> partProgresses) {
        this.id = id;
        this.partNumber = partNumber;
        this.content = content;
        this.audioPath = audioPath;
        this.text = text;
        this.partProgresses = partProgresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public List<TextPartProgress> getPartProgresses() {
        return partProgresses;
    }

    public void setPartProgresses(List<TextPartProgress> partProgresses) {
        this.partProgresses = partProgresses;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TextPart textPart = (TextPart) object;
        return Objects.equals(id, textPart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
