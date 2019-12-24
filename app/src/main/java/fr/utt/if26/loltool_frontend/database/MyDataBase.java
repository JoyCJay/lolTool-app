package fr.utt.if26.loltool_frontend.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import fr.utt.if26.loltool_frontend.dao.FollowerDAO;
import fr.utt.if26.loltool_frontend.dao.SummonerDAO;
import fr.utt.if26.loltool_frontend.dao.UserDAO;
import fr.utt.if26.loltool_frontend.entity.Follower;
import fr.utt.if26.loltool_frontend.entity.Summoner;
import fr.utt.if26.loltool_frontend.entity.User;

@Database(entities = {User.class, Summoner.class, Follower.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    public abstract UserDAO userDAO();
    public abstract SummonerDAO summonerDAO();
    public abstract FollowerDAO followerDAO();

}
