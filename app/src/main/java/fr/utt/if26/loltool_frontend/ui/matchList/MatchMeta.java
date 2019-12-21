package fr.utt.if26.loltool_frontend.ui.matchList;

import org.json.JSONException;
import org.json.JSONObject;

public class MatchMeta {
    public String gameId;
    public String date;
    public String champion;
    public String duration;
    public String winTeam;
    public String accountId;

    public MatchMeta(JSONObject json){
        try {
            this.accountId = json.getString("accountId");
            this.gameId = json.getString("gameId");
            this.date = json.getString("date");
            this.champion = json.getString("champion");
            this.duration = json.getString("duration");
            this.winTeam = json.getString("winTeam");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
