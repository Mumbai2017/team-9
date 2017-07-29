package com.sumit.dell_pc.sanishasakhis.language;


import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.sumit.dell_pc.sanishasakhis.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private GridView gridView;
    private TypedArray allImages;
    private TypedArray allStrings;
    private ArrayList<Drawable> allDrawableImages = new ArrayList<>();;
    private ArrayList<String> string = new ArrayList<>();;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getAllWidgets(view);
        setAdapter();
        return view;
    }

    private void getAllWidgets(View view) {
        gridView = (GridView) view.findViewById(R.id.gridView);
        allImages = getResources().obtainTypedArray(R.array.all_images);
        allStrings = getResources().obtainTypedArray(R.array.Web);
    }

    private void setAdapter()
    {
        allDrawableImages.clear();
        string.clear();
        for (int i = 0; i < allImages.length(); i++) {
            allDrawableImages.add(allImages.getDrawable(i));
            string.add(allStrings.getText(i).toString().trim());
        }
        GridAdapter gridViewAdapter = new GridAdapter(getActivity(), allDrawableImages,string);
        gridView.setAdapter(gridViewAdapter);
    }
}
