package com.example.khya8056.ngo.Auth;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khya8056.ngo.MainActivity;
import com.example.khya8056.ngo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;

import static com.example.khya8056.ngo.R.id.uEmail;
import static com.example.khya8056.ngo.R.id.uPassword;

public class SignUpActivity extends AppCompatActivity {
    private DatabaseReference mSakhi;
    private EditText sName;
    private EditText sEmail;
    private EditText sPassword;
    private DatabaseReference userDatabase;
    private FirebaseAuth mAuth;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db = FirebaseDatabase.getInstance().getReference();


        sName = (EditText) findViewById(R.id.sName);
        sEmail = (EditText) findViewById(R.id.sEmail);
        sPassword = (EditText) findViewById(R.id.sPassword);
        Button button = (Button) findViewById(R.id.bSignUP);

        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("URGENT!!!!!!!!!!!!!!!!",sName.toString());
                    Log.v("URGENT","Not happening");
                    String password  = sPassword.getText().toString().trim();
                String name  = sName.getText().toString().trim();
                String email  = sEmail.getText().toString().trim();
                Admin userList = new Admin(name,email,password);
                Log.v("URGENT!!!!!!!!!!!!!!!!",sName.getText().toString());
                Log.v("URGENT!!!!!!!!!!!!!!!!",sEmail.getText().toString());

                mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.v("URGENT!!!!!!!!!!!!!!!!",sName.getText().toString());
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("Tag", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                    } else {
                                        Log.v("URGENT!!!!!!!!!!!!!!!!",sName.getText().toString());

                                        // If sign in fails, display a message to the user.
                                        Log.w("Tag", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    userDatabase = FirebaseDatabase.getInstance().getReference("Ngo");
                    Log.v("value",userList.toString());
                    String id = userDatabase.push().getKey();
                    userDatabase.child(id).setValue(userList);
                finish();
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                }

        });

    }

    private void userNewEntry(String id, String name, String email) {
        //Add userDetails to user filed...
        //UserList userList = new UserList(id, name, email);

        //userDatabase.child(id).setValue(userList);
    }
}

