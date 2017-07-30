package com.sumit.dell_pc.cfgfinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;


public class InventoryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdaptor viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        toolbar = (Toolbar) findViewById(R.id.homeToolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);

        viewPagerAdapter = new ViewPageAdaptor(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new InventoryFragment(), "Inventory");
        viewPagerAdapter.addFragments(new PartnerFragment(), "Partner History");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alertBox:
                Toast.makeText(this, "AlertBox is Selected! ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,ChatActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Conformation");
        builder.setMessage("For close application please click Ok Button!");
        builder.setIcon(R.drawable.ic_warnning);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void displayAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.alert_dialog, null);

        Button cancelAlert = (Button) view.findViewById(R.id.alertCancel);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Alert Demo");
        alertDialog.setIcon(R.drawable.ic_add_alert_black_24dp);
        alertDialog.setCancelable(false);
        cancelAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alertDialog.isShowing()) {
                    alertDialog.setCancelable(false);
                    alertDialog.dismiss();
                }
            }
        });

        builder.setView(view);
        alertDialog.show();
    }
}
