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

import com.leones.talentguide.fragments.CompetitionFragment;
import com.leones.talentguide.fragments.NearClientsFragment;
import com.leones.talentguide.fragments.PreferencesFragment;
import com.leones.talentguide.fragments.StatsFragment;
import com.leones.talentguide.model.ClientInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompanyActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LocationListener {

    static final int MY_PERMISSIONS_REQUEST_LOCATION = 6969;
    FragmentManager fragmentManager;
    int idFragment;

    public static List<ClientInfo> clientsOne;
    public static List<ClientInfo> clientsTwo;
    public static List<ClientInfo> clientsThree;
    public static List<ClientInfo> clientsFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        initializeUIElements();

    }

    public void checkPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            initializeUIElements();
        }
    }

    public void initializeUIElements() {
        //toolbar reference
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //reference drawer
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.layoutDrawer2);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar
                , R.string.strNavigationDrawerOpen
                , R.string.strNavigationDrawerClose);
        drawerLayout.setDrawerListener(toggle);

        //reference navigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView2);
        navigationView.setNavigationItemSelectedListener(this);

        toggle.syncState();

        //fragment manager to fragments
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new NearClientsFragment()).commit();

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
            case R.id.navDrawerNearClients:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new NearClientsFragment()).commit();
                NearClientsFragment.clientsOne = clientsOne;
                NearClientsFragment.clientsTwo = clientsTwo;
                NearClientsFragment.clientsThree = clientsThree;
                NearClientsFragment.clientsFour = clientsFour;
                break;
            case R.id.navDrawerCompetition:
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, new CompetitionFragment()).commit();
                break;
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.layoutDrawer2);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
