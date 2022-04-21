package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Locale;

public class FileSelector extends JFrame {

    private static String downloadPath = "C:/Users/USER/Downloads";
    private static String DesktopPath = "C:/Users/USER/Desktop";

    public static String excute(JFrame frame, String title, String strOpenPath, String fileType) {
        if (strOpenPath.equals("download")) strOpenPath = downloadPath;
        if (strOpenPath.equals("desktop")) strOpenPath = DesktopPath;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(strOpenPath));
        fileChooser.setDialogTitle(title);

        // 限定檔案格式
        if (fileType != null) {
            FileFilter filter = new FileNameExtensionFilter( (fileType.toUpperCase(Locale.ROOT) + " File") , fileType);
            //fileChooser.addChoosableFileFilter(filter);
            fileChooser.setFileFilter(filter);

            if (fileType.equals("dir")) {
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);
            }
        }

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file.getAbsolutePath();
        }
        else return null;
    }

}