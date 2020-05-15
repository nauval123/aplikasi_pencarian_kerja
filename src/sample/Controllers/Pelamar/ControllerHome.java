package sample.Controllers.Pelamar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.Controllers.ControllerLoginForm;
import sample.Models.Loker;
import sample.Models.akun;
import sample.Models.logpesan;
import sample.help;
import sample.helpers.koneksi;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {



    @FXML
    private Text user;


    @FXML
    private Button home;

    @FXML
    private Button infojob;

    @FXML
    private HBox trainingvideos;

    @FXML
    private Button trainingv;

    @FXML
    private Button propose;

    @FXML
    private Button logout;

    @FXML
    private TableView<sample.Models.logpesan> tabelNotifikasi;

    @FXML
    private TableColumn<sample.Models.logpesan, String> from;

    @FXML
    private TableColumn<sample.Models.logpesan, String> subject;

    @FXML
    private TableColumn<sample.Models.logpesan, String> konten;

    @FXML
    private TableColumn<sample.Models.logpesan, Timestamp> Tanggal;

    private ObservableList<sample.Models.logpesan>tblpesan;
    private ResultSet rs;
    private PreparedStatement ps;




    @FXML
    void homeButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/homePelamar.fxml");
    }

    @FXML
    void Propose(ActionEvent event)  {
        help.gantiHalaman(event,"View/pelamar/proposePelamar.fxml");
    }

    @FXML
    void infojobButton(ActionEvent event) {

        help.gantiHalaman(event,"View/pelamar/infojobPelamar.fxml");
    }

    @FXML
    void profilButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/profilPelamar.fxml");
    }


    @FXML
    void lgoutButton(ActionEvent event) {

        help.gantiHalaman(event,"View/loginform.fxml");
    }

    @FXML
    void trainingvButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/VideoPelatih.fxml");
    }

    private void loadDatatabelpesan() {
        try {
            koneksi k = new koneksi();
            tblpesan = FXCollections.observableArrayList();
            akun a= new akun();
            String user = a.getUnLogin();
            String emailUser;

            ps=k.connection().prepareStatement("select email from akun where username='"+user+"'");
            rs = ps.executeQuery();
            if (rs.next()) {
                emailUser = rs.getString("email");
                System.out.println(emailUser);
                ps = k.connection().prepareStatement("select FromEmail,subject,konten,waktu from logpesan where toEmail='" + emailUser+"' ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tblpesan.add(new logpesan(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4)));
                }
            }
        }    catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
        }
        from.setCellValueFactory(new PropertyValueFactory<>("pengirim"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        konten.setCellValueFactory(new PropertyValueFactory<>("konten"));
        Tanggal.setCellValueFactory(new PropertyValueFactory<>("Tanggal"));
        tabelNotifikasi.setItems(null);
        tabelNotifikasi.setItems(tblpesan);
    }

    private  void showpesan(){
        tabelNotifikasi.setOnMouseClicked(e ->{
            sample.Models.logpesan pesan = tabelNotifikasi.getItems().get(tabelNotifikasi.getSelectionModel().getSelectedIndex());
            help.pesan(Alert.AlertType.CONFIRMATION,pesan.getPengirim(),pesan.getSubject(),pesan.getKonten());
            loadDatatabelpesan();

        });

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            loadDatatabelpesan();
            showpesan();
    }
}
