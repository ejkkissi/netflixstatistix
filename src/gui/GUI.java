package gui;

import managers.DaoManager;
import model.Series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI {
    JFrame frame;
    JTabbedPane tabpane;
    JScrollPane scrPrograms;
    JScrollPane scrAccounts;
    JPanel programs;
    JPanel accounts;

    public void show() {
        frame = new JFrame();
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        programs = new JPanel();
        programs.setLayout(new GridLayout(0, 4));
        accounts = new JPanel();
        accounts.setLayout(new GridLayout(0, 2));

        scrPrograms = new JScrollPane(programs);
        scrAccounts = new JScrollPane(accounts);

        tabpane = new JTabbedPane();
        tabpane.add("Programma's", scrPrograms);
        tabpane.add("Accounts", scrAccounts);

        frame.add(tabpane);
        frame.setVisible(true);

        generatePrograms();
        generateAccounts();
    }

    private void generatePrograms() {
        DaoManager.getInstance().getSeriesDao().getAllSeries().forEach(series -> {
            System.out.println("Rendering..." + series.getTitle());
            JButton button = new JButton();
            button.addActionListener(a -> {
                SeriesFrame sf = new SeriesFrame(series);
                sf.setSize(800, 500);
                sf.setVisible(true);
            });
            button.setText(series.getTitle());
            programs.add(button);
        });

        DaoManager.getInstance().getMovieDao().getAllMovies().forEach(movie -> {
            System.out.println("Rendering..." + movie.getTitle());
            JButton button = new JButton();
            button.addActionListener((a) -> {
                MovieFrame mf = new MovieFrame(movie);
                mf.setSize(800, 500);
                mf.setTitle(movie.getTitle());
                mf.setVisible(true);
            });
            button.setText(movie.getTitle());
            programs.add(button);
        });

        frame.repaint();
    }

    private void generateAccounts() {
        DaoManager.getInstance().getAccountDao().getAllAccounts().forEach(account -> {
            System.out.println("Rendering..." + account.getEmail());
            JButton button = new JButton();
            button.addActionListener(actionEvent -> {
                AccountFrame accFrame = new AccountFrame(account.getEmail());
                accFrame.setSize(800, 700);
                accFrame.setVisible(true);
            });
            button.setText(account.getEmail());
            accounts.add(button);
        });

        frame.repaint();
    }
}
