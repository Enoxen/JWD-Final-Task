package by.tc.task.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Y50-70 on 03.02.2018.
 */
public class Review implements Serializable {
    private static final long serialVersionUID = 6679061724261414955L;
    private String reviewText;
    private int reviewId;
    private int userId;
    private int filmId;
    public Review(){}

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return reviewId == review.reviewId &&
                userId == review.userId &&
                filmId == review.filmId &&
                Objects.equals(reviewText, review.reviewText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewText, reviewId, userId, filmId);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewText='" + reviewText + '\'' +
                ", reviewId=" + reviewId +
                ", userId=" + userId +
                ", filmId=" + filmId +
                '}';
    }
}
