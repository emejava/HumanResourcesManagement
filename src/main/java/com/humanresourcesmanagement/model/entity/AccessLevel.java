package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor

@Entity(name = "accessLevelEntity")
@Table(name = "tb_accessLevel")
public class AccessLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("کد")
    private long id;

    @JsonProperty("عنوان")
    @NonNull
    @Column(name = "title",columnDefinition = "NVARCHAR2(20)")
    private String title;

    @NonNull
    @Column(name = "can_insert")
    private Boolean canInsert;

    @NonNull
    @Column(name = "can_update")
    private Boolean canUpdate;

    @NonNull
    @Column(name = "can_delete")
    private Boolean canDelete;

    @NonNull
    @Column(name = "can_find")
    private Boolean canFind;

    @NonNull
    @Column(name = "can_find_all")
    private Boolean canFindAll;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}