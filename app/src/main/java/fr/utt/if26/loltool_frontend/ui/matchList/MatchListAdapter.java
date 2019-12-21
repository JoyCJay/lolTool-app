package fr.utt.if26.loltool_frontend.ui.matchList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.utt.if26.loltool_frontend.R;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder> {
    private ArrayList<MatchMeta> mDataSet;

    public MatchListAdapter(ArrayList<MatchMeta> data){
        mDataSet = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView match_textView;
        public final TextView gameID_textView;
        public final TextView date_textView;
        public final TextView duration_textView;
        public final TextView winTeam_textView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("matchList", "Element " + getAdapterPosition() + " clicked.");
                }
            });
            match_textView = v.findViewById(R.id.match_textView);
            gameID_textView = v.findViewById(R.id.gameID_textView);
            date_textView = v.findViewById(R.id.date_textView);
            duration_textView = v.findViewById(R.id.duration_textView);
            winTeam_textView = v.findViewById(R.id.winTeam_textView);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragement_matchlist_match_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.match_textView.setText("Match #"+position);
        holder.gameID_textView.setText(holder.gameID_textView.getText() + mDataSet.get(position).gameId);
        holder.date_textView.setText(holder.date_textView.getText() + mDataSet.get(position).date);
        holder.duration_textView.setText(holder.duration_textView.getText() + mDataSet.get(position).duration);
        holder.winTeam_textView.setText(holder.winTeam_textView.getText() + mDataSet.get(position).winTeam);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
