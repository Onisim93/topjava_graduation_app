package ru.topjava.graduation_app.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.topjava.graduation_app.entity.Meal;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MealTo {
    String name;
    BigDecimal price;

    public MealTo(Meal meal) {
        this.name = meal.getName();
        this.price = meal.getPrice();
    }


    @Override
    public String toString() {
        return "MealTo{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
