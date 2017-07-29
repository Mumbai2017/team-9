package com.sumit.dell_pc.sanisasakhis.language;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sumit.dell_pc.sanisasakhis.R;

import java.util.List;

/**
 * Created by DELL_PC on 7/25/2017.
 */

public class LangListAdapter extends RecyclerView.Adapter<com.sumit.dell_pc.sanisasakhis.language.LangListAdapter.ViewHolder> {
    private List<LangList> langLists;
    private Context context;

    public LangListAdapter(List<LangList> langLists, Context context) {
        this.langLists = langLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LangList li = langLists.get(position);

        holder.textLangDemo.setText(li.getLangDemo());
        holder.textLangName.setText(li.getLangName());

    }

    @Override
    public int getItemCount() {
        return langLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textLangDemo;
        public TextView textLangName;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textLangDemo = (TextView) itemView.findViewById(R.id.langDemo);
            textLangName = (TextView) itemView.findViewById(R.id.langName);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linerLayout);
        }
    }
}
