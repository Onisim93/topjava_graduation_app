package ru.topjava.graduation_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation_app.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Query("FROM User u ORDER BY u.email" )
    List<User> getAll();

    @Query("DELETE FROM User u WHERE u.id=:id")
    @Modifying
    @Transactional
    int delete(@Param("id") int id);

    Optional<User> findByEmail(String email);
}
