package data;

import model.Account;
import model.Episode;
import model.Movie;
import model.Profile;

import java.util.List;

public interface IProfileDao {
    List<Profile> getAllProfiles();
    void createProfile(Profile profile);
    void updateProfile(Profile profile);
    void deleteProfile(Profile profile);
    List<Profile> getProfilesOf(Account account);
    List<Profile> getProfilesWhoWatched(Movie movie);
    List<Profile> getProfilesWhoWatched(Episode episode);
    int getProfileWatchTimeForMovie(Profile profile, Movie movie);
    int getProfileWatchTimeForEpisode(Profile profile, Episode episode);
    void setProfileWatchTimeForMovie(Profile profile, Movie movie, int percentage);
    void setProfileWatchTimeForEpisode(Profile profile, Episode episode, int percentage);
}
