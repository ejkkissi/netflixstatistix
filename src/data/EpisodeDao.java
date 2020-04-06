package data;

import model.Episode;
import model.Profile;
import model.Series;

import java.util.List;

public class EpisodeDao implements IEpisodeDao {
    @Override
    public List<Episode> getEpisodesOf(Series series) {
        return null;
    }

    @Override
    public Episode getEpisodeById(int id) {
        return null;
    }

    @Override
    public List<Episode> getEpisodesOfSeriesWatchedByProfile(Series series, Profile profile) {
        return null;
    }
}
