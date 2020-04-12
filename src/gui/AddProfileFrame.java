package gui;

import managers.DaoManager;
import model.Account;
import model.Profile;
import util.MiscUtil;

import javax.swing.*;
import java.util.stream.Collectors;

public class AddProfileFrame extends JFrame {
    private JPanel content;
    private JLabel nameLabel;
    private JTextField name;
    private JLabel ageLabel;
    private JTextField age;
    private JButton btnAdd;
    private JButton btnCancel;

    public AddProfileFrame(AccountFrame af, Account account) {
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        name = new JTextField();
        age = new JTextField();

        nameLabel = new JLabel("Naam:");
        ageLabel = new JLabel("Leeftijd:");

        btnAdd = new JButton("Voeg toe");
        btnCancel = new JButton("Annuleer");

        btnAdd.addActionListener(a -> {
            String txt = name.getText();
            String agetxt = age.getText();
            //check if there aren't too many profiles
            if (DaoManager.getInstance().getProfileDao().getProfilesOf(account).size() == 5) {
                new Popup("Een account mag niet meer dan 5 profielen hebben");
                return;
            }

            //check for not the same profiles
            if (DaoManager.getInstance().getProfileDao().getProfilesOf(account).stream()
            .filter(p -> p.getName() == txt)
            .collect(Collectors.toList()).size() > 0) {
                new Popup("Naam mag niet hetzelfde zijn als een ander profiel!");
                return;
            }

            //checks for name
            if (txt.length() > 20) {
                new Popup("Naam mag niet meer dan 20 karakters hebben!");
                return;
            }
            if (txt.contains("'")) {
                new Popup("Gebruik geen speciale karakters");
                return;
            }
            if (txt.contains("\\")) {
                new Popup("Gebruik geen speciale karakters");
                return;
            }
            if (txt.contains("\"")) {
                new Popup("Gebruik geen speciale karakters");
                return;
            }

            //checks for age
            if (!MiscUtil.isInteger(agetxt)) {
                new Popup("Leeftijd moet een getal zijn");
                return;
            }

            if (Integer.valueOf(agetxt) < 0) {
                new Popup("Leeftijd moet een positief getal zijn");
                return;
            }

            //do the you know the thing
            Profile profile = new Profile(txt, account.getEmail(), Integer.valueOf(agetxt));
            DaoManager.getInstance().getProfileDao().createProfile(profile);
            this.setVisible(false);
            af.generateProfiles();
        });

        btnCancel.addActionListener(a -> {
            this.setVisible(false);
        });

        content.add(nameLabel);
        content.add(name);
        content.add(ageLabel);
        content.add(age);
        content.add(btnAdd);
        content.add(btnCancel);

        this.setContentPane(content);
    }
}
