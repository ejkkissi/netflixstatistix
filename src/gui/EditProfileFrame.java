package gui;

import managers.DaoManager;
import model.Account;
import model.Profile;
import util.MiscUtil;

import javax.swing.*;
import java.util.stream.Collectors;

public class EditProfileFrame extends JFrame {
    private JPanel content;
    private JLabel ageLabel;
    private JTextField age;
    private JButton btnApply;
    private JButton btnCancel;

    public EditProfileFrame(Profile profile) {
        content = new JPanel();
        this.setTitle(profile.getAccountEmail() + " : " + profile.getName());

        ageLabel = new JLabel("Leeftijd:");
        age = new JTextField();
        age.setText(String.valueOf(profile.getAge()));

        btnApply = new JButton("Bevestig");
        btnCancel = new JButton("Annuleer");

        btnApply.addActionListener(a -> {
            String agetxt = age.getText();

            //checks for age
            if (!MiscUtil.isInteger(agetxt)) {
                new Popup("Leeftijd moet een getal zijn");
                return;
            }

            if (Integer.valueOf(agetxt) < 0) {
                new Popup("Leeftijd moet een positief getal zijn");
                return;
            }

            //do the thing
            profile.setAge(Integer.valueOf(agetxt));
            DaoManager.getInstance().getProfileDao().updateProfile(profile);

            //close
            this.setVisible(false);
        });

        btnCancel.addActionListener(a -> {
            this.setVisible(false);
        });

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(ageLabel);
        content.add(age);
        content.add(btnApply);
        content.add(btnCancel);

        this.setContentPane(content);
    }
}
