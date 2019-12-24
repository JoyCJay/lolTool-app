package fr.utt.if26.loltool_frontend.summonersFragment;

import java.util.Date;

public class Tab2ListItem {
    private String summonerName;
    private int level;
    private Date revisionDate;

    public Tab2ListItem(String summonerName, int level, Date revisionDate) {
        this.summonerName = summonerName;
        this.level = level;
        this.revisionDate = revisionDate;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public int getLevel() {
        return level;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }
}
