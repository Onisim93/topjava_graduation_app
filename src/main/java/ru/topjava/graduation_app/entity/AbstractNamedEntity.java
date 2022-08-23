package ru.topjava.graduation_app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractNamedEntity extends AbstractBaseEntity{
    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "name")
    protected String name;

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}
