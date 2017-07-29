package com.nishitadutta.chaakri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nishitadutta.chaakri.models.order;

/**
 * Created by Nishita on 29-07-2017.
 */

public class FirebaseManager {

    public  static DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    public static void addNewOrder(order Order){
        DatabaseReference orders=databaseReference.child("orders");
        orders.push().setValue(Order);
    }
}
