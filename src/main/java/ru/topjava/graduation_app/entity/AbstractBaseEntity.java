package ru.topjava.graduation_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractBaseEntity implements Persistable<Integer> {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @JsonSerialize
    protected Integer id;

    public int id() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
