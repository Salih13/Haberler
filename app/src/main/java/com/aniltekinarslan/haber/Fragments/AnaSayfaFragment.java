package com.aniltekinarslan.haber.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.aniltekinarslan.haber.Adapters.AnaSayfaHaberlerListViewAdapter;
import com.aniltekinarslan.haber.Adapters.MainViewPagerAdapter;
import com.aniltekinarslan.haber.Definactions;
import com.aniltekinarslan.haber.DialogsUtils;
import com.aniltekinarslan.haber.Models.AnaSayfaHaberler;
import com.aniltekinarslan.haber.Models.AnasayfaSliderResponse;
import com.aniltekinarslan.haber.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnaSayfaFragment extends android.app.Fragment {

    static FragmentActivity context;
    CoordinatorLayout clAnaSayfaFragment;
    ScrollView svAnaSayfaFragment;
    ViewPager vpSonHaberler;
    ListView lvAnaSayfaHaberler;

    //region slider
    Button btnSonDakika, btnSpor, btnEkonomi, btnFinans, btnMagazin;
    LinearLayout llGostergeSonDakika, llGostergeSpor, llGostergeEkonomi, llGostergeFinans, llGostergeMagazin;
    List<AnasayfaSliderResponse> anasayfaSliderResponseList;
    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ana_sayfa, container, false);
        clAnaSayfaFragment = (CoordinatorLayout) view.findViewById(R.id.clAnaSayfaFragment);
        svAnaSayfaFragment = (ScrollView) view.findViewById(R.id.svAnaSayfaFragment);

        //region slider
        btnSonDakika = (Button) view.findViewById(R.id.btnSonDakika);
        btnSpor = (Button) view.findViewById(R.id.btnSpor);
        btnEkonomi = (Button) view.findViewById(R.id.btnEkonomi);
        btnFinans = (Button) view.findViewById(R.id.btnFinans);
        btnMagazin = (Button) view.findViewById(R.id.btnMagazin);

        llGostergeSonDakika = (LinearLayout) view.findViewById(R.id.llGostergeSonDakika);
        llGostergeSpor = (LinearLayout) view.findViewById(R.id.llGostergeSpor);
        llGostergeEkonomi = (LinearLayout) view.findViewById(R.id.llGostergeEkonomi);
        llGostergeFinans = (LinearLayout) view.findViewById(R.id.llGostergeFinans);
        llGostergeMagazin = (LinearLayout) view.findViewById(R.id.llGostergeMagazin);

        vpSonHaberler = (ViewPager) view.findViewById(R.id.vpSonHaberler);
        //endregion

        //region slider üstündeki butonların onclick
        btnSonDakika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llGostergeSonDakika.setVisibility(View.VISIBLE);
                llGostergeSpor.setVisibility(View.INVISIBLE);
                llGostergeEkonomi.setVisibility(View.INVISIBLE);
                llGostergeFinans.setVisibility(View.INVISIBLE);
                llGostergeMagazin.setVisibility(View.INVISIBLE);
                getAnasayfaSlider(1);
            }
        });
        btnSpor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llGostergeSonDakika.setVisibility(View.INVISIBLE);
                llGostergeSpor.setVisibility(View.VISIBLE);
                llGostergeEkonomi.setVisibility(View.INVISIBLE);
                llGostergeFinans.setVisibility(View.INVISIBLE);
                llGostergeMagazin.setVisibility(View.INVISIBLE);
                getAnasayfaSlider(2);
            }
        });
        btnEkonomi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llGostergeSonDakika.setVisibility(View.INVISIBLE);
                llGostergeSpor.setVisibility(View.INVISIBLE);
                llGostergeEkonomi.setVisibility(View.VISIBLE);
                llGostergeFinans.setVisibility(View.INVISIBLE);
                llGostergeMagazin.setVisibility(View.INVISIBLE);
                getAnasayfaSlider(3);
            }
        });
        btnFinans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llGostergeSonDakika.setVisibility(View.INVISIBLE);
                llGostergeSpor.setVisibility(View.INVISIBLE);
                llGostergeEkonomi.setVisibility(View.INVISIBLE);
                llGostergeFinans.setVisibility(View.VISIBLE);
                llGostergeMagazin.setVisibility(View.INVISIBLE);
                getAnasayfaSlider(4);
            }
        });
        btnMagazin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llGostergeSonDakika.setVisibility(View.INVISIBLE);
                llGostergeSpor.setVisibility(View.INVISIBLE);
                llGostergeEkonomi.setVisibility(View.INVISIBLE);
                llGostergeFinans.setVisibility(View.INVISIBLE);
                llGostergeMagazin.setVisibility(View.VISIBLE);
                getAnasayfaSlider(5);
            }
        });
        //endregion

        lvAnaSayfaHaberler = (ListView) view.findViewById(R.id.lvAnaSayfaHaberler);

        getAnaSayfaHaberler();

        return view;
    }

    public void getAnaSayfaHaberler() {
        final ProgressDialog progressDialog = DialogsUtils.showProgressDialog(context);
        Call<List<AnaSayfaHaberler>> call = Definactions.getClient().getAnaSayfaHaberler();
        call.enqueue(new Callback<List<AnaSayfaHaberler>>() {
            @Override
            public void onResponse(Call<List<AnaSayfaHaberler>> call, Response<List<AnaSayfaHaberler>> response) {
                if (response.body() != null){
                    List<AnaSayfaHaberler> haberlerList = response.body();
                    if (haberlerList != null && haberlerList.size() != 0){
                        AnaSayfaHaberlerListViewAdapter adapter = new AnaSayfaHaberlerListViewAdapter(context, haberlerList);
                        lvAnaSayfaHaberler.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }else {
                        DialogsUtils.showSnackBar(clAnaSayfaFragment, "Haberler Listelenemedi!", "Tamam", 10);
                    }
                }else {
                    DialogsUtils.showSnackBar(clAnaSayfaFragment, "Haberler Listelenemedi!", "Tamam", 10);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<AnaSayfaHaberler>> call, Throwable t) {
                DialogsUtils.showSnackBar(clAnaSayfaFragment, "Haberler Listelenemedi!", "Tamam", 10);
                progressDialog.dismiss();
            }
        });
    }

    private void getAnasayfaSlider(int kategoriId) {
        final ProgressDialog progressDialog = DialogsUtils.showProgressDialog(context);
        Call<List<AnasayfaSliderResponse>> call = Definactions.getClient().getAnasayfaSlider(kategoriId);
        call.enqueue(new Callback<List<AnasayfaSliderResponse>>() {
            @Override
            public void onResponse(Call<List<AnasayfaSliderResponse>> call, Response<List<AnasayfaSliderResponse>> response) {
                if (response.body() != null){
                    anasayfaSliderResponseList = response.body();
                    if (anasayfaSliderResponseList != null){
                        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(context, anasayfaSliderResponseList);
                        vpSonHaberler.setAdapter(mainViewPagerAdapter);
                        mainViewPagerAdapter.notifyDataSetChanged();
                    }else {
                        DialogsUtils.showSnackBar(clAnaSayfaFragment, "haber Bulunamadı!", "Tamam", 10);
                    }
                }else {
                    DialogsUtils.showSnackBar(clAnaSayfaFragment, "Son Haberler Yüklenemedi!", "Tamam", 10);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<AnasayfaSliderResponse>> call, Throwable t) {
                DialogsUtils.showSnackBar(clAnaSayfaFragment, "Son Haberler Yüklenemedi!", "Tamam", 10);
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
        getActivity().setTitle("Ana Sayfa");
    }
}
