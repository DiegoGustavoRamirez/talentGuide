package com.leones.talentguide;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.leones.talentguide.api.ApiManager;
import com.leones.talentguide.fragments.StatsFragment;
import com.leones.talentguide.model.ClientInfo;
import com.leones.talentguide.model.Devices;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    static final int MY_PERMISSIONS_REQUEST_LOCATION = 6969;

    boolean _next = false;

    //zone tematica
    List<String> apOneG = new ArrayList<>();

    //zone comunidades
    List<String> apTwoG = new ArrayList<>();

    //zone taller
    List<String> apThreeG = new ArrayList<>();

    //zone imperdible
    List<String> apFourG = new ArrayList<>();

    List<String> apName1 = new ArrayList<>();
    List<String> apName2 = new ArrayList<>();
    List<String> apName3 = new ArrayList<>();
    List<String> apName4 = new ArrayList<>();

    List<ClientInfo> timeClientsOne = new ArrayList<>();
    List<ClientInfo> timeClientsTwo = new ArrayList<>();
    List<ClientInfo> timeClientsThree = new ArrayList<>();
    List<ClientInfo> timeClientsFour = new ArrayList<>();

    String mA;
    int mayor;

    int cont = 0;

    List<Integer> times = new ArrayList<>();

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        checkPermission();
    }

    public void checkPermission(){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        }else{
            try {
                getMacAddress();
            } catch (SocketException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case MY_PERMISSIONS_REQUEST_LOCATION:{
                if(grantResults.length > 0){
                    Log.d("PERMISO:", ":)GRAX");
                }
                else{
                    Log.d("PERMISO:", ":(MAL");
                }
                checkPermission();
                return;
            }
        }
    }

    //address of the actual device
    public void getMacAddress() throws SocketException {
        int cont = 1;
        String mac;
        ArrayList interfaces = Collections
                .list(NetworkInterface.getNetworkInterfaces());

        for(int i = 0; i< interfaces.size(); i++){
            NetworkInterface iface = (NetworkInterface) interfaces.get(i);
            for (InterfaceAddress addr : iface.getInterfaceAddresses()) {
                if(cont == 7){
                    mac = addr.getAddress().toString();
                    mA = mac.substring(7, 9) + ":" + mac.substring(9, 11) + ":" + mac.substring(12, 14) + ":" + mac.substring(19, 21) + ":" + mac.substring(22, 24) + ":" + mac.substring(24, 26);
                    Log.d("Macs", mac);
                }
                cont++;
            }
        }

        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String address = info.getMacAddress();
        Log.d("Mac", address);
        //success();
        getDevices();
    }

    public void getDevices(){

        ApiManager apiManager = new ApiManager();

        String token = "db238b915e3dd3b5f82d1c4cd66e8bd3b387408f";
        String type = "application/json";
        String mA = "Q2JD-FECK-RVTN";
        String time = "7200";

        apiManager.getDevices(token, type, mA, time).enqueue(new Callback<List<Devices>>() {
            @Override
            public void onResponse(Call<List<Devices>> call, Response<List<Devices>> response) {
                if(response.isSuccessful()){
                    List<String> apOne = new ArrayList<>();
                    Log.d("Exito", response.body().toString());
                    for (Devices t : response.body()){
                        apOne.add(t.getMac());
                        apName1.add(t.getDescription());
                        Log.d("Elemento", t.getMac());
                    }
                    saveDevices(apOne, 1);
                }
            }

            @Override
            public void onFailure(Call<List<Devices>> call, Throwable t) {

                Log.d("Fallo", call.toString() + t.toString());
            }
        });

        mA = "Q2JD-8RRA-G8VZ";
        apiManager = new ApiManager();
        apiManager.getDevices(token, type, mA, time).enqueue(new Callback<List<Devices>>() {
            @Override
            public void onResponse(Call<List<Devices>> call, Response<List<Devices>> response) {
                if(response.isSuccessful()){
                    List<String> apTwo = new ArrayList<>();
                    Log.d("Exito", response.body().toString());
                    for (Devices t : response.body()){
                        apTwo.add(t.getMac());
                        apName2.add(t.getDescription());
                        Log.d("Elemento", t.getMac());
                    }
                    saveDevices(apTwo, 2);
                }
            }

            @Override
            public void onFailure(Call<List<Devices>> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
            }
        });

        mA = "Q2JD-FUAN-T33U";
        apiManager = new ApiManager();
        apiManager.getDevices(token, type, mA, time).enqueue(new Callback<List<Devices>>() {
            @Override
            public void onResponse(Call<List<Devices>> call, Response<List<Devices>> response) {
                if(response.isSuccessful()){
                    List<String> apThree = new ArrayList<>();
                    Log.d("Exito", response.body().toString());
                    for (Devices t : response.body()){
                        apThree.add(t.getMac());
                        apName3.add(t.getDescription());
                        Log.d("Elemento", t.getMac());
                    }
                    saveDevices(apThree, 3);
                    success();
                }
            }

            @Override
            public void onFailure(Call<List<Devices>> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
            }
        });

        mA = "Q2JD-FW3E-FZED";
        apiManager = new ApiManager();
        apiManager.getDevices(token, type, mA, time).enqueue(new Callback<List<Devices>>() {
            @Override
            public void onResponse(Call<List<Devices>> call, Response<List<Devices>> response) {
                if(response.isSuccessful()){
                    List<String> apFour = new ArrayList<>();
                    Log.d("Exito", response.body().toString());
                    for (Devices t : response.body()){
                        apFour.add(t.getMac());
                        apName4.add(t.getDescription());
                        Log.d("Elemento", t.getMac());
                    }
                    saveDevices(apFour, 4);
                }
            }

            @Override
            public void onFailure(Call<List<Devices>> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
            }
        });

    }

    public void saveDevices(List<String> devices, int id){
        switch (id){
            case 1:
                apOneG = devices;
                cont++;
                break;
            case 2:
                apTwoG = devices;
                cont++;
                break;
            case 3:
                apThreeG = devices;
                cont++;
                break;
            default:
                apFourG = devices;
                cont++;
                break;
        }
    }

    public void getTimeEachZone(){

        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;

        for (int i = 0; i < apOneG.size(); i++){

            if(mA == apOneG.get(i)){
                one += 1;
            }
        }
        times.add(one);

        for (int i = 0; i < apTwoG.size(); i++){

            if(mA == apTwoG.get(i)){
                two += 1;
            }
        }
        times.add(two);

        for (int i = 0; i < apThreeG.size(); i++){

            if(mA == apThreeG.get(i)){
                three += 1;
            }
        }
        times.add(three);

        for (int i = 0; i < apFourG.size(); i++){

            if(mA == apFourG.get(i)){
                four += 1;
            }
        }
        times.add(four);

        if(one > two && one > three && one > four){
            mayor = 1;
        }else if(two > one && two > three && two > four){
            mayor = 2;
        }else if(three > one && three > two && three > four){
            mayor = 3;
        }else if (one == 0 && two == 0 && three == 0 && four == 0){
            mayor = 3;
        }else{
            mayor = 4;
        }

    }

    public void getTimeClient(){
        if(_next){
            ClientInfo clientInfo = new ClientInfo("ff", "0");
            timeClientsOne.add(clientInfo);
            for (int i = 0; i < apName1.size(); i++){
                for(int j = 0; j < timeClientsOne.size(); j++){
                    if (timeClientsOne.get(j).getClientname() == apName1.get(i)){
                        int s = Integer.parseInt(timeClientsOne.get(j).getTimeConect()) + 1;
                        timeClientsOne.get(j).setTimeConect(String.valueOf(s));
                    }else{
                        clientInfo = new ClientInfo(apName1.get(i), "1");
                        timeClientsOne.add(clientInfo);
                    }
                }

            }

            timeClientsTwo.add(clientInfo);
            for (int i = 0; i < apName2.size(); i++){
                for(int j = 0; j < timeClientsTwo.size(); j++){
                    if (timeClientsTwo.get(j).getClientname() == apName2.get(i)){
                        int s = Integer.parseInt(timeClientsTwo.get(j).getTimeConect()) + 1;
                        timeClientsTwo.get(j).setTimeConect(String.valueOf(s));
                    }else{
                        clientInfo = new ClientInfo(apName2.get(i), "1");
                        timeClientsTwo.add(clientInfo);
                    }
                }

            }
            timeClientsThree.add(clientInfo);
            for (int i = 0; i < apName3.size(); i++){
                for(int j = 0; j < timeClientsThree.size(); j++){
                    if (timeClientsThree.get(j).getClientname() == apName3.get(i)){
                        int s = Integer.parseInt(timeClientsThree.get(j).getTimeConect()) + 1;
                        timeClientsThree.get(j).setTimeConect(String.valueOf(s));
                    }else{
                        clientInfo = new ClientInfo(apName3.get(i), "1");
                        timeClientsThree.add(clientInfo);
                    }
                }

            }
            timeClientsFour.add(clientInfo);
            for (int i = 0; i < apName4.size(); i++){
                for(int j = 0; j < timeClientsFour.size(); j++){
                    if (timeClientsFour.get(j).getClientname() == apName4.get(i)){
                        int s = Integer.parseInt(timeClientsFour.get(j).getTimeConect()) + 1;
                        timeClientsFour.get(j).setTimeConect(String.valueOf(s));
                    }else{
                        clientInfo = new ClientInfo(apName4.get(i), "1");
                        timeClientsFour.add(clientInfo);
                    }
                }

            }
        }

    }

    public void success(){

        MainActivity.timeZonesY = times;
        MainActivity.clientsOne = timeClientsOne;
        MainActivity.clientsTwo = timeClientsTwo;
        MainActivity.clientsThree = timeClientsThree;
        MainActivity.clientsFour = timeClientsFour;

        Log.d("array", String.valueOf(timeClientsOne.size()));

        getTimeEachZone();
        getTimeClient();

        SharedPreferences setting = getSharedPreferences("Info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();

        editor.putInt("Preferencias", mayor);
        editor.putInt("Company", 2);
        editor.commit();

        if(cont == 4){
            Log.d("DevicesArray", apOneG.toArray().toString());
            Log.d("DevicesArray", apTwoG.toArray().toString());
            Log.d("DevicesArray", apThreeG.toArray().toString());
            Log.d("DevicesArray", apFourG.toArray().toString());
        }
        Intent goToLand = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(goToLand);
        //SplashScreenActivity.this.finish();
    }

}
