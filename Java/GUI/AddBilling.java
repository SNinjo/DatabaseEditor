package GUI;

import Database.Database;
import Other.ArrayMethod;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

public class AddBilling {
    public AddBilling(Database database){
        new Controller(new Model(database), new View());
    }

    private class Controller {
        private Model model;
        private View view;

        Controller(Model m, View v){
            this.model = m;
            this.view = v;

            view.button.addActionListener(e -> {
                String[] aInfo = new String[] {view.tfStoreId.getText(),
                        view.tfConsumerId.getText(), view.tfProductId.getText(),
                        view.tfQunantity.getText(), view.tfDate.getText()};
                try {
                    model.addBilling(aInfo);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                view.showPlainDialog("更新完成");
                view.frame.dispose();
            });
        }
    }

    private class Model {
        Database database;

        Model(Database database){
            this.database = database;
        }

        void addBilling(String[] aInfo) throws SQLException {
            database.excuteSQL("USE `410877016`");
            String strSQL = "INSERT INTO billing VALUES (" + aInfo[0] + "," + aInfo[1] + "," + aInfo[2] + "," + aInfo[3] + ",'" + aInfo[4] + "')";
            database.excuteSQL(strSQL);

            // Trigger
            String price = database.excuteSQL("SELECT price FROM product WHERE p_id = " + aInfo[2], true)[0][0];
            String originalRevenue = database.excuteSQL("SELECT revenue FROM store WHERE s_id = " + aInfo[0], true)[0][0];
            database.excuteSQL("UPDATE store SET revenue = " + (Integer.parseInt(originalRevenue) + (Integer.parseInt(price) * Integer.parseInt(aInfo[3]))) + " WHERE s_id = " + aInfo[0]);

            System.out.println(strSQL);//
            System.out.println("SELECT price FROM product WHERE p_id = " + aInfo[2]);//
            System.out.println("SELECT revenue FROM store WHERE s_id = " + aInfo[0]);//
            System.out.println("UPDATE store SET revenue = " + (Integer.parseInt(originalRevenue) + (Integer.parseInt(price) * Integer.parseInt(aInfo[3]))) + " WHERE s_id = " + aInfo[0]);//
        }
    }

    private class View {
        private JFrame frame;
        private Container container;
        private JTextField tfStoreId;
        private JTextField tfConsumerId;
        private JTextField tfProductId;
        private JTextField tfQunantity;
        private JTextField tfDate;
        private JButton button;

        View(){
            frame = new JFrame();
            frame.getContentPane().setFont(new Font("Arial", Font.BOLD, 15));
            frame.setTitle("Database Editor");
            frame.getContentPane().setBackground(Color.LIGHT_GRAY);
            frame.getContentPane().setLayout(null);

            tfStoreId = new JTextField();
            tfStoreId.setBounds(49, 68, 96, 21);
            frame.getContentPane().add(tfStoreId);
            tfStoreId.setColumns(10);

            tfConsumerId = new JTextField();
            tfConsumerId.setBounds(197, 68, 96, 21);
            frame.getContentPane().add(tfConsumerId);
            tfConsumerId.setColumns(10);

            tfProductId = new JTextField();
            tfProductId.setBounds(340, 68, 96, 21);
            frame.getContentPane().add(tfProductId);
            tfProductId.setColumns(10);

            tfQunantity = new JTextField();
            tfQunantity.setBounds(49, 175, 96, 21);
            frame.getContentPane().add(tfQunantity);
            tfQunantity.setColumns(10);

            tfDate = new JTextField();
            tfDate.setBounds(197, 175, 96, 21);
            frame.getContentPane().add(tfDate);
            tfDate.setColumns(10);

            JLabel lblid = new JLabel("商家ID");
            lblid.setBounds(75, 43, 46, 15);
            frame.getContentPane().add(lblid);

            JLabel lblid_1 = new JLabel("顧客ID");
            lblid_1.setBounds(222, 43, 46, 15);
            frame.getContentPane().add(lblid_1);

            JLabel lblid_2 = new JLabel("商品ID");
            lblid_2.setBounds(366, 43, 46, 15);
            frame.getContentPane().add(lblid_2);

            JLabel label = new JLabel("數量");
            label.setBounds(86, 150, 46, 15);
            frame.getContentPane().add(label);

            JLabel label_1 = new JLabel("日期");
            label_1.setBounds(233, 150, 46, 15);
            frame.getContentPane().add(label_1);

            button = new JButton("確定");
            button.setBounds(340, 174, 96, 23);
            frame.getContentPane().add(button);

            frame.setBounds(100, 100, 500, 300);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.setResizable(false);
        }

        void showErrorDialog(String message) {
            showDialog(message, 0);
        }
        void showPlainDialog(String message) {
            showDialog(message, -1);
        }
        private void showDialog(String message, int type) {
            JOptionPane.showMessageDialog(frame, message, "Database Editor", type);
        }

    }

}
