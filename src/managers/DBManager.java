package managers;

public class DBManager {
    private static DBManager instance;

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public void query(String sql, QueryOperator fn) {

    }

    public void createDatabase() {

    }

    public void deleteDatabase() {

    }

    public void selectDatabase() {

    }
}
