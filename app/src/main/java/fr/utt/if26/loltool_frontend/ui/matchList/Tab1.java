package fr.utt.if26.loltool_frontend.ui.matchList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import fr.utt.if26.loltool_frontend.R;

public class Tab1 extends Fragment implements SearchView.OnQueryTextListener {

    private static final int GET_DATA_SUCCESS = 200; //获取数据成功的标志
    private Handler mHandler = null;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_matchlist_tab1, container, false);

        initUI(view);
        return view;
    }

    private void initUI(View view) {
        SearchView sv = view.findViewById(R.id.sv_summoner_info);
        final TextView tvSummonerName = view.findViewById(R.id.tv_summonerName);
        final TextView tvAccountId = view.findViewById(R.id.tv_accountId);
        final TextView tvLevel = view.findViewById(R.id.tv_level);
        final TextView tvRevisionDate = view.findViewById(R.id.tv_revision_date);

        //create handler object
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                if(message.what == GET_DATA_SUCCESS) {
                    String summonerInfoStr = message.getData().getString("summonerInfo");
                    if(summonerInfoStr != null){
                        Log.i("test", summonerInfoStr);
                        try {
                            JSONObject summonerInfoObj = new JSONObject(summonerInfoStr);
                            tvSummonerName.setText("Summoner Name: " + summonerInfoObj.getString("summonerName"));
                            tvAccountId.setText("Account Id: " + summonerInfoObj.getString("accountId"));
                            tvLevel.setText("Level: " + summonerInfoObj.getString("summonerLevel"));
                            tvRevisionDate.setText("Revision Date: " + summonerInfoObj.getString("revisionDate"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
                return false;
            }
        });

        sv.setQueryHint("Please enter your summoner name");
        sv.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String summonerName) {
        initData(summonerName);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    private void initData(final String summonerName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String summonerInfo = getSummonerInfo(summonerName);

                Bundle bundle = new Bundle();
                bundle.putString("summonerInfo", summonerInfo);
                //create message object
                Message message = Message.obtain();
                message.setData(bundle);
                message.what = GET_DATA_SUCCESS;
                //send message to main Thread
                mHandler.sendMessage(message);
            }
        }).start();
    }

    private String getSummonerInfo(String summonerName) {
        URL url;
        HttpURLConnection connection = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;

        try {
            url = new URL("http://10.0.2.2:9090/consult/summonerInfo/"+summonerName);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == 200){
                bis = new BufferedInputStream(connection.getInputStream());
                baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = bis.read(buffer)) != -1){
                    baos.write(buffer, 0, len);
                }
                return baos.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(baos != null) baos.close();
                if(bis != null) bis.close();
                if(connection != null) connection.disconnect();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return "";
    }
}
