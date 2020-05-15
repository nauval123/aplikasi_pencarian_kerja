package sample.Controllers.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import sample.Models.akun;
import sample.Models.logpesan;
import sample.help;
import sample.helpers.koneksi;

import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ControllerRegister implements Initializable {



    
    @FXML
    private TextField passEmail;
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
    private TableView<sample.Models.akun> tabelAkun;

    @FXML
    private TableColumn<sample.Models.akun, String> username;

    @FXML
    private TableColumn<sample.Models.akun, String> password;

    @FXML
    private TableColumn<sample.Models.akun, String> email;

        @FXML
    private TableColumn<sample.Models.akun, String> nmr_hp;

    @FXML
    private TableColumn<sample.Models.akun, String> status1;

    @FXML
    private TableColumn <sample.Models.akun,String>passEmailkolom;
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

    @FXML
    private TextField usernamefield;

    @FXML
    private TextField passwordfield;

    @FXML
    private TextField emailfield;

    @FXML
    private TextField passEmailfield;

    @FXML
    private TextField statusfield;

    @FXML
    private TextField nomorhandphonefield;

    @FXML
    private Button ubah;

    @FXML
    private Button hapus;

    @FXML
    private Button tambah;

    @FXML
    private Button cari;

    @FXML
    private TextField pencarian;

    //ObservableList untuk menyimpan data dari model
    private ObservableList<sample.Models.akun> tblakun;
    private ObservableList<sample.Models.logpesan>tblpesan;

    private ResultSet rs;
    private PreparedStatement ps;


    @FXML
    void broadcastButton(ActionEvent event) {
        help.gantiHalaman(event,"View/Admin/broadcast.fxml");
    }

    @FXML
    void homeButton(ActionEvent event) {
        help.gantiHalaman(event,"View/admin/dashboard.fxml");
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

    private void loadDatatabelakun() {
        try {
            koneksi k = new koneksi();
            tblakun = FXCollections.observableArrayList();
            ps =k.connection().prepareStatement("select * from akun");
            rs = ps.executeQuery();

            while(rs.next()){
                tblakun.add(new akun(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));

            }
        }    catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
        }
        username.setCellValueFactory(new PropertyValueFactory<>("unAkun"));
        password.setCellValueFactory(new PropertyValueFactory<>("passAkun"));
        email.setCellValueFactory(new PropertyValueFactory<>("emAkun"));
        passEmailkolom.setCellValueFactory(new PropertyValueFactory<>("passemAkun"));
        status1.setCellValueFactory(new PropertyValueFactory<>("statusAkun"));
        nmr_hp.setCellValueFactory(new PropertyValueFactory<>("nmrhpAkun"));

        tabelAkun.setItems(null);
        tabelAkun.setItems(tblakun);
    }

    private void loadDatatabelpesan() {
        try {
            koneksi k = new koneksi();
            tblpesan = FXCollections.observableArrayList();
            ps =k.connection().prepareStatement("select FromEmail,subject,konten,waktu from logpesan");
            rs = ps.executeQuery();

            while(rs.next()){
                tblpesan.add(new logpesan(rs.getString(1),rs.getString(2),rs.getString(3),rs.getTimestamp(4)));
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

    private void show(){
        tabelAkun.setOnMouseClicked(e ->{
            sample.Models.akun  akun = tabelAkun.getItems().get(tabelAkun.getSelectionModel().getSelectedIndex());
            usernamefield.setText(akun.getUnAkun());
            passwordfield.setText(akun.getPassAkun());
            emailfield.setText(akun.getEmAkun());
            passEmailfield.setText(akun.getPassemAkun());
            statusfield.setText(akun.getStatusAkun());
            nomorhandphonefield.setText(akun.getNmrhpAkun());
        });

        tabelNotifikasi.setOnMouseClicked(e ->{
            sample.Models.logpesan  pesan = tabelNotifikasi.getItems().get(tabelNotifikasi.getSelectionModel().getSelectedIndex());
            help.pesan(Alert.AlertType.INFORMATION,pesan.getPengirim(),pesan.getSubject(),pesan.getKonten());
        });


        tambah.setDisable(false);
        ubah.setDisable(false);
        hapus.setDisable(false);
    }

    public void ubahAkun(ActionEvent actionEvent) {
        koneksi k = new koneksi();
        try {
            String u1 = usernamefield.getText();
            String u2 = passwordfield.getText();
            String u3 = emailfield.getText();
            String u4 = passEmailfield.getText();
            String u5 = statusfield.getText();
            String u6 = nomorhandphonefield.getText();

            ps =k.connection().prepareStatement("insert into akun (username,password,email,passwordEmail,status,nomorHp) values('"+u1+"','"+u2+"','"+u3+"','"+u4+"','"+u5+"','"+u6+"') on duplicate key update username='"+u1+"', password = '"+u2+"', passwordEmail='"+u4+"', status='"+u5+"', nomorHp='"+u6+"'" );
            ps.execute();
            help.dialog(Alert.AlertType.INFORMATION,"data berhasil ditambahkan!");
            loadDatatabelakun();
            kosongin();
        }catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
            help.dialog(Alert.AlertType.INFORMATION,"GAGAL DISIMPAN");
        }

    }

    public void hapusAkun(ActionEvent actionEvent) {
        koneksi k = new koneksi();
        try {
            String u1 = usernamefield.getText();
            String u3 = emailfield.getText();

            ps =k.connection().prepareStatement("delete akun  where  username='"+u1+"',email='"+u3+"'");
            ps.executeUpdate();
            loadDatatabelakun();
            help.dialog(Alert.AlertType.INFORMATION,"data berhasil di ubah!");
        }catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
        }
    }

    public void tambahAkun(ActionEvent actionEvent) {
        koneksi k = new koneksi();
        try {
            String u1 = usernamefield.getText();
            String u2 = passwordfield.getText();
            String u3 = emailfield.getText();
            String u4 = passEmailfield.getText();
            String u5 = statusfield.getText();
            String u6 = nomorhandphonefield.getText();

            ps =k.connection().prepareStatement("insert into akun (username,password,email,passwordEmail,status,nomorHp) values('"+u1+"','"+u2+"','"+u3+"','"+u4+"','"+u5+"','"+u6+"') on duplicate key update username='"+u1+"', password = '"+u2+"', passwordEmail='"+u4+"', status='"+u5+"', nomorHp='"+u6+"'" );
            ps.execute();
            help.dialog(Alert.AlertType.INFORMATION,"data berhasil ditambahkan!");
            loadDatatabelakun();
            kosongin();
        }catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
            help.dialog(Alert.AlertType.INFORMATION,"GAGAL DISIMPAN");
        }

    }

    private void tomboloff(){
        tambah.setDisable(true);
        ubah.setDisable(true);
        hapus.setDisable(true);

    }

    private void kosongin(){
        usernamefield.setText("");
        passwordfield.setText("");
        emailfield.setText("");
        passEmailfield.setText("");
        statusfield.setText("");
        nomorhandphonefield.setText("");
    }

    @FXML
    void aksiCari(ActionEvent event) {

        koneksi k = new koneksi();
        try{
        ps =k.connection().prepareStatement("select * from akun where username='"+pencarian.getText()+"'  or  nomorHp='"+pencarian.getText()+"'  or email='"+pencarian.getText()+"'  " );
        rs = ps.executeQuery();


        }catch (SQLException e){
            help.dialog(Alert.AlertType.INFORMATION,e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDatatabelakun();
        loadDatatabelpesan();
        tomboloff();
        show();
        kosongin();
    }


}
