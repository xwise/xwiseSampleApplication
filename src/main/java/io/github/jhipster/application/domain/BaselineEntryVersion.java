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

    @Column(name = "name")
    private String name;

    @Column(name = "creation_time")
    private Instant creationTime;

    @Column(name = "version")
    private String version;

    @ManyToMany(mappedBy = "baselineEntries")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BaselineEntry> baselineEntryVersions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BaselineEntryVersion name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<BaselineEntry> getBaselineEntryVersions() {
        return baselineEntryVersions;
    }

    public BaselineEntryVersion baselineEntryVersions(Set<BaselineEntry> baselineEntries) {
        this.baselineEntryVersions = baselineEntries;
        return this;
    }

    public BaselineEntryVersion addBaselineEntryVersion(BaselineEntry baselineEntry) {
        this.baselineEntryVersions.add(baselineEntry);
        baselineEntry.getBaselineEntries().add(this);
        return this;
    }

    public BaselineEntryVersion removeBaselineEntryVersion(BaselineEntry baselineEntry) {
        this.baselineEntryVersions.remove(baselineEntry);
        baselineEntry.getBaselineEntries().remove(this);
        return this;
    }

    public void setBaselineEntryVersions(Set<BaselineEntry> baselineEntries) {
        this.baselineEntryVersions = baselineEntries;
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
            ", name='" + getName() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
