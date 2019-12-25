package fr.utt.if26.loltool_frontend.matchMetaFragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.loltool_frontend.MainActivity;
import fr.utt.if26.loltool_frontend.R;
import fr.utt.if26.loltool_frontend.entity.MatchMeta;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchMetaFragment extends Fragment {

    private RecyclerView recyclerView;
    private MatchMetaAdapter adapter;
    private List<MatchMeta> matchMetaList;
    private String summonerName;

    public MatchMetaFragment(String summonerName) {
        // Required empty public constructor
        this.summonerName = summonerName;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_match_meta,container,false);

        recyclerView = view.findViewById(R.id.match_meta_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initData();


        adapter = new MatchMetaAdapter(getContext(), matchMetaList);
        recyclerView.setAdapter(adapter);

        return view;
    }


    private void initData() {
        matchMetaList = new ArrayList<>();
        Log.i("summonerName222", this.summonerName);
        matchMetaList = MainActivity.myDataBase.matchMetaDAO().getMatchMetasBySummonerName(this.summonerName);
        Log.i("matchmeta", String.valueOf(MainActivity.myDataBase.matchMetaDAO().getMatchMetasBySummonerName(this.summonerName).size()));
    }
}
