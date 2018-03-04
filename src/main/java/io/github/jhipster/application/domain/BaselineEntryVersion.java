package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.UploadStatus;

/**
 * A BaselineEntryVersion.
 */
@Entity
@Table(name = "baseline_entry_version")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BaselineEntryVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_time")
    private Instant creationTime;

    @Column(name = "version")
    private String version;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "jhi_blob")
    private byte[] blob;

    @Column(name = "jhi_blob_content_type")
    private String blobContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "upload_status")
    private UploadStatus uploadStatus;

    @ManyToMany(mappedBy = "baselineEntryVersions")
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

    public Instant getCreationTime() {
        return creationTime;
    }

    public BaselineEntryVersion creationTime(Instant creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getVersion() {
        return version;
    }

    public BaselineEntryVersion version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFileName() {
        return fileName;
    }

    public BaselineEntryVersion fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBlob() {
        return blob;
    }

    public BaselineEntryVersion blob(byte[] blob) {
        this.blob = blob;
        return this;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    public String getBlobContentType() {
        return blobContentType;
    }

    public BaselineEntryVersion blobContentType(String blobContentType) {
        this.blobContentType = blobContentType;
        return this;
    }

    public void setBlobContentType(String blobContentType) {
        this.blobContentType = blobContentType;
    }

    public UploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public BaselineEntryVersion uploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
        return this;
    }

    public void setUploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Set<FileEntry> getFileEntries() {
        return fileEntries;
    }

    public BaselineEntryVersion fileEntries(Set<FileEntry> fileEntries) {
        this.fileEntries = fileEntries;
        return this;
    }

    public BaselineEntryVersion addFileEntry(FileEntry fileEntry) {
        this.fileEntries.add(fileEntry);
        fileEntry.getBaselineEntryVersions().add(this);
        return this;
    }

    public BaselineEntryVersion removeFileEntry(FileEntry fileEntry) {
        this.fileEntries.remove(fileEntry);
        fileEntry.getBaselineEntryVersions().remove(this);
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
        BaselineEntryVersion baselineEntryVersion = (BaselineEntryVersion) o;
        if (baselineEntryVersion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baselineEntryVersion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BaselineEntryVersion{" +
            "id=" + getId() +
            ", creationTime='" + getCreationTime() + "'" +
            ", version='" + getVersion() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", blob='" + getBlob() + "'" +
            ", blobContentType='" + getBlobContentType() + "'" +
            ", uploadStatus='" + getUploadStatus() + "'" +
            "}";
    }
}
