package fr.utt.if26.loltool_frontend.summonersFragment;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.loltool_frontend.MainActivity;
import fr.utt.if26.loltool_frontend.R;
import fr.utt.if26.loltool_frontend.entity.Follower;
import fr.utt.if26.loltool_frontend.entity.Summoner;
import fr.utt.if26.loltool_frontend.matchMetaFragment.MatchMetaFragment;

public class Tab2Adapter extends RecyclerView.Adapter<Tab2Adapter.MyViewHolder> {

    Context context;
    List<Summoner> tab2ListItems;
    Dialog myDialog;
    String userName;

    public Tab2Adapter(Context context, List<Summoner> tab2ListItems, String userName) {
        this.context = context;
        this.tab2ListItems = tab2ListItems;
        this.userName = userName;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.follower_item, parent, false);

        final MyViewHolder viewHolder = new MyViewHolder(v);
        setViewHolderListener(viewHolder);

        return viewHolder;
    }

    private void setViewHolderListener(final MyViewHolder viewHolder) {
        viewHolder.item_follower.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.dialog_follower);
                TextView tvDialogName = myDialog.findViewById(R.id.tv_dialog);
                tvDialogName.setText(tab2ListItems.get(viewHolder.getAdapterPosition()).getSummonerName());
                Button btnInfo = myDialog.findViewById(R.id.dialog_btn_info);
                Button btnDelete = myDialog.findViewById(R.id.dialog_btn_delete);

                btnInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new MatchMetaFragment(tab2ListItems.get(viewHolder.getAdapterPosition()).getSummonerName()))
                                .addToBackStack(null).commit();
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String summonerName = tab2ListItems.get(viewHolder.getAdapterPosition()).getSummonerName();
                        List<Follower> followers = MainActivity.myDataBase.followerDAO().getFollowersByUserName(userName);

                        for(Follower follower : followers){
                            if(follower.getSummonerName().equals(summonerName)) {
                                MainActivity.myDataBase.followerDAO().deleteFollower(follower);
                                Toast.makeText(context, "delete successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                });

                myDialog.show();
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Summoner summoner = tab2ListItems.get(position);

//        Log.i("summoner ", summoner.getSummonerName());

        holder.tvSummonerName.setText("SummonerName: "+summoner.getSummonerName());
        holder.tvLevel.setText("Level: "+summoner.getLevel());
        holder.tvRevisionDate.setText("Revision Date: "+summoner.getRevisionDate());

    }

    @Override
    public int getItemCount() {
        return tab2ListItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_follower;
        private TextView tvSummonerName;
        private TextView tvLevel;
        private TextView tvRevisionDate;

        public MyViewHolder(View itemView) {
            super(itemView);

            item_follower = itemView.findViewById(R.id.follower_item_id);
            tvSummonerName = itemView.findViewById(R.id.tv_follower_summonerName);
            tvLevel = itemView.findViewById(R.id.tv_follower_level);
            tvRevisionDate = itemView.findViewById(R.id.tv_follower_revision_date);
        }
    }
}
