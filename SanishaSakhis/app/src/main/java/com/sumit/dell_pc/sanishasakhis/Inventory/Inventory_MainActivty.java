package com.sumit.dell_pc.sanishasakhis.Inventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;

import com.sumit.dell_pc.sanishasakhis.Fragment.HomeFragment;
import com.sumit.dell_pc.sanishasakhis.Fragment.TopFreeFragment;
import com.sumit.dell_pc.sanishasakhis.Fragment.TopPaidFragment;
import com.sumit.dell_pc.sanishasakhis.Fragment.ViewPagerAdapter;
import com.sumit.dell_pc.sanishasakhis.R;

public class Inventory_MainActivty extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory__main_activty);

        toolbar = (Toolbar) findViewById(R.id.homeToolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new HomeFragment(), "Home");
        viewPagerAdapter.addFragments(new TopFreeFragment(), "Top Free");
        viewPagerAdapter.addFragments(new TopPaidFragment(), "Top Paid");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.WHITE, Color.WHITE);
    }
}
