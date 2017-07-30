package com.example.khya8056.ngo;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//
//public class custlist extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_custlist);
//    }
//}


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.khya8056.ngo.Adapter1;
import com.example.khya8056.ngo.Auth.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import static android.R.attr.value;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class PendingOrder extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener mAuthistener;

    Toolbar toolbar;
    ListView lv_languages;


    Adapter1 list_adapter;
    String[] name = new String[]{"SQL",
            "JAVA",
            "JAVA SCRIPT",
            "C#",
            "PYTHON",
            "C++",
            "PHP",
            "IOS",
            "ANDROID"
    };

    String[] location = new String[]{"SQL",
            "JAVA",
            "JAVA SCRIPT",
            "C#",
            "PYTHON",
            "C++",
            "PHP",
            "IOS",
            "ANDROID"
    };

    Integer[] orderno = new Integer[]{1,2,3,4,5,6,7,8,9};
//
//    String[] khaakra = new String[]{"SQL",
//            "JAVA",
//            "JAVA SCRIPT",
//            "C#",
//            "PYTHON",
//            "C++",
//            "PHP",
//            "IOS",
//            "ANDROID"
//    };
//    Integer[] quantity = new Integer[]{1,2,3,4,5,6,7,8,9};

Button[] b1=new Button[400];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_m1);
        init();
        lv_languages.setAdapter(list_adapter);


    }

    private void init() {

        list_adapter = new Adapter1(this, name, location, orderno,b1);
        lv_languages = (ListView) findViewById(R.id.lv_languages);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            FirebaseAuth.getInstance().signOut();
            mAuthistener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (firebaseAuth.getCurrentUser() == null) {
                        System.out.println("Heya");
                        //    Toast.makeText(NavActivity.this,"Heya", Toast.LENGTH_LONG).show();

                    }
                }
            };
            finish();

            Intent intent = new Intent(PendingOrder.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}