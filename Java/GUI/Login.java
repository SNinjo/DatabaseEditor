package GUI;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Login {

    public Login(){
        new Controller(new Model(), new View());
    }

    private class Controller {
        private Model model;
        private View view;

        Controller(Model m, View v){
            this.model = m;
            this.view = v;

            // Button 事件
            view.btnLogin.addActionListener(e -> login());

            // Enter 事件
            view.tfIP.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e){
                    if (e.getKeyCode() == 10) login();
                }
            });
            view.tfAccount.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e){
                    if (e.getKeyCode() == 10) login();
                }
            });
            view.tfPassword.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e){
                    if (e.getKeyCode() == 10) login();
                }
            });

            // focus GUI
            view.frame.addWindowListener( new WindowAdapter() {
                public void windowOpened(WindowEvent e){
                    JTextField tf = view.tfPassword;
                    tf.requestFocus();
                    tf.setCaretPosition(tf.getText().length());
                }
            });
        }

        void login() {
            model.IP = view.tfIP.getText();
            model.Account = view.tfAccount.getText();
            model.Password = view.tfPassword.getText();
            view.frame.dispose();
            Main.Main.excute(model.getConnectInfo());
        }
    }

    private class Model {
        private String IP;
        private String Account;
        private String Password;

        Model(){}

        private String[] getConnectInfo() {
            return new String[] {IP, Account, Password};
        }
    }

    private class View {
        private JFrame frame;
        private Container container;
        private JLabel lblIp;
        private JLabel lblAccount;
        private JLabel lblPassword;
        private JTextField tfIP;
        private JTextField tfAccount;
        private JTextField tfPassword;
        private JButton btnLogin;

        View(){
            frame = new JFrame();
            container = frame.getContentPane();
            container.setFont(new Font("Arial", Font.BOLD, 15));
            frame.setTitle("Login");
            container.setBackground(Color.LIGHT_GRAY);
            container.setLayout(null);

            lblIp = new JLabel("IP");
            lblIp.setFont(new Font("Arial", Font.BOLD, 15));
            lblIp.setBounds(82, 35, 79, 20);
            container.add(lblIp);

            lblAccount = new JLabel("Account");
            lblAccount.setFont(new Font("Arial", Font.BOLD, 15));
            lblAccount.setBounds(82, 65, 79, 20);
            container.add(lblAccount);

            lblPassword = new JLabel("Password");
            lblPassword.setFont(new Font("Arial", Font.BOLD, 15));
            lblPassword.setBounds(82, 95, 79, 21);
            container.add(lblPassword);

            tfIP = new JTextField("default");
            tfIP.setBounds(171, 35, 225, 21);
            container.add(tfIP);
            tfIP.setColumns(10);

            tfAccount = new JTextField("root");
            tfAccount.setBounds(171, 65, 225, 21);
            container.add(tfAccount);
            tfAccount.setColumns(10);

            tfPassword = new JTextField("");
            tfPassword.setBounds(171, 96, 225, 21);
            container.add(tfPassword);
            tfPassword.setColumns(10);

            btnLogin = new JButton("Login");
            btnLogin.setFont(new Font("Arial", Font.PLAIN, 14));
            btnLogin.setBounds(197, 138, 87, 23);
            container.add(btnLogin);

            frame.setBounds(500, 300, 500, 225);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setResizable(false);
        }
    }

}
