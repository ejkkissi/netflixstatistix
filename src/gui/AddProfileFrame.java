package gui;

import model.Account;

import javax.swing.*;

public class AddProfileFrame extends JFrame {
    private JPanel content;
    private JTextField name;
    private JTextField age;
    private JButton btnAdd;
    private JButton btnCancel;

    public AddProfileFrame(Account account) {
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        name = new JTextField();
        age = new JTextField();

        btnAdd = new JButton();
        btnCancel = new JButton();

        btnAdd.addActionListener(a -> {
            //TODO get values and apply
        });

        btnCancel.addActionListener(a -> {
            this.setVisible(false);
        });

        content.add(name);
        content.add(age);
        content.add(btnAdd);
        content.add(btnCancel);

        this.setContentPane(content);
    }
}
