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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import sample.Models.akun;
import sample.Models.video;
import sample.help;
import sample.helpers.koneksi;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerVideoPelatihan implements Initializable {
    @FXML
    private AnchorPane layar;

    @FXML
    private WebView layarVideo;

    @FXML
    private Text user;

    @FXML
    private TableView<video> tabelvideo;

    @FXML
    private TableColumn<sample.Models.video, String > id;

    @FXML
    private TableColumn<sample.Models.video, String > judulVideo;

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

    private ObservableList<sample.Models.video> tblvid;
    private PreparedStatement ps;
    private ResultSet rs;

    @FXML
    void homeButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/homePelamar.fxml");
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
    void proposeButton(ActionEvent event) {
        help.gantiHalaman(event,"View/pelamar/proposePelamar.fxml");
    }

    @FXML
    void traningvButton(ActionEvent event) {

    }

    private void loadtabelvideo (){

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

            judulVideo.setCellValueFactory(new PropertyValueFactory<>("judul"));
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            tabelvideo.setItems(null);
            tabelvideo.setItems(tblvid);


    }

    private void showtabelvideo() {
            tabelvideo.setOnMouseClicked(e ->{
            sample.Models.video video = tabelvideo.getItems().get(tabelvideo.getSelectionModel().getSelectedIndex());
            Desktop d =  Desktop.getDesktop();
                try {
                    d.browse(new URI(video.getLinkVideo()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
            });

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadtabelvideo();
        showtabelvideo();


    }
}
