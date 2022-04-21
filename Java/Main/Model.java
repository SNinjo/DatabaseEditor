package Main;

import Database.Database;
import Other.ArrayMethod;

import java.sql.SQLException;

public class Model {

    Database database;

    Model(String[] aConnectInfo) throws ClassNotFoundException, SQLException {
        if (aConnectInfo[0].equals("default")) database = new Database();
        else database = new Database(aConnectInfo);
    }

    String excuteSQL(String strInput) throws SQLException {
        return database.excuteSQL(strInput);
    }

}
