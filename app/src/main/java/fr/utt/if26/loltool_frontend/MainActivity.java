package fr.utt.if26.loltool_frontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fr.utt.if26.loltool_frontend.database.MyDataBase;
import fr.utt.if26.loltool_frontend.entity.MatchMeta;
import fr.utt.if26.loltool_frontend.entity.Summoner;
import fr.utt.if26.loltool_frontend.entity.User;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static FragmentManager fragmentManager;
    public static MyDataBase myDataBase;
    public static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            Log.d(TAG, "onCreate() Restoring previous state");
        }else{
            Log.d(TAG, "onCreate() No save state available");
        }

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDataBase = Room.databaseBuilder(getApplicationContext(), MyDataBase.class, "my_db").allowMainThreadQueries().build();
        saveUsers(myDataBase);
        saveSummoners(myDataBase);
        saveMatchMetas(myDataBase);

        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();

                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new LoginFragment())
                        .commit();
                break;
        }
        return true;
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

    private void saveMatchMetas(MyDataBase dataBase) {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
        try {
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4252392862", "JoyCJay", "81", sdf.parse("2019-10-29 08:49:18"), 17, 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4263199914", "JoyCJay", "81", sdf.parse("2019-11-04 21:15:13"), 17, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274556047", "JoyCJay", "121", sdf.parse("2019-11-12 00:27:41"), 3 , 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274609270", "JoyCJay", "121", sdf.parse("2019-11-12 00:48:58"), 17, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4286905204", "JoyCJay", "121", sdf.parse("2019-11-20 06:18:55"), 35, 0));

            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4252392862", "haoyang", "81", sdf.parse("2019-10-29 08:49:18"), 22, 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4263199914", "haoyang", "123", sdf.parse("2019-11-04 21:15:13"), 19, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274556047", "haoyang", "107", sdf.parse("2019-11-12 00:27:41"), 3 , 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274609270", "haoyang", "121", sdf.parse("2019-11-12 00:48:58"), 22, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4286905204", "haoyang", "121", sdf.parse("2019-11-20 06:18:55"), 35, 0));

            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4252392862", "fy", "81", sdf.parse("2019-11-20 06:18:55"), 22, 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4263199914", "fy", "123", sdf.parse("2019-11-20 06:18:55"), 26, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274556047", "fy", "35", sdf.parse("2019-11-20 06:18:55"), 33, 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274609270", "fy", "121", sdf.parse("2019-11-20 06:18:55"), 18, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4286905204", "fy", "6", sdf.parse("2019-11-20 06:18:55"), 35, 0));

            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4286905204", "ame", "6", sdf.parse("2019-11-20 06:18:55"), 45, 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4263199914", "ame", "6", sdf.parse("2019-11-20 06:18:55"), 26, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274556047", "ame", "6", sdf.parse("2019-11-20 06:18:55"), 48, 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274609270", "ame", "6", sdf.parse("2019-11-20 06:18:55"), 18, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4286905204", "ame", "6", sdf.parse("2019-11-20 06:18:55"), 19, 0));

            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4286905204", "maybe", "38", sdf.parse("2019-11-20 06:18:55"), 45, 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4263199914", "maybe", "123", sdf.parse("2019-11-20 06:18:55"), 26, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274556047", "maybe", "35", sdf.parse("2019-11-20 06:18:55"), 38, 0));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4274609270", "maybe", "76", sdf.parse("2019-11-20 06:18:55"), 18, 1));
            dataBase.matchMetaDAO().addMatchMeta(new MatchMeta("4286905204", "maybe", "6", sdf.parse("2019-11-20 06:18:55"), 35, 0));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
