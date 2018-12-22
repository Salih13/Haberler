package com.aniltekinarslan.haber.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aniltekinarslan.haber.Models.HaberlerResponse;
import com.aniltekinarslan.haber.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HaberListesiListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    List<HaberlerResponse> haberlerList;

    public HaberListesiListViewAdapter(Context context, List<HaberlerResponse> haberlerList) {
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
            convertView =  layoutInflater.inflate(R.layout.haberler_listview_item,null);
        }
        LinearLayout llHaberListesiLvItems = (LinearLayout) convertView.findViewById(R.id.llHaberlerLvItems);

        ImageView imgHaber = (ImageView) convertView.findViewById(R.id.imgHaberListesiLvItemLogo);
        TextView tvTarih = (TextView) convertView.findViewById(R.id.tvHaberListesiLvItemTarih);
        TextView tvBaslik = (TextView) convertView.findViewById(R.id.tvHaberListesiLvItemBaslik);
        final ProgressBar pbHaber = (ProgressBar) convertView.findViewById(R.id.pbHaberListesiLvItem);

        Picasso.get().load(haberlerList.get(position).getAnaResim().toString()).into(imgHaber, new Callback() {
            @Override
            public void onSuccess() {
                pbHaber.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                pbHaber.setVisibility(View.VISIBLE);
            }
        });

        tvBaslik.setText(haberlerList.get(position).getBaslik());
        tvTarih.setText("Tarih: " + haberlerList.get(position).getYayinTarihi());

        llHaberListesiLvItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DetayFragment.haberId = Integer.parseInt(haberlerList.get(position).getId());
//                Constant.openFragment(new DetayFragment(), "detayFragment", context);
            }
        });

        return convertView;
    }
}
