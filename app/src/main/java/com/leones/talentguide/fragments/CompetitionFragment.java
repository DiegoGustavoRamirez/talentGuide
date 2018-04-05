package com.leones.talentguide.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.leones.talentguide.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Diegormz on 05/04/2018.
 */

public class CompetitionFragment extends Fragment{

    View v;
    PieChart pieChart;

    public static List<Integer> mountPeopleY = new ArrayList<>();
    private String [] zonesX = {"BOSH", "HISTORU CHANNEL", "CISCO", "MERCADO PAGO"};
    private Integer [] demo = {34, 27, 16, 39};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_competition, container, false);

            mountPeopleY.clear();
            for(int i = 0; i < 4; i++){
                Random rand = new Random();
                int n = rand.nextInt(5);
                int number = demo[i] * n;
                mountPeopleY.add(number);
            }

        pieChart = (PieChart) v.findViewById(R.id.pcStands);

        pieChart.setDescription("Cantidad de personas en: ");
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(7);
        pieChart.setDrawEntryLabels(true);

        addData();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d("PieChart", e.toString() + " " + h.toString());
                int pos1 = e.toString().indexOf("(sum): ");
                String time = e.toString().substring(pos1 + 7);

                for (int i = 0; i < mountPeopleY.size(); i++){
                    if (mountPeopleY.get(i) == Float.parseFloat(time)){
                        pos1 = i;
                        break;
                    }
                }
                String zone = zonesX[pos1];
                Toast.makeText(getActivity(), "Stand " + zone + "\n" + "Personas " + time, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });


        return v;
    }

    public void addData() {

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for (int i=0; i<mountPeopleY.size(); i++){
            yEntrys.add(new PieEntry(mountPeopleY.get(i), i));
        }
        for(int j = 0; j <zonesX.length; j++){
            xEntrys.add(zonesX[j]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Stands");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(10);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

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