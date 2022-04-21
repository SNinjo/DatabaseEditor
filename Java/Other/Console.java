package Other;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://";
    private static String database = ""; // 引用全數字的 database 需用 `` 框起來(Ex. `410877016`)
    private static String IP = "140.127.74.170";
    private static String user = "410877016";
    private static String password = "410877016";

    private static String IP2 = "localhost";
    private static String user02 = "root";
    private static String password02 = "";

    private static int dbFlag = 1;

    public static void main(String[] args){
        try {
            // JVM 動態加載類別
            Class.forName(driver);

            // 挑選 Database 連線
            Connection conn = null;
            if (dbFlag == 1) conn = DriverManager.getConnection( (url + IP + "/" + database) , user, password);
            if (dbFlag == 2) conn = DriverManager.getConnection( (url + IP2 + "/" + database) , user02, password02);
            if (conn != null && !conn.isClosed()) System.out.println("資料庫連線測試成功！");

            // 輸入指令
            Statement stmt = conn.createStatement();
            Scanner sc = new Scanner(System.in);
            String strInput = "";
            while (!(strInput = sc.nextLine()).equals("exit")){
                if (!stmt.execute(strInput)) {
                    System.out.println("Ok!");
                }
                else System.out.println(Other.Method.ResultSet_toString(stmt.executeQuery(strInput)));
            }
            stmt.close();
        } catch (ClassNotFoundException e) {
            // 處理 Class.forName 錯誤
            System.out.println("找不到驅動程式類別");
            e.printStackTrace();
        } catch (SQLException e) {
            // 處理 JDBC 錯誤
            e.printStackTrace();
        }
    }

    public static void printDatabaseInfo(Statement stmt) {
        try {
            ArrayList<String> listDatabaseName = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery("SHOW DATABASES");
            ResultSetMetaData rsmd = rs.getMetaData();
            int iColumnCount = rsmd.getColumnCount();

            System.out.println("------ Database ------");
            while (rs.next()){
                String dbName = rs.getString(1);
                System.out.println(dbName);
                listDatabaseName.add(dbName);
            }
            System.out.println("----------------------\n\n");

            for (int index = 0; index < listDatabaseName.size(); index++){
                String dbName = listDatabaseName.get(index);
                System.out.println("--- " + dbName + " ---");
                if (Method.IsNum(dbName)) dbName = "`" + dbName + "`";
                stmt.execute("USE " + dbName);
                rs = stmt.executeQuery("SHOW TABLES");
                System.out.println(Method.ResultSet_toString(rs));
                System.out.println("--- END ---\n");
            }
        } catch (SQLException e) {
            // 處理 JDBC 錯誤
            e.printStackTrace();
        }
    }

}
