package sample.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import sample.Controllers.ControllerLoginForm;
import sample.Models.akun;
import sample.help;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML
    private Text username;

    @FXML
    private Button home;

    @FXML
    private Button register;

    @FXML
    private Button uploadv;

    @FXML
    private Button broadcast;

    @FXML
    private Button logout;

    @FXML
    void broadcastButton(ActionEvent event) {

        help.gantiHalaman(event,"View/Admin/broadcast.fxml");
    }

    @FXML
    void homeButton(ActionEvent event) {
        help.gantiHalaman(event,"View/Admin/dashboard.fxml");
    }

    @FXML
    void lgoutButton(ActionEvent event) {
        help.gantiHalaman(event,"View/loginform.fxml");
    }

    @FXML
    void registerButton(ActionEvent event) {
        help.gantiHalaman(event,"View/Admin/register data.fxml");
    }



    @FXML
    void uploadvButton(ActionEvent event) {
        help.gantiHalaman(event,"View/Admin/upVideo.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        akun a = new akun();
        username.setText(a.getUnLogin());
    }
}
