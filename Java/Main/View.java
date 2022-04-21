package Main;

import java.awt.*;

import javax.swing.*;

public class View {

    JFrame frame;
    private Container container;
    private JScrollPane scrollPane;
    private JScrollBar scrollBar;
    private JTextArea taConsole;
    JTextField tfUserInput;
    private JLabel lblMadeBy;

    private JMenuBar menuBar;
    private JMenu mnRetailer;
    JMenuItem mnitStoreArrange;
    JMenuItem mnitProductArrange;
    JMenuItem mnitSpecialQuery;
    JMenuItem mnitAddBilling;
    JMenuItem mnitUpdateInventory;
    JMenuItem mnitFrequentData;
    private JMenu mnDatabase;
    JMenuItem mnitListStructure;
    private JMenu mnAccount;
    private JMenuItem mnitManageUser;
    private JMenu mnSystem;
    JMenuItem mnitDisconnect;
    JMenuItem mnitExit;


    View(){
        frame = new JFrame();
        container = frame.getContentPane();
        container.setFont(new Font("Arial", Font.BOLD, 15));
        frame.setTitle("Database Editor");
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        // Other.Console 加上卷軸
        taConsole = new JTextArea();
        taConsole.setEditable(false);
        taConsole.setBounds(0, 0, 411, 251);
        taConsole.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 16));
        scrollPane = new JScrollPane(taConsole);
        scrollPane.setBounds(37, 23, 411, 251);
        container.add(scrollPane);
        scrollBar = scrollPane.getVerticalScrollBar();

        tfUserInput = new JTextField();
        tfUserInput.setBounds(37, 290, 411, 27);
        tfUserInput.setFont(new Font("Arial", Font.PLAIN, 14));
        container.add(tfUserInput);
        tfUserInput.setColumns(10);

        lblMadeBy = new JLabel("Made by 廖儒均");
        lblMadeBy.setForeground(Color.WHITE);
        lblMadeBy.setFont(new Font("新細明體", Font.BOLD, 14));
        lblMadeBy.setBounds(354, 329, 95, 15);
        container.add(lblMadeBy);

        frame.setBounds(100, 100, 500, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

//        mnRetailer = new JMenu("量販店");
//        mnRetailer.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
//        menuBar.add(mnRetailer);
//
//        mnitStoreArrange = new JMenuItem("店家排名");
//        mnitStoreArrange.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
//        mnRetailer.add(mnitStoreArrange);
//
//        mnitProductArrange = new JMenuItem("商品排名");
//        mnitProductArrange.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
//        mnRetailer.add(mnitProductArrange);
//
//        mnitSpecialQuery = new JMenuItem("特殊查詢(可樂銷路比較)");
//        mnitSpecialQuery.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
//        mnRetailer.add(mnitSpecialQuery);
//
//        mnitAddBilling = new JMenuItem("新增帳單");
//        mnitAddBilling.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
//        mnRetailer.add(mnitAddBilling);
//
//        mnitUpdateInventory = new JMenuItem("更新庫存");
//        mnitUpdateInventory.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
//        mnRetailer.add(mnitUpdateInventory);
//
//        mnitFrequentData = new JMenuItem("常客資訊");
//        mnitFrequentData.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
//        mnRetailer.add(mnitFrequentData);

        mnDatabase = new JMenu("Database");
        mnDatabase.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        menuBar.add(mnDatabase);

        mnitListStructure = new JMenuItem("List Structure");
        mnitListStructure.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        mnDatabase.add(mnitListStructure);

        mnAccount = new JMenu("Account");
        mnAccount.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        menuBar.add(mnAccount);

        mnitManageUser = new JMenuItem("Manage User");
        mnitManageUser.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        mnAccount.add(mnitManageUser);
        mnitManageUser.setEnabled(false);

        mnSystem = new JMenu("System");
        mnSystem.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
        menuBar.add(mnSystem);

        mnitDisconnect = new JMenuItem("Disconnect");
        mnSystem.add(mnitDisconnect);
        mnitDisconnect.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));

        mnitExit = new JMenuItem("Exit");
        mnitExit.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
        mnSystem.add(mnitExit);
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

    void printToConsole(String strOutput) {
        taConsole.append(strOutput + "\n");
        scrollBar.setValue(scrollBar.getMaximum());
    }

}
