package io.github.jhipster.application.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.BaselineStatus;

/**
 * A DTO for the Baseline entity.
 */
public class BaselineDTO implements Serializable {

    private Long id;

    private Instant creationTime;

    private String baselineName;

    private Instant milestone;

    private BaselineStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getBaselineName() {
        return baselineName;
    }

    public void setBaselineName(String baselineName) {
        this.baselineName = baselineName;
    }

    public Instant getMilestone() {
        return milestone;
    }

    public void setMilestone(Instant milestone) {
        this.milestone = milestone;
    }

    public BaselineStatus getStatus() {
        return status;
    }

    public void setStatus(BaselineStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaselineDTO baselineDTO = (BaselineDTO) o;
        if(baselineDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), baselineDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BaselineDTO{" +
            "id=" + getId() +
            ", creationTime='" + getCreationTime() + "'" +
            ", baselineName='" + getBaselineName() + "'" +
            ", milestone='" + getMilestone() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
