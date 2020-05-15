package sample.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class akun {

    private  static String unLogin;
    private  static String passwordLogin;
    private  StringProperty unAkun;
    private  StringProperty passAkun;
    private  StringProperty emAkun;
    private  StringProperty passemAkun;
    private  StringProperty statusAkun;
    private  StringProperty nmrhpAkun;

    public akun(String unAkun, String passAkun, String emAkun, String passemAkun, String statusAkun, String nmrhpAkun) {
        this.unAkun = new SimpleStringProperty(unAkun);
        this.passAkun =new SimpleStringProperty(passAkun);
        this.emAkun = new SimpleStringProperty(emAkun);
        this.passemAkun = new SimpleStringProperty(passemAkun);
        this.statusAkun = new SimpleStringProperty(statusAkun);
        this.nmrhpAkun = new SimpleStringProperty(nmrhpAkun);
    }

    public  akun () {

    }


    public static String getPassword() {
        return passwordLogin;
    }

    public static void setPassword(String password) {
        akun.passwordLogin = password;
    }

    public static String getUnLogin() {
        return unLogin;
    }

    public static void setUnLogin(String unLogin) {
        akun.unLogin = unLogin;
    }

    public String getUnAkun() {
        return unAkun.get();
    }

    public String getPassAkun() {
        return passAkun.get();
    }


    public String getEmAkun() {
        return emAkun.get();
    }


    public String getPassemAkun() {
        return passemAkun.get();
    }


    public String getStatusAkun() {
        return statusAkun.get();
    }

    public String getNmrhpAkun() {
        return nmrhpAkun.get();
    }


}
