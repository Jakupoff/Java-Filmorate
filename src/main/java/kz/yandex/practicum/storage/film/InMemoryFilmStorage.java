package kz.yandex.practicum.storage.film;

import kz.yandex.practicum.model.Film;
import kz.yandex.practicum.storage.film.FilmStorage;
import kz.yandex.practicum.util.IdGenerator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private final Map<Long, Film> films = new HashMap<>();

    @Override
    public Film addFilm(Film film) {
        Long maxId = getMaxId();
        film.setId(maxId);
        films.put(film.getId(), film);
        return films.get(film.getId());
    }

    @Override
    public Optional<Film> findFilmById(long filmId) {
        return Optional.ofNullable(films.get(filmId));
    }

    @Override
    public List<Film> findAllFilms() {
        return films.values().stream()
                .toList();
    }

    @Override
    public Film updateFilm(Film film) {
        films.put(film.getId(), film);
        return films.get(film.getId());
    }

    @Override
    public boolean deleteFilmById(long filmId) {
        Film removedFilm = films.remove(filmId);
        return filmId == removedFilm.getId();
    }

    private Long getMaxId(){
        return IdGenerator.getMaxIdOfFilms();
    }
}
