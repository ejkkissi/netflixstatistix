package gui;

import managers.DaoManager;
import model.Movie;
import model.Profile;
import util.MiscUtil;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class MovieFrame extends JFrame {
    private Movie movie;
    private JPanel content;
    private JLabel title;
    private JLabel duration;
    private JLabel genre;
    private JLabel language;
    private JLabel ageIndication;
    private JPanel watchedBy;
    private JLabel watchedByLabel;
    private JScrollPane wbWrapper;
    private JLabel fullyWatchedBy;

    public MovieFrame(Movie movie) {
        this.movie = movie;

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        title = new JLabel("Titel: " + movie.getTitle());
        duration = new JLabel("Tijdsduur: " + MiscUtil.formatDurationToString(movie.getDuration()));
        genre = new JLabel("Genre: " + movie.getGenre());
        language = new JLabel("Taal: " + movie.getLanguage());
        ageIndication = new JLabel("Leeftijdsindicatie: " + movie.getAgeIndication() + " jaar en ouder");

        watchedBy = new JPanel();
        watchedBy.setLayout(new GridLayout(0, 2));

        watchedByLabel = new JLabel("Bekeken door:");
        fullyWatchedBy = new JLabel();

        wbWrapper = new JScrollPane(watchedBy);

        content.add(title);
        content.add(duration);
        content.add(genre);
        content.add(language);
        content.add(ageIndication);
        content.add(watchedByLabel);
        content.add(wbWrapper);
        content.add(fullyWatchedBy);

        this.setContentPane(content);

        SwingUtilities.invokeLater(this::generateWatchedBy);
        SwingUtilities.invokeLater(() -> fullyWatchedBy.setText("Volledig bekeken door: " + calcFullyWatched() + " personen"));
    }

    private void generateWatchedBy() {
        DaoManager daos = DaoManager.getInstance();
        List<Profile> profiles = daos.getProfileDao().getProfilesWhoWatched(movie);

        profiles.forEach(p -> {
            JButton btn = new JButton();
            btn.setText(p.getName() + ": " + daos.getProfileDao().getProfileWatchTimeForMovie(p, movie) + "%");

            btn.addActionListener(actionEvent -> {
                AccountFrame accFrame = new AccountFrame(p.getAccountEmail());
                accFrame.setSize(800, 500);
                accFrame.setVisible(true);
            });

            watchedBy.add(btn);
        });

        repaint();
    }

    private int calcFullyWatched() {
        return DaoManager.getInstance().getProfileDao().getProfilesWhoWatched(movie).stream()
                .filter(p -> DaoManager.getInstance().getProfileDao().getProfileWatchTimeForMovie(p, movie) == 100)
                .collect(Collectors.toList()).size();
    }
}
