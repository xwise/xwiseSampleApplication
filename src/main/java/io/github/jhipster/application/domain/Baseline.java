package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.BaselineStatus;

/**
 * A Baseline.
 */
@Entity
@Table(name = "baseline")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Baseline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_time")
    private Instant creationTime;

    @Column(name = "baseline_name")
    private String baselineName;

    @Column(name = "milestone")
    private Instant milestone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BaselineStatus status;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "baseline_file_group",
               joinColumns = @JoinColumn(name="baselines_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="file_groups_id", referencedColumnName="id"))
    private Set<FileEntry> fileGroups = new HashSet<>();

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

    public Baseline creationTime(Instant creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getBaselineName() {
        return baselineName;
    }

    public Baseline baselineName(String baselineName) {
        this.baselineName = baselineName;
        return this;
    }

    public void setBaselineName(String baselineName) {
        this.baselineName = baselineName;
    }

    public Instant getMilestone() {
        return milestone;
    }

    public Baseline milestone(Instant milestone) {
        this.milestone = milestone;
        return this;
    }

    public void setMilestone(Instant milestone) {
        this.milestone = milestone;
    }

    public BaselineStatus getStatus() {
        return status;
    }

    public Baseline status(BaselineStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(BaselineStatus status) {
        this.status = status;
    }

    public Set<FileEntry> getFileGroups() {
        return fileGroups;
    }

    public Baseline fileGroups(Set<FileEntry> fileEntries) {
        this.fileGroups = fileEntries;
        return this;
    }

    public Baseline addFileGroup(FileEntry fileEntry) {
        this.fileGroups.add(fileEntry);
        fileEntry.getFileGroups().add(this);
        return this;
    }

    public Baseline removeFileGroup(FileEntry fileEntry) {
        this.fileGroups.remove(fileEntry);
        fileEntry.getFileGroups().remove(this);
        return this;
    }

    public void setFileGroups(Set<FileEntry> fileEntries) {
        this.fileGroups = fileEntries;
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
        Baseline baseline = (Baseline) o;
        if (baseline.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baseline.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Baseline{" +
            "id=" + getId() +
            ", creationTime='" + getCreationTime() + "'" +
            ", baselineName='" + getBaselineName() + "'" +
            ", milestone='" + getMilestone() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
