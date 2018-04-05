package com.leones.talentguide;

import android.Manifest;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.leones.talentguide.fragments.StatsFragment;
import com.leones.talentguide.fragments.PreferencesFragment;

import java.util.ArrayList;
import java.util.List;

public class LandActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LocationListener {

    static final int MY_PERMISSIONS_REQUEST_LOCATION = 6969;
    FragmentManager fragmentManager;
    int idFragment;

    public static List<Integer> timeZonesY = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_land);

        initializeUIElements();
        //checkPermissions();

    }

    public void checkPermissions(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        }else{
            initializeUIElements();
        }
    }

    public void initializeUIElements(){
        //toolbar reference
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //reference drawer
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.layoutDrawer);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar
                , R.string.strNavigationDrawerOpen
                , R.string.strNavigationDrawerClose);
        drawerLayout.setDrawerListener(toggle);

        //reference navigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        toggle.syncState();

        //fragment manager to fragments
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new PreferencesFragment()).commit();

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        idFragment = item.getItemId();
        fragmentManager = getFragmentManager();

        switch (idFragment) {
            case R.id.navDrawerPreferences:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new PreferencesFragment()).commit();
                break;
            case R.id.navDrawerMap:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new StatsFragment()).commit();
                StatsFragment.timeZonesY = timeZonesY;
                break;
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.layoutDrawer);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }


    //Function to fragments
    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.layoutDrawer);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        finishAffinity();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idFragment = item.getItemId();

        if (idFragment == R.id.landAction) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
