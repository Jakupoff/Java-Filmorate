package kz.yandex.practicum.storage.film;

import kz.yandex.practicum.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmStorage {
     Film addFilm(Film film);
     Optional<Film> findFilmById(long filmId);
     List<Film> findAllFilms();
     Film updateFilm(Film film);
     boolean deleteFilmById(long filmId);

}
