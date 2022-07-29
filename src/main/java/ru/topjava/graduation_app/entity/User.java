package ru.topjava.graduation_app.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AbstractNamedEntity{

    @Email
    @NotBlank
    @Size(max = 128)
    private String email;

    @NotBlank
    @Size(min = 5, max = 128)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Column(name = "registered", updatable = false)
    private LocalDateTime registered = LocalDateTime.now();

    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles")})
    @Column(name = "role")
    @BatchSize(size = 200)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;


    public void setRoles(Role... roles) {
        this.roles = new HashSet<>(List.of(roles));
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registered=" + registered +
                ", enabled=" + enabled +
                ", roles=" + roles +
                ", id=" + id +
                '}';
    }
}
