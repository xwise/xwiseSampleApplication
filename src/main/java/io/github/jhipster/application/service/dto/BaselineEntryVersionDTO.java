package io.github.jhipster.application.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.UploadStatus;

/**
 * A DTO for the BaselineEntryVersion entity.
 */
public class BaselineEntryVersionDTO implements Serializable {

    private Long id;

    private Instant creationTime;

    private String version;

    private String fileName;

    @Lob
    private byte[] blob;
    private String blobContentType;

    private UploadStatus uploadStatus;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaselineEntryVersionDTO baselineEntryVersionDTO = (BaselineEntryVersionDTO) o;
        if(baselineEntryVersionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baselineEntryVersionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BaselineEntryVersionDTO{" +
            "id=" + getId() +
            ", creationTime='" + getCreationTime() + "'" +
            ", version='" + getVersion() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", blob='" + getBlob() + "'" +
            ", uploadStatus='" + getUploadStatus() + "'" +
            "}";
    }
}
