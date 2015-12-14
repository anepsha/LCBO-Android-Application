package com.prog3210.ngalatsis.lcboapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ttran2-cc on 11/19/2015.
 */
public class FragmentCategories extends Fragment implements View.OnClickListener {
    private Button btnCategoryWine;
    private Button btnCategorySpirits;
    private Button btnCategoryBeer;
    private Button btnCategoryCider;
    private Button btnCategoryCoolers;
    private TextView txtStoreName;
    private int storeId;
    private String storeName;
    private OnFragmentInteractionListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        getActivity().setTitle("Categories");
        storeId = getArguments().getInt("storeId");
        storeName = getArguments().getString("storeName");

        btnCategoryWine = (Button)view.findViewById(R.id.btnCategoryWine);
        btnCategoryWine.setOnClickListener(this);
        btnCategorySpirits = (Button)view.findViewById(R.id.btnCategorySpirits);
        btnCategorySpirits.setOnClickListener(this);
        btnCategoryBeer = (Button)view.findViewById(R.id.btnCategoryBeer);
        btnCategoryBeer.setOnClickListener(this);
        btnCategoryCider = (Button)view.findViewById(R.id.btnCategoryCider);
        btnCategoryCider.setOnClickListener(this);
        btnCategoryCoolers = (Button)view.findViewById(R.id.btnCategoryCoolers);
        btnCategoryCoolers.setOnClickListener(this);

        txtStoreName = (TextView)view.findViewById(R.id.txtStoreName);
        txtStoreName.setText(storeName);

        return view;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (OnFragmentInteractionListener)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCategoryWine:
                mCallback.onCategoryClicked("Wine", storeId, storeName);
                break;
            case R.id.btnCategorySpirits:
                mCallback.onCategoryClicked("Spirits", storeId, storeName);
                break;
            case R.id.btnCategoryBeer:
                mCallback.onCategoryClicked("Beer", storeId, storeName);
                break;
            case R.id.btnCategoryCider:
                mCallback.onCategoryClicked("Ciders", storeId, storeName);
                break;
            case R.id.btnCategoryCoolers:
                mCallback.onCategoryClicked("Ready-to-Drink/Coolers", storeId, storeName);
                break;
        }
    }

    public interface OnFragmentInteractionListener{
        public void onCategoryClicked(String category, int storeId, String storeName);
    }
}
