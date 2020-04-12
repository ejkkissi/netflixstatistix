import gui.GUI;
import managers.DBManager;
import util.DataUtil;
import util.SQLUtil;

public class Main {
    public static void main(String[] args) {
        DBManager.getInstance().createDatabase("default");
        DBManager.getInstance().selectDatabase("default");
        //generate data
        DBManager.getInstance().query(SQLUtil.getQuery("default"), null);
        DataUtil.generateAccountData();
        DataUtil.generateProfiles();
        new GUI().show();
    }
}
