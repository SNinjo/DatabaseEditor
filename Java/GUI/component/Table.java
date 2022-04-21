package GUI.component;

import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import Other.ArrayMethod;

public class Table extends JTable {

    private tableModel tableModel;

    public Table(String[] columns, String[][] data) {
        this(columns, ArrayMethod.fill(columns.length, "String"), data, "");
    }

    public Table(String[] columns, String[] classArray, String[][] data, String editModel) {
        tableModel = new tableModel(columns, classArray, data, editModel);
        super.setModel(tableModel);

        // 例外處理(限定輸入範圍)
        if (editModel.equals("Score")) {
            tableModel.addTableModelListener(new TableModelListener() {
                public void tableChanged(TableModelEvent e) {
                    String[][] data = tableModel.getData();
                    int number = Integer.parseInt( data[e.getLastRow()][e.getColumn()] );
                    if (number > 100) data[e.getLastRow()][e.getColumn()] = "100";
                    else if (number < 0) data[e.getLastRow()][e.getColumn()] = "0";
                }
            });
        }
    }

    @Override
    public Class getColumnClass(int column) {
        String[] classArray = this.tableModel.getClassArray();
        switch (classArray[column]) {
            case "Integer":
                return Integer.class;
            case "Boolean":
                return Boolean.class;
            default:
                return String.class;
        }
    }

    public String[][] getTableData() {
        return tableModel.getData();
    }

    public String[] getColumnData(int column) {
        return tableModel.getColumnData(column);
    }

    public void addRow(String[] rowData) {
        int rowCount = tableModel.getRowCount();
        int columnCount = tableModel.getColumnCount();
        String[][] data = tableModel.data;
        String[][] newData = new String[rowCount + 1][columnCount];

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                newData[rowIndex][columnIndex] = data[rowIndex][columnIndex];
            }
        }

        if (rowData != null) {
            newData[rowCount] = rowData;
        }

        tableModel.data = newData;
        tableModel.fireTableRowsInserted(rowCount, rowCount);
    }

    public void deleteRow() {
        int rowCount = tableModel.getRowCount();
        int columnCount = tableModel.getColumnCount();
        String[][] data = tableModel.data;
        String[][] newData = new String[rowCount - 1][columnCount];
        int dataRowIndex = 0;
        int selectedRow = this.getSelectedRow();
        // 若未選擇列則刪除最後一筆
        if (selectedRow == -1) selectedRow = data.length - 1;

        for (int rowIndex = 0; rowIndex < (rowCount - 1); rowIndex++, dataRowIndex++) {
            if (dataRowIndex == selectedRow) dataRowIndex++;
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                newData[rowIndex][columnIndex] = data[dataRowIndex][columnIndex];
            }
        }

        tableModel.data = newData;
        tableModel.fireTableRowsDeleted(selectedRow, newData.length);
    }

    public void arrangeData() {
        ArrayMethod.selectSort(tableModel.getData());
        tableModel.fireTableRowsUpdated(0, tableModel.getRowCount());
    }

    public int getFirstColumn() {
        // 給 Manage 模式使用 (因為第一行不能直接更改)
        int rowIndex = this.getSelectedRow();
        if (rowIndex != -1) {
            return (int)tableModel.getValueAt(rowIndex, 0);
        }
        return -1;
    }

    public boolean setFirstColumn(int value) {
        // 給 Manage 模式使用 (因為第一行不能直接更改)
        int rowIndex = this.getSelectedRow();
        if (rowIndex != -1) {
            tableModel.setValueAt(value, rowIndex, 0);
            return true;
        }
        else return false;
    }


    // override method 用以將元件連接到自定義的資料結構上
    private static class tableModel extends AbstractTableModel {

        private String[] columns;
        private String[] classArray;// 	Table 根據此陣列生成特定的 Cell
        private String[][] data;
        private String editModel;// 意義上有三種編輯模式	default(不能編輯)、Score、Manage、ManageExceptFirst

        public tableModel(String[] columns, String[] classArray, String[][] data, String editModel) {
            this.columns = columns;
            this.classArray = classArray;
            this.data = data;
            this.editModel = editModel;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int col){
            return columns[col];
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // 同階級不能互相編輯
            if (this.getValueAt(row, column).equals("Manager")) return false;


            if (this.editModel.equals("Score")) {
                if (column == 2) return true;
                return false;
            }
            else if (this.editModel.equals("ManageExceptFirst")) {
                if (row == 0) return false;
                if (column == 0) return false;
                else return true;
            }
            else if (this.editModel.equals("Manage")) {
                // 有另外的更改 id 方法
                if (column == 0) return false;
                return true;
            }

            return false;
        }

        @Override
        public Object getValueAt(int row, int column) {
            String Element = data[row][column];
            // 設定 Table 預設值
            switch (this.classArray[column]) {
                case "String":
                    if (Element == null) {
                        data[row][column] = "";
                        return "";
                    }
                    return Element;
                case "Integer":
                    // 預設值 + 欄位為空的情況
                    if ((Element == null) || (Element.equals("null"))) {
                        data[row][column] = "0";
                        return 0;
                    }
                    return Integer.parseInt(Element);
                case "Boolean":
                    if (Element == null) {
                        data[row][column] = "0";
                        return false;
                    }
                    if (Element.equals("1")) return true;
                    else return false;
            }

            return null;
        }

        @Override
        public void setValueAt(Object value, int row, int column) {
            if (value instanceof Boolean) {
                if ((Boolean)value == true) data[row][column] = "1";
                else data[row][column] = "0";
            }
            else data[row][column] = value + "";
            fireTableCellUpdated(row, column);
        }

        String[] getColumnData(int column) {
            int rowCount = getRowCount();
            String[] outputData = new String[rowCount];

            for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
                outputData[rowIndex] = this.data[rowIndex][column];
            }

            return outputData;
        }

        String[] getRowData(int row) {
            return this.data[row];
        }

        String[] getClassArray() {
            return this.classArray;
        }

        String[][] getData(){
            return this.data;
        }

    }

}
