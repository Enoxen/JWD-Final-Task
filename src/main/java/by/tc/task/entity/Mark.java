package by.tc.task.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Y50-70 on 08.02.2018.
 */
public class Mark implements Serializable {

    private static final long serialVersionUID = 7029340677674092705L;
    private String filmTitle;
    private int filmId;
    private int userId;
    private float filmMark;

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public float getFilmMark() {
        return filmMark;
    }

    public void setFilmMark(float filmMark) {
        this.filmMark = filmMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return filmId == mark.filmId &&
                userId == mark.userId &&
                Float.compare(mark.filmMark, filmMark) == 0 &&
                Objects.equals(filmTitle, mark.filmTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmTitle, filmId, userId, filmMark);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "filmTitle='" + filmTitle + '\'' +
                ", filmId=" + filmId +
                ", userId=" + userId +
                ", filmMark=" + filmMark +
                '}';
    }
}
