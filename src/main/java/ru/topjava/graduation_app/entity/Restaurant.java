package ru.topjava.graduation_app.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {
        @UniqueConstraint(name = "restaurants_unique_name_idx", columnNames = {"name"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant extends AbstractNamedEntity{
    @NotBlank
    @Size(min = 5, max = 256)
    @Column(name = "address")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @BatchSize(size = 20)
    @JsonManagedReference(value = "meal")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Meal> menu;

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
