package kz.yandex.practicum.util;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public final class IdGenerator {
    Long maxIdUsers = 0L;
    Long maxIdFilms = 0L;

    public static Long getMaxIdOfUsers() {
        return ++maxIdUsers;
    }

    public static Long getMaxIdOfFilms() {
        return ++maxIdFilms;
    }
}