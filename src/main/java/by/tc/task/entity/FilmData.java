package by.tc.task.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Y50-70 on 18.11.2017.
 */
public class FilmData implements Serializable {

    private static final long serialVersionUID = 2300384072083630265L;
    private String defaultTitle;
    private String localTitle;
    private int year;
    private int[] interval;
    private String defaultFilmDescription;
    private String localFilmDescription;
    private List<Integer> genresIn;
    public FilmData(){
        interval = new int[2];
    }

    public int[] getInterval() {
        return interval;
    }

    public void setInterval(int[] interval) {
        this.interval = interval;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public List<Integer> getGenresIn() {
        return genresIn;
    }

    public void setGenresIn(List<Integer> genresIn) {
        this.genresIn = genresIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmData data = (FilmData) o;
        return year == data.year &&
                Objects.equals(defaultTitle, data.defaultTitle) &&
                Objects.equals(localTitle, data.localTitle) &&
                Arrays.equals(interval, data.interval) &&
                Objects.equals(defaultFilmDescription, data.defaultFilmDescription) &&
                Objects.equals(localFilmDescription, data.localFilmDescription) &&
                Objects.equals(genresIn, data.genresIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(defaultTitle, localTitle, year, interval, defaultFilmDescription, localFilmDescription, genresIn);
    }

    @Override
    public String toString() {
        return "FilmData{" +
                "defaultTitle='" + defaultTitle + '\'' +
                ", localTitle='" + localTitle + '\'' +
                ", year=" + year +
                ", interval=" + Arrays.toString(interval) +
                ", defaultFilmDescription='" + defaultFilmDescription + '\'' +
                ", localFilmDescription='" + localFilmDescription + '\'' +
                ", genresIn=" + genresIn +
                '}';
    }
}
