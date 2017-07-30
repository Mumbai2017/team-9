package com.sumit.dell_pc.cfgfinal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sumit.dell_pc.cfgfinal.R;
import com.sumit.dell_pc.cfgfinal.fragment.ListItem;
import com.sumit.dell_pc.cfgfinal.fragment.listAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PartnerFragment extends Fragment {

    private static final String JSON_URL = "http://fragit.000webhostapp.com/api/getCustomer.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItem;
    private LayoutInflater la;

    public PartnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_partner, container, false);
        la = inflater;
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listItem = new ArrayList<>();
        loadData(view);
        return view;
    }

    private void loadData(View view) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray serverResponse = response.getJSONArray("response");
                    Log.e("data  : ",serverResponse.length()+"");
                    for (int i = 0; i < serverResponse.length(); i++) {
                        Log.e("response : ", serverResponse.getJSONObject(i).toString());
                        JSONObject jsonObject = serverResponse.getJSONObject(i);
                        ListItem item = new ListItem(
                                jsonObject.getString("name"),
                                jsonObject.getString("email"),
                                jsonObject.getString("distance"),
                                jsonObject.getString("distance")
                        );
                        listItem.add(item);
                    }


                    adapter = new listAdapter(listItem, recyclerView.getContext());
                    recyclerView.setAdapter(adapter);

                    recyclerView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            displayAlert();

                        }
                    });
                    Toast.makeText(recyclerView.getContext(), response.toString(), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error: ", error.getMessage() + " ");
                    }
                }
        );

        VolleyRequestQueue.getInstance(view.getContext()).addRequestQueue(jsonObjectRequest);
    }


    public void displayAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = la.inflate(R.layout.alert_dialog, null);

        Button cancelAlert = (Button) view.findViewById(R.id.alertCancel);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Alert Demo");
        alertDialog.setIcon(R.drawable.ic_add_alert_black_24dp);
        alertDialog.setCancelable(false);
        cancelAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alertDialog.isShowing()) {
                    alertDialog.setCancelable(false);
                    alertDialog.dismiss();
                }
            }
        });

        builder.setView(view);
        alertDialog.show();
    }

}
