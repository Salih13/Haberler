package com.aniltekinarslan.haber.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aniltekinarslan.haber.Adapters.HaberListesiListViewAdapter;
import com.aniltekinarslan.haber.Definactions;
import com.aniltekinarslan.haber.DialogsUtils;
import com.aniltekinarslan.haber.Models.HaberlerResponse;
import com.aniltekinarslan.haber.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HaberlerFragment extends android.app.Fragment {

    Context context;
    CoordinatorLayout clHaberlerFragment;
    ListView lvHaberlerFragment;
    public static int kategoriId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_haberler, container, false);

        clHaberlerFragment = (CoordinatorLayout) view.findViewById(R.id.clHaberlerFragment);
        lvHaberlerFragment = (ListView) view.findViewById(R.id.lvHaberlerFragment);
        lvHaberlerFragment.setDividerHeight(8);

        getHaberler(kategoriId);

        return view;
    }

    private void getHaberler(int kategoriId) {
        final ProgressDialog progressDialog = DialogsUtils.showProgressDialog(context);
        Call<List<HaberlerResponse>> call = Definactions.getClient().posthaberler(kategoriId);
        call.enqueue(new Callback<List<HaberlerResponse>>() {
            @Override
            public void onResponse(Call<List<HaberlerResponse>> call, Response<List<HaberlerResponse>> response) {
                if (response.body() != null){
                    List<HaberlerResponse> haberlerList = response.body();
                    if (haberlerList != null && haberlerList.size() != 0){
                        HaberListesiListViewAdapter adapter = new HaberListesiListViewAdapter(context, haberlerList);
                        lvHaberlerFragment.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }else {
                        DialogsUtils.showSnackBar(clHaberlerFragment, "Haberler Listelenemedi!", "Tamam", 10);
                    }
                }else {
                    DialogsUtils.showSnackBar(clHaberlerFragment, "Haberler Listelenemedi!", "Tamam", 10);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<HaberlerResponse>> call, Throwable t) {
                DialogsUtils.showSnackBar(clHaberlerFragment, "Haberler Listelenemedi!", "Tamam", 10);
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
        getActivity().setTitle("Haberler");
    }
}
