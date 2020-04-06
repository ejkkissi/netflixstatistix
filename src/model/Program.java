package model;

public abstract class Program {
    private int id;
    private String title;
    private int duration;

    public Program(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }
}
