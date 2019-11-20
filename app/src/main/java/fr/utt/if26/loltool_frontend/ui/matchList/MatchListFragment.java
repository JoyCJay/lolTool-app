package fr.utt.if26.loltool_frontend.ui.matchList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import fr.utt.if26.loltool_frontend.R;

public class MatchListFragment extends Fragment {

//    private MatchListViewModel galleryViewModel;
    MatchListPagerAdapter matchListPagerAdapter;
    ViewPager viewPager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        galleryViewModel =
//                ViewModelProviders.of(this).get(MatchListViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_matchlist, container, false);
//        final TextView textView = root.findViewById(R.id.text_matchlist);
//        galleryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
        return inflater.inflate(R.layout.fragment_matchlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        matchListPagerAdapter = new MatchListPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(matchListPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}