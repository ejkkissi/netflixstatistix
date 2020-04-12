package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class SQLUtil {
    public static String getQuery(String name) {
        InputStream stream = SQLUtil.class.getResourceAsStream("../queries/" + name + ".sql");
        return new BufferedReader(new InputStreamReader(stream))
                .lines().collect(Collectors.joining("\n"));
    }
}
