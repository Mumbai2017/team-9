package com.nishitadutta.chaakri.orderplacing;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nishitadutta.chaakri.FeedbackActivity;
import com.nishitadutta.chaakri.FirebaseManager;
import com.nishitadutta.chaakri.R;
import com.nishitadutta.chaakri.ViewSakhiActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nishita on 29-07-2017.
 */

public class DeliveryActivity  extends AppCompatActivity{

    @BindView(R.id.etAddress)
     EditText etAddress;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;
@BindView(R.id.viewSakhi)
        Button viewSakhi;
    com.nishitadutta.chaakri.models.Order Order;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delivery);
        ButterKnife.bind(this);

       final int qty= getIntent().getIntExtra("volume",0);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAddress.getText().toString()==null)return;
                else {
                    // 1. Instantiate an AlertDialog.Builder with its constructor
                    AlertDialog.Builder builder = new AlertDialog.Builder(DeliveryActivity.this);

// 2. Chain together various setter methods to set the dialog characteristics
                    builder.setMessage("Are you sure you want to place the Order?")
                            .setTitle("Confirm Order");
                    builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                            FirebaseManager.addNewOrder(qty, etAddress.getText().toString());
                            btnSubmit.setEnabled(false);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
// 3. Get the AlertDialog from create()
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });


viewSakhi.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent in=new Intent(DeliveryActivity.this,ViewSakhiActivity.class);
        startActivity(in);
    }
});


    }

   /* @OnClick(R.id.btnFeedback)
    public void giveFeedback(View v){
        Intent in=new Intent(DeliveryActivity.this,FeedbackActivity.class);
        startActivity(in);
    }*/
}
