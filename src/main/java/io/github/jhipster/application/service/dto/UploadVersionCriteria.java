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
 * Criteria class for the UploadVersion entity. This class is used in UploadVersionResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /upload-versions?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UploadVersionCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter version;

    private LongFilter fileEntryId;

    public UploadVersionCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getVersion() {
        return version;
    }

    public void setVersion(StringFilter version) {
        this.version = version;
    }

    public LongFilter getFileEntryId() {
        return fileEntryId;
    }

    public void setFileEntryId(LongFilter fileEntryId) {
        this.fileEntryId = fileEntryId;
    }

    @Override
    public String toString() {
        return "UploadVersionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (version != null ? "version=" + version + ", " : "") +
                (fileEntryId != null ? "fileEntryId=" + fileEntryId + ", " : "") +
            "}";
    }

}
