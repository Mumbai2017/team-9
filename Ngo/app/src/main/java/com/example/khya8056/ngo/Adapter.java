package com.example.khya8056.ngo.Auth;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khya8056.ngo.CustDetails;
import com.example.khya8056.ngo.MainActivity;
import com.example.khya8056.ngo.R;
import com.example.khya8056.ngo.custlist;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class Adapter extends BaseAdapter{
    String [] result;
    Context context;
    String [] imageId;
    private static LayoutInflater inflater=null;

    public Adapter(custlist mainActivity, String[] prgmNameList) {
// TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
// TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
// TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv_language;
        ImageView im_language;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        Holder holder=new Holder();
        View view;
        view = inflater.inflate(R.layout.layout_language_list_item, null);

        holder.tv_language=(TextView) view.findViewById(R.id.tv_language);

        holder.tv_language.setText(result[position]);
//        Picasso.with(context).load(imageId[position]).into(holder.im_language);

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent = new Intent(context, CustDetails.class);
                intent.putExtra(EXTRA_MESSAGE,result[position]);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        return view;
    }

}