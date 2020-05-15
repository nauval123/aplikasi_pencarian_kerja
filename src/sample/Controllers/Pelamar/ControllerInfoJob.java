package sample.Controllers.Pelamar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.Models.Loker;
import sample.help;
import sample.helpers.koneksi;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class ControllerInfoJob implements Initializable {

    @FXML
    private Button home;

    @FXML
    private Button infojob;

    @FXML
    private Button trainingv;

    @FXML
    private Button lamar;

    @FXML
    private Button logout;

    @FXML
    private TextField kolomcari;

    @FXML
    private Button cari;

    @FXML
    private TableView<Loker> tabelPengumumanKerja;

    @FXML
    private TableColumn<Loker, String> judulPekerjaan;

    @FXML
    private TableColumn<Loker, String> namaPerusahaan;

    @FXML
    private TableColumn <Loker,String> profesi;

    @FXML
    private TableColumn<Loker, String> deskripsiPekerjaan;

    @FXML
    private TableColumn <Loker, Timestamp> tanggal;


    @FXML
    private TableView<Loker> tabelPengumumanKerja2;

    @FXML
    private TableColumn<Loker, String> namaPerusahaan2;

    @FXML
    private TableColumn<Loker, String> judulPekerjaan2;

    @FXML
    private TableColumn<Loker, String> profesi2;

    @FXML
    private TableColumn<Loker, String> deskripsiPekerjaan2;

    @FXML
    private TableColumn<Loker, Timestamp> tanggal2;

    @FXML
    private TextField profesiefieldcari;

    private ObservableList<Loker> tblLoker;
    private ObservableList<Loker>tbllokercari;
    private ResultSet rs;
    private PreparedStatement ps;





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
    void propose(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/proposePelamar.fxml");
    }

    @FXML
    void profilButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/profilPelamar.fxml");
    }

    @FXML
    void trainingvButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/VideoPelatih.fxml");
    }



    @FXML
    void pencarian(ActionEvent event) {
        loadDatatabelLokercari(profesiefieldcari.getText());
        showtabelcari();
    }



    private void loadDatatabelLoker() {
        try {
            koneksi k = new koneksi();
            tblLoker = FXCollections.observableArrayList();
            ps =k.connection().prepareStatement("select Nama_Perusahaan,Judul_Lowongan,Profesi,Deskripsi,Tanggal_Upload from lowongan_pekerjaan");
            rs = ps.executeQuery();

            while(rs.next()){
                tblLoker.add(new Loker(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5)));
                break;
            }
        }    catch (SQLException ex){
            System.out.println(ex.getErrorCode());
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
        }
        namaPerusahaan.setCellValueFactory(new PropertyValueFactory<>("namaperusahaan"));
        judulPekerjaan.setCellValueFactory(new PropertyValueFactory<>("judul"));
        profesi.setCellValueFactory(new PropertyValueFactory<>("profesi"));
        deskripsiPekerjaan.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));


        tabelPengumumanKerja.setItems(null);
        tabelPengumumanKerja.setItems(tblLoker);
    }

    private void loadDatatabelLokercari(String cari) {
        try {
            koneksi k = new koneksi();
            tbllokercari = FXCollections.observableArrayList();
            ps =k.connection().prepareStatement("select Nama_Perusahaan,Judul_Lowongan,Profesi,Deskripsi,Tanggal_Upload from lowongan_pekerjaan where  Nama_Perusahaan='"+cari+"' or Judul_Lowongan='"+cari+"' or Profesi='"+cari+"'");
            rs = ps.executeQuery();

            while(rs.next()){
                tbllokercari.add(new Loker(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getTimestamp(5)));
                break;
            }
        }    catch (SQLException ex){
            System.out.println(ex.getErrorCode());
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
        }
        namaPerusahaan2.setCellValueFactory(new PropertyValueFactory<>("namaperusahaan"));
        judulPekerjaan2.setCellValueFactory(new PropertyValueFactory<>("judul"));
        profesi2.setCellValueFactory(new PropertyValueFactory<>("profesi"));
        deskripsiPekerjaan2.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        tanggal2.setCellValueFactory(new PropertyValueFactory<>("tanggal"));


        tabelPengumumanKerja2.setItems(null);
        tabelPengumumanKerja2.setItems(tbllokercari);
    }

    private void showtabel (){
        tabelPengumumanKerja.setOnMouseClicked(e ->{
            sample.Models.Loker  loker = tabelPengumumanKerja.getItems().get(tabelPengumumanKerja.getSelectionModel().getSelectedIndex());
            help.info(Alert.AlertType.CONFIRMATION,loker.getNamaperusahaan(),loker.getJudul(),loker.getProfesi(),loker.getDeskripsi());
            loadDatatabelLoker();

        });

    }
    private void showtabelcari (){
        tabelPengumumanKerja2.setOnMouseClicked(e ->{
            sample.Models.Loker  loker = tabelPengumumanKerja2.getItems().get(tabelPengumumanKerja2.getSelectionModel().getSelectedIndex());
            help.info(Alert.AlertType.CONFIRMATION,loker.getNamaperusahaan(),loker.getJudul(),loker.getProfesi(),loker.getDeskripsi());
            loadDatatabelLoker();

        });

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showtabel();
        loadDatatabelLoker();
    }
}
