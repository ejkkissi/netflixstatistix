package managers;

import data.*;

public class DaoManager {
    private static DaoManager instance;
    private IAccountDao accountDao;
    private IEpisodeDao episodeDao;
    private IMovieDao movieDao;
    private IProfileDao profileDao;
    private ISeriesDao seriesDao;

    public DaoManager(IAccountDao accountDao, IEpisodeDao episodeDao, IMovieDao movieDao, IProfileDao profileDao, ISeriesDao seriesDao) {
        this.accountDao = accountDao;
        this.episodeDao = episodeDao;
        this.movieDao = movieDao;
        this.profileDao = profileDao;
        this.seriesDao = seriesDao;
    }

    public static DaoManager getInstance() {
        if (instance == null) {
            instance = new DaoManager(new AccountDao(), new EpisodeDao(), new MovieDao(), new ProfileDao(), new SeriesDao());
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
