package com.sumit.dell_pc.sanishasakhis.language;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.sumit.dell_pc.sanishasakhis.R;


/**
 * Created by Sumit_Busa on 02-04-2017.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private int columnWidth,rowHeight;

    // Constructor
    public ImageAdapter(Context c,int columnWidth,int rowHeight) {
        mContext = c;
        this.columnWidth = columnWidth;
        this.rowHeight = rowHeight;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.ic_image_edit, R.drawable.ic_image_edit,
    };
}
