package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the MyFirstComponent entity. This class is used in MyFirstComponentResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /my-first-components?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MyFirstComponentCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private InstantFilter creationTime;

    private StringFilter name;

    private StringFilter attribute1;

    private StringFilter attribute2;

    private StringFilter attribute3;

    private StringFilter attribute4;

    private StringFilter attribute5;

    private StringFilter attribute6;

    private StringFilter attribute7;

    public MyFirstComponentCriteria() {
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

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(StringFilter attribute1) {
        this.attribute1 = attribute1;
    }

    public StringFilter getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(StringFilter attribute2) {
        this.attribute2 = attribute2;
    }

    public StringFilter getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(StringFilter attribute3) {
        this.attribute3 = attribute3;
    }

    public StringFilter getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(StringFilter attribute4) {
        this.attribute4 = attribute4;
    }

    public StringFilter getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(StringFilter attribute5) {
        this.attribute5 = attribute5;
    }

    public StringFilter getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(StringFilter attribute6) {
        this.attribute6 = attribute6;
    }

    public StringFilter getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(StringFilter attribute7) {
        this.attribute7 = attribute7;
    }

    @Override
    public String toString() {
        return "MyFirstComponentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creationTime != null ? "creationTime=" + creationTime + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (attribute1 != null ? "attribute1=" + attribute1 + ", " : "") +
                (attribute2 != null ? "attribute2=" + attribute2 + ", " : "") +
                (attribute3 != null ? "attribute3=" + attribute3 + ", " : "") +
                (attribute4 != null ? "attribute4=" + attribute4 + ", " : "") +
                (attribute5 != null ? "attribute5=" + attribute5 + ", " : "") +
                (attribute6 != null ? "attribute6=" + attribute6 + ", " : "") +
                (attribute7 != null ? "attribute7=" + attribute7 + ", " : "") +
            "}";
    }

}
