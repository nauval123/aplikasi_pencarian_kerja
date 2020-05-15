package sample.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;

public class Loker {

    private  final StringProperty namaperusahaan;
    private  final StringProperty judul;
    private  final StringProperty profesi;
    private  final StringProperty deskripsi;
    private  final Timestamp tanggal;



    public Loker(String namaperusahaan, String judul, String profesi, String deskripsi, Timestamp tanggal) {
        this.namaperusahaan = new SimpleStringProperty(namaperusahaan);
        this.judul = new SimpleStringProperty(judul);
        this.profesi = new SimpleStringProperty(profesi);
        this.deskripsi = new SimpleStringProperty(deskripsi);
        this.tanggal = tanggal;
    }

    public String getNamaperusahaan() {
        return namaperusahaan.get();
    }

    public StringProperty namaperusahaanProperty() {
        return namaperusahaan;
    }

    public String getJudul() {
        return judul.get();
    }

    public StringProperty judulProperty() {
        return judul;
    }

    public String getProfesi() {
        return profesi.get();
    }

    public StringProperty profesiProperty() {
        return profesi;
    }

    public String getDeskripsi() {
        return deskripsi.get();
    }

    public StringProperty deskripsiProperty() {
        return deskripsi;
    }

    public Timestamp getTanggal() {
        return tanggal;
    }
}
