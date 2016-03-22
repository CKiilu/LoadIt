package com.scurrae.chris.loadit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tumblr.loglr.Interfaces.ExceptionHandler;
import com.tumblr.loglr.Interfaces.LoginListener;
import com.tumblr.loglr.LoginResult;
import com.tumblr.loglr.Loglr;

/**
 * Created by chris on 3/22/16.
 */
public class Login extends AppCompatActivity  implements LoginListener,ExceptionHandler {


    private static final String TAG = MainActivity.class.getSimpleName();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
        Button button = (Button)findViewById(R.id.mainactivity_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lLogin();
            }
        });
    }
    public void lLogin(){

        Loglr.getInstance()
                //Set your application consumer key from Tumblr
                .setConsumerKey("0gqrAGH5xQmhjLwJbO2pqO8dTqwUFEgjni5kBECkjhbrdS45EB")

                        //Set your application Secret consumer key from Tumblr
                .setConsumerSecretKey("0Wj9lzJlXtmK0ReaRnSAMiFfBspeIT6gBUTnayPejq7ksj5qOS")

                        //Implement interface to receive Token and Secret Token
                .setLoginListener(Login.this)

                        //Interface to receive call backs when things go wrong
                .setExceptionHandler(Login.this)

                        //The URL set as a callback on Tumblr
                        //NOTE: Has to be same as the one entered on Tumblr dev console.
                        //Library will not work otherwise
                .setUrlCallBack("http://bit.ly/1pxd1BS")

                        //There are two ways to initiate the login process

                        //First :
                        //initiate login process in an activity
                .initiateInDialog(getSupportFragmentManager());




                        //OR

                        //Second :
                        //Initiate the login process in a dialogFragment | The support fragmentManager is an mandatory field
//      Activity, fragment is above
//                .initiateInActivity(context);
    }

    @Override
    public void onLoginSuccessful(LoginResult loginResult) {
        if(loginResult != null && !TextUtils.isEmpty(loginResult.getOAuthToken()) && !TextUtils.isEmpty(loginResult.getOAuthTokenSecret())) {
            Log.i(TAG, "Tumblr Token : " + loginResult.getOAuthToken());
            Log.i(TAG, "Tumblr Secret Token : " + loginResult.getOAuthTokenSecret());
            Button btnClickMe = (Button) findViewById(R.id.mainactivity_button);
            btnClickMe.setText("Congratulations, Tumblr login succeeded");
            btnClickMe.setEnabled(false);
        }
    }

    @Override
    public void onLoginFailed(RuntimeException exception) {

    }
}
