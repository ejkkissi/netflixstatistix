package data;

import model.Movie;
import model.Profile;

import java.util.List;

public interface IMovieDao {
    List<Movie> getAllMovies();
    Movie getMovieById(int id);
    List<Movie> getMoviesWatchedBy(Profile profile);
}
