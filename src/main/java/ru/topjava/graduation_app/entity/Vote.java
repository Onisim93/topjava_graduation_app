package ru.topjava.graduation_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "votes", uniqueConstraints = {
        @UniqueConstraint(name = "votes_unique_user_id_local_date_idx", columnNames = {"user_id", "local_date"})
})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"user", "restaurant"})
public class Vote extends AbstractBaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "local_date", nullable = false)
    private LocalDate localDate = LocalDate.now();
}
