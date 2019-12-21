package fr.utt.if26.loltool_frontend.ui.matchList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MatchListPagerAdapter extends FragmentStatePagerAdapter {

    public MatchListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    Tab[] fragments = {new SummonerInfo(), new MatchList()};

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position].getPageTitle();
    }
}
