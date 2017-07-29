package com.nishitadutta.chaakri.orderplacing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.nishitadutta.chaakri.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Nishita on 29-07-2017.
 */

public class PlaceOrderActivity extends AppCompatActivity {

    @BindViews({R.id.checkbox1, R.id.checkbox2, R.id.checkbox3, R.id.checkbox3, R.id.checkbox4, R.id.checkbox5, R.id.checkbox6,
    R.id.checkbox7, R.id.checkbox8, R.id.checkbox9})
    List<CheckBox> checkboxes;

    @BindViews({R.id.number1, R.id.number2, R.id.number3, R.id.number3, R.id.number4, R.id.number5, R.id.number6,
            R.id.number7, R.id.number8, R.id.number9})
    List<NumberPicker> numberpickers;

    @BindView(R.id.btnProceed)
    Button btnProceed;
    int total;
    @BindView(R.id.tvCost)
    TextView tvCost;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.placing_order);
        ButterKnife.bind(this);
        for (NumberPicker num: numberpickers) {
            num.setMinValue(0);
            num.setMaxValue(50);
        }
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               for(int i=0; i<checkboxes.size();i++){
                   if(checkboxes.get(i).isChecked()){
                       int qty=numberpickers.get(i).getValue();
                       total+=qty*50;
                   }
               }

            }
        });



    }
}
