package gui;

import javax.swing.*;
import java.awt.*;

public class Popup extends JDialog {
    private JPanel wrapper;
    private JLabel text;
    private JButton ok;

    public Popup(String text) {
        this.text = new JLabel(text);
        ok = new JButton("OK");
        ok.addActionListener(a -> {
            this.setVisible(false);
        });

        wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.add(this.text);
        wrapper.add(ok);

        this.setContentPane(wrapper);
        this.setSize(400, 100);
        this.pack();
        this.setVisible(true);
    }
}
