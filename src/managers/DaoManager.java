package managers;

import data.*;

public class DaoManager {
    private static DaoManager instance;
    private IAccountDao accountDao;
    private IEpisodeDao episodeDao;
    private IMovieDao movieDao;
    private IProfileDao profileDao;
    private ISeriesDao seriesDao;

    public DaoManager getInstance() {
        if (instance == null) {
            instance = new DaoManager();
        }
        return instance;
    }

    public IAccountDao getAccountDao() {
        return accountDao;
    }

    public IEpisodeDao getEpisodeDao() {
        return episodeDao;
    }

    public IMovieDao getMovieDao() {
        return movieDao;
    }

    public IProfileDao getProfileDao() {
        return profileDao;
    }

    public ISeriesDao getSeriesDao() {
        return seriesDao;
    }
}
