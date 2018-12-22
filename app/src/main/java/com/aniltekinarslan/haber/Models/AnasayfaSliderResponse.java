package com.aniltekinarslan.haber.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnasayfaSliderResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("baslik")
    @Expose
    private String baslik;
    @SerializedName("resim_url")
    @Expose
    private String resimUrl;
    @SerializedName("tarih")
    @Expose
    private String tarih;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getResimUrl() {
        return resimUrl;
    }

    public void setResimUrl(String resimUrl) {
        this.resimUrl = resimUrl;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

}