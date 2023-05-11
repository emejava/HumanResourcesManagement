package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor


@Entity(name = "fileEntity")
@Table(name = "tb_file")
@NamedQueries({
        @NamedQuery(
                name = "file.findAllActive", query = "SELECT f FROM fileEntity f WHERE f.status=:status"),
        @NamedQuery(
                name = "file.findAllByUploader", query = "SELECT f FROM fileEntity f WHERE f.uploader=:uploader"),
        @NamedQuery(
                name = "file.findByNameAndUploader", query = "SELECT f FROM fileEntity f WHERE f.name=:name AND f.uploader=:uploader AND f.status=:status")})
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("کد")
    private Long id;

    @JsonProperty("نام")
    @NonNull
    @NotBlank(message = "نام وارد نشده")
    @Pattern(regexp = "[آ-ی\\s]*", message = "لطفا از حروف فارسی استفاده کنید")
    @Column(name = "name", columnDefinition = "NVARCHAR2(20)")
    private String name;

    @JsonProperty("شخص")
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private User uploader;

    @JsonProperty("محل ذخیره")
    @NonNull
    @Column(name = "path", columnDefinition = "NVARCHAR2(100)")
    private String path;

    @JsonProperty("وضعیت")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "NVARCHAR2(10)")
    private Status status;

    @PrePersist
    public void statusSet() {
        status = Status.Active;
    }

    public Attachment(Long id, @NonNull String name, @NonNull User uploader, @NonNull String path) {
        this.id = id;
        this.name = name;
        this.uploader = uploader;
        this.path = path;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
