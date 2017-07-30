package com.example.khya8056.ngo;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Adapter2 extends BaseAdapter{
    String [] khaakra;
    Integer[] quantity;
    //    String [] Khaakra;
//    Integer[] Quantity;
//    Dictionary<String,Integer> orderItem;
    Context context;
    String [] imageId;
    private static LayoutInflater inflater=null;

    public Adapter2(OrderDetail mainActivity, String[] khaakra1, Integer[] quantity1 ) {
// TODO Auto-generated constructor stub
        khaakra = khaakra1;
        quantity=quantity1;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
// TODO Auto-generated method stub
        return khaakra.length;
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
        TextView Khaakra2;
        TextView Quantity2;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
// TODO Auto-generated method stub
        Holder holder=new Holder();
        View view;
        view = inflater.inflate(R.layout.layout_language_list_item2, null);

        holder.Khaakra2=(TextView) view.findViewById(R.id.khaakra);
        holder.Khaakra2.setText(khaakra[position]);

        holder.Quantity2=(TextView) view.findViewById(R.id.quantity);
        holder.Quantity2.setText(quantity[position].toString());



//        holder.Khaakra2=(TextView) view.findViewById(R.id.khaakra);
//        holder.Khaakra2.setText(Khaakra[position]);
//
//        holder.Quantity2=(TextView) view.findViewById(R.id.quantity);
//        holder.Quantity2.setText(Quantity[position].toString());
//        Picasso.with(context).load(imageId[position]).into(holder.im_language);


        return view;
    }

}