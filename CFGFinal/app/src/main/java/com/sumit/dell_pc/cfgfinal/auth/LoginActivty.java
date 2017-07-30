package com.sumit.dell_pc.cfgfinal.auth;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sumit.dell_pc.cfgfinal.InventoryActivity;
import com.sumit.dell_pc.cfgfinal.MainActivity;
import com.sumit.dell_pc.cfgfinal.R;
import com.sumit.dell_pc.cfgfinal.VolleyRequestQueue;

import java.util.HashMap;
import java.util.Map;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


public class LoginActivty extends AppCompatActivity {
    private static final String JSON_URL = "http://fragit.000webhostapp.com/api/login.php";
    private Button button;
    private EditText email ;
    private EditText password;
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);

        sharedpreferences = getSharedPreferences("CFG", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        email  = (EditText) findViewById(R.id.uEmail);
        password = (EditText) findViewById(R.id.uPassword);

        button = (Button) findViewById(R.id.bSignin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uemail = email.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                new SendPostRequest(uemail, pwd, editor).execute();
            }
        });
    }

    //    private void loadData() {
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle(R.string.loadData);
//        progressDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                progressDialog.dismiss();
//                Log.e("Val : ",response);
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("Error! ",error.getMessage());
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                Log.e("email : ",uname.getText().toString());
//                params.put("email",uname.getText().toString().trim());
//                params.put("password",password.getText().toString().trim());
//                return super.getParams();
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
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

        protected void onPreExecute() {
            pr = new ProgressDialog(LoginActivty.this);
            pr.setMessage("Retrieving data...");
            pr.show();
        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://fragit.000webhostapp.com/api/login.php"); // here is your URL path http://techprominds.esy.es/login.php

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", umail);
                postDataParams.put("hash_key", pwd);
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
                writer.write(getPostDataString(postDataParams));

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
                editor.putString("email", umail);
                editor.putString("hash_key", pwd);
                editor.commit();
                Toast.makeText(getApplicationContext(), "Login Sucessfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(), InventoryActivity.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), result,
                        Toast.LENGTH_LONG).show();
            }
            pr.dismiss();
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
