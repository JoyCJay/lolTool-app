package fr.utt.if26.loltool_frontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;

import fr.utt.if26.loltool_frontend.database.MyDataBase;
import fr.utt.if26.loltool_frontend.entity.User;

public class MainActivity extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    public static MyDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataBase = Room.databaseBuilder(getApplicationContext(), MyDataBase.class, "my_db").allowMainThreadQueries().build();
        saveUsers(myDataBase);

        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.fragment_container) != null){
            if (savedInstanceState != null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new LoginFragment()).commit();
        }
    }

    private void saveUsers(MyDataBase usersDataBase) {
        usersDataBase.userDAO().addUser(new User("JoyCJay", "1234"));
        usersDataBase.userDAO().addUser(new User("haoyang", "4321"));
    }


}
