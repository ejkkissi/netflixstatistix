package managers;

import java.sql.ResultSet;

public interface QueryOperator {
    void apply(ResultSet rs);
}
