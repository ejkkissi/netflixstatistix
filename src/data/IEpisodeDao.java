package data;

import model.Episode;
import model.Profile;
import model.Series;

import java.util.List;

public interface IEpisodeDao {
    List<Episode> getEpisodesOf(Series series);
    Episode getEpisodeById(int id);
    List<Episode> getEpisodesOfSeriesWatchedByProfile(Series series, Profile profile);
}
