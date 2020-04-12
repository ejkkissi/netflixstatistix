import gui.GUI;
import managers.DBManager;
import managers.DaoManager;
import util.DataUtil;
import util.SQLUtil;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DBManager.getInstance().createDatabase("default");
        DBManager.getInstance().selectDatabase("default");
        //generate data
        if (DaoManager.getInstance().getMovieDao().getAllMovies().isEmpty()) {
            JFrame temp = new JFrame();
            JPanel panel = new JPanel();
            temp.setContentPane(panel);
            panel.add(new JLabel("Database genereert wat gegevens..."));
            panel.add(new JLabel("Dit kan ongeveer 5 minuten duren..."));
            panel.add(new JLabel("Ik weet niet waarom het zo lang duurt ¯\\_(ツ)_/¯"));
            temp.setVisible(true);
            DBManager.getInstance().query(SQLUtil.getQuery("default"), null);
            DataUtil.generateAccountData();
            DataUtil.generateProfiles();
            DataUtil.generateViewerData();
            temp.setVisible(false);
        }
        new GUI().show();
    }
}
