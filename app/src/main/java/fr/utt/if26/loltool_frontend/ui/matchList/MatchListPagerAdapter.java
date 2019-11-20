package fr.utt.if26.loltool_frontend.ui.matchList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import fr.utt.if26.loltool_frontend.ui.matchList.Tab2;

public class MatchListPagerAdapter extends FragmentStatePagerAdapter {

    public MatchListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    Tab[] fragments = {new SummonerInfo(), new Tab2()};

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
