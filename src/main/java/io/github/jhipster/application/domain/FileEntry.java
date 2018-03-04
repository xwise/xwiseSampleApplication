package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

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

    @Column(name = "file_name")
    private String fileName;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "file_entry_file_group",
               joinColumns = @JoinColumn(name="file_entries_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="file_groups_id", referencedColumnName="id"))
    private Set<FileGroup> fileGroups = new HashSet<>();

    @ManyToOne
    private UploadVersion uploadVersion;

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

    public UploadVersion getUploadVersion() {
        return uploadVersion;
    }

    public FileEntry uploadVersion(UploadVersion uploadVersion) {
        this.uploadVersion = uploadVersion;
        return this;
    }

    public void setUploadVersion(UploadVersion uploadVersion) {
        this.uploadVersion = uploadVersion;
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
            ", fileName='" + getFileName() + "'" +
            "}";
    }
}
