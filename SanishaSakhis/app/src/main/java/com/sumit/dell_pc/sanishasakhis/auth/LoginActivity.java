package com.sumit.dell_pc.sanishasakhis.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sumit.dell_pc.sanishasakhis.MainActivity;
import com.sumit.dell_pc.sanishasakhis.R;
import com.sumit.dell_pc.sanishasakhis.language.LangList;
import com.sumit.dell_pc.sanishasakhis.language.LangListAdapter;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivty implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private DatabaseReference userDatabase;

    private GoogleApiClient mGoogleApiClient;

    private ProgressDialog mProgressDialog;
    private TextView txtviewCountry;
    private Bundle bundle;
    private AlertDialog alertDialog;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<LangList> langLists;

    //    EditTextView username & password
    private EditText uEmail;
    private EditText uPassword;
    private Button bSignin;
    private Button bSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uEmail = (EditText) findViewById(R.id.uEmail);
        uPassword = (EditText) findViewById(R.id.uPassword);
        bSignin = (Button) findViewById(R.id.bSignin);
        bSignUp = (Button) findViewById(R.id.bSignUp);

//        bSignin.setEnabled(false);
        //User DataBase Ref.
        userDatabase = FirebaseDatabase.getInstance().getReference("Sakhis");
        //End UserDatabase

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        bSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!uEmail.getText().toString().trim().equals("") && !uPassword.getText().toString().trim().equals("")) {
                    if (android.util.Patterns.EMAIL_ADDRESS.matcher(uEmail.getText().toString().trim()).matches()) {
                        Log.e("Button", " Button is Clicked");
                        // Right Thing
                        mAuth.signInWithEmailAndPassword(uEmail.getText().toString().trim(), uPassword.getText().toString().trim())
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "signInWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            updateUI(user);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            updateUI(null);
                                        }
                                    }
                                });

                    } else {
                        uEmail.setError("Please Enter Valid Email Id");
                    }
                } else {
                    if (uEmail.getText().toString().trim().equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(uEmail.getText().toString().trim()).matches()) {
                        uEmail.setError("Please Enter Valid Email Id");
                    } else {
                        uPassword.setError("Please Enter password");
                    }
                }
            }
        });

//        mStatusTextView = (TextView) findViewById(R.id.status);
//        mDetailTextView = (TextView) findViewById(R.id.detail);
        txtviewCountry = (TextView) findViewById(R.id.country);

        txtviewCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLanguage();
            }
        });

        findViewById(R.id.signin).setOnClickListener(this);
        findViewById(R.id.signOut).setOnClickListener(this);
        findViewById(R.id.disConnect).setOnClickListener(this);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        mAuth = FirebaseAuth.getInstance();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }
    // [END onactivityresult]

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgressDialog();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signin]

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(null);
                    }
                });
    }


    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(null);
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            bundle = new Bundle();
            bundle.putString("uemail", user.getEmail());
            bundle.putString("uname", user.getUid());

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

//            mStatusTextView.setText(getString(R.string.status, user.getEmail()));
//            mDetailTextView.setText(getString(R.string.details, user.getDisplayName()));

//            findViewById(R.id.signin).setVisibility(View.GONE);
//            findViewById(R.id.signOut).setVisibility(View.VISIBLE);
        } else {
//            mStatusTextView.setText(R.string.signout);
//            mDetailTextView.setText(null);

            findViewById(R.id.signin).setVisibility(View.VISIBLE);
            findViewById(R.id.signOut).setVisibility(View.GONE);
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signin) {
            signIn();
        } else if (i == R.id.signOut) {
            signOut();
        } else if (i == R.id.disConnect) {
            revokeAccess();
        }
    }


    private void selectLanguage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.fragment_lang, null);

//        Button cancelAlert = (Button) view.findViewById(R.id.alertCancel);

        recyclerView = (RecyclerView) view.findViewById(R.id.langList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        langLists = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            LangList li = new LangList(
                    "English",
                    "English-USA"
            );
            langLists.add(li);
        }

        adapter = new LangListAdapter(langLists, this);

        recyclerView.setAdapter(adapter);

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Select Your Language");
        alertDialog.setIcon(R.drawable.ic_assignment_black_24dp);
        alertDialog.setCancelable(true);
//        cancelAlert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(alertDialog.isShowing()) {
//                    alertDialog.setCancelable(false);
//                    alertDialog.dismiss();
//                }
//            }
//        });

        builder.setView(view);
        alertDialog.show();
    }
}