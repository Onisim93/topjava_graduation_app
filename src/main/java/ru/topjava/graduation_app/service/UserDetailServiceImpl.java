package ru.topjava.graduation_app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation_app.AuthorizedUser;
import ru.topjava.graduation_app.entity.User;
import ru.topjava.graduation_app.repository.CrudUserRepository;

import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private CrudUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating {}", username);
        Optional<User> user = repository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(String.format("User %s was not found", username));
        }
        return new AuthorizedUser(user.get());
    }
}
