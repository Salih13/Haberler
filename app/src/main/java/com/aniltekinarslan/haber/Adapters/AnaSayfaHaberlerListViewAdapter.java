package com.aniltekinarslan.haber.Adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aniltekinarslan.haber.Fragments.HaberDetayFragment;
import com.aniltekinarslan.haber.Models.AnaSayfaHaberler;
import com.aniltekinarslan.haber.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnaSayfaHaberlerListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    List<AnaSayfaHaberler> haberlerList;

    public AnaSayfaHaberlerListViewAdapter(Context context, List<AnaSayfaHaberler> haberlerList) {
        this.context = context;
        this.haberlerList = haberlerList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return haberlerList.size();
    }

    @Override
    public Object getItem(int position) {
        return haberlerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.ana_sayfa_haberler_listview_item, null);
        }
        LinearLayout llMainHaberLvItems = (LinearLayout) convertView.findViewById(R.id.llAnaSayfaHaberlerLvItems);

        llMainHaberLvItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HaberDetayFragment haberDetayFragment = new HaberDetayFragment();
                HaberDetayFragment.haberId = haberlerList.get(position).getId();
                if (haberDetayFragment != null) {
                    FragmentTransaction ft =  ((Activity) context).getFragmentManager().beginTransaction();
                    ft.replace(R.id.llMainActivityFragmentsGroup, haberDetayFragment).addToBackStack("detayFragment");
                    ft.commit();
                }
            }
        });

        TextView tvHaberBaslik = (TextView) convertView.findViewById(R.id.tvAnaSayfaHaberlerLvItemBaslik);
        TextView tvHaberTarih = (TextView) convertView.findViewById(R.id.tvAnaSayfaHaberlerLvItemTarih);
        ImageView imgHaber = (ImageView) convertView.findViewById(R.id.imgAnaSayfaHaberlerLvResim);

        tvHaberBaslik.setText(haberlerList.get(position).getBaslik().toString());
        tvHaberTarih.setText(haberlerList.get(position).getYayinTarihi().toString());

        final ProgressBar pbMainHaberLvHaber = (ProgressBar) convertView.findViewById(R.id.pbAnaSayfaHaberler);
        Picasso.get().load(haberlerList.get(position).getAnaResim().toString()).into(imgHaber, new Callback() {
            @Override
            public void onSuccess() {
                pbMainHaberLvHaber.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                pbMainHaberLvHaber.setVisibility(View.VISIBLE);
            }
        });

        return convertView;
    }
}
