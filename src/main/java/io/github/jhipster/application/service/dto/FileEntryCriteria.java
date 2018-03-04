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
 * Criteria class for the FileEntry entity. This class is used in FileEntryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /file-entries?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FileEntryCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter entryName;

    private LongFilter baselineEntryVersionId;

    private LongFilter baselineId;

    public FileEntryCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEntryName() {
        return entryName;
    }

    public void setEntryName(StringFilter entryName) {
        this.entryName = entryName;
    }

    public LongFilter getBaselineEntryVersionId() {
        return baselineEntryVersionId;
    }

    public void setBaselineEntryVersionId(LongFilter baselineEntryVersionId) {
        this.baselineEntryVersionId = baselineEntryVersionId;
    }

    public LongFilter getBaselineId() {
        return baselineId;
    }

    public void setBaselineId(LongFilter baselineId) {
        this.baselineId = baselineId;
    }

    @Override
    public String toString() {
        return "FileEntryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (entryName != null ? "entryName=" + entryName + ", " : "") +
                (baselineEntryVersionId != null ? "baselineEntryVersionId=" + baselineEntryVersionId + ", " : "") +
                (baselineId != null ? "baselineId=" + baselineId + ", " : "") +
            "}";
    }

}
