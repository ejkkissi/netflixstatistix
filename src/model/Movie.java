package model;

public class Movie extends Program {
    private String genre;
    private String language;
    private int ageIndication;

    public Movie(int id, String title, int duration, String genre, String language, int ageIndication) {
        super(id, title, duration);
        this.genre = genre;
        this.language = language;
        this.ageIndication = ageIndication;
    }
}
