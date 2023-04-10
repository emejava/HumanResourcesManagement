package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor


@Entity(name = "fileEntity")
@Table(name = "tb_file")
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

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(15)")
    private Status status;

    @PrePersist
    public void statusSet() {
        status = Status.Active;
    }

    public News(
            Long id,
            @NonNull String subject,
            @NonNull String title,
            @NonNull String newsReport) {
        this.id = id;
        this.subject = subject;
        this.title = title;
        this.newsReport = newsReport;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
