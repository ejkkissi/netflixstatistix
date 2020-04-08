package model;

public class Series {
    private int id;
    private String title;
    private String genre;
    private String language;
    private int ageIndication;

    public Series() {

    }

    public Series(int id, String title, String genre, String language, int ageIndication) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.ageIndication = ageIndication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getAgeIndication() {
        return ageIndication;
    }

    public void setAgeIndication(int ageIndication) {
        this.ageIndication = ageIndication;
    }
}
