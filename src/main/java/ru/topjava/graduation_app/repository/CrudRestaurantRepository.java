package ru.topjava.graduation_app.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation_app.entity.Restaurant;

import java.time.LocalDate;
import java.util.List;



@Repository
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    @Modifying
    @Transactional
    int delete(@Param("id") int id);

    @EntityGraph(attributePaths = "menu", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN r.menu m where m.localDate=:ld ORDER BY r.name")
    List<Restaurant> getAllWithMeals(@Param("ld")LocalDate ld);

    @Query("Select r FROM Restaurant  r LEFT join r.menu m where r.id=:id and m.localDate=:ld")
    Restaurant getWithMeals(@Param("id") int id, @Param("ld") LocalDate ld);
}
