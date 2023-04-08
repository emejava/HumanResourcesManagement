package com.humanresourcesmanagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(columnDefinition = "NVARCHAR2(20)")
    private String title;

    @NonNull
    private Boolean canInsert;

    @NonNull
    private Boolean canUpdate;

    @NonNull
    private Boolean canDelete;

    @NonNull
    private Boolean canFind;

    @NonNull
    private Boolean canFindAll;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Boolean return the canInsert
     */
    public Boolean isCanInsert() {
        return canInsert;
    }

    /**
     * @param canInsert the canInsert to set
     */
    public void setCanInsert(Boolean canInsert) {
        this.canInsert = canInsert;
    }

    /**
     * @return Boolean return the canUpdate
     */
    public Boolean isCanUpdate() {
        return canUpdate;
    }

    /**
     * @param canUpdate the canUpdate to set
     */
    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    /**
     * @return Boolean return the canDelete
     */
    public Boolean isCanDelete() {
        return canDelete;
    }

    /**
     * @param canDelete the canDelete to set
     */
    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    /**
     * @return Boolean return the canFind
     */
    public Boolean isCanFind() {
        return canFind;
    }

    /**
     * @param canFind the canFind to set
     */
    public void setCanFind(Boolean canFind) {
        this.canFind = canFind;
    }

    /**
     * @return Boolean return the canFindAll
     */
    public Boolean isCanFindAll() {
        return canFindAll;
    }

    /**
     * @param canFindAll the canFindAll to set
     */
    public void setCanFindAll(Boolean canFindAll) {
        this.canFindAll = canFindAll;
    }

}