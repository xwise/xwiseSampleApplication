package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import io.github.jhipster.application.domain.enumeration.BaselineStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the Baseline entity. This class is used in BaselineResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /baselines?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BaselineCriteria implements Serializable {
    /**
     * Class for filtering BaselineStatus
     */
    public static class BaselineStatusFilter extends Filter<BaselineStatus> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private InstantFilter creationTime;

    private StringFilter baselineName;

    private InstantFilter milestone;

    private BaselineStatusFilter status;

    private LongFilter fileGroupId;

    public BaselineCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(InstantFilter creationTime) {
        this.creationTime = creationTime;
    }

    public StringFilter getBaselineName() {
        return baselineName;
    }

    public void setBaselineName(StringFilter baselineName) {
        this.baselineName = baselineName;
    }

    public InstantFilter getMilestone() {
        return milestone;
    }

    public void setMilestone(InstantFilter milestone) {
        this.milestone = milestone;
    }

    public BaselineStatusFilter getStatus() {
        return status;
    }

    public void setStatus(BaselineStatusFilter status) {
        this.status = status;
    }

    public LongFilter getFileGroupId() {
        return fileGroupId;
    }

    public void setFileGroupId(LongFilter fileGroupId) {
        this.fileGroupId = fileGroupId;
    }

    @Override
    public String toString() {
        return "BaselineCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creationTime != null ? "creationTime=" + creationTime + ", " : "") +
                (baselineName != null ? "baselineName=" + baselineName + ", " : "") +
                (milestone != null ? "milestone=" + milestone + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (fileGroupId != null ? "fileGroupId=" + fileGroupId + ", " : "") +
            "}";
    }

}
