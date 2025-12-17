package kz.yandex.practicum.controller;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import javassist.NotFoundException;
import kz.yandex.practicum.model.Film;
import kz.yandex.practicum.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static kz.yandex.practicum.util.IdGenerator.getMaxIdOfFilms;


import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final Map<Long, Film> films = new HashMap<>();
    private final static LocalDate EARLIEST_DATE = LocalDate.of(1895, Month.DECEMBER, 28);


    public ResponseEntity<Film> addFilm(@Valid Film film){
        validateReleaseDateAndDuration(film);
        film.setId(getMaxIdOfFilms());
        film.setDescription(film.getDescription());
        films.put(film.getId(), film);

        return new ResponseEntity<>(films.get(film.getId()), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Film>> getFilms() {
        return ResponseEntity.of(Optional.of(new ArrayList<>(films.values())));
    }

    @PutMapping()
    public ResponseEntity<Film> updateFilm(@Valid @RequestBody Film film) throws NotFoundException {
        validateReleaseDateAndDuration(film);
        Film filmToUpdate = Optional.ofNullable(films.get(film.getId()))
                .orElseThrow(() -> new NotFoundException("Не найден фильм с id: " + film.getId()));
        if (film.getDescription() != null) {
            filmToUpdate.setDescription(film.getDescription());
        }
        if (film.getName() != null) {
            filmToUpdate.setName(film.getName());
        }
        films.put(filmToUpdate.getId(), filmToUpdate);
        return ResponseEntity.of(Optional.of(films.get(film.getId())));
    }

    private void validateReleaseDateAndDuration(Film film){
        log.warn("Валидация фильма {}", film.getName());
        log.warn("Валидация даты релиза {}", film.getReleaseDate());
        if (film.getReleaseDate().isBefore(Instant.from(EARLIEST_DATE))){
            throw new ValidationException("Дата релиза фильма слишком старая");
        }
        log.warn("Валидация продолжительности {}", film.getDuration());
        if (film.getDuration() > 0) {
            throw new ValidationException("Продолжительность фильма не может быть ниже нуля");
        }
        log.warn("Валидация описания {}", film.getDescription());
        if (film.getDescription().length() > 200) {
            throw new ValidationException("Описание фильма не может быть длиннее 200 символов");
        }
        log.info("Успешная валидация фильма {}", film.getName());
    }


}
