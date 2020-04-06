package data;

import model.Account;
import model.Episode;
import model.Movie;
import model.Profile;

import java.util.List;

public class ProfileDao implements IProfileDao {
    @Override
    public List<Profile> getAllProfiles() {
        return null;
    }

    @Override
    public void createProfile(Profile profile) {

    }

    @Override
    public void updateProfile(Profile profile) {

    }

    @Override
    public void deleteProfile(Profile profile) {

    }

    @Override
    public List<Profile> getProfilesOf(Account account) {
        return null;
    }

    @Override
    public List<Profile> getProfilesWhoWatched(Movie movie) {
        return null;
    }

    @Override
    public List<Profile> getProfilesWhoWatched(Episode episode) {
        return null;
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
