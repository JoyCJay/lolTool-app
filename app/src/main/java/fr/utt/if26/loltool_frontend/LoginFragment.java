package fr.utt.if26.loltool_frontend;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.utt.if26.loltool_frontend.entity.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView txtUserName;
    private TextView txtPassword;
    private Button btnLogin;

    private String userName;

    public LoginFragment() {
        // Required empty public constructor
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtUserName = view.findViewById(R.id.txt_user_name);
        txtPassword = view.findViewById(R.id.txt_password);
        btnLogin = view.findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();

                List<User> users  = MainActivity.myDataBase.userDAO().getUsers();
                for (User user : users){
                    if(userName.equals(user.getUserName()) && password.equals(user.getPassword())){
                        Toast.makeText(getActivity(), "exist", Toast.LENGTH_SHORT).show();
                        setUserName(userName);
                    }
                }
            }
        });

         return view;
    }

}