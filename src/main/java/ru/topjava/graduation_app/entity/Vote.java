package ru.topjava.graduation_app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote extends AbstractBaseEntity{

    private Integer userId;
    private Integer restaurantId;
    private LocalDate localDate = LocalDate.now();

    public Vote (int userId, int restaurantId) {
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", localDate=" + localDate +
                '}';
    }
}
