package data;

import managers.DBManager;
import model.Episode;
import model.Movie;
import model.Profile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao implements IMovieDao {
    @Override
    public List<Movie> getAllMovies() {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT * FROM movie";
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    Movie m = new Movie(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("duration"),
                            rs.getString("genre"),
                            rs.getString("language"),
                            rs.getInt("age_indication")
                    );
                    list.add(m);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public Movie getMovieById(int id) {
        Movie m = new Movie();
        String sql = "SELECT * FROM movie WHERE id = " + id;
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    m.setId(rs.getInt("id"));
                    m.setTitle(rs.getString("title"));
                    m.setDuration(rs.getInt("duration"));
                    m.setGenre(rs.getString("genre"));
                    m.setLanguage(rs.getString("language"));
                    m.setAgeIndication(rs.getInt("age_indication"));
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        });
        return m;
    }

    @Override
    public List<Movie> getMoviesWatchedBy(Profile profile) {
        List<Movie> list = new ArrayList<>();
        String sql = "SELECT * FROM movie\n"
                    +"JOIN profile_movie ON profile_movie.movie = movie.id\n"
                    +"WHERE profile_movie.profile_email = " + profile.getAccountEmail()
                    +"AND profile_movie.profile_name = " + profile.getName();
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    Movie m = new Movie(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("duration"),
                            rs.getString("genre"),
                            rs.getString("language"),
                            rs.getInt("age_indication")
                    );
                    list.add(m);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }
}
