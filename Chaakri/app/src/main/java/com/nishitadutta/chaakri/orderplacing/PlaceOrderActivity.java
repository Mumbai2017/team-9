package com.nishitadutta.chaakri.orderplacing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;



import com.nishitadutta.chaakri.R;
import com.nishitadutta.chaakri.models.order;

import java.io.FileInputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Nishita on 29-07-2017.
 */

public class PlaceOrderActivity extends AppCompatActivity {
   // @BindView(R.id.tvCost1)
    TextView tvCost;
    @BindViews({R.id.checkbox11, R.id.checkbox22, R.id.checkbox33, R.id.checkbox44, R.id.checkbox55, R.id.checkbox66,
    R.id.checkbox77, R.id.checkbox88, R.id.checkbox99})
    List<CheckBox> checkboxes;

    @BindViews({R.id.number11, R.id.number22, R.id.number33, R.id.number44, R.id.number55, R.id.number66,
            R.id.number77, R.id.number88, R.id.number99})
    List<NumberPicker> numberpickers;
    @BindView(R.id.Calculate)
    Button cal;
    @BindView(R.id.btnProceed)
    Button btnProceed;
    int total,qty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
           // FileInputStream serviceAccount =
          //          new FileInputStream("chaakri-64318-firebase-adminsdk-lcj79-61e70237cc.json");

           // FirebaseOptions options = new FirebaseOptions.Builder()
           //         .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
           //         .setDatabaseUrl("https://chaakri-64318.firebaseio.com")
           //         .build();

           // FirebaseApp.initializeApp(options);
        }catch(Exception e){
            Log.e("DeliverActivity", "onCreate: " + e.getMessage() );
        }
        setContentView(R.layout.placing_order);
        ButterKnife.bind(this);
        tvCost=(TextView)findViewById(R.id.tvCost1);
        for (NumberPicker num: numberpickers) {
            num.setMinValue(0);
            num.setMaxValue(50);
        }
       cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               for(int i=0; i<checkboxes.size();i++){
                   if(checkboxes.get(i).isChecked()){
                        qty=numberpickers.get(i).getValue();
                       total+=qty*50;
                       Log.d(tvCost.getText().toString(),"MSSAGE");
                       UpdateText(total);
                   }

               }
             //   tvCost.setText(total);

            }
        });
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(PlaceOrderActivity.this,DeliveryActivity.class);
                i.putExtra("volume",qty);
                startActivity(i);
            }
        });




    }
    public void UpdateText(int total){
        tvCost.setText(new Integer(total).toString());
    }
}
