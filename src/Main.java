import gui.GUI;
import managers.DBManager;
import managers.DaoManager;
import util.DataUtil;
import util.SQLUtil;

public class Main {
    public static void main(String[] args) {
        DBManager.getInstance().createDatabase("default");
        DBManager.getInstance().selectDatabase("default");
        //generate data
        if (DaoManager.getInstance().getMovieDao().getAllMovies().isEmpty()) {
            DBManager.getInstance().query(SQLUtil.getQuery("default"), null);
            DataUtil.generateAccountData();
            DataUtil.generateProfiles();
            DataUtil.generateViewerData();
        }
        new GUI().show();
    }
}
