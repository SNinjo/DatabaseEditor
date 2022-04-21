package Main;

import GUI.ListStructure.ListStructure;
import GUI.Login;
import Other.ArrayMethod;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    private Model model;
    private View view;

    Controller(String[] aConnectInfo) {
        this.view = new View();
        try {
            this.model = new Model(aConnectInfo);
        } catch (ClassNotFoundException e) {
            view.showErrorDialog("找不到驅動程式類別 (Class.forName 錯誤)");
            view.frame.dispose();
            new Login();
        } catch (SQLException e) {
            view.showErrorDialog("資料庫尚未開啟、設定錯誤 (JDBC 錯誤)");
            e.printStackTrace();
            view.frame.dispose();
            new Login();
        }

        // Button 事件
//        // Start 功課必須功能
//        view.mnitStoreArrange.addActionListener(e -> {
//            view.tfUserInput.setText("USE `410877016`");
//            excuteSQL();
//            view.tfUserInput.setText("SELECT s_id, name FROM store ORDER BY revenue DESC");
//            excuteSQL();
//            view.showPlainDialog("已列出商家今年業績排名 (從高到低)");
//        });
//        view.mnitProductArrange.addActionListener(e -> {
//            view.tfUserInput.setText("USE `410877016`");
//            excuteSQL();
//            view.tfUserInput.setText("SELECT type, sum(quantity) AS total FROM product, billing WHERE product.p_id = billing.p_id GROUP BY type ORDER BY total DESC");
//            excuteSQL();
//            view.showPlainDialog("已列出商品種類排名 (從高到低)");
//        });
//        view.mnitSpecialQuery.addActionListener(e -> {
//            try {
//                view.tfUserInput.setText("USE `410877016`");
//                excuteSQL();
//                String[][] a2Data = model.database.excuteSQL("SELECT s_id, p_id, quantity FROM billing WHERE p_id = 1 OR p_id = 2", true);
//                int iMaxId = Integer.parseInt(model.database.excuteSQL("SELECT max(s_id) FROM billing", true)[0][0]);
//                int[][] iRecorder = new int[iMaxId][2];
//                for (int index = 0; index < a2Data.length; index++){
//                    int recorderIndex = Integer.parseInt(a2Data[index][0]) - 1;
//                    if (a2Data[index][1].equals("1")) iRecorder[recorderIndex][0] += Integer.parseInt(a2Data[index][2]);
//                    else iRecorder[recorderIndex][1] += Integer.parseInt(a2Data[index][2]);
//                }
//                ArrayList<String> listId = new ArrayList<String>();
//                for (int index = 0; index < iMaxId; index++){
//                    if (iRecorder[index][0] > iRecorder[index][1]) listId.add(index + 1 + "");
//                }
//                String strOutput = ArrayMethod.toString(listId.toArray(new String[listId.size()]), "\n");
//                view.printToConsole("count(s_id)\n ------ \n " + listId.size() + "\n");
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            view.showPlainDialog("已列出可口可樂賣的比百事可樂好的商家數");
//        });
//        view.mnitAddBilling.addActionListener(e -> {
//            new GUI.AddBilling(model.database);
//        });
//        view.mnitUpdateInventory.addActionListener(e -> {
//            new GUI.UpdateInventory(model.database);
//        });
//        view.mnitFrequentData.addActionListener(e -> {
//            view.tfUserInput.setText("USE `410877016`");
//            excuteSQL();
//            view.tfUserInput.setText("SELECT c_id, count(c_id) as time FROM billing GROUP BY c_id ORDER BY time DESC");
//            excuteSQL();
//            view.showPlainDialog("已列出顧客光顧排名 (從高到低)");
//        });
//        // End

        view.mnitListStructure.addActionListener(e -> new ListStructure(model.database));
        view.mnitDisconnect.addActionListener(e -> {
            view.frame.dispose();
            new Login();
        });
        view.mnitExit.addActionListener(e -> view.frame.dispose());

        // Enter 事件
        view.tfUserInput.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == 10) excuteSQL();
            }
        });

        // focus GUI
        view.frame.addWindowListener( new WindowAdapter() {
            public void windowOpened(WindowEvent e){
                view.tfUserInput.requestFocus();
            }
        });
    }

    void excuteSQL() {
        try {
            String strOutput = model.excuteSQL(view.tfUserInput.getText());
            view.printToConsole(strOutput);
            view.tfUserInput.setText("");
        } catch (SQLException e) {
            view.showErrorDialog("SQL 語法/邏輯錯誤");
            e.printStackTrace();
        }
    }

}
