package data;

import managers.DBManager;
import model.Account;
import model.Episode;
import model.Movie;
import model.Profile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
                    +"WHERE account = " + account.getEmail();
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
        AtomicInteger x = new AtomicInteger();
        String sql = "SELECT watch_time FROM profile_movie\n"
                    +"WHERE profile_name = " + profile.getName() + "AND profile_account = " + profile.getAccountEmail()
                    +"AND movie = " + movie.getId();
        DBManager.getInstance().query(sql, rs -> {
           try {
               while (rs.next()) {
                   x.set(rs.getInt("watch_time"));
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
        });
        return x.get();
    }

    @Override
    public int getProfileWatchTimeForEpisode(Profile profile, Episode episode) {
        AtomicInteger x = new AtomicInteger();
        String sql = "SELECT watch_time FROM profile_episode\n"
                    +"WHERE profile_name = " + profile.getName() + "AND profile_account = " + profile.getAccountEmail()
                    +"AND episode = " + episode.getId();
        DBManager.getInstance().query(sql, rs -> {
            try {
                while (rs.next()) {
                    x.set(rs.getInt("watch_time"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return x.get();
    }

    @Override
    public void setProfileWatchTimeForMovie(Profile profile, Movie movie, int percentage) {
        //first check if there's data already
        String sql = "SELECT * FROM profile_movie\n"
                    +"WHERE profile_name = " + profile.getName() + "AND profile_account = " + profile.getAccountEmail()
                    +"AND movie = " + movie.getId();
        DBManager.getInstance().query(sql, rs -> {
           try {
               if (!rs.next()) { // insert new if none
                   String sql2 = "INSERT INTO profile_movie (profile_name, profile_email, movie, watch_time) VALUES\n"
                        +"(" + profile.getName() + ", " + profile.getName() + ", " + movie.getId() + ", " + percentage + ")";
                   DBManager.getInstance().query(sql2, null);
               } else { //update if there is
                   String sql3 = "UPDATE profile_movie SET\n"
                        +"profile_name = " + profile.getName() + ", "
                        +"profile_email = " + profile.getAccountEmail() + ", "
                        +"movie = " + movie.getId() + ", "
                        +"watch_time = " + percentage + "\n"
                           +"WHERE profile_name = " + profile.getName() + "AND profile_account = " + profile.getAccountEmail()
                           +"AND movie = " + movie.getId();
                   DBManager.getInstance().query(sql3, null);
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
        });
    }

    @Override
    public void setProfileWatchTimeForEpisode(Profile profile, Episode episode, int percentage) {
        //first check if there's data already
        String sql = "SELECT * FROM profile_episode\n"
                +"WHERE profile_name = " + profile.getName() + "AND profile_account = " + profile.getAccountEmail()
                +"AND episode = " + episode.getId();
        DBManager.getInstance().query(sql, rs -> {
            try {
                if (!rs.next()) { // insert new if none
                    String sql2 = "INSERT INTO profile_episode (profile_name, profile_email, episode, watch_time) VALUES\n"
                            +"(" + profile.getName() + ", " + profile.getName() + ", " + episode.getId() + ", " + percentage + ")";
                    DBManager.getInstance().query(sql2, null);
                } else { //update if there is
                    String sql3 = "UPDATE profile_movie SET\n"
                            +"profile_name = " + profile.getName() + ", "
                            +"profile_email = " + profile.getAccountEmail() + ", "
                            +"episode = " + episode.getId() + ", "
                            +"watch_time = " + percentage + "\n"
                            +"WHERE profile_name = " + profile.getName() + "AND profile_account = " + profile.getAccountEmail()
                            +"AND episode = " + episode.getId();
                    DBManager.getInstance().query(sql3, null);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
