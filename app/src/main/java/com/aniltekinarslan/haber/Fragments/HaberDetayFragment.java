package com.aniltekinarslan.haber.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aniltekinarslan.haber.Definactions;
import com.aniltekinarslan.haber.DialogsUtils;
import com.aniltekinarslan.haber.Models.HaberDetayResponse;
import com.aniltekinarslan.haber.R;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HaberDetayFragment extends android.app.Fragment {

    static FragmentActivity context;
    CoordinatorLayout clDetayFragment;
    ImageView imgDetayResim;
    TextView tvHaberKategori, tvHaberTarih, tvHaberBaslik, tvHaberAciklama;
    WebView webview;
    LinearLayout llHaberDetayTarih;
    public static int haberId;
    List<HaberDetayResponse> haberDetay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_haber_detay, container, false);

        clDetayFragment = (CoordinatorLayout) view.findViewById(R.id.clDetayFragment);
        imgDetayResim = (ImageView) view.findViewById(R.id.imgHaberDetayResim);
        tvHaberTarih = (TextView) view.findViewById(R.id.tvHaberDetayTarih);
        tvHaberKategori = (TextView) view.findViewById(R.id.tvHaberDetayKategori);
        tvHaberBaslik = (TextView) view.findViewById(R.id.tvHaberDetayBaslik);
        tvHaberAciklama = (TextView) view.findViewById(R.id.tvHaberDetayAciklama);
        getHaberDetay();
        webview = (WebView) view.findViewById(R.id.wvHaberDetayIcerik);
        webview.getSettings().setJavaScriptEnabled(true);
        llHaberDetayTarih = (LinearLayout) view.findViewById(R.id.llHaberDetayTarih);
        llHaberDetayTarih.getBackground().setAlpha(127);

        return view;
    }

    private void getHaberDetay() {
        final ProgressDialog progressDialog = DialogsUtils.showProgressDialog(context);
        Call<List<HaberDetayResponse>> haberDetayGetCall = Definactions.getClient().getHaberDetay(haberId);
        haberDetayGetCall.enqueue(new Callback<List<HaberDetayResponse>>() {
            @Override
            public void onResponse(Call<List<HaberDetayResponse>> call, Response<List<HaberDetayResponse>> response) {
                if(response.body() != null){
                    haberDetay = response.body();
                    if (haberDetay != null){
                        tvHaberBaslik.setText(haberDetay.get(0).getBaslik().toString());
                        tvHaberKategori.setText(haberDetay.get(0).getKategori().toString());
                        tvHaberTarih.setText("Tarih: " + haberDetay.get(0).getYayinTarihi().toString());
                        Picasso.get().load(haberDetay.get(0).getAnaResim().toString()).into(imgDetayResim);

//                        String icerik = "";
//                        try {
//                            icerik = new String(haberDetayList.get(0).getIcerik().getBytes("ISO-8859-1"), "UTF-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
                        byte[] bytes = new byte[0];
                        try {
                            bytes = haberDetay.get(0).getIcerik().toString().getBytes("UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        String text = null;
                        try {
                            text = new String(bytes, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        webview.loadData(text, "text/html; charset=utf-8", "utf-8");
//                        tvHaberAciklama.setText(Html.fromHtml(icerik));
                    }else {
                        DialogsUtils.showSnackBar(clDetayFragment, "Hata Oluştu!", "Tamam", 10);
                    }
                }else {
                    DialogsUtils.showSnackBar(clDetayFragment, "Hata Oluştu!", "Tamam", 10);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<HaberDetayResponse>> call, Throwable t) {
                Log.e("Anil", "Haber Detay: " + t.getMessage().toString());
                DialogsUtils.showSnackBar(clDetayFragment, "Hata Oluştu!", "Tamam", 10);
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        context =(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        //Başlık Güncelleme
        getActivity().setTitle("Haber Detay");
    }
}
