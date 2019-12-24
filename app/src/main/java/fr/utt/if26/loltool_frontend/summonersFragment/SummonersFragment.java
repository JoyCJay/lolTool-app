package fr.utt.if26.loltool_frontend.summonersFragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import fr.utt.if26.loltool_frontend.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummonersFragment extends Fragment {

    private String userName;

    public SummonersFragment(String userName) {
        this.userName = userName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summoners, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SectionsPageAdapter sectionsPageAdapter = new SectionsPageAdapter(getChildFragmentManager());
        sectionsPageAdapter.addFragment(new Tab1Fragment(userName), "Search Summoner");
        sectionsPageAdapter.addFragment(new Tab2Fragment(userName), "Follower List");

        ViewPager viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPageAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
