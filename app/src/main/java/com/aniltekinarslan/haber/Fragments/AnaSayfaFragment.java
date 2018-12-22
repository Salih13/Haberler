package com.aniltekinarslan.haber.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.aniltekinarslan.haber.R;

public class AnaSayfaFragment extends android.app.Fragment {

    static FragmentActivity context;
    CoordinatorLayout clAnaSayfaFragment;
    ScrollView svAnaSayfaFragment;

    //region slider
    Button btnSonDakika, btnSpor, btnEkonomi, btnFinans, btnMagazin;
    LinearLayout llGostergeSonDakika, llGostergeSpor, llGostergeEkonomi, llGostergeFinans, llGostergeMagazin;
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
        //endregion

        //region slider üstündeki butonlar
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

        return view;
    }

    private void getAnasayfaSlider(int kategoriId) {

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
