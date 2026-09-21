package br.com.lucaspapini.repetilingua.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_text_part_progress")
public class TextPartProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- DIA 1 ---
    @Column(name = "d1_read_listen")
    private Integer day1ReadAndListen;

    @Column(name = "d1_listen_only")
    private Integer day1OnlyListen;

    @Column(name = "d1_final_check")
    private Integer day1ReadAndListenFinalCheck;

    // --- DIA 2 ---
    @Column(name = "d2_read_listen")
    private Integer day2ReadAndListen;

    @Column(name = "d2_listen_only")
    private Integer day2OnlyListen;

    @Column(name = "d2_final_check")
    private Integer day2ReadAndListenFinalCheck;

    @Column(nullable = false)
    private Boolean complete;

    @Column(name = "last_activity_at")
    private LocalDateTime lastActivity;

    @ManyToOne
    @JoinColumn(name = "text_part_id")
    private TextPart textPart;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TextPartProgress() {
    }

    public TextPartProgress(Long id, Integer day1ReadAndListen, Integer day1OnlyListen, Integer day1ReadAndListenFinalCheck, Integer day2ReadAndListen, Integer day2OnlyListen, Integer day2ReadAndListenFinalCheck, Boolean complete, LocalDateTime lastActivity, TextPart textPart, User user) {
        this.id = id;
        this.day1ReadAndListen = day1ReadAndListen;
        this.day1OnlyListen = day1OnlyListen;
        this.day1ReadAndListenFinalCheck = day1ReadAndListenFinalCheck;
        this.day2ReadAndListen = day2ReadAndListen;
        this.day2OnlyListen = day2OnlyListen;
        this.day2ReadAndListenFinalCheck = day2ReadAndListenFinalCheck;
        this.complete = complete;
        this.lastActivity = lastActivity;
        this.textPart = textPart;
        this.user = user;
    }

    public Integer getDay1ReadAndListen() {
        return day1ReadAndListen;
    }

    public void setDay1ReadAndListen(Integer day1ReadAndListen) {
        this.day1ReadAndListen = day1ReadAndListen;
    }

    public Integer getDay1OnlyListen() {
        return day1OnlyListen;
    }

    public void setDay1OnlyListen(Integer day1OnlyListen) {
        this.day1OnlyListen = day1OnlyListen;
    }

    public Integer getDay1ReadAndListenFinalCheck() {
        return day1ReadAndListenFinalCheck;
    }

    public void setDay1ReadAndListenFinalCheck(Integer day1ReadAndListenFinalCheck) {
        this.day1ReadAndListenFinalCheck = day1ReadAndListenFinalCheck;
    }

    public Integer getDay2ReadAndListen() {
        return day2ReadAndListen;
    }

    public void setDay2ReadAndListen(Integer day2ReadAndListen) {
        this.day2ReadAndListen = day2ReadAndListen;
    }

    public Integer getDay2OnlyListen() {
        return day2OnlyListen;
    }

    public void setDay2OnlyListen(Integer day2OnlyListen) {
        this.day2OnlyListen = day2OnlyListen;
    }

    public Integer getDay2ReadAndListenFinalCheck() {
        return day2ReadAndListenFinalCheck;
    }

    public void setDay2ReadAndListenFinalCheck(Integer day2ReadAndListenFinalCheck) {
        this.day2ReadAndListenFinalCheck = day2ReadAndListenFinalCheck;
    }

    public LocalDateTime getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDateTime lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public TextPart getTextPart() {
        return textPart;
    }

    public void setTextPart(TextPart textPart) {
        this.textPart = textPart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TextPartProgress that = (TextPartProgress) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TextPartProgress{" +
                "id=" + id +
                ", day1ReadAndListen=" + day1ReadAndListen +
                ", day1OnlyListen=" + day1OnlyListen +
                ", day1ReadAndListenFinalCheck=" + day1ReadAndListenFinalCheck +
                ", day2ReadAndListen=" + day2ReadAndListen +
                ", day2OnlyListen=" + day2OnlyListen +
                ", day2ReadAndListenFinalCheck=" + day2ReadAndListenFinalCheck +
                ", complete=" + complete +
                ", lastActivity=" + lastActivity +
                ", textPart=" + textPart +
                ", user=" + user +
                '}';
    }
}
