package model;

public class Episode extends Program {
    private int episodeNumber;
    private int season;

    public Episode(int id, String title, int duration, int episodeNumber, int season) {
        super(id, title, duration);
        this.episodeNumber = episodeNumber;
        this.season = season;
    }
}
