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
import sample.helpers.sendEmail;

import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerAnouncement implements Initializable {
    @FXML
    private TextArea konten;

    @FXML
    private TextField attachment;

    @FXML
    private Text user;

    @FXML
    private Button home;

    @FXML
    private Button infojob;

    @FXML
    private Button announcement;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField RecipientEmail;

    @FXML
    private TextField SubjectEmail;

    @FXML
    private TextField recipient;

    @FXML
    private Button SendButton;

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
    void lgoutButton(ActionEvent event) {
        help.gantiHalaman(event,"View/loginform.fxml");
    }

    @FXML
    void profilButton(ActionEvent event) {
        help.gantiHalaman(event,"View/PenyediaKerja/profilPenyedia.fxml");
    }


    @FXML
    void aksiBatal(ActionEvent event) {
        kosongin();
    }

    @FXML
    void aksiFile(MouseEvent event) {
        FileChooser attach = new FileChooser();
        Stage stage = new Stage();
        File file = attach.showOpenDialog(stage);
        if (file != null) {
            attachment.setText(file.toString());

        } else {
            help.dialog(Alert.AlertType.INFORMATION,"Error!");
        }
    }


    public void aksiKirim(ActionEvent actionEvent) throws SQLException {
        koneksi k = new koneksi();
        akun a= new akun();
        user.setText(a.getUnLogin());
        PreparedStatement ps1,ps2;
        ResultSet rs1,rs2;
        ps1 =k.connection().prepareStatement("select passwordEmail from akun  where username ='"+user.getText()+"'");
        rs1 = ps1.executeQuery();
        ps2 =k.connection().prepareStatement("select email from akun  where username ='"+user.getText()+"'");
        rs2 = ps2.executeQuery();
        sendEmail kirim = new sendEmail();
        Timestamp dtf = new Timestamp(System.currentTimeMillis());
        if (rs1.next() && rs2.next()) {

            kirim.setEmail(rs2.getString("email"));
            kirim.setPassword(rs1.getString("passwordEmail"));
            System.out.println(kirim.getEmail() + " " + kirim.getPassword());
            String sbjct= SubjectEmail.getText();
            String kntn= konten.getText();
            String recip=RecipientEmail.getText();
            System.out.println(dtf);
            kosongin();
            try {
                if(attachment.getText().equalsIgnoreCase("")){
                    kirim.send(recip,sbjct,kntn);}else {
                    kirim.sendwithattachment(recip,sbjct,kntn,attachment.getText());
                }

            }catch (Exception e){
                help.dialog(Alert.AlertType.ERROR,"'"+e+"'");
            }
        }
        ps1 = k.connection().prepareStatement("insert into logpesan values ('"+kirim.getEmail()+"','"+RecipientEmail.getText()+"','"+SubjectEmail.getText()+"','"+konten.getText()+"','"+dtf+"')");
        ps1.execute();
    }


    private void kosongin(){
        RecipientEmail.setText("");
        attachment.setText("");
        konten.setText("");
        SubjectEmail.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    kosongin();
    }
}
