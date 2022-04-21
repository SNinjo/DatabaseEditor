package Other;

import java.io.*;
import java.util.ArrayList;

public class IO {
    // 以 CSV 格式輸入/輸出到 .txt
    private static String defaultFileType = ".csv";

    public static String[][] InputAll(String filePath) {
        ArrayList<String[]> listOutput = new ArrayList<String[]>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while (br.ready()) {
                String strInput = br.readLine();
                listOutput.add(strInput.split(","));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (listOutput.size() == 0) return null;
        String[][] aOutput = listOutput.toArray(new String[listOutput.size()][]);
        return aOutput;
    }

    public static void OutputAll(String filePath, String strOutput) {
        try {
            int iCopyNumber = 1;
            String originalFilePath = filePath.substring(0, filePath.indexOf("."));
            String originalFileType = filePath.substring(filePath.indexOf("."));
            while (IsExist(filePath)){
                filePath = originalFilePath + "(" + (iCopyNumber++) + ")" + originalFileType;
            }
            FileWriter fw = new FileWriter(filePath);
            fw.write(strOutput);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean IsExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

}
