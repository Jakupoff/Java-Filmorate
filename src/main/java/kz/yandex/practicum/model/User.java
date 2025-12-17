package kz.yandex.practicum.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
@Data
@EqualsAndHashCode(of = "email")
public class User {
    long id;
    String email;
    String name;
    Instant birthday;
}
