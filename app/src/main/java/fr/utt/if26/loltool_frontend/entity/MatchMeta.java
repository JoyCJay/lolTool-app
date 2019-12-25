package fr.utt.if26.loltool_frontend.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverters;

import java.util.Date;

import fr.utt.if26.loltool_frontend.converter.DateConverter;

@Entity(tableName = "match_meta", primaryKeys = {"match_id", "summoner_name"})
public class MatchMeta {

    @NonNull
    @ColumnInfo(name = "match_id")
    private String matchId;
    @NonNull
    @ColumnInfo(name = "summoner_name")
    private String summonerName;

    private String champion;
    private Integer duration;

    @ColumnInfo(name = "match_date")
    @TypeConverters(DateConverter.class)
    private Date matchDate;
    private Integer result;

    public MatchMeta(@NonNull String matchId, @NonNull String summonerName, String champion, Date matchDate, Integer duration, int result) {
        this.summonerName = summonerName;
        this.matchId = matchId;
        this.champion = champion;
        this.duration = duration;
        this.matchDate = matchDate;
        this.result = result;
    }

    @NonNull
    public String getMatchId() {
        return matchId;
    }

    @NonNull
    public String getSummonerName() {
        return summonerName;
    }

    public String getChampion() {
        return champion;
    }

    public Integer getDuration() {
        return duration;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public Integer getResult() {
        return result;
    }
}
