package com.leones.talentguide.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leones.talentguide.R;
import com.leones.talentguide.model.EventsInfo;

import java.util.List;

/**
 * Created by Diegormz on 05/04/2018.
 */

public class AdapterEvents extends RecyclerView.Adapter<AdapterEvents.EventHolder>{

    private List<EventsInfo> listData;
    private LayoutInflater inflater;

    public AdapterEvents(List<EventsInfo> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public AdapterEvents.EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_view_events, parent, false);
        return new AdapterEvents.EventHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterEvents.EventHolder holder, int position) {
        EventsInfo item = listData.get(position);
        holder.tvName.setText(item.getNameEvent());
        holder.tvTime.setText(item.getTime());
        holder.tvZone.setText(item.getZone());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvName;
        private TextView tvTime;
        private TextView tvZone;


        public EventHolder(View itemView){
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvNameEvent);
            tvTime = (TextView) itemView.findViewById(R.id.tvStartEvent);
            tvZone = (TextView) itemView.findViewById(R.id.tvZone);

        }

        @Override
        public void onClick(View v) {

        }
    }

}

