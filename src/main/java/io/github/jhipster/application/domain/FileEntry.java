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

    @OneToMany(mappedBy = "fileEntry")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<UploadVersion> uploadVersions = new HashSet<>();

    @ManyToOne
    private Baseline baseline;

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

    public Set<UploadVersion> getUploadVersions() {
        return uploadVersions;
    }

    public FileEntry uploadVersions(Set<UploadVersion> uploadVersions) {
        this.uploadVersions = uploadVersions;
        return this;
    }

    public FileEntry addUploadVersion(UploadVersion uploadVersion) {
        this.uploadVersions.add(uploadVersion);
        uploadVersion.setFileEntry(this);
        return this;
    }

    public FileEntry removeUploadVersion(UploadVersion uploadVersion) {
        this.uploadVersions.remove(uploadVersion);
        uploadVersion.setFileEntry(null);
        return this;
    }

    public void setUploadVersions(Set<UploadVersion> uploadVersions) {
        this.uploadVersions = uploadVersions;
    }

    public Baseline getBaseline() {
        return baseline;
    }

    public FileEntry baseline(Baseline baseline) {
        this.baseline = baseline;
        return this;
    }

    public void setBaseline(Baseline baseline) {
        this.baseline = baseline;
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
