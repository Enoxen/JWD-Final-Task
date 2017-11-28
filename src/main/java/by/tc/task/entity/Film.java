package by.tc.task.entity;

import java.io.Serializable;

/**
 * Created by Y50-70 on 18.11.2017.
 */
public class Film implements Serializable {

    private static final long serialVersionUID = 2300384072083630265L;
    private String name;
    private String genre;

    public Film(){
    }
    public Film(String name, String genre){
        this.genre = genre;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;

        Film film = (Film) o;

        if (name != null ? !name.equals(film.name) : film.name != null) return false;
        return genre != null ? genre.equals(film.genre) : film.genre == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
