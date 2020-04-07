package managers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBManager {
    private static DBManager instance;
    private String database;

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public void query(String sql, QueryOperator fn) {
        Connection connection;
        Statement statement;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./NetflixStatistix/" + database);
            statement = connection.createStatement();
            statement.execute(sql);
            if (statement.getResultSet() != null) {
                fn.apply(statement.getResultSet());
                statement.getResultSet().close();
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createDatabase(String name) {
        try {
            Connection connection;
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./NetflixStatistix/" + name);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDatabase(String name) {
        try {
            File file = new File("./NetflixStatistix/" + name + ".mv.db");
            if (file.isFile()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectDatabase(String name) {
        if (new File("./NetflixStatistix/" + name + ".mv.db").isFile()) {
            database = name;
        }
    }
}
