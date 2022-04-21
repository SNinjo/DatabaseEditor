package Other;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Method {

    public static boolean IsNum (String str){
        if (str.equals("")) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }
        return true;
    }

    public static String ResultSet_toString(ResultSet rs) {
        return ResultSet_toString(rs, " ", "\n");
    }
    public static String ResultSet_toString(ResultSet rs, String token, String EOF) {
        String strOutput = "";
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int iColumnCount = rsmd.getColumnCount();
            while(rs.next()){
                for (int index = 1; index < iColumnCount; index++){
                    strOutput += rs.getString(index) + token;
                }
                strOutput += rs.getString(iColumnCount);
                if (!rs.isLast()) strOutput += EOF;
            }
        } catch (SQLException e) {
            // 處理 JDBC 錯誤
            e.printStackTrace();
        }
        return strOutput;
    }

    public static String[] ResultSet_toArray(ResultSet rs) {
        return ResultSet_toArray(rs, " ");
    }
    public static String[] ResultSet_toArray(ResultSet rs, String token) {
        String[] aOutput = null;
        try {
            ArrayList<String> listData = new ArrayList<String>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int iColumnCount = rsmd.getColumnCount();

            while (rs.next()) {
                String strData = "";
                for (int index = 1; index < iColumnCount; index++){
                    strData += rs.getString(index) + token;
                }
                strData += rs.getString(iColumnCount);
                listData.add(strData);
            }
            aOutput = listData.toArray(new String[listData.size()]);
        } catch (SQLException e) {
            // 處理 JDBC 錯誤
            e.printStackTrace();
        }
        return aOutput;
    }

    public static String[][] ResultSet_toArray2(ResultSet rs) {
        String[][] aOutput = null;
        try {
            ArrayList<String[]> listData = new ArrayList<String[]>();
            ResultSetMetaData rsmd = rs.getMetaData();
            int iColumnCount = rsmd.getColumnCount();

            while (rs.next()) {
                String[] aData = new String[iColumnCount];
                for (int index = 1; index <= iColumnCount; index++){
                    aData[index - 1] = rs.getString(index);
                }
                listData.add(aData);
            }
            aOutput = listData.toArray(new String[listData.size()][]);
        } catch (SQLException e) {
            // 處理 JDBC 錯誤
            e.printStackTrace();
        }
        return aOutput;
    }

}
