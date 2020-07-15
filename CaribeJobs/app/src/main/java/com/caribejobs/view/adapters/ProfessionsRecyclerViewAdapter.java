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

import java.util.ArrayList;

public class ProfessionsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity context;
    private ArrayList<UserProfession> professions;
    private OnClickListener onClickListener;

    public ProfessionsRecyclerViewAdapter(Activity context, ArrayList<UserProfession> professions, OnClickListener onClickListener) {
        this.context = context;
        this.professions = professions;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.profession_item, parent, false);
        return new ProfessionRecyclerViewHolder(rootView, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserProfession userProfession = professions.get(position);
        ProfessionRecyclerViewHolder viewHolder = (ProfessionRecyclerViewHolder) holder;
        viewHolder.professionname.setText(userProfession.getProfession().getProfessionname());
        String message = "año";
        if (userProfession.getExperienceyears() != 1) {
            message+="s";
        }
        viewHolder.experienceyears.setText(userProfession.getExperienceyears() + " " + message + " de experiencia");
        if (userProfession.isaNegociar()) {
            viewHolder.costperhour.setText("A negociar costo por hora");
        } else {
            viewHolder.costperhour.setText(Double.toString(userProfession.getCostperhour()) + " costo por hora");
        }
        viewHolder.details.setText(userProfession.getDetails());
        viewHolder.photosCount.setText(userProfession.getReferencePictures().size() + " fotos");
    }

    @Override
    public int getItemCount() {
        return professions.size();
    }

    public ArrayList<UserProfession> getProfessions() {
        return professions;
    }

    class ProfessionRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView professionname;
        TextView experienceyears;
        TextView costperhour;
        TextView details;
        TextView photosCount;
        OnClickListener onClickListener;

        public ProfessionRecyclerViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);
            professionname = itemView.findViewById(R.id.professionname);
            experienceyears = itemView.findViewById(R.id.experienceyears);
            costperhour = itemView.findViewById(R.id.costperhour);
            details = itemView.findViewById(R.id.details);
            photosCount = itemView.findViewById(R.id.photosCount);
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


