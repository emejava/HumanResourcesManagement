package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor


@Entity(name = "newsEntity")
@Table(name = "tb_file")
@NamedQueries(
        @NamedQuery(
                name = "findAllActive",
                query = "SELECT n FROM newsEntity n WHERE n.status=:status"
        )
)
public class News {
    @Id
    @JsonProperty("کد")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("موضوع")
    @NonNull
    @NotBlank(message = "موضوع وارد نشده")
    @Column(name = "subject", columnDefinition = "NVARCHAR2(30)")
    private String subject;

    @JsonProperty("عنوان(خلاصه)")
    @NonNull
    @NotBlank(message = "عنوان وارد نشده")
    @Column(name = "title", columnDefinition = "NVARCHAR2(100)")
    private String title;

    @JsonProperty("شرح خبر")
    @NonNull
    @NotBlank(message = "شرح خبر وارد نشده")
    @Column(name = "news_report", columnDefinition = "NVARCHAR2(255)")
    private String newsReport;

    @JsonProperty("ضمیمه 1")
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment mainAttachment;

    @JsonProperty("ضمیمه 2")
    @OneToOne(cascade = CascadeType.ALL)
    private Attachment secondAttachment;

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(15)")
    private Status status;

    @PrePersist
    public void statusSet() {
        status = Status.Active;
    }

    public News(
            @NonNull String subject,
            @NonNull String title,
            @NonNull String newsReport,
            Attachment mainAttachment,
            Attachment secondAttachment) {
        this.subject = subject;
        this.title = title;
        this.newsReport = newsReport;
        this.mainAttachment = mainAttachment;
        this.secondAttachment = secondAttachment;
    }

    public News(
            Long id,
            @NonNull String subject,
            @NonNull String title,
            @NonNull String newsReport,
            Attachment mainAttachment,
            Attachment secondAttachment) {
        this.id = id;
        this.subject = subject;
        this.title = title;
        this.newsReport = newsReport;
        this.mainAttachment = mainAttachment;
        this.secondAttachment = secondAttachment;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
