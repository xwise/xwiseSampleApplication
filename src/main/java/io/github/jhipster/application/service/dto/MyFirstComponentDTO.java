package io.github.jhipster.application.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MyFirstComponent entity.
 */
public class MyFirstComponentDTO implements Serializable {

    private Long id;

    private String name;

    private Instant creationTime;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    private String attribute4;

    private String attribute5;

    private String attribute6;

    private String attribute7;

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

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyFirstComponentDTO myFirstComponentDTO = (MyFirstComponentDTO) o;
        if(myFirstComponentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), myFirstComponentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MyFirstComponentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", creationTime='" + getCreationTime() + "'" +
            ", attribute1='" + getAttribute1() + "'" +
            ", attribute2='" + getAttribute2() + "'" +
            ", attribute3='" + getAttribute3() + "'" +
            ", attribute4='" + getAttribute4() + "'" +
            ", attribute5='" + getAttribute5() + "'" +
            ", attribute6='" + getAttribute6() + "'" +
            ", attribute7='" + getAttribute7() + "'" +
            "}";
    }
}
