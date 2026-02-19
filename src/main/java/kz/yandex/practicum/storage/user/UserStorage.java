package kz.yandex.practicum.storage.user;

import kz.yandex.practicum.model.User;

import java.util.List;
import java.util.Optional;

public interface UserStorage {
    User addUser(User user);
    Optional<User> findUserById(long userId);
    List<User> findAllUsers();
    User updateUser(User user);
    boolean deleteUserById(long userId);

}
