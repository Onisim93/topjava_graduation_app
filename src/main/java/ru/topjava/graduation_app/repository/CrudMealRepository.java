package ru.topjava.graduation_app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation_app.entity.Meal;

import java.util.List;


@Repository
public interface CrudMealRepository extends CrudRepository<Meal, Integer> {
    @Query("DELETE FROM Meal m WHERE m.id=:id and m.restaurant.id=:restaurantId")
    @Modifying
    @Transactional
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    Meal getByIdAndRestaurantId(int id, int restaurantId);

    List<Meal> getAllByRestaurantIdOrderByName(int restaurantId);
}
