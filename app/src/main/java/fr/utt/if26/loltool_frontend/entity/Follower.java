package fr.utt.if26.loltool_frontend.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "follower", primaryKeys = {"user_name", "summoner_name"})
public class Follower {

    @NonNull
    @ColumnInfo(name = "user_name")
    private String userName;

    @NonNull
    @ColumnInfo(name = "summoner_name")
    private String summonerName;

    public Follower(@NonNull String userName, @NonNull String summonerName) {
        this.userName = userName;
        this.summonerName = summonerName;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    @NonNull
    public String getSummonerName() {
        return summonerName;
    }
}
