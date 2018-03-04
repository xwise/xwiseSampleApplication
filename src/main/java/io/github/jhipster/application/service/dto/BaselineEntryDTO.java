package io.github.jhipster.application.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.UploadStatus;

/**
 * A DTO for the BaselineEntry entity.
 */
public class BaselineEntryDTO implements Serializable {

    private Long id;

    private String entryName;

    private Instant creationTime;

    private String fileName;

    @Lob
    private byte[] blob;
    private String blobContentType;

    private UploadStatus uploadStatus;

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

    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    public String getBlobContentType() {
        return blobContentType;
    }

    public void setBlobContentType(String blobContentType) {
        this.blobContentType = blobContentType;
    }

    public UploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
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

        BaselineEntryDTO baselineEntryDTO = (BaselineEntryDTO) o;
        if(baselineEntryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baselineEntryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BaselineEntryDTO{" +
            "id=" + getId() +
            ", entryName='" + getEntryName() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", blob='" + getBlob() + "'" +
            ", uploadStatus='" + getUploadStatus() + "'" +
            "}";
    }
}
