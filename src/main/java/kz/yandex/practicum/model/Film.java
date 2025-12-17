package kz.yandex.practicum.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = "id")
public class Film {
    long id;
    String name;
    String description;
    Instant releaseDate;
    long duration;
}
