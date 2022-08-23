package ru.topjava.graduation_app.to;

import lombok.Getter;
import lombok.Setter;
import ru.topjava.graduation_app.entity.Restaurant;
import ru.topjava.graduation_app.util.MealUtil;

import java.util.List;

@Getter
@Setter
public class RestaurantTo {
    private Integer id;
    private String name;
    private String address;

    private List<MealTo> menu;

    private long votes;

    public RestaurantTo(Restaurant restaurant, long votes) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.menu = MealUtil.getTos(restaurant.getMenu());
        this.votes = votes;
    }

    public RestaurantTo(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.menu = MealUtil.getTos(restaurant.getMenu());
    }


    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", amountVotes=" + votes +
                '}';
    }
}
