package ru.topjava.graduation_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "meals", uniqueConstraints = {
        @UniqueConstraint(name = "meals_unique_restaurant_name_date_idx", columnNames = {"restaurant_id", "name", "local_date"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meal extends AbstractNamedEntity {
    @Range(min = 1, max = 5000)
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "local_date", nullable = false)
    private LocalDate localDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "meal")
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
