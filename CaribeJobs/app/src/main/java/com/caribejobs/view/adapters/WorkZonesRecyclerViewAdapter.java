package com.caribejobs.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caribejobs.R;
import com.caribejobs.model.UserProfession;
import com.caribejobs.model.WorkZone;

import java.util.ArrayList;

public class WorkZonesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private ArrayList<WorkZone> workZones;
    private WorkZonesRecyclerViewAdapter.OnClickListener onClickListener;

    public WorkZonesRecyclerViewAdapter(Activity context, ArrayList<WorkZone> workZones, OnClickListener onClickListener) {
        this.context = context;
        this.workZones = workZones;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.work_zone_item, parent, false);
        return new WorkZonesRecyclerViewAdapter.WorkZoneRecyclerViewHolder(rootView, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WorkZone workZone = workZones.get(position);
        WorkZoneRecyclerViewHolder workZoneRecyclerViewHolder = (WorkZoneRecyclerViewHolder) holder;
        workZoneRecyclerViewHolder.provincia.setText(workZone.getProvincia());
        workZoneRecyclerViewHolder.canton.setText(workZone.getCanton());
    }

    @Override
    public int getItemCount() {
        return workZones.size();
    }

    class WorkZoneRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView provincia;
        TextView canton;
        WorkZonesRecyclerViewAdapter.OnClickListener onClickListener;

        public WorkZoneRecyclerViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            provincia = itemView.findViewById(R.id.provinciaLB);
            canton = itemView.findViewById(R.id.cantonLB);
            this.onClickListener = onClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(getAdapterPosition());
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }
}
