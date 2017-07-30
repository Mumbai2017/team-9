package com.sumit.dell_pc.cfg.inventory.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sumit.dell_pc.cfg.R;
import com.sumit.dell_pc.cfg.model.SakhiData;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFragment extends Fragment {
    private String TAG = "InventoryFragment";
    private RecyclerView recyclerView;
    private LinearLayoutManager mManager;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    public InventoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        loadData(view);
        return recyclerView;
    }

    private void loadData(final View view) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FirebaseUser user = mAuth.getCurrentUser();
                String id = user.getUid();
                SakhiData sakhiData = dataSnapshot.child(id).getValue(SakhiData.class);

                TextView name = (TextView) view.findViewById(R.id.name);
                TextView invenotry = (TextView) view.findViewById(R.id.Inno);
                TextView add = (TextView) view.findViewById(R.id.addreess);

                name.setText(sakhiData.getName());
                invenotry.setText(String.valueOf(sakhiData.getInventoryNo()));
                add.setText(sakhiData.getAddress());

                Log.e(TAG,""+sakhiData.getAddress());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
