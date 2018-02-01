package by.tc.task.entity;

import by.tc.task.dao.search.SearchDAO;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by Y50-70 on 01.02.2018.
 */
public class FilmOutput implements Serializable{
    private static final long serialVersionUID = 6502838742129818943L;
    private String title;
    private float rating;
    private int year;
    private int filmId;
    private String filmDescription;
    private String genres;
    private String actors;
    private String directors;
    private int filmVotes;

    public FilmOutput(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmDescription() {
        return filmDescription;
    }

    public void setFilmDescription(String filmDescription) {
        this.filmDescription = filmDescription;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public int getFilmVotes() {
        return filmVotes;
    }

    public void setFilmVotes(int filmVotes) {
        this.filmVotes = filmVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmOutput that = (FilmOutput) o;
        return Float.compare(that.rating, rating) == 0 &&
                year == that.year &&
                filmId == that.filmId &&
                filmVotes == that.filmVotes &&
                Objects.equals(title, that.title) &&
                Objects.equals(filmDescription, that.filmDescription) &&
                Objects.equals(genres, that.genres) &&
                Objects.equals(actors, that.actors) &&
                Objects.equals(directors, that.directors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating, year, filmId, filmDescription, genres, actors, directors, filmVotes);
    }

    @Override
    public String toString() {
        return "FilmOutput{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                ", filmId=" + filmId +
                ", filmDescription='" + filmDescription + '\'' +
                ", genres='" + genres + '\'' +
                ", actors='" + actors + '\'' +
                ", directors='" + directors + '\'' +
                ", filmVotes=" + filmVotes +
                '}';
    }
}
