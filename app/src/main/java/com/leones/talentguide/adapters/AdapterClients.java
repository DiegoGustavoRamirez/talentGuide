package com.leones.talentguide.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leones.talentguide.R;
import com.leones.talentguide.model.ClientInfo;

import java.util.List;

/**
 * Created by Diegormz on 05/04/2018.
 */

public class AdapterClients extends RecyclerView.Adapter<AdapterClients.ClientHolder>{

    private List<ClientInfo> listData;
    private LayoutInflater inflater;

    public AdapterClients(List<ClientInfo> listData, Context c){
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public AdapterClients.ClientHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_view_client, parent, false);
        return new AdapterClients.ClientHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterClients.ClientHolder holder, int position) {
        ClientInfo item = listData.get(position);
        holder.tvName.setText(item.getClientname());
        holder.tvTime.setText(item.getTimeConect());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ClientHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvName;
        private TextView tvTime;


        public ClientHolder(View itemView){
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvNameClient);
            tvTime = (TextView) itemView.findViewById(R.id.tvTimeConnect);


        }

        @Override
        public void onClick(View v) {

        }
    }

}
