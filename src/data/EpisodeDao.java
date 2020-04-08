package data;

import managers.DBManager;
import model.Episode;
import model.Profile;
import model.Program;
import model.Series;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpisodeDao implements IEpisodeDao {
    @Override
    public List<Episode> getEpisodesOf(Series series) {
        List<Episode> list = new ArrayList<>();

        String sql = "SELECT * FROM episode\n"
                    +"JOIN series ON series.id = episode.series\n"
                    +"WHERE series.id = " + series.getId();

        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    Episode e = new Episode(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("duration"),
                            rs.getInt("episode_number"),
                            rs.getString("season"));
                    list.add(e);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public Episode getEpisodeById(int id) {
        Episode e = new Episode();
        String sql = "SELECT * FROM episode WHERE id = " + id;
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    e.setId(rs.getInt("id"));
                    e.setTitle(rs.getString("title"));
                    e.setDuration(rs.getInt("duration"));
                    e.setEpisodeNumber(rs.getInt("episode_number"));
                    e.setSeason(rs.getString("season"));
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        });
        return e;
    }

    @Override
    public List<Episode> getEpisodesOfSeriesWatchedByProfile(Series series, Profile profile) {
        List<Episode> list = new ArrayList<>();
        //TODO can;t do until seriesdao
        return null;
    }
}
