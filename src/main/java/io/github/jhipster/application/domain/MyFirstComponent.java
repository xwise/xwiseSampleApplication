package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MyFirstComponent.
 */
@Entity
@Table(name = "my_first_component")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MyFirstComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_time")
    private Instant creationTime;

    @Column(name = "attribute_1")
    private String attribute1;

    @Column(name = "attribute_2")
    private String attribute2;

    @Column(name = "attribute_3")
    private String attribute3;

    @Column(name = "attribute_4")
    private String attribute4;

    @Column(name = "attribute_5")
    private String attribute5;

    @Column(name = "attribute_6")
    private String attribute6;

    @Column(name = "attribute_7")
    private String attribute7;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MyFirstComponent name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public MyFirstComponent creationTime(Instant creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public void setCreationTime(Instant creationTime) {
        this.creationTime = creationTime;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public MyFirstComponent attribute1(String attribute1) {
        this.attribute1 = attribute1;
        return this;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public MyFirstComponent attribute2(String attribute2) {
        this.attribute2 = attribute2;
        return this;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public MyFirstComponent attribute3(String attribute3) {
        this.attribute3 = attribute3;
        return this;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public MyFirstComponent attribute4(String attribute4) {
        this.attribute4 = attribute4;
        return this;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    public String getAttribute5() {
        return attribute5;
    }

    public MyFirstComponent attribute5(String attribute5) {
        this.attribute5 = attribute5;
        return this;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    public String getAttribute6() {
        return attribute6;
    }

    public MyFirstComponent attribute6(String attribute6) {
        this.attribute6 = attribute6;
        return this;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    public String getAttribute7() {
        return attribute7;
    }

    public MyFirstComponent attribute7(String attribute7) {
        this.attribute7 = attribute7;
        return this;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
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
        MyFirstComponent myFirstComponent = (MyFirstComponent) o;
        if (myFirstComponent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), myFirstComponent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MyFirstComponent{" +
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
