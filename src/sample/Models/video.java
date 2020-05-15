package sample.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class video {
    private final IntegerProperty id;
    private final StringProperty judul;
    private final StringProperty linkVideo;

    public video(Integer id, String judul,String linkVideo) {
        this.id = new SimpleIntegerProperty(id);
        this.judul = new SimpleStringProperty(judul);
        this.linkVideo= new SimpleStringProperty(linkVideo);
    }

    public int getId() {
        return id.get();
    }

    public String getJudul() {
        return judul.get();
    }

    public String getLinkVideo() {
        return linkVideo.get();
    }
}
