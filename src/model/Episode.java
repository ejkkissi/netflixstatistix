package model;

public class Episode extends Program {
    private int episodeNumber;
    private String season;

    public Episode() {

    }

    public Episode(int id, String title, int duration, int episodeNumber, String season) {
        super(id, title, duration);
        this.episodeNumber = episodeNumber;
        this.season = season;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
