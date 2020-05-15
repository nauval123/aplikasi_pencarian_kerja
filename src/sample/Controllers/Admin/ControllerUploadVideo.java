package sample.Controllers.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Models.video;
import sample.help;
import sample.helpers.koneksi;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerUploadVideo implements Initializable {

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
    private TableView<sample.Models.video> tabelVideo;

    @FXML
    private TableColumn<sample.Models.video, Integer> ID;

    @FXML
    private TableColumn<sample.Models.video, String> Judul;

    @FXML
    private TableColumn<sample.Models.video, String> link;

    @FXML
    private TextField idfield;

    @FXML
    private TextField judulfield;

    @FXML
    private TextField linkfield;

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

    private ObservableList<sample.Models.video>tblvid;
    private PreparedStatement ps;
    private ResultSet rs;

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

    }

    private void loadtabelvideo(){

        try {
            koneksi k = new koneksi();
            tblvid = FXCollections.observableArrayList();
            ps =k.connection().prepareStatement("select * from video");
            rs = ps.executeQuery();

            while(rs.next()){
                tblvid.add(new video(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        }    catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
        }

        Judul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        link.setCellValueFactory(new PropertyValueFactory<>("linkVideo"));
        tabelVideo.setItems(null);
        tabelVideo.setItems(tblvid);
    }

    private void show(){
        tabelVideo.setOnMouseClicked(e ->{
            sample.Models.video video = tabelVideo.getItems().get(tabelVideo.getSelectionModel().getSelectedIndex());
            idfield.setText(String.valueOf(video.getId()));
            judulfield.setText(video.getJudul());
            linkfield.setText(video.getLinkVideo());
        });
    }

    @FXML
    public void ubahVideo(ActionEvent actionEvent) {
        koneksi k = new koneksi();
        try {
            idfield.setDisable(true);
            int u1 = Integer.parseInt(idfield.getText());
            String u2 = judulfield.getText();
            String u3 = linkfield.getText();

            ps =k.connection().prepareStatement("insert into video (id,judulVideo,linkVideo) values('"+u1+"','"+u2+"','"+u3+"') on duplicate key update judulVideo = '"+u2+"', linkVideo='"+u3+"'" );
            ps.execute();
            help.dialog(Alert.AlertType.INFORMATION,"data berhasil di ubah!");
            kosongin();
            loadtabelvideo();
        }catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
        }
    }

    @FXML
    public void hapusVideo(ActionEvent actionEvent) {
        koneksi k = new koneksi();
        try {
            String u2 = judulfield.getText();
            String u3 = linkfield.getText();

            ps =k.connection().prepareStatement("delete video  where  judul='"+u2+"',email='"+u3+"'");
            ps.executeUpdate();
            loadtabelvideo();
            help.dialog(Alert.AlertType.INFORMATION,"data berhasil di hapus!");
            loadtabelvideo();
            kosongin();
        }catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
        }
    }

    @FXML
    public void tambahVideo(ActionEvent actionEvent) {
        koneksi k = new koneksi();
        try {
            String u1 = "";
            String u2 = judulfield.getText();
            String u3 = linkfield.getText();

            ps =k.connection().prepareStatement("insert into video (judulVideo,linkVideo) values('"+u2+"','"+u3+"') on duplicate key update judulVideo = '"+u2+"', linkVideo='"+u3+"'" );
            ps.execute();
            help.dialog(Alert.AlertType.INFORMATION,"data berhasil di tambahkan!");
            loadtabelvideo();
            kosongin();
        }catch (SQLException ex){
            help.dialog(Alert.AlertType.ERROR,ex.getMessage());
        }

    }

    private void tomboloff(){
        tambah.setDisable(true);
        ubah.setDisable(true);
        hapus.setDisable(true);
        pencarian.setDisable(false);
    }

    private void kosongin(){
        judulfield.setText("");
        linkfield.setText("");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadtabelvideo();
        show();
        tomboloff();
        idfield.setDisable(true);
    }
}
