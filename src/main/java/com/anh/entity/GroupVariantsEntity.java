package com.anh.entity;

import javax.persistence.*;

@Entity
@Table(name = "group_variants", schema = "test")
public class GroupVariantsEntity {
    private long id;
    private String variantName;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "variant_name")
    public String getVariantName() {
        return variantName;
    }

    public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupVariantsEntity that = (GroupVariantsEntity) o;

        if (id != that.id) return false;
        if (variantName != null ? !variantName.equals(that.variantName) : that.variantName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (variantName != null ? variantName.hashCode() : 0);
        return result;
    }
}
