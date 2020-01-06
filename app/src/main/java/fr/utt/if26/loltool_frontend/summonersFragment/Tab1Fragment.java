package fr.utt.if26.loltool_frontend.summonersFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import fr.utt.if26.loltool_frontend.MainActivity;
import fr.utt.if26.loltool_frontend.R;
import fr.utt.if26.loltool_frontend.entity.Follower;
import fr.utt.if26.loltool_frontend.entity.Summoner;

/**
 * Created by User on 2/28/2017.
 */

public class Tab1Fragment extends Fragment implements SearchView.OnQueryTextListener {
//    private static final String TAG = "Tab1Fragment";

    private TextView tvWelcome;
    private SearchView searchView;
    private TextView tvSummonerName;
    private TextView tvLevel;
    private TextView tvRevisionDate;
    private Button btnFollow;
    private Switch switchHide;
    private Button btnExport;

    private String userName;
    private String searchName;
    private boolean isSummonerExist;

    Tab1Fragment(String userName) {
        this.userName = userName;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);

        Log.i("test", this.userName);

        initUI(view);
        setSwitchHide();

        searchView.setOnQueryTextListener(this);

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String summonerName = searchName;

                Log.i("summonerName ", summonerName);

                Follower follower = new Follower(userName, summonerName);
                MainActivity.myDataBase.followerDAO().addFollower(follower);
                Toast.makeText(getActivity(), "summoner is added in follower list", Toast.LENGTH_SHORT).show();
            }
        });

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                export(view);
            }
        });

        return view;
    }

    private void setSwitchHide() {
        boolean isVisible = MainActivity.myDataBase.summonerDAO().checkVivible(userName);
        if(isVisible) switchHide.setChecked(false);
        else switchHide.setChecked(true);

        switchHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    MainActivity.myDataBase.summonerDAO().setSummonerUnVisible(userName);
                }else{
                    MainActivity.myDataBase.summonerDAO().setSummonerVisible(userName);
                }
            }
        });
    }

    private void initUI(View view) {
        tvWelcome = view.findViewById(R.id.tv_welcome);
        tvWelcome.setText("Welcome "+userName);
        searchView = view.findViewById(R.id.sv_summoner_info);
        tvSummonerName = view.findViewById(R.id.tv_summonerName);
        tvLevel = view.findViewById(R.id.tv_level);
        tvRevisionDate = view.findViewById(R.id.tv_revision_date);
        btnFollow = view.findViewById(R.id.btnFollow);
        btnFollow.setEnabled(false);
        switchHide = view.findViewById(R.id.switchHide);
        btnExport = view.findViewById(R.id.btnExport);

        isSummonerExist = false;

        searchView.setQueryHint("Please enter a summoner name");
    }


    @Override
    public boolean onQueryTextSubmit(String summonerName) {
        isSummonerExist = false;
        List<Summoner> summoners  = MainActivity.myDataBase.summonerDAO().getSummoners();
        for (Summoner summoner : summoners){
            if(summonerName.equals(summoner.getSummonerName()) && summoner.isVisible()){
                Toast.makeText(getActivity(), "summoner exists", Toast.LENGTH_SHORT).show();
                tvSummonerName.setText("Summoner Name: "+summoner.getSummonerName());
                tvLevel.setText("Level: "+summoner.getLevel());
                tvRevisionDate.setText("Revision Date: "+summoner.getRevisionDate());
                isSummonerExist = true;
                searchName = summoner.getSummonerName();
            }
        }
        if(!isSummonerExist)  Toast.makeText(getActivity(), "summoner doesn't exist", Toast.LENGTH_SHORT).show();
        btnFollow.setEnabled(isSummonerExist);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public void export(View view){
        StringBuilder data = new StringBuilder();
        data.append("Your Followers");
        List<Follower> followers = MainActivity.myDataBase.followerDAO().getFollowersByUserName(userName);
        for(Follower follower : followers){
            data.append("\n").append(follower.getSummonerName());
        }

        try{
            FileOutputStream out = Objects.requireNonNull(getContext()).openFileOutput("data.csv", Context.MODE_PRIVATE);
            out.write(data.toString().getBytes());
            out.close();

            Context context = getContext();
            File fileLocation = new File(context.getFilesDir(), "data.csv");
            Uri path = FileProvider.getUriForFile(context, "fr.utt.if26.loltool_frontend.fileProvider", fileLocation);
            Intent fileIntent = new Intent(Intent.ACTION_SEND);
            fileIntent.setType("text/csv");
            fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            fileIntent.putExtra(Intent.EXTRA_STREAM, path);
            startActivity(Intent.createChooser(fileIntent, "Send mail"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
