package gui;

import data.IAccountDao;
import managers.DaoManager;
import model.Account;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AccountFrame extends JFrame {
    private JPanel content;
    private JLabel email;
    private JLabel name;
    private JLabel address;
    private JLabel city;
    private JSeparator sep1;
    private JButton addProfile;
    private JPanel profiles;
    private JScrollPane watchedWrapper;
    private JPanel watched;
    private Account account;
    private JLabel watchedLabel;

    public AccountFrame(String email) {
        IAccountDao accountdao = DaoManager.getInstance().getAccountDao();
        account = accountdao.getAccountByEmail(email);

        this.setTitle(account.getEmail());

        this.email = new JLabel("Email: " + account.getEmail());
        name = new JLabel("Naam: " + account.getName());
        address = new JLabel("Adres: " + account.getAddress());
        city = new JLabel("Woonplaats: " + account.getCity());

        sep1 = new JSeparator();

        addProfile = new JButton("Nieuw Profiel");
        addProfile.addActionListener(actionEvent -> {
            AddProfileFrame apf = new AddProfileFrame(this, account);
            apf.setSize(300, 300);
            apf.setVisible(true);
        });

        profiles = new JPanel();
        profiles.setLayout(new GridLayout(0, 1));

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        watched = new JPanel();
        watched.setLayout(new GridLayout(0, 2));
        watchedWrapper = new JScrollPane(watched);
        watchedLabel = new JLabel("Bekeken films:");


        content.add(this.email);
        content.add(name);
        content.add(address);
        content.add(city);
        content.add(sep1);
        content.add(addProfile);
        content.add(profiles);
        content.add(watchedLabel);
        content.add(watched);

        this.setContentPane(content);
        SwingUtilities.invokeLater( this::generateProfiles);
        SwingUtilities.invokeLater(this::generateWatched);
    }

    public void generateProfiles() {
        profiles.removeAll();

        DaoManager.getInstance().getProfileDao().getProfilesOf(account).forEach(profile -> {
            JPanel panel = new JPanel();
            JLabel label = new JLabel(profile.getName());
            panel.addMouseListener(null); //TODO to profile page ripperoni
            JButton btnDelete = new JButton("Delete");
            JButton btnEdit = new JButton("Edit");

            btnDelete.addActionListener(a -> {
                DaoManager.getInstance().getProfileDao().deleteProfile(profile);
                generateProfiles();
            });

            btnEdit.addActionListener(a -> {
                EditProfileFrame epf = new EditProfileFrame(profile);
                epf.setSize(300, 300);
                epf.setVisible(true);
            });

            panel.add(label);
            panel.add(btnEdit);
            panel.add(btnDelete);

            profiles.add(panel);
        });

        revalidate();
        repaint();
    }

    private void generateWatched() {
        watched.removeAll();

        DaoManager daos = DaoManager.getInstance();
        daos.getProfileDao().getProfilesOf(account).forEach( p -> {
            System.out.println("Iterating over " + p.getName());
            daos.getMovieDao().getMoviesWatchedBy(p).forEach( m -> {
                System.out.println("Iterating over " + m.getTitle());
                JButton btn = new JButton(m.getTitle());
                btn.addActionListener(a -> {
                    MovieFrame mf = new MovieFrame(m);
                    mf.setSize(800, 500);
                    mf.setTitle(m.getTitle());
                    mf.setVisible(true);
                });

                boolean check = false;
                for (int i = 0; i < watched.getComponents().length; i++) {
                    JButton button = (JButton) watched.getComponent(i);
                    if (button.getText() == btn.getText()) check = true;
                }
                if (!check) {
                    watched.add(btn);
                }
            });
        });

        revalidate();
        repaint();
    }
}
