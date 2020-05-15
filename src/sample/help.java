package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class help {


    public static void gantiHalaman(ActionEvent event, String viewer) {

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            Parent view = null;
            try {
                view = FXMLLoader.load(help.class.getResource(viewer));
                Scene scene = new Scene(view);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (Exception e){
                e.printStackTrace();
            }

    }
    public static void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }

    public static void pesan(Alert.AlertType alertType,String f,String s, String k){
        Alert alert = new Alert(alertType);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Subject Pesan :"+" "+s);
        alert.setHeaderText("Dari:"+" "+f);
        alert.setContentText(k);
        alert.showAndWait();
    }
    public static void info(Alert.AlertType alertType,String n,String j, String p,String d){
        Alert alert = new Alert(alertType);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Subject Pesan :"+" "+n);
        alert.setHeaderText("Judul:"+" "+j);
        alert.setContentText("'"+p+"'%n'"+d+"'");
        alert.showAndWait();
    }


}
