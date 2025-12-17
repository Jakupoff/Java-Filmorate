package kz.yandex.practicum.storage.user;

import kz.yandex.practicum.model.User;
import kz.yandex.practicum.util.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryUserStorage implements UserStorage{
    private final Map<Long, User> users = new HashMap<>();
    private Long getNextId() {
        return IdGenerator.getMaxIdOfUsers();
    }
    @Override
    public User addUser(User user) {
        Long id = getNextId();
        user.setId(id);
        users.put(id, user);
        return user;
    }


    @Override
    public Optional<User> findUserById(long userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public List<User> findAllUsers() {
        return users.values()
                .stream()
                .toList();
    }

    @Override
    public User updateUser(User user) {
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    @Override
    public boolean deleteUser(long userId) {
        User removedUser = users.get(userId);
        return userId == removedUser.getId();
    }
}
