package com.example.smi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class facebook extends AppCompatActivity {

    private TextView tv,tv2;
    private LoginButton bt;
    private CallbackManager c;
    private ProfilePictureView profilePictureView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        tv = findViewById(R.id.text);
        tv2=findViewById(R.id.text2);
        bt = (LoginButton) findViewById(R.id.login_button);
        c = CallbackManager.Factory.create();
        profilePictureView = findViewById(R.id.profile);
        getSupportActionBar().hide();
        bt.setPermissions(Arrays.asList("public_profile"));
        bt.registerCallback(c, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data){
        c.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    AccessTokenTracker t = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                tv.setText("");
                tv2.setText("");
                profilePictureView.setProfileId("");
                Toast.makeText(facebook.this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(facebook.this,MainActivity.class));
            } else {
                loadUserProfile(currentAccessToken);
            }
        }
    };

    private void loadUserProfile(final AccessToken newaccessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(newaccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (object != null) {
                    try {

                        String email = object.getString("email");
                        String fname = object.getString("first_name");
                        String lname = object.getString("last_name");
                        String id=String.valueOf(object.getString("id"));
                        //profilePictureView.setCropped(true);
                        profilePictureView.setProfileId(id);
                        Toast.makeText(facebook.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        tv2.setText(email);
                        tv.setText(fname+" "+lname);


                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "email,first_name,last_name,picture");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }
}