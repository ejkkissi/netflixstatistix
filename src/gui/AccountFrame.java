package gui;

import data.IAccountDao;
import managers.DaoManager;
import model.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFrame extends JFrame {
    private JPanel content;
    private JLabel email;
    private JLabel name;
    private JLabel address;
    private JLabel city;
    private JSeparator sep1;
    private JButton addProfile;
    private JPanel profiles;
    private Account account;

    public AccountFrame(String email) {
        IAccountDao accountdao = DaoManager.getInstance().getAccountDao();
        account = accountdao.getAccountByEmail(email);

        this.setTitle(account.getEmail());

        this.email = new JLabel("Email: " + account.getEmail());
        name = new JLabel("Naam: " + account.getName());
        address = new JLabel("Adres: " + account.getAddress());
        city = new JLabel("Woonplaats: " + account.getCity());

        sep1 = new JSeparator();

        addProfile = new JButton("Add");
        addProfile.addActionListener(actionEvent -> {
            AddProfileFrame apf = new AddProfileFrame(account);
            apf.setSize(300, 300);
            apf.setVisible(true);
        });

        profiles = new JPanel();
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        content.add(this.email);
        content.add(name);
        content.add(address);
        content.add(city);
        content.add(sep1);
        content.add(addProfile);
        content.add(profiles);

        this.setContentPane(content);
        generateProfiles();
    }

    private void generateProfiles() {
        DaoManager.getInstance().getProfileDao().getProfilesOf(account).forEach(profile -> {
            JPanel panel = new JPanel();
            JLabel label = new JLabel(profile.getName());
            panel.addMouseListener(null); //TODO to profile page
            JButton btnDelete = new JButton("Delete");
            JButton btnEdit = new JButton("Edit");

            btnDelete.addActionListener(a -> {
                DaoManager.getInstance().getProfileDao().deleteProfile(profile);
                generateProfiles();
            });

            btnEdit.addActionListener(a -> {
                //TODO create profile edit frame
            });

            panel.add(label);
            panel.add(btnEdit);
            panel.add(btnDelete);

            profiles.add(panel);
        });

        this.repaint();
    }
}
