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
 * A FileEntry.
 */
@Entity
@Table(name = "file_entry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FileEntry implements Serializable {

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
    @Column(name = "jhi_file")
    private byte[] file;

    @Column(name = "jhi_file_content_type")
    private String fileContentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "upload_status")
    private UploadStatus uploadStatus;

    @ManyToMany(mappedBy = "fileEntries")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FileGroup> fileGroups = new HashSet<>();

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

    public FileEntry creationTime(Instant creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getVersion() {
        return version;
    }

    public FileEntry version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFileName() {
        return fileName;
    }

    public FileEntry fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getFile() {
        return file;
    }

    public FileEntry file(byte[] file) {
        this.file = file;
        return this;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public FileEntry fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public UploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public FileEntry uploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
        return this;
    }

    public void setUploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Set<FileGroup> getFileGroups() {
        return fileGroups;
    }

    public FileEntry fileGroups(Set<FileGroup> fileGroups) {
        this.fileGroups = fileGroups;
        return this;
    }

    public FileEntry addFileGroup(FileGroup fileGroup) {
        this.fileGroups.add(fileGroup);
        fileGroup.getFileEntries().add(this);
        return this;
    }

    public FileEntry removeFileGroup(FileGroup fileGroup) {
        this.fileGroups.remove(fileGroup);
        fileGroup.getFileEntries().remove(this);
        return this;
    }

    public void setFileGroups(Set<FileGroup> fileGroups) {
        this.fileGroups = fileGroups;
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
        FileEntry fileEntry = (FileEntry) o;
        if (fileEntry.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileEntry.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileEntry{" +
            "id=" + getId() +
            ", creationTime='" + getCreationTime() + "'" +
            ", version='" + getVersion() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", file='" + getFile() + "'" +
            ", fileContentType='" + getFileContentType() + "'" +
            ", uploadStatus='" + getUploadStatus() + "'" +
            "}";
    }
}
