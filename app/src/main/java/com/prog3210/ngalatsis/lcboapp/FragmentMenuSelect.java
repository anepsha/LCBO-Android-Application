package com.prog3210.ngalatsis.lcboapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ttran2-cc on 12/1/2015.
 */
public class FragmentMenuSelect extends Fragment implements View.OnClickListener {
    private Button btnCategories;
    private Button btnChangeStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_select, container, false);
        getActivity().setTitle("Menu Select");
        btnCategories = (Button)view.findViewById(R.id.btnCategories);
        btnCategories.setOnClickListener(this);
        btnChangeStore = (Button)view.findViewById(R.id.btnChangeStore);
        btnChangeStore.setOnClickListener(this);

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
            case R.id.btnCategories:
                getFragmentManager().beginTransaction().add(android.R.id.content, new FragmentCategories()).addToBackStack(null).commit();
                //getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, 0, 0).replace(android.R.id.content, new FragmentCategories()).addToBackStack("FragmentCategories").commit();
                break;
            case R.id.btnChangeStore:
                getFragmentManager().beginTransaction().add(android.R.id.content, new StoreList()).addToBackStack(null).commit();
                //getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter, R.anim.exit, 0, 0).replace(android.R.id.content, new StoreList()).addToBackStack("StoreList").commit();
                break;
        }
    }
}
