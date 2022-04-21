package GUI;

import Database.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UpdateInventory {
    public UpdateInventory(Database database){
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
                        view.tfProductId.getText(), view.tfQunantity.getText()};
                try {
                    model.updateInventory(aInfo);
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

        void updateInventory(String[] aInfo) throws SQLException {
            database.excuteSQL("USE `410877016`");
            String strSQL = "UPDATE stock SET quantity=" + aInfo[2] + " WHERE s_id=" + aInfo[0] + " AND p_id=" + aInfo[1];
            database.excuteSQL(strSQL);
        }
    }

    private class View {
        private JFrame frame;
        private Container container;
        private JTextField tfStoreId;
        private JTextField tfProductId;
        private JTextField tfQunantity;
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

            tfProductId = new JTextField();
            tfProductId.setBounds(197, 68, 96, 21);
            frame.getContentPane().add(tfProductId);
            tfProductId.setColumns(10);

            tfQunantity = new JTextField();
            tfQunantity.setBounds(340, 68, 96, 21);
            frame.getContentPane().add(tfQunantity);
            tfQunantity.setColumns(10);

            JLabel lblid = new JLabel("商家ID");
            lblid.setBounds(75, 43, 46, 15);
            frame.getContentPane().add(lblid);

            JLabel lblid_1 = new JLabel("商品ID");
            lblid_1.setBounds(222, 43, 46, 15);
            frame.getContentPane().add(lblid_1);

            JLabel lblid_2 = new JLabel("數量");
            lblid_2.setBounds(366, 43, 46, 15);
            frame.getContentPane().add(lblid_2);

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
