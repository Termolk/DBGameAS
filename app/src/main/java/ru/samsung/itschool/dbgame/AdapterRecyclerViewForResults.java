package ru.samsung.itschool.dbgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerViewForResults extends RecyclerView.Adapter<AdapterRecyclerViewForResults.ViewHolder> {
    LayoutInflater layoutInflater;
    ArrayList<Result> results;

    public AdapterRecyclerViewForResults(ArrayList<Result> results) {
        this.results = results;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName;
        TextView textViewScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewScore = itemView.findViewById(R.id.textViewScore);
        }
    }


    @NonNull
    @Override
    public AdapterRecyclerViewForResults.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.activity_recycler_view_for_results, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerViewForResults.ViewHolder holder, int position) {
        TextView textViewUserName = holder.textViewUserName;
        TextView textViewScore = holder.textViewScore;

        String userName = results.get(position).name;
        String score = results.get(position).score + "";

        textViewUserName.setText(userName);
        textViewScore.setText(score);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


}
