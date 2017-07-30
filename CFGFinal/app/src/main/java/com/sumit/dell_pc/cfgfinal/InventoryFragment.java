package com.sumit.dell_pc.cfgfinal;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sumit.dell_pc.cfgfinal.R;
import com.sumit.dell_pc.cfgfinal.auth.LoginActivty;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;




/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment {
    private View view;
    private String TAG = "InventoryFragment";
    private RecyclerView recyclerView;
    private LinearLayoutManager mManager;
    private TextView name,Inno,addreess,breakfast,jeera,Khichdi;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public InventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            view = inflater.inflate(R.layout.fragment_inventory, container, false);

            sharedPreferences = view.getContext().getSharedPreferences("CFG", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            name = (TextView)  view.findViewById(R.id.name);
            Inno = (TextView)  view.findViewById(R.id.Inno);
            addreess = (TextView)  view.findViewById(R.id.addreess);
            breakfast = (TextView)  view.findViewById(R.id.breakfast);
            jeera = (TextView)  view.findViewById(R.id.jeera);
            Khichdi = (TextView) view.findViewById(R.id.Khichdi);

            name.setText("Sumit Busa");
            Inno.setText("00");
            addreess.setText("Dahisar(E),Mumbai");
            breakfast.setText("00");
            jeera.setText("00");
            Khichdi.setText("00");
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        loadData(view);
        return view;
    }

    private void loadData(final View view) {
        new SendPostRequest("sumitbusa@gmail.com", "", editor).execute();
    }

    public class SendPostRequest extends AsyncTask<String, Void, String> {
        ProgressDialog pr;
        private String umail;
        private String pwd;
        private SharedPreferences.Editor editor;

        SendPostRequest(String umail, String pwd, SharedPreferences.Editor editor) {
            this.umail = umail;
            this.pwd = pwd;
            this.editor = editor;
        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://fragit.000webhostapp.com/api/getInvetory.php"); // here is your URL path http://techprominds.esy.es/login.php

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", umail);
                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            result = result.trim();
            if (result.equals("true")) {
                Toast.makeText(view.getContext(), result,
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(view.getContext(), result,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

}
