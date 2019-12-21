package fr.utt.if26.loltool_frontend.ui.matchList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import fr.utt.if26.loltool_frontend.R;

public class MatchList extends Tab {
    public String pageTitle = "Matches";
    public int index = 1; // show first 5 games
    private ArrayList<MatchMeta> mDataSet = new ArrayList(5);

    private static final int GET_DATA_SUCCESS = 200;
    private Handler mHandler = null;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter matchListAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public String getPageTitle() {
        return this.pageTitle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_matchlist_matches, container, false);

        //create handler object
        initUI(rootView);
        initDataStr();
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                if(message.what == GET_DATA_SUCCESS) {
                    String matchesListStr = message.getData().getString("matches");
                    if(matchesListStr != null){
                        try {
                            JSONArray matchesListJSON = new JSONArray(matchesListStr);
                            for (int i = 0; i < matchesListJSON.length(); i++) {
                                JSONObject matchMetaJSON = matchesListJSON.getJSONObject(i).getJSONObject("meta");
                                mDataSet.add(i,new MatchMeta(matchMetaJSON));
                                Log.i("matchStr"+i, mDataSet.get(i).toString());
                            }
                            updateUI(rootView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }
        });
        return rootView;
    }

    private void initUI(View view){
        final TextView status = view.findViewById(R.id.matchList_status_textView);
        status.setText("non data, log in first");
    }

    private void initDataStr(){
        //子线程请求数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                String matchesStr = getMatchesList();
                Bundle bundle = new Bundle();
                bundle.putString("matches", matchesStr);
                //create message object
                Message message = Message.obtain();
                message.setData(bundle);
                message.what = GET_DATA_SUCCESS;
                //send message to main Thread
                mHandler.sendMessage(message);
            }
        }).start();
    }

    private String getMatchesList() {
        URL url;
        HttpURLConnection connection = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;

        try {
            url = new URL("http://10.0.2.2:9090/consult/matches/-2ZuBG00UOZvnD2Py9BUSCPi4ZxYqeHH8JZC4WWQ5_ejtK8/"+index);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == GET_DATA_SUCCESS){
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

    private  void updateUI(View view){
        final TextView status = view.findViewById(R.id.matchList_status_textView);
        status.setText("Current count: ");
        recyclerView = view.findViewById(R.id.matchList_recyclerView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        matchListAdapter = new MatchListAdapter(mDataSet);
        recyclerView.setAdapter(matchListAdapter);
    }

}
