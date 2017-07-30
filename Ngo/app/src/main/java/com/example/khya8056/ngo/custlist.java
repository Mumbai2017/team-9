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
        import android.widget.ListView;

        import com.example.khya8056.ngo.Auth.Adapter;
        import com.example.khya8056.ngo.Auth.LoginActivity;
        import com.google.firebase.auth.FirebaseAuth;

public class custlist extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener mAuthistener;

    Toolbar toolbar;
    ListView lv_languages;


    Adapter list_adapter;
    String[] languages = new String[]{"SQL",
            "JAVA",
            "JAVA SCRIPT",
            "C#",
            "PYTHON",
            "C++",
            "PHP",
            "IOS",
            "ANDROID"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_m);

        init();
        lv_languages.setAdapter(list_adapter);


    }

    private void init() {

        list_adapter = new Adapter(this, languages);
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

            Intent intent = new Intent(custlist.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}