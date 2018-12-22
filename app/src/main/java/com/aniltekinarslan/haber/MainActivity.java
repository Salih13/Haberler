package com.aniltekinarslan.haber;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aniltekinarslan.haber.Fragments.AnaSayfaFragment;
import com.aniltekinarslan.haber.Fragments.HaberlerFragment;
import com.aniltekinarslan.haber.Models.KategorilerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Activity mainActivity;

    List<KategorilerResponse> kategorilerResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mainActivity = MainActivity.this;

        AnaSayfaFragment anasayfaFragment = new AnaSayfaFragment();
        if (anasayfaFragment != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.llMainActivityFragmentsGroup, anasayfaFragment);
            ft.commit();
        }

        getKategoriler();
    }

    public void getKategoriler(){
        final ProgressDialog progressDialog = DialogsUtils.showProgressDialog(mainActivity);
        Call<List<KategorilerResponse>> call = Definactions.getClient().getKategoriler();
        call.enqueue(new Callback<List<KategorilerResponse>>() {
            @Override
            public void onResponse(Call<List<KategorilerResponse>> call, Response<List<KategorilerResponse>> response) {
                if (response.body() != null){
                    kategorilerResponseList = response.body();
                    if (kategorilerResponseList != null && kategorilerResponseList.size() != 0){
                        addMenuItemInNavMenuDrawer();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<KategorilerResponse>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void addMenuItemInNavMenuDrawer() {
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        Menu menu = navView.getMenu();
        Menu submenu = menu.addSubMenu("HABERLER");

        for (int i=0; i<kategorilerResponseList.size(); i++){
            submenu.add(0,i,0,kategorilerResponseList.get(i).getBaslik());
        }

        navView.invalidate();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ana_sayfa) {
            AnaSayfaFragment anasayfaFragment = new AnaSayfaFragment();
            if (anasayfaFragment != null) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.llMainActivityFragmentsGroup, anasayfaFragment);
                ft.commit();
            }
        } else if (id == R.id.nav_hakkimizda) {
            Toast.makeText(mainActivity, "Hakkımızda", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_iletisim) {
            Toast.makeText(mainActivity, "İletişim", Toast.LENGTH_LONG).show();
        } else {
            HaberlerFragment haberlerFragment = new HaberlerFragment();
            HaberlerFragment.kategoriId = id;
            for (int i=0; i<kategorilerResponseList.size(); i++){
                if (id == kategorilerResponseList.get(i).getId()){
                    HaberlerFragment.kategoriBasligi = kategorilerResponseList.get(i).getBaslik();
                    break;
                }
            }
            if (haberlerFragment != null) {
                FragmentTransaction ft =  ((Activity) mainActivity).getFragmentManager().beginTransaction();
                ft.replace(R.id.llMainActivityFragmentsGroup, haberlerFragment).addToBackStack("haberlerFragment");
                ft.commit();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
