package com.nishitadutta.chaakri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Nishita on 29-07-2017.
 */

public class FirebaseManager {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

   // public static void addNewOrder(Order order){
   //     databaseReference.child(orders)
   // }
}
