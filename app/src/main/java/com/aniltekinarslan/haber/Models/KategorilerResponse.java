package com.aniltekinarslan.haber.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KategorilerResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("baslik")
    @Expose
    private String baslik;
    @SerializedName("aciklama")
    @Expose
    private String aciklama;

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

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

}