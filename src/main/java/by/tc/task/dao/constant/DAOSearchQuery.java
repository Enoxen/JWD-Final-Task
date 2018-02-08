package by.tc.task.dao.constant;

/**
 * Created by Y50-70 on 01.02.2018.
 */
public class DAOSearchQuery {
    public static final String SQL_SEARCH_FILM_BY_TITLE = "select film.film_id, film.film_default_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "where locate(?, film.film_default_title);";
    public static final String SQL_SEARCH_FILM_BY_TITLE_EN = "select film.film_id, film.film_loc_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "where locate(?, film.film_loc_title);";

    public static final String SQL_GET_RANDOM_FILM = "select  film.film_id, film.film_default_title, film.film_year, film.film_rating,\n" +
            "GROUP_CONCAT(distinct film_genre.film_genre) as genre,\n" +
            "GROUP_CONCAT(distinct actor.actor_name,' ',actor.actor_surname) as actor,\n" +
            "group_concat(distinct director.director_name,' ',director.director_surname) as director\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where film.film_id = ?;";
    public static final String SQL_GET_RANDOM_FILM_EN = "select  film.film_id, film.film_rating,film.film_loc_title, film.film_loc_description,\n" +
            "GROUP_CONCAT(distinct film_genre.film_genre_EN) as genre,\n" +
            "GROUP_CONCAT(distinct actor.actor_name_EN,' ',actor.actor_surname_EN) as actor,\n" +
            "group_concat(distinct director.director_name_EN,' ',director.director_surname_EN) as director\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where film.film_id = ?;";






    public static final String SQL_ADVANCED_SEARCH_TITLE_NOT_NULL = "select  film.film_id, film.film_default_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, film.film_default_title) and\n" +
            "((film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?)) or film_has_genre.genre_id = ?)\n" +
            " group by film.film_id;";
    public static final String SQL_ADVANCED_SEARCH_TITLE_NOT_NULL_EN = "select  film.film_id, film.film_loc_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, film.film_loc_title) and\n" +
            "((film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?)) or film_has_genre.genre_id = ?)\n" +
            " group by film.film_id limit;";
    public static final String SQL_ADVANCED_SEARCH_NULL_TITLE = "select  film.film_id, film.film_default_title, film.film_year, film.film_rating " +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, film.film_default_title) or " +
            "(film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?) or film_has_genre.genre_id = ?)\n" +
            " group by film.film_id;";
    public static final String SQL_ADVANCED_SEARCH_NULL_TITLE_EN = "select  film.film_id, film.film_loc_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, film.film_loc_title) or((film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?)) or film_has_genre.genre_id = ?)\n" +
            " group by film.film_id limit;";
    public static final String SQL_SEARCH_FILM_BY_ACTOR = "select  film.film_id, film.film_default_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, actor_name) and locate(?, actor_surname) group by film.film_id limit ?,?;";
    public static final String SQL_SEARCH_FILM_BY_ACTOR_EN = "select    film.film_id, film.film_loc_title, film.film_year, film.film_rating\n" +
            "from film" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\\n\" +\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\\n\" +\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\\n\" +\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \\n\" +\n" +
            "join director_has_film on film.film_id = director_has_film.film_id\\n\" +\n" +
            "join director on director_has_film.director_id = director.director_id \\n\" +\n" +
            "where locate(?, actor.actor_name_EN) and locate(?, actor.actor_surname_EN) group by film.film_id limit ?,?;";
    public static final String SQL_SEARCH_FILM_BY_DIRECTOR = "select   film.film_id, film.film_default_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, director.director_name) and locate(?, director.director_surname) group by film.film_id limit ?,?;";
    public static final String SQL_SEARCH_FILM_BY_DIRECTOR_EN = "select    film.film_id, film.film_loc_title, film.film_year, film.film_rating\n" +
            "from film\n" +
            "JOIN film_has_genre ON film.film_id = film_has_genre.film_id\n" +
            "JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\n" +
            "join actor_has_film on film.film_id = actor_has_film.film_id\n" +
            "join actor on actor_has_film.actor_id = actor.actor_id \n" +
            "join director_has_film on film.film_id = director_has_film.film_id\n" +
            "join director on director_has_film.director_id = director.director_id \n" +
            "where locate(?, director.director_name_EN) and locate(?, director.director_surname_EN) group by film.film_id limit ?,?;";

}
