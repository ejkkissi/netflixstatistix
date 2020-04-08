package data;

import managers.DBManager;
import model.Profile;
import model.Series;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeriesDao implements ISeriesDao {
    @Override
    public List<Series> getAllSeries() {
        List<Series> list = new ArrayList<>();
        String sql = "SELECT * FROM series";
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    Series s = new Series(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getString("language"),
                            rs.getInt("age_indication"));
                    list.add(s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public Series getSeriesById(int id) {
        Series s = new Series();
        String sql = "SELECT * FROM series WHERE id = " + id;
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    s.setId(rs.getInt("id"));
                    s.setTitle(rs.getString("title"));
                    s.setGenre(rs.getString("genre"));
                    s.setLanguage(rs.getString("language"));
                    s.setAgeIndication(rs.getInt("age_indication"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return s;
    }

    @Override
    public List<Series> getSeriesWatchedBy(Profile profile) {
        List<Series> list = new ArrayList<>();

        String sql = "SELECT * FROM series\n"
                    +"JOIN episode ON episode.series = series.id\n"
                    +"JOIN profile_episode ON profile_episode.episode = episode.id"
                    +"WHERE profile.account = " + profile.getAccountEmail() + " AND profile.name = " + profile.getName();

        DBManager.getInstance().query(sql, rs -> {
            try {
                while(rs.next()) {
                    Series s = new Series(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("genre"),
                            rs.getString("language"),
                            rs.getInt("age_indication"));
                    list.add(s);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }
}
