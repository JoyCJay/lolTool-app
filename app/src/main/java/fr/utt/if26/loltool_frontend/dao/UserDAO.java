package fr.utt.if26.loltool_frontend.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.utt.if26.loltool_frontend.entity.User;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addUser(User user);

    @Query("select * from user")
    List<User> getUsers();
}
