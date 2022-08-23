package ru.topjava.graduation_app.util;

import ru.topjava.graduation_app.entity.Restaurant;
import ru.topjava.graduation_app.to.RestaurantTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestaurantUtil {
    public static List<RestaurantTo> getTos(List<Restaurant> restaurants, Map<Integer, Long> votes) {
        List<RestaurantTo> result = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (checkMenu(r)) {
                result.add(getTo(r, votes.get(r.id())));
            }
        }
        return result;
    }

    public static RestaurantTo getTo(Restaurant restaurant, Long vote) {
        return new RestaurantTo(restaurant, vote == null ? 0 : vote);
    }

    public static boolean checkMenu(Restaurant restaurant) {
        return restaurant.getMenu() != null && !restaurant.getMenu().isEmpty();
    }
}
