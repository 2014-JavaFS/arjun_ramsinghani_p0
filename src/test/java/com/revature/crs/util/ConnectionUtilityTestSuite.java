package com.revature.crs.util;

import com.revature.crs.Util.ConnectionUtility;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConnectionUtilityTestSuite {
    @Test
    public void test_getConnectionValidConnection() {
        try (Connection conn = ConnectionUtility.getConnectionUtility().getConnection()) {
            assertNotNull(conn);
        }

        catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
