package com.sumit.dell_pc.sanishasakhis.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumit.dell_pc.sanishasakhis.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopPaidFragment extends Fragment {


    public TopPaidFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_paid, container, false);
    }

}
