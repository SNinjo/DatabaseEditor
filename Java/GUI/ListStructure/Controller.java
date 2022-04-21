package GUI.ListStructure;

import GUI.FileSelector;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Controller {

    private Model model;
    private View view;

    Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        refreshList();

        // Button 事件
        view.mnitUp.addActionListener(e -> exitDatabase());
        view.mnitImport.addActionListener(e -> importTable());
        view.mnitExport.addActionListener(e -> exportTable());
        view.mnitDelete.addActionListener(e -> deleteTable());

        // Mouse Click 事件
        view.list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                int index = list.getSelectedIndex();
                if (index == -1) return;
                String strDataName = model.getData(index);

                if (evt.getClickCount() == 2) {
                    if (model.strDatabase == null) enterDatabase(strDataName);
                }
                else if (evt.getClickCount() == 1) {
                    if (model.strDatabase != null) {
                        model.strTable = strDataName;
                        view.setEnableForSelectedFile(true);
                    }
                }
            }
        });
    }

    void refreshList() {
        try {
            view.list(model.getListData());
        } catch (SQLException e) {
            view.showErrorDialog("SQL 語法/邏輯錯誤");
            e.printStackTrace();
        }
    }

    void enterDatabase(String strDatabaseName) {
        try {
            model.selectDatabase(strDatabaseName);
        } catch (SQLException e) {
            view.showErrorDialog("SQL 語法/邏輯錯誤");
            e.printStackTrace();
        }
        refreshList();
        view.showPath(model.strDatabase);
        view.mnitUp.setEnabled(true);
        view.mnitImport.setEnabled(true);
    }
    void exitDatabase(){
        // MySQL 不需退出資料庫，系統邏輯上退出
        model.strDatabase = null;
        refreshList();
        view.showPath("");
        view.mnitUp.setEnabled(false);
        view.mnitImport.setEnabled(false);
        view.setEnableForSelectedFile(false);
    }

    void importTable() {
        try {
            String strPath = FileSelector.excute(view.frame, "選擇上傳檔案 (只接受 CSV)", "desktop", "csv");
            if (strPath == null) return;
            model.importTable(strPath);
            refreshList();
            view.showPlainDialog("上傳成功");
        } catch (SQLException e) {
            view.showErrorDialog("SQL 語法/邏輯錯誤");
            e.printStackTrace();
        }
    }
    void exportTable() {
        try {
            String strPath = FileSelector.excute(view.frame, "選擇下載位置", "download", "dir");
            model.exportTable(strPath);
            refreshList();
            view.showPlainDialog(model.strTable + " 已下載至 " + strPath);
        } catch (SQLException e) {
            view.showErrorDialog("SQL 語法/邏輯錯誤");
            e.printStackTrace();
        }
    }

    void deleteTable() {
        try {
            if (!view.showConfirmDialog("刪除後將無法恢復，確定刪除嗎?")) return;
            model.deteleTable();
            refreshList();
            view.setEnableForSelectedFile(false);
        } catch (SQLException e) {
            view.showErrorDialog("SQL 語法/邏輯錯誤");
            e.printStackTrace();
        }
    }

}
