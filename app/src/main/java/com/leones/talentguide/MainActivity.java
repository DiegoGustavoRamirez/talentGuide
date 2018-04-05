package com.leones.talentguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.leones.talentguide.model.ClientInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public static List<Integer> timeZonesY = new ArrayList<>();
    public static List<ClientInfo> clientsOne;
    public static List<ClientInfo> clientsTwo;
    public static List<ClientInfo> clientsThree;
    public static List<ClientInfo> clientsFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void goToUser(View v){
        LandActivity.timeZonesY = timeZonesY;
        Intent goTo = new Intent(MainActivity.this, LandActivity.class);
        startActivity(goTo);
        MainActivity.this.finish();

    }

    public void goToCompany(View v){
        CompanyActivity.clientsOne = clientsOne;
        CompanyActivity.clientsTwo = clientsTwo;
        CompanyActivity.clientsThree = clientsThree;
        CompanyActivity.clientsFour = clientsFour;
        Intent goTo = new Intent(MainActivity.this, CompanyActivity.class);
        startActivity(goTo);
        //MainActivity.this.finish();

    }
}
