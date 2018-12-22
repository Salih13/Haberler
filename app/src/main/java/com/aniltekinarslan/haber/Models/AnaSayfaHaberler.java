package com.aniltekinarslan.haber.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnaSayfaHaberler {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("kategori")
    @Expose
    private String kategori;
    @SerializedName("baslik")
    @Expose
    private String baslik;
    @SerializedName("haber_kodu")
    @Expose
    private String haberKodu;
    @SerializedName("ana_resim")
    @Expose
    private String anaResim;
    @SerializedName("icerik")
    @Expose
    private String icerik;
    @SerializedName("yayin_tarihi")
    @Expose
    private String yayinTarihi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getHaberKodu() {
        return haberKodu;
    }

    public void setHaberKodu(String haberKodu) {
        this.haberKodu = haberKodu;
    }

    public String getAnaResim() {
        return anaResim;
    }

    public void setAnaResim(String anaResim) {
        this.anaResim = anaResim;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getYayinTarihi() {
        return yayinTarihi;
    }

    public void setYayinTarihi(String yayinTarihi) {
        this.yayinTarihi = yayinTarihi;
    }

}
