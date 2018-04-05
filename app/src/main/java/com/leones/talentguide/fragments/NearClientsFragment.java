package com.leones.talentguide.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leones.talentguide.R;
import com.leones.talentguide.adapters.AdapterClients;
import com.leones.talentguide.adapters.AdapterEvents;
import com.leones.talentguide.model.ClientInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearClientsFragment extends Fragment {

    View v;
    RecyclerView recyclerViewClients;
    AdapterClients adapterClients;

    List<ClientInfo> prefe = new ArrayList();

    SharedPreferences sharedPreferences;

    public static List<ClientInfo> clientsOne;
    public static List<ClientInfo> clientsTwo;
    public static List<ClientInfo> clientsThree;
    public static List<ClientInfo> clientsFour;

    String [] names = {"IphoneJuan", "Android465", "Guess", "IPHONELUIS", "mariana654", "DiegoRMZ", "ANDROIDlll", "IphoneSofia", "HugoL", "Oct98", "iphone", "jilioP", "TEbut", "iphone56", "AndroidLuis", "FlamKOP12", "iPhoness", "Fran45", "huji51369", "Oswaldo1", "MariFer", "6546546", "Android_64"};
    String [] timeClient = {"45", "90", "24", "86", "6", "45", "67", "45", "33", "97", "12", "34", "55", "105", "56", "49", "77", "200", "24", "18", "48", "23", "32", "43", "55"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_near_clients, container, false);

        recyclerViewClients = v.findViewById(R.id.rvClients);
        recyclerViewClients.setLayoutManager(new LinearLayoutManager(getActivity()));

        sharedPreferences = NearClientsFragment.this.getActivity().getSharedPreferences("Info", Context.MODE_PRIVATE);
        int index = sharedPreferences.getInt("Company", 2);

        setData(index);

        adapterClients = new AdapterClients(prefe, getActivity());
        recyclerViewClients.setAdapter(adapterClients);

        return v;
    }

    public void setData(int index){
        switch (index){

            case 1:
                for (int i = 0; i < clientsOne.size(); i++) {
                    String str = clientsOne.get(i).getClientname();
                    String n = clientsOne.get(i).getTimeConect();
                    ClientInfo clientInfo = new ClientInfo(str, n);
                    prefe.add(clientInfo);
                }
                break;

            case 2:
                if(clientsOne != null){
                    for (int i = 0; i < clientsOne.size(); i++) {
                        String str = clientsOne.get(i).getClientname();
                        String n = clientsOne.get(i).getTimeConect();
                        ClientInfo clientInfo = new ClientInfo(str, n);
                        prefe.add(clientInfo);
                    }
                }

                break;

            case 3:
                for (int i = 0; i < clientsThree.size(); i++) {
                    String str = clientsThree.get(i).getClientname();
                    String n = clientsThree.get(i).getTimeConect();
                    ClientInfo clientInfo = new ClientInfo(str, n);
                    prefe.add(clientInfo);
                }
                break;
            default:
                for (int i = 0; i < clientsFour.size(); i++) {
                    String str = clientsFour.get(i).getClientname();
                    String n = clientsFour.get(i).getTimeConect();
                    ClientInfo clientInfo = new ClientInfo(str, n);
                    prefe.add(clientInfo);
                }
                break;
        }

        if (clientsOne == null || clientsOne.size() == 0){
            for(int i = 0; i < names.length; i++){
                ClientInfo clientInfo = new ClientInfo(names[i], timeClient[i]);
                prefe.add(clientInfo);
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
