package data;

import managers.DBManager;
import model.Account;
import model.Episode;
import model.Movie;
import model.Profile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDao implements IProfileDao {
    @Override
    public List<Profile> getAllProfiles() {
        List<Profile> list = new ArrayList<>();
        String sql = "SELECT * FROM profile";
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    Profile p = new Profile(rs.getString("name"), rs.getString("account"), rs.getInt("age"));
                    list.add(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public void createProfile(Profile profile) {
        String sql = "INSERT INTO profile (name, account, email) VALUES (" + profile.getName() + " " + profile.getAccountEmail() + " " + profile.getAge() + ")";
        DBManager.getInstance().query(sql, null);
    }

    @Override
    public void updateProfile(Profile profile) {
        String sql = "UPDATE profile SET\n"
                    +"name = " + profile.getName() + ", "
                    +"account = " + profile.getAccountEmail() + ", "
                    +"age = " + profile.getAge();
        DBManager.getInstance().query(sql, null);
    }

    @Override
    public void deleteProfile(Profile profile) {
        String sql ="DELETE FROM profile WHERE account = " + profile.getAccountEmail() + " AND name = " + profile.getName();
        DBManager.getInstance().query(sql, null);
    }

    @Override
    public List<Profile> getProfilesOf(Account account) {
        List<Profile> list = new ArrayList<>();
        String sql = "SELECT * FROM profile\n"
                    +"JOIN account ON account.email = profile.account\n"
                    +"WHERE account.email = " + account.getEmail();
        DBManager.getInstance().query(sql, rs-> {
            try {
                while (rs.next()) {
                    Profile p = new Profile(
                            rs.getString("name"),
                            rs.getString("account"),
                            rs.getInt("age")
                    );
                    list.add(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public List<Profile> getProfilesWhoWatched(Movie movie) {
        List<Profile> list = new ArrayList<>();
        String sql = "SELECT * FROM profile\n"
                    +"JOIN profile_movie ON profile_movie.profile_name = profile.name AND profile_movie.profile_email = profile.account\n"
                    +"WHERE profile_movie.movie =" + movie.getId();
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    Profile p = new Profile(
                            rs.getString("name"),
                            rs.getString("account"),
                            rs.getInt("age")
                    );
                    list.add(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public List<Profile> getProfilesWhoWatched(Episode episode) {
        List<Profile> list = new ArrayList<>();
        String sql = "SELECT * FROM profile\n"
                +"JOIN profile_episode ON profile_episode.profile_name = profile.name AND profile_episode.profile_email = profile.account\n"
                +"WHERE profile_episode.episode =" + episode.getId();
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    Profile p = new Profile(
                            rs.getString("name"),
                            rs.getString("account"),
                            rs.getInt("age")
                    );
                    list.add(p);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return list;
    }

    @Override
    public int getProfileWatchTimeForMovie(Profile profile, Movie movie) {
        return 0;
    }

    @Override
    public int getProfileWatchTimeForEpisode(Profile profile, Episode episode) {
        return 0;
    }

    @Override
    public void setProfileWatchTimeForMovie(Profile profile, Movie movie) {

    }

    @Override
    public void setProfileWatchTimeForEpisode(Profile profile, Episode episode) {

    }
}
