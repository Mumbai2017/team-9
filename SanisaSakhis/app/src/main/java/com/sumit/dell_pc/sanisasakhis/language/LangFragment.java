package com.sumit.dell_pc.sanisasakhis.language;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.sumit.dell_pc.firebasedemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LangFragment extends Fragment {


    public LangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lang, container, false);


        return view;
    }

}
