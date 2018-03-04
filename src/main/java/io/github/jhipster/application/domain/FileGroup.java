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
 * A FileGroup.
 */
@Entity
@Table(name = "file_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FileGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @ManyToMany(mappedBy = "fileGroups")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Baseline> baselines = new HashSet<>();

    @ManyToMany(mappedBy = "fileGroups")
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

    public String getGroupName() {
        return groupName;
    }

    public FileGroup groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<Baseline> getBaselines() {
        return baselines;
    }

    public FileGroup baselines(Set<Baseline> baselines) {
        this.baselines = baselines;
        return this;
    }

    public FileGroup addBaseline(Baseline baseline) {
        this.baselines.add(baseline);
        baseline.getFileGroups().add(this);
        return this;
    }

    public FileGroup removeBaseline(Baseline baseline) {
        this.baselines.remove(baseline);
        baseline.getFileGroups().remove(this);
        return this;
    }

    public void setBaselines(Set<Baseline> baselines) {
        this.baselines = baselines;
    }

    public Set<FileEntry> getFileEntries() {
        return fileEntries;
    }

    public FileGroup fileEntries(Set<FileEntry> fileEntries) {
        this.fileEntries = fileEntries;
        return this;
    }

    public FileGroup addFileEntry(FileEntry fileEntry) {
        this.fileEntries.add(fileEntry);
        fileEntry.getFileGroups().add(this);
        return this;
    }

    public FileGroup removeFileEntry(FileEntry fileEntry) {
        this.fileEntries.remove(fileEntry);
        fileEntry.getFileGroups().remove(this);
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
        FileGroup fileGroup = (FileGroup) o;
        if (fileGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fileGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FileGroup{" +
            "id=" + getId() +
            ", groupName='" + getGroupName() + "'" +
            "}";
    }
}
