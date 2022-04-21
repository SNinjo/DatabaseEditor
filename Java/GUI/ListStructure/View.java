package GUI.ListStructure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View {

    JFrame frame;
    private Container container;
    private JLabel lbPath;
    private JLabel lblMadeBy;
    private JScrollPane scrollPane;
    JList list;
    private DefaultListModel listModel;
    private JPopupMenu popupMenu;
    JMenuItem mnitUp;
    JMenuItem mnitEdit;
    JMenuItem mnitImport;
    JMenuItem mnitExport;
    JMenuItem mnitDelete;

    View(){
        frame = new JFrame();
        container = frame.getContentPane();
        container.setFont(new Font("Arial", Font.BOLD, 15));
        frame.setTitle("Database Editor");
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(null);

        lbPath = new JLabel("");
        lbPath.setFont(new Font("Arial", Font.BOLD, 14));
        lbPath.setBounds(49, 18, 400, 15);
        container.add(lbPath);

        lblMadeBy = new JLabel("Made by 廖儒均");
        lblMadeBy.setForeground(Color.WHITE);
        lblMadeBy.setFont(new Font("新細明體", Font.BOLD, 14));
        lblMadeBy.setBounds(343, 344, 95, 15);
        container.add(lblMadeBy);

        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setFont(new Font("Arial", Font.PLAIN, 16));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setBounds(49, 43, 389, 276);
        container.add(scrollPane);

        popupMenu = new JPopupMenu();
        popupMenu.setPopupSize(new Dimension(80, 100));
        addPopup(list, popupMenu);

        mnitUp = new JMenuItem("UP");
        mnitUp.setFont(new Font("Arial", Font.PLAIN, 14));
        popupMenu.add(mnitUp);
        mnitUp.setEnabled(false);

        mnitEdit = new JMenuItem("Edit");
        mnitEdit.setFont(new Font("Arial", Font.PLAIN, 14));
        popupMenu.add(mnitEdit);
        mnitEdit.setEnabled(false);

        mnitImport = new JMenuItem("Import");
        mnitImport.setFont(new Font("Arial", Font.PLAIN, 14));
        popupMenu.add(mnitImport);
        mnitImport.setEnabled(false);

        mnitExport = new JMenuItem("Export");
        mnitExport.setFont(new Font("Arial", Font.PLAIN, 14));
        popupMenu.add(mnitExport);
        mnitExport.setEnabled(false);

        mnitDelete = new JMenuItem("Delete");
        mnitDelete.setFont(new Font("Arial", Font.PLAIN, 14));
        popupMenu.add(mnitDelete);
        mnitDelete.setEnabled(false);

        frame.setBounds(620, 100, 500, 420);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
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

    boolean showConfirmDialog(String message) {
        int iOption = JOptionPane.showConfirmDialog(frame, message, "確認", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (iOption == JOptionPane.YES_OPTION) return true;
        return false;
    }

    void list(String[] aDataName){
        listModel.clear();
        for (int index = 0; index < aDataName.length; index++){
            listModel.addElement(aDataName[index]);
        }
    }

    void showPath(String strPath) {
        lbPath.setText(strPath);
    }

    void setEnableForSelectedFile(boolean IsEnable){
        mnitExport.setEnabled(IsEnable);
        mnitDelete.setEnabled(IsEnable);
    }

}
