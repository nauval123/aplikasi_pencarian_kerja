package sample.Controllers;

import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import sample.help;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Models.akun;
import sample.helpers.koneksi;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerCreate implements Initializable {

    public TextField emailAkun;
    @FXML
    private TextField UsernameCreate;

    @FXML
    private PasswordField PasswordCreate;

    @FXML
    private TextField Status;

    @FXML
    private TextField nomorHandphone;

    @FXML
    private PasswordField PasswordCreate2;

    @FXML
    private Button CreateAccountButton2;

    @FXML
    private Text Judul;

    @FXML
    private Button cancelButton;

    @FXML
    void warning(MouseEvent event) {
        help.dialog(Alert.AlertType.INFORMATION,"Masukkan angka saja ! 1.untuk pelamar , 2.penyedia kerja");
    }

    @FXML
    void cancelButton(ActionEvent event) {
       kosongin();
        help.gantiHalaman(event,"View/loginform.fxml");
    }

    @FXML
    void createButton(ActionEvent event) {
        koneksi k = new koneksi();
        PreparedStatement ps1,ps2,ps3,ps4;
        try{
        if(UsernameCreate.getText().equals("")|| PasswordCreate.equals("") || emailAkun.equals("") || PasswordCreate2.equals("") || Status.equals("") || nomorHandphone.equals("")) {
            help.dialog(Alert.AlertType.INFORMATION,"Harap periksa kembali karena ada yang kosong");
            kosongin();
        }else if (PasswordCreate.getText().length()!=8){
            help.dialog(Alert.AlertType.INFORMATION,"Pasword 8 character!");
            PasswordCreate.setText("");
        }else if(UsernameCreate.getText().length()!=50){
            help.dialog(Alert.AlertType.INFORMATION,"username 50 character!");
            UsernameCreate.setText("");
        }else if(nomorHandphone.getText().length()!=12){
            help.dialog(Alert.AlertType.INFORMATION,"nomorhandphone melebihi 12 digit!");
            nomorHandphone.setText("");
        }
        else {
            akun c = new akun(UsernameCreate.getText(), PasswordCreate.getText(), emailAkun.getText(), PasswordCreate2.getText(), Status.getText(), nomorHandphone.getText());

            ps1 = k.connection().prepareStatement("select * from akun where  password='" + c.getPassAkun() + "' ");
            ResultSet rs1 = ps1.executeQuery();

            ps2 = k.connection().prepareStatement("select * from akun where email='" + c.getEmAkun() + "' ");
            ResultSet rs2 = ps2.executeQuery();

            ps3 = k.connection().prepareStatement("select * from akun where email='" + c.getNmrhpAkun() + "' ");
            ResultSet rs3 = ps3.executeQuery();


            if (rs1.next()) {
                help.dialog(Alert.AlertType.INFORMATION, "Password sudah terpakai!");
                help.dialog(Alert.AlertType.INFORMATION, "data gagal disimpan");
                kosongin();
            } else if (rs2.next()) {
                help.dialog(Alert.AlertType.INFORMATION, "email sudah terpakai!");
                help.dialog(Alert.AlertType.INFORMATION, "data gagal disimpan");
                kosongin();
            } else if (rs3.next()) {
                help.dialog(Alert.AlertType.INFORMATION, "nomor handphone sudah terpakai!");
                help.dialog(Alert.AlertType.INFORMATION, "data gagal disimpan");
                kosongin();
            } else {


                ps4 = k.connection().prepareStatement("insert into akun values('" + c.getUnAkun() + "','" + c.getPassAkun() + "','" + c.getEmAkun() + "','" + c.getPassemAkun() + "','" + c.getStatusAkun() + "','" + c.getNmrhpAkun() + "')");
                ps4.execute();
                help.dialog(Alert.AlertType.INFORMATION, "data telah tersimpan");
            }

        }
                help.gantiHalaman(event, "View/loginform.fxml");
                kosongin();
            }catch (SQLException e){
            help.dialog(Alert.AlertType.INFORMATION,e.getMessage());
        }
        }


    void kosongin(){
        UsernameCreate.setText("");
        PasswordCreate.setText("");
        emailAkun.setText("");
        PasswordCreate2.setText("");
        Status.setText("");
        nomorHandphone.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kosongin();
        help.dialog(Alert.AlertType.INFORMATION,
                "Untuk bisa menggunakan fitur email" +
                        "Open your Google Admin console (admin.google.com).\n" +
                        "Click Security > Basic settings .\n" +
                        "Under Less secure apps, select Go to settings for less secure apps .\n" +
                        "In the subwindow, select the Enforce access to less secure apps for all users radio button. ...\n" +
                        "Click the Save button."+
                        "Terima Kasih ~Dev"
                );
        help.dialog(Alert.AlertType.INFORMATION,"");
    }
}
