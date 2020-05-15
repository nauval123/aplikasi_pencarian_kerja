package sample.Controllers.Pelamar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import sample.Models.akun;
import sample.help;
import sample.helpers.koneksi;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerProfilpelamar implements Initializable {

    @FXML
    private Text user;

    @FXML
    private Button home;

    @FXML
    private Button infojob;

    @FXML
    private Button trainingv;

    @FXML
    private Button propose;

    @FXML
    private Button logoutButton;

    @FXML
    private Text username;

    @FXML
    private Text email;

    @FXML
    private Text status;

    @FXML
    private Text nmrhp;

    PreparedStatement ps;
    ResultSet rs;

    @FXML
    void homeButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/homePelamar.fxml");
    }

    @FXML
    void infojobButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/infojobPelamar.fxml");
    }

    @FXML
    void lgoutButton(ActionEvent event) {
        help.gantiHalaman(event,"View/loginform.fxml");
    }

    @FXML
    void profilButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/profilPelamar.fxml");
    }

    @FXML
    void proposeButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/proposePelamar.fxml");
    }

    @FXML
    void traningvButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/VideoPelatih.fxml");
    }

    private void loaddata(){
        koneksi k = new koneksi();
        akun a = new akun();
        username.setText(a.getUnLogin());
        try {
            ps = k.connection().prepareStatement("select email,status,nomorHp from akun where username='" + username.getText() + "'");
            rs = ps.executeQuery();
            if(rs.next()){
                email.setText(rs.getString("email"));
                status.setText(rs.getString("status"));
                nmrhp.setText(rs.getString("nomorHp"));
            }
        }catch (SQLException e){
            help.dialog(Alert.AlertType.INFORMATION,e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loaddata();
    }
}
