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
 * A BaselineEntry.
 */
@Entity
@Table(name = "baseline_entry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BaselineEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_name")
    private String entryName;

    @Column(name = "creation_time")
    private Instant creationTime;

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

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "baseline_entry_baseline_entry_version",
               joinColumns = @JoinColumn(name="baseline_entries_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="baseline_entry_versions_id", referencedColumnName="id"))
    private Set<BaselineEntryVersion> baselineEntryVersions = new HashSet<>();

    @ManyToMany(mappedBy = "baselineEntries")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Baseline> baselines = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryName() {
        return entryName;
    }

    public BaselineEntry entryName(String entryName) {
        this.entryName = entryName;
        return this;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public BaselineEntry creationTime(Instant creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getFileName() {
        return fileName;
    }

    public BaselineEntry fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBlob() {
        return blob;
    }

    public BaselineEntry blob(byte[] blob) {
        this.blob = blob;
        return this;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }

    public String getBlobContentType() {
        return blobContentType;
    }

    public BaselineEntry blobContentType(String blobContentType) {
        this.blobContentType = blobContentType;
        return this;
    }

    public void setBlobContentType(String blobContentType) {
        this.blobContentType = blobContentType;
    }

    public UploadStatus getUploadStatus() {
        return uploadStatus;
    }

    public BaselineEntry uploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
        return this;
    }

    public void setUploadStatus(UploadStatus uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Set<BaselineEntryVersion> getBaselineEntryVersions() {
        return baselineEntryVersions;
    }

    public BaselineEntry baselineEntryVersions(Set<BaselineEntryVersion> baselineEntryVersions) {
        this.baselineEntryVersions = baselineEntryVersions;
        return this;
    }

    public BaselineEntry addBaselineEntryVersion(BaselineEntryVersion baselineEntryVersion) {
        this.baselineEntryVersions.add(baselineEntryVersion);
        baselineEntryVersion.getBaselineEntries().add(this);
        return this;
    }

    public BaselineEntry removeBaselineEntryVersion(BaselineEntryVersion baselineEntryVersion) {
        this.baselineEntryVersions.remove(baselineEntryVersion);
        baselineEntryVersion.getBaselineEntries().remove(this);
        return this;
    }

    public void setBaselineEntryVersions(Set<BaselineEntryVersion> baselineEntryVersions) {
        this.baselineEntryVersions = baselineEntryVersions;
    }

    public Set<Baseline> getBaselines() {
        return baselines;
    }

    public BaselineEntry baselines(Set<Baseline> baselines) {
        this.baselines = baselines;
        return this;
    }

    public BaselineEntry addBaseline(Baseline baseline) {
        this.baselines.add(baseline);
        baseline.getBaselineEntries().add(this);
        return this;
    }

    public BaselineEntry removeBaseline(Baseline baseline) {
        this.baselines.remove(baseline);
        baseline.getBaselineEntries().remove(this);
        return this;
    }

    public void setBaselines(Set<Baseline> baselines) {
        this.baselines = baselines;
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
        BaselineEntry baselineEntry = (BaselineEntry) o;
        if (baselineEntry.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baselineEntry.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BaselineEntry{" +
            "id=" + getId() +
            ", entryName='" + getEntryName() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", blob='" + getBlob() + "'" +
            ", blobContentType='" + getBlobContentType() + "'" +
            ", uploadStatus='" + getUploadStatus() + "'" +
            "}";
    }
}
