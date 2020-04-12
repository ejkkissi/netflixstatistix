package gui;

import model.Movie;
import util.MiscUtil;

import javax.swing.*;

public class MovieFrame extends JFrame {
    private JPanel content;
    private JLabel title;
    private JLabel duration;
    private JLabel genre;
    private JLabel language;
    private JLabel ageIndication;

    public MovieFrame(Movie movie) {
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        title = new JLabel(movie.getTitle());
        duration = new JLabel(MiscUtil.formatDurationToString(movie.getDuration()));
        genre = new JLabel(movie.getGenre());
        language = new JLabel(movie.getAgeIndication() + " jaar en ouder");

        content.add(title);
        content.add(duration);
        content.add(genre);
        content.add(language);

        this.setContentPane(content);
    }
}
