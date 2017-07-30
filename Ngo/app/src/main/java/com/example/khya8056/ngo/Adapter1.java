package com.example.khya8056.ngo;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Dictionary;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class Adapter1 extends BaseAdapter{
    String [] name;
    String [] location;
    Integer[] orderNo;
    Button[] b11;
    //    String [] Khaakra;
//    Integer[] Quantity;
//    Dictionary<String,Integer> orderItem;
    Context context;
    String [] imageId;
    private static LayoutInflater inflater=null;

    public Adapter1(PendingOrder mainActivity, String[] name1, String[] location1, Integer[] orderNo1, Button[] b1) {
// TODO Auto-generated constructor stub
        name = name1;
        location=location1;
        orderNo=orderNo1;
        b11 = b1;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return name.length;
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
        TextView name2;
        TextView location2;
        TextView orderNo2;
        TextView Khaakra2;
        TextView Quantity2;
        Button b12;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        Holder holder=new Holder();
        View view;
        view = inflater.inflate(R.layout.layout_language_list_item1, null);

        holder.name2=(TextView) view.findViewById(R.id.name);
        holder.name2.setText(name[position]);

        holder.location2=(TextView) view.findViewById(R.id.location);
        holder.location2.setText(location[position]);

        holder.orderNo2=(TextView) view.findViewById(R.id.orderNo);
        holder.orderNo2.setText(orderNo[position].toString());

        holder.b12=(Button) view.findViewById(R.id.submit);


//        holder.Khaakra2=(TextView) view.findViewById(R.id.khaakra);
//        holder.Khaakra2.setText(Khaakra[position]);
//
//        holder.Quantity2=(TextView) view.findViewById(R.id.quantity);
//        holder.Quantity2.setText(Quantity[position].toString());
//        Picasso.with(context).load(imageId[position]).into(holder.im_language);

        holder.b12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Intent intent = new Intent(context, OrderDetail.class);
                intent.putExtra(EXTRA_MESSAGE,orderNo[position]);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
        return view;
    }

}