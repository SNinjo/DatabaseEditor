package Main;

import GUI.Login;
import Other.ArrayMethod;

public class Main {

    public static void main(String[] args) {
        new Login();
    }

    public static void excute(String[] aConnectInfo) {
        new Controller(aConnectInfo);
    }

}
