package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the FileEntry entity.
 */
public class FileEntryDTO implements Serializable {

    private Long id;

    private String entryName;

    private Set<BaselineEntryVersionDTO> baselineEntryVersions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public Set<BaselineEntryVersionDTO> getBaselineEntryVersions() {
        return baselineEntryVersions;
    }

    public void setBaselineEntryVersions(Set<BaselineEntryVersionDTO> baselineEntryVersions) {
        this.baselineEntryVersions = baselineEntryVersions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileEntryDTO fileEntryDTO = (FileEntryDTO) o;
        if(fileEntryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileEntryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileEntryDTO{" +
            "id=" + getId() +
            ", entryName='" + getEntryName() + "'" +
            "}";
    }
}
