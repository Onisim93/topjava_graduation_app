package ru.topjava.graduation_app;

import lombok.Getter;
import lombok.ToString;
import ru.topjava.graduation_app.entity.User;

import javax.validation.constraints.NotNull;
import java.io.Serial;

@ToString
@Getter
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    @Serial
    private static final long serialVersionUID = 1L;

    private final User user;

    public AuthorizedUser(@NotNull User user) {
        super(user.getName(), user.getPassword(), true, true, true, true, user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }
}
