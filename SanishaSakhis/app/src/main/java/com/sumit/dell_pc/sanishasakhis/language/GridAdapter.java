package com.sumit.dell_pc.sanishasakhis.language;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.preference.PreferenceManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;


import com.sumit.dell_pc.sanishasakhis.R;

import java.util.ArrayList;

import static com.sumit.dell_pc.sanishasakhis.R.id.parent;

/**
 * Created by DELL_PC on 7/23/2017.
 */

public class GridAdapter extends BaseAdapter {
    Activity activity;
    private ArrayList<HomeMenu> arrayList;
    private LayoutInflater layoutInflater = null;
    private int columnWidth, rowHeight;

    public GridAdapter(Activity activity, int columnWidth, int rowHeight) {
        this.activity = activity;
        this.columnWidth = columnWidth;
        this.rowHeight = rowHeight;

        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arrayList = new ArrayList<>();

        String[] name = {activity.getString(R.string.Inventory),
                activity.getString(R.string.order)};

        TypedArray id = activity.getResources().obtainTypedArray(R.array.all_images);
        for (int i = 0; i < name.length; i++) {
            arrayList.add(new HomeMenu(name[i], id.getResourceId(i, 0)));
        }

    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View view;

        if (convertView == null)
            view = layoutInflater.inflate(R.layout.home_grid_item, null);
        else
            view = convertView;
        holder.rl = (RelativeLayout) view.findViewById(R.id.homeGridItemContainer);
        holder.rl.setLayoutParams(new GridView.LayoutParams(columnWidth, rowHeight));
        holder.imageView = (ImageView) view.findViewById(R.id.homeGridImage);
        holder.imageView.setImageResource(arrayList.get(position).iconId);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("Clicked", arrayList.get(position).optionName);
                HomeMenu clickedItem = arrayList.get(position);

                try {
                    if (clickedItem.optionName.equalsIgnoreCase(activity.getString(R.string.Inventory))) {
                        // Intent intent = new Intent(activity, MemberActivity.class);
                        //activity.startActivity(intent);
                    }
                } catch (Exception e) {
                    Log.e("Ex:", e.toString());
                }
            }
        });

        return view;
    }

    private class Holder {
        RelativeLayout rl;
        ImageView imageView;
    }

    //Menu item Pair
    private class HomeMenu {
        String optionName;
        int iconId;

        HomeMenu() {
            optionName = null;
            iconId = 0;
        }

        HomeMenu(String optionName, int iconId) {
            this.optionName = optionName;
            this.iconId = iconId;
        }
    }
}
