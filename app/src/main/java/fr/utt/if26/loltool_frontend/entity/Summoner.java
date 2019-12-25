package fr.utt.if26.loltool_frontend.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import fr.utt.if26.loltool_frontend.converter.DateConverter;

@Entity(tableName = "summoner")
public class Summoner {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "summoner_name")
    private String summonerName;
    private Integer level;
    @TypeConverters(DateConverter.class)
    private Date revisionDate;
    private boolean isVisible;

    public Summoner(@NonNull String summonerName, Integer level, Date revisionDate, boolean isVisible) {
        this.summonerName = summonerName;
        this.level = level;
        this.revisionDate = revisionDate;
        this.isVisible = isVisible;
    }

    @NonNull
    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(@NonNull String summonerName) {
        this.summonerName = summonerName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
