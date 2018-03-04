package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
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

    @Column(name = "entry_name")
    private String entryName;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "file_entry_baseline_entry_version",
               joinColumns = @JoinColumn(name="file_entries_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="baseline_entry_versions_id", referencedColumnName="id"))
    private Set<BaselineEntryVersion> baselineEntryVersions = new HashSet<>();

    @ManyToMany(mappedBy = "fileEntries")
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

    public FileEntry entryName(String entryName) {
        this.entryName = entryName;
        return this;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public Set<BaselineEntryVersion> getBaselineEntryVersions() {
        return baselineEntryVersions;
    }

    public FileEntry baselineEntryVersions(Set<BaselineEntryVersion> baselineEntryVersions) {
        this.baselineEntryVersions = baselineEntryVersions;
        return this;
    }

    public FileEntry addBaselineEntryVersion(BaselineEntryVersion baselineEntryVersion) {
        this.baselineEntryVersions.add(baselineEntryVersion);
        baselineEntryVersion.getFileEntries().add(this);
        return this;
    }

    public FileEntry removeBaselineEntryVersion(BaselineEntryVersion baselineEntryVersion) {
        this.baselineEntryVersions.remove(baselineEntryVersion);
        baselineEntryVersion.getFileEntries().remove(this);
        return this;
    }

    public void setBaselineEntryVersions(Set<BaselineEntryVersion> baselineEntryVersions) {
        this.baselineEntryVersions = baselineEntryVersions;
    }

    public Set<Baseline> getBaselines() {
        return baselines;
    }

    public FileEntry baselines(Set<Baseline> baselines) {
        this.baselines = baselines;
        return this;
    }

    public FileEntry addBaseline(Baseline baseline) {
        this.baselines.add(baseline);
        baseline.getFileEntries().add(this);
        return this;
    }

    public FileEntry removeBaseline(Baseline baseline) {
        this.baselines.remove(baseline);
        baseline.getFileEntries().remove(this);
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
            ", entryName='" + getEntryName() + "'" +
            "}";
    }
}
