package com.nishitadutta.chaakri;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nishitadutta.chaakri.models.order;

import java.util.HashMap;

/**
 * Created by Nishita on 29-07-2017.
 */

public class FirebaseManager {



    public static void addNewOrder(int qty, String address){
       DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        DatabaseReference orders=databaseReference.child("orders");
        Log.e("Firebase", "addNewOrder: " + qty + address );
        HashMap<String, Integer> order=new HashMap<>();
        order.put(address,qty);
        orders.push().setValue(order);
    }
}
