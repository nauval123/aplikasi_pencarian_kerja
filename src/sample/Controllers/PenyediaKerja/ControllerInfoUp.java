package sample.Controllers.PenyediaKerja;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Models.akun;
import sample.help;
import sample.helpers.koneksi;

import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class ControllerInfoUp implements Initializable {

    @FXML
    private TextField profesi;

    @FXML
    private TextArea deskripsiPekerjaan;


    @FXML
    private TextField judul;

    @FXML
    private Text username;

    @FXML
    private Button home;

    @FXML
    private Button infojob;

    @FXML
    private Button announcement;

    @FXML
    private Button logout;



    @FXML
    void announcementButton(ActionEvent event) {
        help.gantiHalaman(event,"View/PenyediaKerja/announcement.fxml");
    }

    @FXML
    void homeButton(ActionEvent event) {
        help.gantiHalaman(event,"View/PenyediaKerja/HomePenyediaKerja.fxml");
    }

    @FXML
    void infojobButton(ActionEvent event) {
        help.gantiHalaman(event,"View/PenyediaKerja/infoUpPenyediaKerja.fxml");
    }

    @FXML
    void profilButton(ActionEvent event) {
        help.gantiHalaman(event,"View/PenyediaKerja/profilPenyedia.fxml");
    }


    @FXML
    void lgoutButton(ActionEvent event) {
        help.gantiHalaman(event,"View/loginform.fxml");
    }



    @FXML
    private void aksiKirim(ActionEvent actionEvent) {
        koneksi k = new koneksi();
        akun a = new akun();
        username.setText(a.getUnLogin());
        PreparedStatement ps;
        String user = null;
        ResultSet rs;
        try {
            ps=k.connection().prepareStatement("select email from akun where username = '"+username.getText()+"'");
            rs=ps.executeQuery();
            if(rs.next()){
                user=rs.getString("email");
                System.out.println(user);
            }
            Timestamp dtf = new Timestamp(System.currentTimeMillis());
            ps=k.connection().prepareStatement("insert into lowongan_pekerjaan values('"+username.getText()+"','"+judul.getText()+"','"+profesi.getText()+"','"+deskripsiPekerjaan.getText()+"','"+user+"','"+dtf+"')");
            ps.execute();
            help.dialog(Alert.AlertType.INFORMATION,"Sukses TerKirim");
            kosongin();
        } catch (SQLException e) {
            System.out.printf(e.getMessage());
        }

    }

    @FXML
    private void aksiBatal(ActionEvent actionEvent) {
        kosongin();
    }

    private void kosongin (){
        judul.setText("");
        profesi.setText("");
        deskripsiPekerjaan.setText("");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kosongin();
    }


}
