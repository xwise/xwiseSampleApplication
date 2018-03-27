package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import io.github.jhipster.application.domain.enumeration.UploadStatus;

/**
 * A DTO for the UploadVersion entity.
 */
public class UploadVersionDTO implements Serializable {

    private Long id;

    private String version;

    @Lob
    private byte[] file;
    private String fileContentType;

    private UploadStatus uploadStatus;

    private Set<FileEntryDTO> fileEntries = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public UploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Set<FileEntryDTO> getFileEntries() {
        return fileEntries;
    }

    public void setFileEntries(Set<FileEntryDTO> fileEntries) {
        this.fileEntries = fileEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UploadVersionDTO uploadVersionDTO = (UploadVersionDTO) o;
        if(uploadVersionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uploadVersionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UploadVersionDTO{" +
            "id=" + getId() +
            ", version='" + getVersion() + "'" +
            ", file='" + getFile() + "'" +
            ", uploadStatus='" + getUploadStatus() + "'" +
            "}";
    }
}
