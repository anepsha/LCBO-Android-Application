package com.prog3210.ngalatsis.lcboapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class FragmentChecklist extends Fragment implements View.OnClickListener{
    private Button btnClearChecklist;
    private ListView listChecklist;
    private ArrayList<Item> items;
    private ArrayList<HashMap<String, String>> data;
    private SimpleAdapter adapter;
    public Context context;
    private TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Checklist");
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);
        btnClearChecklist = (Button)view.findViewById(R.id.btnClearChecklist);
        btnClearChecklist.setOnClickListener(this);
        text=(TextView)view.findViewById(R.id.txtChecklistItemName);
        listChecklist = (ListView)view.findViewById(R.id.listChecklistItems);
        items = getArguments().getParcelableArrayList("checklistItems");
        data = new ArrayList<HashMap<String, String>>();
        for (Item i : items) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", i.getName());
            Log.d("I shows: ", i.toString());
            data.add(map);

        }
        //http://stackoverflow.com/questions/16661662/how-to-implement-pagination-in-android-listview
        int resource = R.layout.listview_checklist_item;
        String[] from = {"name"};
        int[] to = {R.id.txtChecklistItemName};
        adapter = new SimpleAdapter(FragmentChecklist.this.getActivity(), data, resource, from, to);
        listChecklist.setAdapter(adapter);
        registerForContextMenu(listChecklist);
        return view;
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClearChecklist:
                //http://stackoverflow.com/questions/2250770/how-to-refresh-android-listview/21862750#21862750
                items.clear();
                data.clear();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        listChecklist.invalidateViews();
                        listChecklist.refreshDrawableState();
                    }
                });
                break;
        }
    }

    // Shows context menu with several options to delete and modify items
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.add(0, 1, 0, "Delete item");
    }

    // Functions to delete and modify items
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo acmi=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if (item.getItemId()==1){

            data.remove(acmi.position);
            items.remove(acmi.position);
            adapter.notifyDataSetChanged();
            listChecklist.refreshDrawableState();
            Toast.makeText(getActivity(), "removed", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
