package com.sumit.dell_pc.sanishasakhis.auth;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sumit.dell_pc.sanishasakhis.MainActivity;
import com.sumit.dell_pc.sanishasakhis.R;

public class SignUpActivity extends BaseActivty {
    private TextView sName;
    private TextView sMobileNo;
    private TextView sEmail;
    private TextView sPassword;
    private TextView sAddreess;
    private DatabaseReference userDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sName = (TextView) findViewById(R.id.sName);
        sMobileNo = (TextView) findViewById(R.id.sMoblieNo);
        sEmail = (TextView) findViewById(R.id.sEmail);
        sPassword = (TextView) findViewById(R.id.sPassword);
        sAddreess = (TextView) findViewById(R.id.sAddreess);

        Button button = (Button) findViewById(R.id.bSignUP);
        mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(TextUtils.isEmpty(sName.getText())){
//                    sName.setError("Enter data");
//                }
//                else if(TextUtils.isEmpty(sMobileNo.getText())){
//                    sMobileNo.setError("Enter data");
//                }
//                else if(TextUtils.isEmpty(sEmail.getText())){
//                    sEmail.setError("Enter data");
//                }
//                else if(TextUtils.isEmpty(sPassword.getText())){
//                    sPassword.setError("Enter data");
//                }
//                else if(TextUtils.isEmpty(sAddreess.getText())){
//                    sAddreess.setError("Error data");
//                }
//                else{
                showProgressDialog();
                mAuth.createUserWithEmailAndPassword(sEmail.getText().toString().trim(), sPassword.getText().toString().trim())
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("TAG : ", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("TAG: ", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

//                    mAuth = FirebaseAuth.getInstance();

                    UserList userList = new UserList(sName.getText().toString().trim(),sMobileNo.getText().toString().trim(),sEmail.getText().toString().trim(),sPassword.getText().toString().trim(),"0","0","0","0");
                    userDatabase = FirebaseDatabase.getInstance().getReference("Sakhis");
                    String id = userDatabase.push().getKey();
                    userDatabase.child(id).setValue(userList);
                    hideProgressDialog();
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }
//            }
        });

    }

    private void userNewEntry(String id, String name, String email) {
        //Add userDetails to user filed...
        //UserList userList = new UserList(id, name, email);

        //userDatabase.child(id).setValue(userList);
    }
}
