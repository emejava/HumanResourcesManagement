package com.humanresourcesmanagement.model.entity;

import com.google.gson.Gson;
import com.humanresourcesmanagement.model.entity.enums.FileType;
import com.humanresourcesmanagement.model.entity.enums.Access;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity(name="attachmentEntity")
@Table(name="t_attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    private long fileSize;

    @Column(columnDefinition = "VARCHAR2(50)")
    private String title;

    @Column(columnDefinition = "VARCHAR2(50)")
    private String filePath;

    @Enumerated(EnumType.STRING)
    private Access access;

    public Attachment(FileType fileType, long fileSize, String title, String filePath, Access access) {
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.title = title;
        this.filePath = filePath;
        this.access = access;
    }
}
