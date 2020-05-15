package sample.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


import java.sql.Timestamp;

public class logpesan {
    private final StringProperty pengirim;
    private final StringProperty subject;
    private final StringProperty konten;
    private final Timestamp Tanggal;


    public logpesan(String pengirim, String subject, String konten, Timestamp Tanggal) {
        this.pengirim   = new SimpleStringProperty(pengirim);
        this.subject    = new SimpleStringProperty(subject);;
        this.konten     = new SimpleStringProperty(konten);;
        this.Tanggal    = Tanggal;
    }

    public String getPengirim() {
        return pengirim.get();
    }

      public String getSubject() {
        return subject.get();
    }

    public String getKonten() {
        return konten.get();
    }

    public Timestamp getTanggal() {
        return Tanggal;
    }
}
