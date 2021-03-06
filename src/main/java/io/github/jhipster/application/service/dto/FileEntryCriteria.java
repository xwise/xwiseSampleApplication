package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import io.github.jhipster.application.domain.enumeration.UploadStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the FileEntry entity. This class is used in FileEntryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /file-entries?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FileEntryCriteria implements Serializable {
    /**
     * Class for filtering UploadStatus
     */
    public static class UploadStatusFilter extends Filter<UploadStatus> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private InstantFilter creationTime;

    private StringFilter fileNameVersion;

    private StringFilter fileName;

    private StringFilter version;

    private UploadStatusFilter uploadStatus;

    private LongFilter baselineId;

    public FileEntryCriteria() {
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

    public StringFilter getFileNameVersion() {
        return fileNameVersion;
    }

    public void setFileNameVersion(StringFilter fileNameVersion) {
        this.fileNameVersion = fileNameVersion;
    }

    public StringFilter getFileName() {
        return fileName;
    }

    public void setFileName(StringFilter fileName) {
        this.fileName = fileName;
    }

    public StringFilter getVersion() {
        return version;
    }

    public void setVersion(StringFilter version) {
        this.version = version;
    }

    public UploadStatusFilter getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(UploadStatusFilter uploadStatus) {
        this.uploadStatus = uploadStatus;
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
                (creationTime != null ? "creationTime=" + creationTime + ", " : "") +
                (fileNameVersion != null ? "fileNameVersion=" + fileNameVersion + ", " : "") +
                (fileName != null ? "fileName=" + fileName + ", " : "") +
                (version != null ? "version=" + version + ", " : "") +
                (uploadStatus != null ? "uploadStatus=" + uploadStatus + ", " : "") +
                (baselineId != null ? "baselineId=" + baselineId + ", " : "") +
            "}";
    }

}
