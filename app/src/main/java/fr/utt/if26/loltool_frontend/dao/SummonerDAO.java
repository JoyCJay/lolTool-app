package fr.utt.if26.loltool_frontend.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.loltool_frontend.entity.Summoner;

@Dao
public interface SummonerDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addSummoner(Summoner summoner);

    @Query("select * from summoners")
    List<Summoner> getSummoners();

    @Query("select * from summoners where summoner_name = :summonerName")
    Summoner getSummonerBySummonerName(String summonerName);

    @Query("select isVisible from summoners where summoner_name = :summonerName")
    boolean checkVivible(String summonerName);

    @Query("update summoners set isVisible=1 where summoner_name = :summonerName")
    void setSummonerVisible(String summonerName);

    @Query("update summoners set isVisible=0 where summoner_name = :summonerName")
    void setSummonerUnVisible(String summonerName);
}
