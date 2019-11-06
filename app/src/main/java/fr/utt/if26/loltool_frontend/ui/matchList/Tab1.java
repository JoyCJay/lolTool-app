package fr.utt.if26.loltool_frontend.ui.matchList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.utt.if26.loltool_frontend.R;

public class Tab1 extends Fragment {
    private String[] name = {"Name: Jean Marc Nigro","Name: Jean Marc Nigro","Name: Jean Marc Nigro",
            "Name: Jean Marc Nigro","Name: Jean Marc Nigro","Name: Jean Marc Nigro"};
    private String[] email = {"Email: jean_marc_nigro@utt.fr","Email: jean_marc_nigro@utt.fr","Email: jean_marc_nigro@utt.fr",
            "Email: jean_marc_nigro@utt.fr","Email: jean_marc_nigro@utt.fr","Email: jean_marc_nigro@utt.fr"};
    private String[] detail = {"More information", "More information", "More information",
            "More information","More information","More information"};
    private int[] images = {R.drawable.nigro, R.drawable.nigro, R.drawable.nigro,
            R.drawable.nigro, R.drawable.nigro, R.drawable.nigro};
    private List<Map<String, Object>> list_map = new ArrayList<>();

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_matchlist_tab1, container, false);

        ListView listView = (ListView) view.findViewById(R.id.profList);

        for (int i = 0; i < 6; i++) {
            Map<String, Object> items = new HashMap<String, Object>();
            items.put("img", images[i]);
            items.put("name", name[i]);
            items.put("email", email[i]);
            items.put("detail", detail[i]);
            list_map.add(items);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getActivity(),
                list_map,
                R.layout.item_matchlist_tab1,
                new String[]{"img", "name", "email", "detail"},
                new int[]{R.id.img_contact, R.id.name_contact, R.id.credit, R.id.detail_proposal});

        listView.setAdapter(simpleAdapter);

        return view;
    }
}
