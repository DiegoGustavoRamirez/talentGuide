package com.leones.talentguide.fragments;

import android.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leones.talentguide.R;
import com.leones.talentguide.adapters.AdapterEvents;
import com.leones.talentguide.model.EventsInfo;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;
import java.util.List;


public class PreferencesFragment extends Fragment {

    View v;
    RecyclerView recyclerViewEvents;

    //Carga de agenda

    // TEMATICA
    String [] eventosTematica = {"ENTROPIA “ Tendencias Tecnológicas que Revolucionan el Turismo”", "Ser Programadores Poliglotas", "Microservices: An opinionated approach", "Desmitificando DevOps", "Sistemas de Innovación en Negocios Digitales", "IoT e Inteligencia artificial en la vida real", "Cultura Colectiva", "Como una cámara web observa todos tus movimientos.", "Innovación Cisco en Rio 2016", "Desarrollando prototipos y productos con IoT y Amazon Web Services IoT Module & AWS #IoT", "Making Industry 4.0 a reality", "Quantum Computing for ̶d̶u̶m̶m̶i̶e̶s̶ beginners", "Visor Urbano, estrategia de Gobierno Abierto para combatir la corrupción", "Google Maps LIMITED", "TORO FX Studio - Haciendo Maquillaje FX para Cine desde Guadalajara", "Narrativa gráfica, arte, diseño, futuro, digital", "Gobiernos Innovadores", "MIAX (Muestra Independiente Audiovisual Xalisco) La necesidad de generar un espacio para el encuentro de la industria del cine independiente en Jalisco.\n" +
            " 16:00 • Hackeando la creatividad", "Hackeando la creatividad", "Fundador y host @NerdcorePodcast", "Academia Mexicana de la Creatividad", "El museo de moda como catalizador para la creatividad", "Mobile First", "Del jardín a la NASA", "México es un Hack", "IAB Mèxico", "Analù Solana", "Mitos y verdades sobre el Growth hacking", "Veronica Flores", "Design Sprint, la metodología de Google para validar ideas"};
    int [] timeTematica = {11, 12, 13, 15, 16, 17, 18, 21, 12, 13, 15, 18, 20, 13, 11, 12, 13, 15, 16, 18, 20, 21, 11, 12, 13, 17, 18, 20, 21, 22};

    String [] zoneTematica = {"B1", "B1", "B1", "B1", "B1", "B1", "B1", "B1", "B2", "B2", "B2", "B2", "B2", "B4", "C1", "C1", "C1", "C1", "C1", "C1", "C1", "C1", "C2", "C2", "C2", "C2", "C2", "C2", "C2", "C2"};

    //COMUNIDADES
    String [] eventosComunidades = {"Principales fallas en equipos de cómputo", "¿Qué oculta esta imagen? Esteganografía", "Programando microcontroladores ARM´s de 32 bits en C.", "Telemática, el futuro está aquí!", "Data Science for Women", "El ABC del Open Source", "Pipeline de producción en los Videojuegos", "Haciendo videojuegos con amigos en menos de 48 horas", "Todo lo que querías saber del Fake News, pero tenías miedo de preguntar", "Visión Artificial aplicado a Robots de Búsqueda y Rescate"};

    int [] timeComunidades = {22, 23, 24, 1, 23, 24, 23, 24, 23, 23};

    String [] zoneComunidades= {"B1", "B1", "B1", "B1", "B2", "B2", "C1", "C1", "C2", "D1"};

    //TALLER
    String [] eventosTaller = {"La importancia de la auditabilidad en los sistemas en producción-Kueski LIMITED", "Introducción a GraphQL LIMITED", "Manufactura Cisco-Roberto Valdes LIMITED", "Creando aplicaciones en tiempo real usando JavaScript isomórfico. LIMITED", "¿Cómo desarrollar mi videojuego en México? LIMITED", "El Arte de la Clonación Virtual LIMITED", "Modulo 1 - Primeros pasos con Google Cloud Platform LIMITED", "Modulo 2 - Infraestructura y Datastore con Google Cloud LIMITED", "Startup Offices Hours LIMITED", "Modulo 3 - Máquinas virtuales, Big Data & Machine Learning con Google Cloud LIMITED", "Nuevos instrumentos electronicos- Enrique Garcia Alcala LIMITED", " Negociando con talento", "Arturo Black Fonseca & Mauricio Lezama Infografía, ¿Con qué se come? LIMITED", "El caótico y genial proceso para desarrollar un proyecto animado. (animación 2d) LIMITED", "Llegas Pacheco-De la idea al post en redes sociales LIMITED", "BodyPaint en vivo LIMITED", "Tu Primer Chatbot: Automatiza y Personaliza LIMITED"};

    int [] timeTaller = {15, 16, 18, 20, 22, 24, 10, 14, 16, 18, 12, 15, 16, 20, 20, 22, 24};

    String [] zoneTaller = {"B3", "B3", "B3", "B3", "B3", "B3", "B3", "B4", "B4", "B4", "C3", "C3", "C3", "C3", "C3", "C3", "C3" };

    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    SharedPreferences sharedPreferences;

    List<EventsInfo> prefe = new ArrayList();

    Calendar calander;

    SimpleDateFormat simpleDateFormat;

    AdapterEvents adapterEvents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_preferences, container, false);
        Log.d("time", currentDateTimeString);

        recyclerViewEvents = v.findViewById(R.id.rvEvents);
        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(getActivity()));

        sharedPreferences = PreferencesFragment.this.getActivity().getSharedPreferences("Info", Context.MODE_PRIVATE);
        int index = sharedPreferences.getInt("Preferencias", 2);

        showPreferences(index);

        Log.d("Size", prefe.get(0).getNameEvent());
        adapterEvents = new AdapterEvents(prefe, getActivity());
        recyclerViewEvents.setAdapter(adapterEvents);

        return v;
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

    public String fixTime(int time){
        String fixed;
        if(time == 24)
            fixed = "00:00";
        else
            fixed = String.valueOf(time) + ":" + "00";
        return fixed;

    }

    public void showPreferences(int i){
        switch (i){
            //Tematicas
            case 1:
                for(int j = 0; j<eventosTematica.length; j++){
                    calander = Calendar.getInstance();
                    simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    String times = simpleDateFormat.format(calander.getTime());
                    int actualHour = Integer.parseInt(times.substring(0, 2));
                    if(actualHour < timeTematica[j]){
                        EventsInfo eventsInfo = new EventsInfo(eventosTematica[j], fixTime(timeTematica[j]), zoneTematica[j]);
                        prefe.add(eventsInfo);
                    }
                }
                break;
                //Comunidades
            case 2:
                for(int j = 0; j<eventosComunidades.length; j++){
                    calander = Calendar.getInstance();
                    simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    String times = simpleDateFormat.format(calander.getTime());
                    int actualHour = Integer.parseInt(times.substring(0, 2));
                    if(actualHour < timeTematica[j]){
                        EventsInfo eventsInfo = new EventsInfo(eventosComunidades[j], fixTime(timeComunidades[j]), zoneComunidades[j]);
                        prefe.add(eventsInfo);
                    }
                }
                break;
                //Taller
            case 3:
                for(int j = 0; j<eventosTaller.length; j++){
                    calander = Calendar.getInstance();
                    simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    String times = simpleDateFormat.format(calander.getTime());
                    int actualHour = Integer.parseInt(times.substring(0, 2));
                    if(actualHour < timeTaller[j]){
                        EventsInfo eventsInfo = new EventsInfo(eventosTaller[j], fixTime(timeTaller[j]), zoneTaller[j]);
                        prefe.add(eventsInfo);
                    }
                }
                break;
            default:
                break;
        }
    }

}
