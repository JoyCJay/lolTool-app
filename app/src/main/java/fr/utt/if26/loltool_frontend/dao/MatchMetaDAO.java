package fr.utt.if26.loltool_frontend.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.utt.if26.loltool_frontend.entity.MatchMeta;

@Dao
public interface MatchMetaDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addMatchMeta(MatchMeta matchMeta);

    @Query("select * from match_meta where summoner_name = :summonerName")
    List<MatchMeta> getMatchMetasBySummonerName(String summonerName);
}
