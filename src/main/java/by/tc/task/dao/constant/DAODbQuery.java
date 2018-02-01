package by.tc.task.dao.constant;

/**
 * Created by Y50-70 on 12.11.2017.
 */
public class DAODbQuery {

    // auth user queries
    public static final  String SQL_IS_LOGIN_FREE = "select username from user where username = ?";
    public static final String SQL_ADD_NEW_USER = "insert into user (username, email, password, salt, role) values(?,?,?,?,?)";
    public static final String SQL_AUTHORIZE_USER_BY_LOGIN = "select username, password, salt, role,user_id from user where (username = ?)";
    public static final String SQL_UPDATE_USER_PASSWORD_BY_LOGIN = "update user set password = ?, salt = ? where username = ?";
    public static final String SQL_GET_USER_PASSWORD = "select password, salt from user where username = ?";
    public static final String SQL_UPDATE_USER_PASSWORD_BY_EMAIL = "update user set password = ? where email = ?";
    public static final String SQL_AUTH_USER_EMAIL = "select email from user where email = ?";
    public static final String SQL_UPDATE_USER_LOGIN = "update user set username = ? where user_id = ?";
    public static final String SQL_GET_USER_ID_BY_LOGIN = "select user_id from user where username = ?";
    public static final String SQL_UPDATE_USER_EMAIL_BY_LOGIN = "update user set email = ? where username = ?";

    // admin queries
    public static final String SQL_BAN_USER_BY_LOGIN = "update user set is_banned = ? where username = ?";

    //actor queries
    public static final String SQL_INSERT_ACTOR_INTO_TABLE = "insert into actor_test(actor_name, actor_surname, actor_name_EN, actor_surname_EN) values(?,?,?,?)";
    public static final String SQL_GET_ACTOR_ID_BY_NAME_SURNAME = "select actor_id from actor where actor_name = ? and actor_surname = ?";
    public static final String SQL_GET_ACTOR_ID_BY_NAME_SURNAME_LOCAL = "select actor_id from actor where actor_name_EN = ? and actor_surname_EN = ?";
    public static final String SQL_GET_ACTOR_ID_REPEAT_CHECK = "select actor_id from actor_test where actor_name = ? and actor_surname = ? and actor_name_EN = ? and actor_surname_EN = ?";
    public static final String SQL_CONNECT_ACTOR_WITH_FILM = "insert into actor_has_film_test(actor_id, film_id) values(?,?)";
    public static final String SQL_GET_MAX_ACTOR_ID = "select max(actor_id) from actor_test";

    //director queries
    public static final String SQL_GET_DIRECTOR_ID_REPEAT_CHECK = "select director_id from director_test where director_name = ? and director_surname = ? and director_name_EN = ? and director_surname_EN = ?";
    public static final String SQL_GET_DIRECTOR_MAX_ID = "select max(director_id) from director_test";
    public static final String SQL_CONNECT_DIRECTOR_WITH_A_FILM = "insert into director_has_film_test(director_id, film_id) values(?,?)";
    public static final String SQL_INSERT_DIRECTOR_INTO_TABLE = "insert into director_test(director_name,director_surname,director_name_EN, director_surname_EN) values(?,?,?,?)";

    //user action queries
    public static final String SQL_ADD_FILM_TO_FAVORITE = "insert into favorite_films (film_id, user_id) values(?,?)";
    public static final String SQL_DELETE_FROM_FAVORITE = "delete from favorite_films where user_id = ? and film_id = ?";
    public static final String SQL_FOLLOW_USER = "insert into user_follows (user_id,user_to_follow_id) values(?,?)";
    public static final String SQL_UNFOLLOW_USER = "delete from user_follows where user_id = ? and user_to_follow_id = ?";
    public static final String SQL_ADD_REVIEW = "insert into reviews(review_text, film_id, user_id) values(?,?,?)";
    public static final String SQL_DELETE_USER_REVIEW = "delete from reviews where id_review = ?";

    //film queries
    public static final String SQL_INSERT_FILM_DATA_INTO_TABLE = "insert into film_test(film_default_title,film_default_description,film_year, film_loc_title, film_loc_description, film_add_date) values(?,?,?,?,?,?)";
    public static final String SQL_GET_FILM_ID_BY_TITLE = "select film_id from film where (film_default_title = ?) or (film_loc_title = ?)";
    public static final String SQL_CONNECT_FILM_WITH_GENRE = "insert into film_has_genre_test(film_id,genre_id) values(?,?)";
    public static final String SQL_GET_MAX_FILM_ID = "select max(film_id) from film_test";
    public static final String SQL_CALL_RECOUNT_RATING = "call recountRating(?,?,?)";
    public static final String SQL_ADVANCED_SEARCH_TITLE_NOT_NULL = "select  film.film_id, film.film_rating,film.film_default_title, film.film_votes, film.film_year, film.film_default_descripption,\n" +
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
            "where locate(?, film.film_default_title) and\n" +
            "((film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?)) and film_genre.idfilm_genre = ?)\n" +
            " group by film.film_id;";
    public static final String SQL_ADVANCED_SEARCH_TITLE_NOT_NULL_EN = "select  film.film_id, film.film_rating,film.film_loc_title, film.film_votes, film.film_year, film.film_loc_description,\n" +
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
            "where locate(?, film.film_loc_title) and\n" +
            "((film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?)) and film_genre.idfilm_genre = ?)\n" +
            " group by film.film_id;";
    public static final String SQL_ADVANCED_SEARCH_NULL_TITLE = "select  film.film_id, film.film_rating,film.film_default_title, film.film_votes, film.film_year, film.film_default_descripption,\n" +
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
            "where locate(?, film.film_default_title) or " +
            "(film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?) or film_genre.idfilm_genre = ?)\n" +
            " group by film.film_id;";
    public static final String SQL_ADVANCED_SEARCH_NULL_TITLE_EN = "select  film.film_id, film.film_rating,film.film_loc_title, film.film_votes, film.film_year, film.film_loc_description,\n" +
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
            "where locate(?, film.film_loc_title) or((film.film_year = ? xor (film.film_year >= ? and film.film_year <= ?)) or film_genre.idfilm_genre = ?)\n" +
            " group by film.film_id;";
    public static final String SQL_SEARCH_FILM_BY_ACTOR = "select  film.film_id, film.film_rating,film.film_default_title, film.film_votes, film.film_year, film.film_default_descripption,\n" +
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
            "where locate(?, actor_name) and locate(?, actor_surname) group by film.film_id;";
    public static final String SQL_SEARCH_FILM_BY_ACTOR_EN = "select  film.film_id, film.film_rating,film.film_loc_title, film.film_votes, film.film_year, film.film_loc_description,\n" +
            "\" +\n" +
            "            \"GROUP_CONCAT(distinct film_genre.film_genre_EN) as genre,\\n\" +\n" +
            "            \"GROUP_CONCAT(distinct actor.actor_name_EN,' ',actor.actor_surname_EN) as actor,\\n\" +\n" +
            "            \"group_concat(distinct director.director_name_EN,' ',director.director_surname_EN) as director\\n\" +\n" +
            "            \"from film\\n\" +\n" +
            "            \"JOIN film_has_genre ON film.film_id = film_has_genre.film_id\\n\" +\n" +
            "            \"JOIN film_genre ON film_has_genre.genre_id = film_genre.idfilm_genre\\n\" +\n" +
            "            \"join actor_has_film on film.film_id = actor_has_film.film_id\\n\" +\n" +
            "            \"join actor on actor_has_film.actor_id = actor.actor_id \\n\" +\n" +
            "            \"join director_has_film on film.film_id = director_has_film.film_id\\n\" +\n" +
            "            \"join director on director_has_film.director_id = director.director_id \\n\" +\n" +
            "            \"where locate(?, actor.actor_name_EN) and locate(?, actor.actor_surname_EN) group by film.film_id;";
    public static final String SQL_SEARCH_FILM_BY_DIRECTOR = "select  film.film_id, film.film_rating,film.film_default_title, film.film_votes, film.film_year, film.film_default_descripption,\n" +
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
            "where locate(?, director.director_name) and locate(?, director.director_surname) group by film.film_id;";
    public static final String SQL_SEARCH_FILM_BY_DIRECTOR_EN = "select  film.film_id, film.film_rating,film.film_loc_title, film.film_votes, film.film_year, film.film_loc_description,\n" +
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
            "where locate(?, director.director_name_EN) and locate(?, director.director_surname_EN) group by film.film_id;";
    private DAODbQuery(){}
}
