package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Models.akun;
import sample.help;
import sample.helpers.koneksi;

import java.sql.*;

public class ControllerLoginForm {


    @FXML
    private TextField unField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button logButton;

    @FXML
    private Button createAccountButton;

    @FXML
    private Text Judul;

    @FXML
    private Button logButton1;

    @FXML
    private Button logButton2;


    @FXML
    void createAkun(ActionEvent event) throws SQLException {
        help.gantiHalaman(event,"View/create.fxml");
    }

    @FXML
    void loginButtonAdmin(ActionEvent event)  {
        try {
            akun a = new akun();
            a.setUnLogin(unField.getText());
            a.setPassword(passField.getText());
            koneksi k = new koneksi();
            PreparedStatement ps;
            ResultSet rs;
            if (unField.getText().equals("") && passField.getText().equals("")) {
                help.dialog(Alert.AlertType.INFORMATION, "Username dan Password Kosong!!");
            }

            ps = k.connection().prepareStatement("select * from akun  where username ='" + unField.getText() + "' and password ='" + passField.getText() + "'");
            rs = ps.executeQuery();

            while (rs.next()) {
                int statuslogin = Integer.parseInt(rs.getString("status"));
                if (statuslogin == 1) {
                    help.gantiHalaman(event, "View/pelamar/homePelamar.fxml");
                } else if (statuslogin == 2) {
                    help.gantiHalaman(event, "View/PenyediaKerja/HomePenyediaKerja.fxml");
                } else if (statuslogin == 3) {
                    help.gantiHalaman(event, "View/Admin/broadcast.fxml");
                }
            }

        }catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());

        }

    }



}
