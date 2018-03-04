package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the FileGroup entity. This class is used in FileGroupResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /file-groups?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FileGroupCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter groupName;

    private LongFilter fileEntryId;

    private LongFilter baselineId;

    public FileGroupCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getGroupName() {
        return groupName;
    }

    public void setGroupName(StringFilter groupName) {
        this.groupName = groupName;
    }

    public LongFilter getFileEntryId() {
        return fileEntryId;
    }

    public void setFileEntryId(LongFilter fileEntryId) {
        this.fileEntryId = fileEntryId;
    }

    public LongFilter getBaselineId() {
        return baselineId;
    }

    public void setBaselineId(LongFilter baselineId) {
        this.baselineId = baselineId;
    }

    @Override
    public String toString() {
        return "FileGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (groupName != null ? "groupName=" + groupName + ", " : "") +
                (fileEntryId != null ? "fileEntryId=" + fileEntryId + ", " : "") +
                (baselineId != null ? "baselineId=" + baselineId + ", " : "") +
            "}";
    }

}
