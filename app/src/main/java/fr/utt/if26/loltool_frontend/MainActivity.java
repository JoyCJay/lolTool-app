package fr.utt.if26.loltool_frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.Date;

import fr.utt.if26.loltool_frontend.database.MyDataBase;
import fr.utt.if26.loltool_frontend.entity.Summoner;
import fr.utt.if26.loltool_frontend.entity.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static FragmentManager fragmentManager;
    public static MyDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");

        myDataBase = Room.databaseBuilder(getApplicationContext(), MyDataBase.class, "my_db").allowMainThreadQueries().build();
        saveUsers(myDataBase);
        saveSummoners(myDataBase);

        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
        }
    }

    private void saveUsers(MyDataBase dataBase) {
        dataBase.userDAO().addUser(new User("JoyCJay", "1234"));
        dataBase.userDAO().addUser(new User("haoyang", "4321"));
    }

    private void saveSummoners(MyDataBase dataBase) {
        dataBase.summonerDAO().addSummoner(new Summoner("JoyCJay", 184, new Date(), true));
        dataBase.summonerDAO().addSummoner(new Summoner("haoyang", 14, new Date(), true));
        dataBase.summonerDAO().addSummoner(new Summoner("ame", 282, new Date(), true));
        dataBase.summonerDAO().addSummoner(new Summoner("fy", 433, new Date(), true));
        dataBase.summonerDAO().addSummoner(new Summoner("maybe", 524, new Date(), true));
    }

}
