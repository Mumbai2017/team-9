package com.nishitadutta.chaakri;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        orders.push().setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
             //   Log.e(TAG, "onComplete: " );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
             //12   Log.e(TAG, "onFailure: " + e.getMessage() );
            }
        });

    }
}
