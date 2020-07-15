package com.caribejobs.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.caribejobs.R;
import com.caribejobs.model.Reference;
import com.caribejobs.model.UserProfession;

import java.util.ArrayList;

public class ReferencesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private ArrayList<Reference> references;
    private ReferencesRecyclerViewAdapter.OnClickListener onClickListener;

    public ReferencesRecyclerViewAdapter(Activity context, ArrayList<Reference> references, OnClickListener onClickListener) {
        this.context = context;
        this.references = references;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.reference_item, parent, false);
        return new ReferencesRecyclerViewAdapter.ReferencesRecyclerViewHolder(rootView, onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Reference reference = references.get(position);
        ReferencesRecyclerViewHolder referencesRecyclerViewHolder = (ReferencesRecyclerViewHolder) holder;
        referencesRecyclerViewHolder.lastjob.setText(reference.getLastJob());
        referencesRecyclerViewHolder.name.setText(reference.getFirstname() + " " + reference.getLastname() + " " + reference.getLastname2());
        referencesRecyclerViewHolder.phonenumber.setText(reference.getPhonenumber());
    }

    @Override
    public int getItemCount() {
        return references.size();
    }

    class ReferencesRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView lastjob;
        TextView name;
        TextView phonenumber;
        ReferencesRecyclerViewAdapter.OnClickListener onClickListener;

        public ReferencesRecyclerViewHolder(@NonNull View itemView, ReferencesRecyclerViewAdapter.OnClickListener onClickListener) {
            super(itemView);
            lastjob = itemView.findViewById(R.id.jobname);
            name = itemView.findViewById(R.id.complete_name);
            phonenumber = itemView.findViewById(R.id.phonenumber);
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
