package data;

import model.Profile;
import model.Series;

import java.util.List;

public interface ISeriesDao {
    List<Series> getAllSeries();
    Series getSeriesById(int id);
    List<Series> getSeriesWatchedBy(Profile profile);
}
