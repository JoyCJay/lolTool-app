package fr.utt.if26.loltool_frontend.summonersFragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.utt.if26.loltool_frontend.MainActivity;
import fr.utt.if26.loltool_frontend.R;
import fr.utt.if26.loltool_frontend.entity.Follower;
import fr.utt.if26.loltool_frontend.entity.Summoner;

/**
 * Created by User on 2/28/2017.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private RecyclerView recyclerView;
    private Tab2Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String userName;
    private List<Summoner> followerList;

    Tab2Fragment(String userName) {
        this.userName = userName;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        followerList = new ArrayList<>();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);

        recyclerView = view.findViewById(R.id.followRecyclerView);
        adapter = new Tab2Adapter(getContext(), followerList);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeRefreshLayout = view.findViewById(R.id.swipe_follower_list);

        recyclerView.setAdapter(adapter);

        initRefreshListener();

        return view;
    }

    private void initData() {
        followerList.clear();
        List<Follower> followers = MainActivity.myDataBase.followerDAO().getFollowersByUserName(userName);

        for(Follower follower : followers){
            followerList.add(MainActivity.myDataBase.summonerDAO().getSummonerBySummonerName(follower.getSummonerName()));
        }

        Log.i("follower size ", String.valueOf(followerList.size()));

    }

    private void initRefreshListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }
}
