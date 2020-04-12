package gui;

import managers.DaoManager;
import model.Episode;
import model.Series;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SeriesFrame extends JFrame {
    private Series series;
    private JPanel content;
    private JLabel title;
    private JLabel genre;
    private JLabel amountEps;
    private JLabel language;
    private JLabel ageIndication;
    private JLabel episodesLabel;

    private JScrollPane epWrapper;
    private JPanel episodes;

    public SeriesFrame(Series series) {
        this.series = series;
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        title = new JLabel("Titel" + series.getTitle());
        genre = new JLabel("Genre: " + series.getGenre());
        amountEps = new JLabel("Aantal afleveringen: " + DaoManager.getInstance().getEpisodeDao().getEpisodesOf(series).size());
        language = new JLabel("Taal" + series.getLanguage());
        ageIndication = new JLabel("Leeftijdsindicatie " + series.getAgeIndication() + " en ouder");
        episodesLabel = new JLabel("Afleveringen:");

        episodes = new JPanel();
        episodes.setLayout(new GridLayout(0, 2));
        epWrapper = new JScrollPane(episodes);

        content.add(title);
        content.add(genre);
        content.add(amountEps);
        content.add(language);
        content.add(ageIndication);
        content.add(episodesLabel);
        content.add(epWrapper);

        this.setContentPane(content);
        generateEpisodes();
    }

    private void generateEpisodes() {
        DaoManager daos = DaoManager.getInstance();
        daos.getEpisodeDao().getEpisodesOf(series).forEach(e -> {
            JPanel panel = new JPanel();
            JLabel title = new JLabel(e.getTitle());
            JLabel ep = new JLabel(e.getSeason() + ", ep: " + e.getEpisodeNumber());
            JLabel avgwatchtime = new JLabel();

            AtomicInteger counter = new AtomicInteger();
            AtomicInteger total = new AtomicInteger();

            daos.getProfileDao().getProfilesWhoWatched(e).stream()
                    .filter(p -> daos.getProfileDao().getProfileWatchTimeForEpisode(p, e) > 0)
                    .forEach(p -> {

                        total.addAndGet(daos.getProfileDao().getProfileWatchTimeForEpisode(p, e));
                        counter.getAndIncrement();
                    });

            int avg = total.get() / counter.get();
            avgwatchtime.setText("Gemiddeld bekeken tijdsduur: " + avg + "%");
            episodes.add(panel);
        });
        repaint();
    }
}
