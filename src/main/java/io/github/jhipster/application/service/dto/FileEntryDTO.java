package io.github.jhipster.application.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the FileEntry entity.
 */
public class FileEntryDTO implements Serializable {

    private Long id;

    private Instant creationTime;

    private String fileName;

    private Long baselineId;

    private String baselineBaselineName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getBaselineId() {
        return baselineId;
    }

    public void setBaselineId(Long baselineId) {
        this.baselineId = baselineId;
    }

    public String getBaselineBaselineName() {
        return baselineBaselineName;
    }

    public void setBaselineBaselineName(String baselineBaselineName) {
        this.baselineBaselineName = baselineBaselineName;
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
            ", creationTime='" + getCreationTime() + "'" +
            ", fileName='" + getFileName() + "'" +
            "}";
    }
}
