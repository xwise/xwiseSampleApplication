package io.github.jhipster.application.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the BaselineEntryVersion entity.
 */
public class BaselineEntryVersionDTO implements Serializable {

    private Long id;

    private String name;

    private Instant creationTime;

    private String version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaselineEntryVersionDTO baselineEntryVersionDTO = (BaselineEntryVersionDTO) o;
        if(baselineEntryVersionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baselineEntryVersionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BaselineEntryVersionDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", version='" + getVersion() + "'" +
            "}";
    }
}
