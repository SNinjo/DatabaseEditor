package Other;

import java.util.Arrays;
import java.util.ArrayList;

public class ArrayMethod {

    public static void selectSort(String[] array) {
        // pass by address
        for (int index = 0; index < (array.length - 1); index++) {
            int intMin = getMinIndex(array, index);
            String temp = array[index];
            array[index] = array[intMin];
            array[intMin] = temp;
        }
    }

    private static int getMinIndex(String[] array, int beginIndex) {
        int intMin = beginIndex;
        for (int index = (beginIndex + 1); index < array.length; index++) {
            int valueMin = Integer.parseInt(array[intMin]);
            int valueIndex = Integer.parseInt(array[index]);
            if (valueMin > valueIndex) intMin = index;
        }
        return intMin;
    }

    // 選取一維陣列的開頭部分排序
    public static void selectSort(String[] array, String token) {
        // pass by address
        for (int index = 0; index < (array.length - 1); index++) {
            int intMin = getMinIndex(array, index, token);
            String temp = array[index];
            array[index] = array[intMin];
            array[intMin] = temp;
        }
    }

    private static int getMinIndex(String[] array, int beginIndex, String token) {
        int intMin = beginIndex;
        for (int index = (beginIndex + 1); index < array.length; index++) {
            String strMin = array[intMin];
            String strIndex = array[index];
            int valueMin = Integer.parseInt( strMin.substring(0, strMin.indexOf(token)) );
            int valueIndex = Integer.parseInt( strIndex.substring(0, strIndex.indexOf(token)) );
            if (valueMin > valueIndex) intMin = index;
        }

        return intMin;
    }

    public static void selectSort(String[][] array) {
        // pass by address
        for (int index = 0; index < (array.length - 1); index++) {
            int intMin = getMinIndex(array, index);
            String[] temp = array[index];
            array[index] = array[intMin];
            array[intMin] = temp;
        }
    }

    private static int getMinIndex(String[][] array, int beginIndex) {
        int intMin = beginIndex;
        for (int index = (beginIndex + 1); index < array.length; index++) {
            int valueMin = Integer.parseInt(array[intMin][0]);
            int valueIndex = Integer.parseInt(array[index][0]);
            if (valueMin > valueIndex) intMin = index;
        }

        return intMin;
    }

    public static int find(String[] array, String str) {
        for (int index = 0; index < array.length; index++) {
            if (array[index].equals(str)) return index;
        }
        return -1;
    }

    public static String[] fill(int length, String element) {
        String[] array = new String[length];
        Arrays.fill(array, element);
        return array;
    }

    public static String[][] copy(int length, int elementNumber, String[][] array){
        String[][] newArray = new String[length][elementNumber];
        for (int index = 0; index < length; index++) {
            newArray[index] = Arrays.copyOf(array[index], elementNumber);
        }
        return newArray;
    }

    public static String[] subtraction(String[] array01, String[] array02) {
        // have to sort
        int index01 = 0, index02 = 0;
        ArrayList<String> newArray = new ArrayList<String>();
        for ( ; (index01 < array01.length) && (index02 < array02.length); ) {
            int value01 = Integer.parseInt(array01[index01]);
            int value02 = Integer.parseInt(array02[index02]);
            if (value01 == value02) {
                index01++;
                index02++;
            }
            else if (value01 > value02) {
                newArray.add(value02 + "");
                index02++;
            }
            else {
                newArray.add(value01 + "");
                index01++;
            }
        }

        // array 中剩餘的輸出到 newArray
        for ( ; index01 < array01.length; index01++) {
            newArray.add(array01[index01]);
        }
        for ( ; index02 < array02.length; index02++) {
            newArray.add(array02[index02]);
        }
        String[] outputList = new String[newArray.size()];
        return newArray.toArray(outputList);
    }

    public static <T> String toString(T[] array) {
        return toString(array, " ", false);
    }
    public static <T> String toString(T[] array, String token) {
        return toString(array, token, false);
    }
    public static <T> String toString(T[] array, String token, boolean IsReverse) {
        if (array.length == 0) {
            System.out.println("Warning- 要求輸出的陣列為空(Other.Method.array_toString())");
            return "";
        }

        int intStart = 0;
        int intEnd = array.length - 1;
        int intDifference = 1;
        if (IsReverse) {
            intStart = array.length - 1;
            intEnd = 0;
            intDifference = -1;
        }

        String strOutput = "";
        for (int index = intStart; index != intEnd; index += intDifference) {
            strOutput += array[index] + token;
        }
        strOutput += array[intEnd];
        return strOutput;
    }

    public static <T> String array2_toString(T[][] array, String token, String EOL) {
        String strOutput = "";
        for (int row = 0; row < array.length; row++){
            for (int col = 0; col < (array[row].length - 1); col++) {
                strOutput += array[row][col] + token;
            }
            strOutput += array[row][array[row].length - 1] + EOL;
        }
        return strOutput;
    }

    public static <T> int indexOf(T[] array, T target) {
        for (int index = 0; index < array.length; index++){
            if (array[index].equals(target)) return index;
        }
        return -1;
    }

    public static String[] reduceDimensionality(String[][] data, String token) {
        String[] aOutput = new String[data.length];
        for (int index = 0; index < data.length; index++){
            aOutput[index] = toString(data[index], token);
        }
        return aOutput;
    }

}
