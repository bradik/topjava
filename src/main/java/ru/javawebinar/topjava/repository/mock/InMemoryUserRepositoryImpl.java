package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new User(null, "Admin", "admin@mail.com", "123", Role.ROLE_ADMIN));
        save(new User(null, "User 1", "user1@mail.com", "111", Role.ROLE_USER));
        save(new User(null, "User 2", "user2@mail.com", "222", Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);

        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);

        if (user.isNew())
            user.setId(counter.incrementAndGet());

        repository.put(user.getId(), user);

        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);

        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");

        return repository.values().stream().
                sorted((user1, user2) -> user1.getName().compareTo(user2.getName())).collect(Collectors.toList());

    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        Optional<User> result = repository.values().stream().filter(u -> email.equals(u.getEmail())).findFirst();
        return result.isPresent() ? result.get() : null;
    }
}
