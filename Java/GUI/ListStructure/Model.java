package GUI.ListStructure;

import Database.Database;
import Other.ArrayMethod;
import Other.IO;
import Other.Method;

import java.sql.SQLException;
import java.util.Locale;

public class Model {

    private Database database;
    String strDatabase = null;
    String strTable = null;
    private String[] aData; // array of database / table

    Model(Database database) {
        this.database = database;
    }

    String[] getListData() throws SQLException {
        String strInput = "SHOW TABLES";
        if (strDatabase == null) strInput = "SHOW DATABASES";
        String[][] a2Data = database.excuteSQL(strInput, true);
        aData = ArrayMethod.reduceDimensionality(a2Data, null);

        // 若 database 或是 table 名稱為全數字則需要用特殊符號框起來
        for (int index = 0; index < aData.length; index++){
            if (Method.IsNum(aData[index])) aData[index] = "`" + aData[index] + "`";
        }
        return aData;
    }

    String getData(int index){
        return aData[index];
    }

    void selectDatabase(String strDatabaseName) throws SQLException {
        strDatabase = strDatabaseName;
        database.excuteSQL("USE " + strDatabaseName);
    }

    void importTable(String strPath) throws SQLException {
        String[][] a2Input = IO.InputAll(strPath);
        // 在Windows下用文本編輯器創建的文本文件，如果選擇以UTF-8等Unicode格式保存，會在文件頭（第一個字符）加入一個BOM標識
        // Excel 則會在表頭(第一列第一行)加入
        a2Input[0][0] = a2Input[0][0].replace("\uFEFF", "");

        String strFileName = strPath.replaceAll("\\\\", "/");
        strFileName = strFileName.substring(strFileName.lastIndexOf("/") + 1, strFileName.indexOf(".")).toLowerCase();
        int index = ArrayMethod.indexOf(aData, strFileName);
        if (index != -1) database.excuteSQL("DROP TABLE " + strFileName);

        String strSQL = "CREATE TABLE " + strFileName + " (";
        for (int column = 0; column < a2Input[0].length; column++){
            strSQL += a2Input[0][column] + " " + a2Input[1][column];
            if (column != (a2Input[0].length - 1)) strSQL += ",";

            // 字串資料需要加入引號
            if (a2Input[1][column].indexOf("char") != -1) {
                for (int row = 2; row < a2Input.length; row++){
                    a2Input[row][column] = "'" + a2Input[row][column] + "'";
                }
            }
        }
        database.excuteSQL(strSQL + ")");

        strSQL = "INSERT INTO " + strFileName + " VALUES (";
        for (int row = 2; row < a2Input.length; row++){
            database.excuteSQL(strSQL + ArrayMethod.toString(a2Input[row], ",") + ")");
        }
    }
    void exportTable(String strPath) throws SQLException {
        String strOutput = "";
        String[][] a2TableInfo = database.getTableInfo(strTable);
        for (int column = 0; column < 2; column++){
            for (int row = 0; row < a2TableInfo.length; row++){
                strOutput += a2TableInfo[row][column];

                // 若是 not null 要輸出 (CREATE TABLE 需要用到)
                if ((column == 1) && (a2TableInfo[row][2].equals("NO"))) strOutput += " not null";

                if (row != (a2TableInfo.length - 1)) strOutput += ",";
            }
            strOutput += "\n";
        }

        String[][] a2Output = database.excuteSQL("SELECT * FROM " + strTable, true);
        strOutput += ArrayMethod.array2_toString(a2Output, ",", "\n");
        IO.OutputAll(strPath + "\\" + strTable + ".csv", strOutput);
    }

    void deteleTable() throws SQLException {
        database.excuteSQL("DROP TABLE " + strTable);
    }

}
