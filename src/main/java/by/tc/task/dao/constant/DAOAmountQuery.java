package by.tc.task.dao.constant;

/**
 * Created by Y50-70 on 06.02.2018.
 */
public class DAOAmountQuery {
    public static final String SQL_ADVANCED_SEARCH_AMOUNT_TITLE = "select COUNT(*) FROM( select film.film_id, film.film_default_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, film.film_default_title) and\n" +
            "((film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?)) or film_has_genre.genre_id = ?)\n" +
            " group by film.film_id) as count_tbl";
    public static final String SQL_ADVANCED_SEARCH_AMOUNT_TITLE_EN = "select count(*) from  ( select film.film_id, film.film_loc_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, film.film_loc_title) and\n" +
            "((film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?)) or film_has_genre.genre_id = ?)\n" +
            " group by film.film_id) as count_tbl";

    public static final String SQL_ADVANCED_SEARCH_AMOUNT_TITTLE_NULL = "select count(*) from (select film.film_id, film.film_default_title, film.film_year, film.film_rating " +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, film.film_default_title) or " +
            "(film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?) or film_has_genre.genre_id = ?)\n" +
            " group by film.film_id) as count_tbl";
    public static final String SQL_ADVANCED_SEARCH_AMOUNT_NULL_TITLE_EN = "select count(*) from(select film.film_id, film.film_loc_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, film.film_loc_title) or((film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?)) or film_has_genre.genre_id = ?)\n" +
            " group by film.film_id) as count_tbl";
}
