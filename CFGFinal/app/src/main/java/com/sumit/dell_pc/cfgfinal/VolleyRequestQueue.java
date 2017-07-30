package com.sumit.dell_pc.cfgfinal;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by DELL_PC on 7/21/2017.
 */

public class VolleyRequestQueue {
    private static VolleyRequestQueue mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    public VolleyRequestQueue(Context context) {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleyRequestQueue getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyRequestQueue(context);
        }
        return mInstance;
    }

    public <T> void addRequestQueue(Request<T> request) {
        requestQueue.add(request);
    }
}
