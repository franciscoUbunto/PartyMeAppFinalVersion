package com.partymeappfinalversion.ui;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.partymeappfinalversion.R;

public class ActivitySignin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_SIGN_IN = 9001;

    private CallbackManager callbackManager;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private GoogleApiClient mGoogleApiClient;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this api should be declared as private **ask ???
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setApplicationId("956962284359655");

        setContentView(R.layout.activity_signin);
        login = (Button) findViewById(R.id.activity_signin_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActivitySignin.this, "login with native login", Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(ActivitySignin.this, NativeLogin.class));
            }
        });

        initFacebookButton();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        // Create a GoogleApiClient instance

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // [END configure_signin]

        // [START build_client]
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(ActivitySignin.this, "onConnectionFailed 22", Toast.LENGTH_LONG).show();
                    }
                })
                //.enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        goToActivityGoogle();
                        Toast.makeText(ActivitySignin.this, "onConnected 22", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Toast.makeText(ActivitySignin.this, "onConnectionSuspended 2", Toast.LENGTH_LONG).show();
                    }
                })
                .build();

        mGoogleApiClient.connect(GoogleApiClient.SIGN_IN_MODE_OPTIONAL);
//
//        if(mGoogleApiClient.isConnected()) {
//            goToActivityGoogle();
//        }

//        mGoogleApiClient = new GoogleApiClient.Builder(ActivitySignin.this)
//                //.enableAutoManage(ActivitySignin.this, ActivitySignin.this)
//                .addApi(Plus.API)
//                .addScope(Plus.SCOPE_PLUS_LOGIN)
//                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                    @Override
//                    public void onConnected(@Nullable Bundle bundle) {
//                        Toast.makeText(ActivitySignin.this, "onConnected 2", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onConnectionSuspended(int i) {
//                        Toast.makeText(ActivitySignin.this, "onConnectionSuspended 2", Toast.LENGTH_LONG).show();
//                    }
//                })
//                .addOnConnectionFailedListener(ActivitySignin.this)
//                .build();

        SignInButton googleButton = (SignInButton) findViewById(R.id.sign_in_google);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);

//                if (mGoogleApiClient == null) {
//                    mGoogleApiClient = new GoogleApiClient.Builder(ActivitySignin.this)
//                            //.enableAutoManage(ActivitySignin.this, ActivitySignin.this)
//                            .addApi(Plus.API)
//                            .addScope(Plus.SCOPE_PLUS_LOGIN)
//                            .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                                @Override
//                                public void onConnected(@Nullable Bundle bundle) {
//                                    goToActivityGoogle(0;
//                                    Toast.makeText(ActivitySignin.this, "onConnected", Toast.LENGTH_LONG).show();
//                                }
//
//                                @Override
//                                public void onConnectionSuspended(int i) {
//                                    Toast.makeText(ActivitySignin.this, "onConnectionSuspended", Toast.LENGTH_LONG).show();
//                                }
//                            })
//                            .addOnConnectionFailedListener(ActivitySignin.this)
//                            .build();
//                }
//                if(!mGoogleApiClient.isConnected())
//                    mGoogleApiClient.connect();
            }
        });
    }

    @Override
    protected void onStop() {
        if(mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    private void initFacebookButton() {

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
//        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(ActivitySignin.this, "onSuccess", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(ActivitySignin.this, "onCancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(ActivitySignin.this, "onError", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()) {
                goToActivityGoogle();
            }
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void goToActivityGoogle(){
        startActivity(new Intent(ActivitySignin.this, MapsActivityMain.class));
    }

    public void loginWithEmail(View button) {
        Toast.makeText(ActivitySignin.this, "login with email", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
       if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, connectionResult.getErrorCode());
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                mGoogleApiClient.connect();
            }
        }

    }
    /*
    public void NewMe(View v){
        Intent me = new Intent(ActivitySignin.this, NativeLogin.class);
        startActivity(me);
    }*/


    /*
    //trying different buttons on a switch
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_signin_login:
                startActivity(new Intent(this, NativeLogin.class));
                break;

            case R.id.backToLogin:
                startActivity(new Intent(this, NativeLogin.class));
                break;
        }

    }*/


}

