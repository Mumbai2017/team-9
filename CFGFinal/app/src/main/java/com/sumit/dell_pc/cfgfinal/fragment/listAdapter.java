package com.sumit.dell_pc.cfgfinal.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.sumit.dell_pc.cfgfinal.MainActivity;
import com.sumit.dell_pc.cfgfinal.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by DELL_PC on 7/20/2017.
 */

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {

    private List<ListItem> listItem;
    private Context context;

    public listAdapter(List<ListItem> listItem, Context context) {
        this.listItem = listItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ListItem li = listItem.get(position);

        holder.textHeading.setText(li.getId());
        holder.textDescription.setText(li.getDescription());
        holder.textPhoneNo.setText(li.getPhone_number());
//        Picasso.with(context)
//                .load(li.getImageUrl())
//                .into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("You Clicked : ", li.getId().toString());
                Toast.makeText(context,"Your request will be Notify to the user",Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Conformation");
                builder.setMessage("For close application please click Ok Button!");
                builder.setIcon(R.drawable.ic_warnning);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textHeading;
        public TextView textDescription;
        public TextView textPhoneNo;
        public ImageView imageView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textHeading = (TextView) itemView.findViewById(R.id.Heading);
            textDescription = (TextView) itemView.findViewById(R.id.Description);
            textPhoneNo = (TextView) itemView.findViewById(R.id.phoneNo);
//            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linerLayout);
        }
    }
}
