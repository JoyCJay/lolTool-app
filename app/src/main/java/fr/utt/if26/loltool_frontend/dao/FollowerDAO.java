package fr.utt.if26.loltool_frontend.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.utt.if26.loltool_frontend.entity.Follower;

@Dao
public interface FollowerDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addFollower(Follower follower);

    @Query("select * from follower where user_name = :userName")
    List<Follower> getFollowersByUserName(String userName);

    @Delete
    void deleteFollower(Follower follower);

}
