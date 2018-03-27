package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.UploadStatus;

/**
 * A UploadVersion.
 */
@Entity
@Table(name = "upload_version")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UploadVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "version")
    private String version;

    @Lob
    @Column(name = "jhi_file")
    private byte[] file;

    @Column(name = "jhi_file_content_type")
    private String fileContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "upload_status")
    private UploadStatus uploadStatus;

    @ManyToMany(mappedBy = "uploadVersions")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FileEntry> fileEntries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public UploadVersion version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public byte[] getFile() {
        return file;
    }

    public UploadVersion file(byte[] file) {
        this.file = file;
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public UploadVersion fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public UploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public UploadVersion uploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
        return this;
    }

    public void setUploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Set<FileEntry> getFileEntries() {
        return fileEntries;
    }

    public UploadVersion fileEntries(Set<FileEntry> fileEntries) {
        this.fileEntries = fileEntries;
        return this;
    }

    public UploadVersion addFileEntry(FileEntry fileEntry) {
        this.fileEntries.add(fileEntry);
        fileEntry.getUploadVersions().add(this);
        return this;
    }

    public UploadVersion removeFileEntry(FileEntry fileEntry) {
        this.fileEntries.remove(fileEntry);
        fileEntry.getUploadVersions().remove(this);
        return this;
    }

    public void setFileEntries(Set<FileEntry> fileEntries) {
        this.fileEntries = fileEntries;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UploadVersion uploadVersion = (UploadVersion) o;
        if (uploadVersion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uploadVersion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UploadVersion{" +
            "id=" + getId() +
            ", version='" + getVersion() + "'" +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            ", uploadStatus='" + getUploadStatus() + "'" +
            "}";
    }
}
