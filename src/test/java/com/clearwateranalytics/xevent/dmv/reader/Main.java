package com.clearwateranalytics.xevent.dmv.reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;

public class Main {
    @Test
    public void main() throws SQLException {
        String connectionString = "jdbc:jtds:sqlserver://bilbo";  // jTDS
        //String connectionString = "jdbc:sqlserver://bilbo";
        try (Connection connection = DriverManager.getConnection(connectionString, "java", "java")) {
            DmvReaderListener listener = new DmvPrinter();
            DmvReader reader = new DmvReader();
            reader.read(connection, listener);
        }
    }
}
