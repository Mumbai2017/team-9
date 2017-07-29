package com.sumit.dell_pc.sanishasakhis.language;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sumit.dell_pc.sanishasakhis.Inventory.Inventory_MainActivty;
import com.sumit.dell_pc.sanishasakhis.R;

import java.util.ArrayList;

/**
 * Created by DELL_PC on 7/23/2017.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Drawable> imageViews;
    private ArrayList<String> strings;

    public GridAdapter(Context context, ArrayList<Drawable> imageViews, ArrayList<String> strings) {
        this.context = context;
        this.imageViews = imageViews;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int i) {
        return strings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {

            grid = new View(context);
            grid = inflater.inflate(R.layout.grid_signal, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            textView.setText(strings.get(i));
            imageView.setImageDrawable(imageViews.get(i));


            grid.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Log.e("Clicked", strings.get(i));
                    String val= strings.get(i);

                    try{
                        if(val.equalsIgnoreCase("Inventory")) {
                            Intent intent = new Intent(context, Inventory_MainActivty.class);
                            context.startActivity(intent);
                        }
                    }catch(Exception e){
                        Log.e("Ex:", e.toString());
                    }
                }
            });


        } else{
                grid = (View) view;
            }


            return grid;
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
