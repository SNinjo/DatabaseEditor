package Database;

import java.sql.*;
import java.util.ArrayList;

import Other.Method;

public class Database {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://";
    private static String database = ""; // 引用全數字的 database 需用 `` 框起來(Ex. `410877016`)
    private static String IP = "localhost";
    private static String user = "root";
    private static String password = "";
    private static Connection conn;
    private static Statement stmt;

    public Database() throws ClassNotFoundException, SQLException {
        Connect();
    }
    public Database(String[] aConnectInfo) throws ClassNotFoundException, SQLException {
        IP = aConnectInfo[0];
        user = aConnectInfo[1];
        password = aConnectInfo[2];
        Connect();
    }

    private void Connect() throws ClassNotFoundException, SQLException {
        // JVM 動態加載類別
        Class.forName(driver);

        conn = DriverManager.getConnection( (url + IP + "/" + database) , user, password);
        stmt = conn.createStatement();
    }

    public String excuteSQL(String strInput) throws SQLException {
        if (!stmt.execute(strInput)) {
            return "Database << Ok";
        }
        else {
            String strOutput = "\n";
            ResultSet rs = stmt.executeQuery(strInput);

            // 取得 Column Name
            ResultSetMetaData rsmd = rs.getMetaData();
            int iColumnCount = rsmd.getColumnCount();
            for (int index = 1; index < iColumnCount; index++){
                strOutput += rsmd.getColumnName(index) + "\t";
            }
            strOutput += rsmd.getColumnName(iColumnCount) + "\n ---------------------------------------------------- \n";

            // 資料輸出
            strOutput += Method.ResultSet_toString(rs, "\t", "\n") + "\n";

            return strOutput;
        }
    }

    public String[][] excuteSQL(String strInput, boolean IsReturnArray) throws SQLException {
        if (IsReturnArray == false) return null;
        ResultSet rs = stmt.executeQuery(strInput);
        return Method.ResultSet_toArray2(rs);
    }

    public String[][] getTableInfo(String strTableName) throws SQLException {
        // Table 有六個屬性分別為 Field | Type | Null | Key | Default | Extra
        ArrayList<String[]> listInfo = new ArrayList<String[]>();
        ResultSet rs = stmt.executeQuery("DESCRIBE " + strTableName);
        while(rs.next()){
            String[] aRowData = new String[6];
            for (int column = 1; column <= 6; column++){
                String strData = rs.getString(column);

                // 將 int(?) 簡化成 int
                if ((strData != null) && (strData.indexOf("int") != -1)) strData = "int";

                aRowData[column - 1] = strData;
            }
            listInfo.add(aRowData);
        }
        String[][] aInfo = listInfo.toArray(new String[listInfo.size()][]);
        return aInfo;
    }

}
