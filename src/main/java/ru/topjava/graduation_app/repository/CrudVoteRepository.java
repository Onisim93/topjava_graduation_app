package ru.topjava.graduation_app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation_app.entity.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface CrudVoteRepository extends CrudRepository<Vote, Integer> {
    Optional<Vote> findByUserIdAndLocalDate(int id, LocalDate localDate);
    List<Vote> getAllByRestaurantIdAndLocalDate(int id, LocalDate localDate);

    @Transactional
    @Modifying
    @Query("delete from Vote v where v.id=:id")
    int delete(@Param("id") int id);

    @Query("from Vote v where v.localDate=:ld")
    List<Vote> getAllForToday(@Param("ld") LocalDate localDate);

    @Query("select count (v) from Vote v where v.restaurant.id=:id and v.localDate=:ld")
    Long getAmountVotesByRestaurantIdAndLocalDate(@Param("id") int restaurantId, @Param("ld") LocalDate ld);
}
