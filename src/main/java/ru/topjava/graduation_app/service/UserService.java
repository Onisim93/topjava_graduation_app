package ru.topjava.graduation_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.topjava.graduation_app.entity.Role;
import ru.topjava.graduation_app.entity.User;
import ru.topjava.graduation_app.repository.CrudUserRepository;

import java.util.List;

import static ru.topjava.graduation_app.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class UserService {
    private CrudUserRepository repository;
    private PasswordEncoder encoder;

    @Autowired
    public UserService(CrudUserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        user.setPassword(encoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Role.USER);
        }
        return repository.save(user);
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.id());
    }

    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }


}
