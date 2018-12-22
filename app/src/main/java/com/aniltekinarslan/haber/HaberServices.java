package com.aniltekinarslan.haber;

import com.aniltekinarslan.haber.Models.AnaSayfaHaberler;
import com.aniltekinarslan.haber.Models.AnasayfaSliderResponse;
import com.aniltekinarslan.haber.Models.HaberDetayResponse;
import com.aniltekinarslan.haber.Models.HaberlerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface HaberServices {

    @GET("/api/Slider")
    Call<List<AnasayfaSliderResponse>> getAnasayfaSlider(@Query("category_id") Integer kategoriid);

    @GET("api/News/{id}")
    Call<List<HaberDetayResponse>> getHaberDetay (@Query("id")Integer haberid);

    @GET("api/News")
    Call<List<AnaSayfaHaberler>> getAnaSayfaHaberler();




    @FormUrlEncoded
    @POST("/admin/api/json.php")
    Call<List<HaberlerResponse>> posthaberler(@Field("kategori_id") Integer kategori_id);

}
