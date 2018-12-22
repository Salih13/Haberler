package com.aniltekinarslan.haber.Adapters;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aniltekinarslan.haber.Fragments.HaberDetayFragment;
import com.aniltekinarslan.haber.Models.AnasayfaSliderResponse;
import com.aniltekinarslan.haber.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainViewPagerAdapter extends PagerAdapter {
    Context context;
    private LayoutInflater layoutInflater;
    List<AnasayfaSliderResponse> anasayfaSliderResponseList;

    public MainViewPagerAdapter(Context context, List<AnasayfaSliderResponse> anasayfaSliderResponseList) {
        this.anasayfaSliderResponseList = anasayfaSliderResponseList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return anasayfaSliderResponseList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_viewpager, null);

        //arkaplan transparent
        LinearLayout llviewPagerKonuTarih = (LinearLayout) view.findViewById(R.id.llviewPagerKonuTarih);
//        llviewPagerKonuTarih.getBackground().setAlpha(127);

        ImageView imageView = (ImageView) view.findViewById(R.id.imgViewPager);
        final ProgressBar pbSlider = (ProgressBar) view.findViewById(R.id.pbViewPagerMainSlider);
        Picasso.get().load(anasayfaSliderResponseList.get(position).getResimUrl()).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                pbSlider.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                pbSlider.setVisibility(View.VISIBLE);
            }
        });

        TextView tvViewPagerTarih = (TextView) view.findViewById(R.id.tvViewPagerTarih);
        tvViewPagerTarih.setText("Tarih: " + anasayfaSliderResponseList.get(position).getTarih().toString());
        TextView tvBaslik = (TextView) view.findViewById(R.id.tvviewPagerBaslik);
        tvBaslik.setText(anasayfaSliderResponseList.get(position).getBaslik());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HaberDetayFragment haberDetayFragment = new HaberDetayFragment();
                HaberDetayFragment.haberId = anasayfaSliderResponseList.get(position).getId();
                if (haberDetayFragment != null) {
                    FragmentTransaction ft =  ((Activity) context).getFragmentManager().beginTransaction();
                    ft.replace(R.id.llMainActivityFragmentsGroup, haberDetayFragment).addToBackStack("detayFragment");
                    ft.commit();
                }
            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
