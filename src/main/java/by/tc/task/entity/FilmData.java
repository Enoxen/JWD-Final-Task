package by.tc.task.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by Y50-70 on 18.11.2017.
 */
public class FilmData implements Serializable {

    private static final long serialVersionUID = 2300384072083630265L;
    private String defaultTitle;
    private String localTitle;
    private float rating;
    private int year;
    private int filmId;
    private String defaultFilmDescription;
    private String localFilmDescription;
    private List<Integer> genres;

    public FilmData(){
    }

    public String getDefaultTitle() {
        return defaultTitle;
    }

    public void setDefaultTitle(String defaultTitle) {
        this.defaultTitle = defaultTitle;
    }

    public String getLocalTitle() {
        return localTitle;
    }

    public void setLocalTitle(String localTitle) {
        this.localTitle = localTitle;
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

    public String getDefaultFilmDescription() {
        return defaultFilmDescription;
    }

    public void setDefaultFilmDescription(String defaultFilmDescription) {
        this.defaultFilmDescription = defaultFilmDescription;
    }

    public String getLocalFilmDescription() {
        return localFilmDescription;
    }

    public void setLocalFilmDescription(String localFilmDescription) {
        this.localFilmDescription = localFilmDescription;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmData filmData = (FilmData) o;
        return Float.compare(filmData.rating, rating) == 0 &&
                year == filmData.year &&
                filmId == filmData.filmId &&
                Objects.equals(defaultTitle, filmData.defaultTitle) &&
                Objects.equals(localTitle, filmData.localTitle) &&
                Objects.equals(defaultFilmDescription, filmData.defaultFilmDescription) &&
                Objects.equals(localFilmDescription, filmData.localFilmDescription) &&
                Objects.equals(genres, filmData.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultTitle, localTitle, rating, year, filmId, defaultFilmDescription, localFilmDescription, genres);
    }

    @Override
    public String toString() {
        return "FilmData{" +
                "defaultTitle='" + defaultTitle + '\'' +
                ", localTitle='" + localTitle + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                ", filmId=" + filmId +
                ", defaultFilmDescription='" + defaultFilmDescription + '\'' +
                ", localFilmDescription='" + localFilmDescription + '\'' +
                ", genres=" + genres +
                '}';
    }
}
