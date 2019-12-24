package fr.utt.if26.loltool_frontend.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "followers", primaryKeys = {"user_name", "summonerName"})
public class Follower {

    @NonNull
    @ColumnInfo(name = "user_name")
    private String userName;

    @NonNull
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
