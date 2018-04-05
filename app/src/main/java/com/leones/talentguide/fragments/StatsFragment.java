package com.leones.talentguide.fragments;

import android.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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

public class StatsFragment extends Fragment {

    View v;
    PieChart pieChart;

    public static List<Integer> timeZonesY = new ArrayList<>();
    private String [] zonesX = {"Conferencias Tematica", "Conferencias Comunidades", "Talleres", "Imperdible"};
    private Integer [] demo = {127, 206, 413, 78};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_stats, container, false);

        Log.d("Chart", String.valueOf(timeZonesY.get(0)));

        if(timeZonesY.get(0) == 0){
            timeZonesY.clear();
            for(int i = 0; i < 4; i++){
                timeZonesY.add(demo[i]);
            }
        }

        pieChart = (PieChart) v.findViewById(R.id.pcStats);

        pieChart.setDescription("Tiempo por zona en minutos");
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

                for (int i = 0; i < timeZonesY.size(); i++){
                    if (timeZonesY.get(i) == Float.parseFloat(time)){
                        pos1 = i;
                        break;
                    }
                }
                String zone = zonesX[pos1];
                Toast.makeText(getActivity(), "Zona " + zone + "\n" + "Minutos " + time, Toast.LENGTH_SHORT).show();
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

        for (int i=0; i<timeZonesY.size(); i++){
            yEntrys.add(new PieEntry(timeZonesY.get(i), i));
        }
        for(int j = 0; j <zonesX.length; j++){
            xEntrys.add(zonesX[j]);
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Zonas");
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
