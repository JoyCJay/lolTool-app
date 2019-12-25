package fr.utt.if26.loltool_frontend.matchMetaFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.loltool_frontend.R;
import fr.utt.if26.loltool_frontend.entity.MatchMeta;

public class MatchMetaAdapter extends RecyclerView.Adapter<MatchMetaAdapter.MyViewHolder> {

    Context context;
    List<MatchMeta> matchMetaList;

    public MatchMetaAdapter(Context context, List<MatchMeta> matchMetaList) {
        this.context = context;
        this.matchMetaList = matchMetaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.match_meta_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MatchMeta matchMeta = matchMetaList.get(position);
        holder.tvSummonerName.setText("Summoner Name: "+matchMeta.getSummonerName());
        holder.tvMatchId.setText("Match Id: "+matchMeta.getMatchId());
        holder.tvChampion.setText("Champion: "+matchMeta.getChampion());
        holder.tvDuration.setText("Duration: "+matchMeta.getDuration()+ " min");
        holder.tvmatchDate.setText("Match Date: "+matchMeta.getMatchDate());

        if(matchMeta.getResult() == 0) holder.tvResult.setText("Result: win");
        else holder.tvResult.setText("Result: lose");
    }

    @Override
    public int getItemCount() {
        return matchMetaList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout itemMatchMeta;
        private TextView tvSummonerName;
        private TextView tvMatchId;
        private TextView tvChampion;
        private TextView tvDuration;
        private TextView tvmatchDate;
        private TextView tvResult;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemMatchMeta = itemView.findViewById(R.id.match_meta_item_id);
            tvSummonerName = itemView.findViewById(R.id.tv_match_meta_summonerName);
            tvMatchId = itemView.findViewById(R.id.tv_match_meta_match_id);
            tvChampion = itemView.findViewById(R.id.tv_match_meta_champion);
            tvDuration = itemView.findViewById(R.id.tv_match_meta_duration);
            tvmatchDate = itemView.findViewById(R.id.tv_match_meta_match_date);
            tvResult = itemView.findViewById(R.id.tv_match_meta_result);
        }
    }
}
