package com.trustudio.android.paguyuban.mitra;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    private EditText mFcmKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFcmKey = (EditText) findViewById(R.id.fcmKey);

        RetrieveRegId();

        LocalBroadcastManager.getInstance(this).registerReceiver(tokenReceiver,
                new IntentFilter("tokenReceiver"));
    }

    public void RetrieveRegId(){
        String token = FirebaseInstanceId.getInstance().getToken();
        String msg = getString(R.string.msg_token_fmt, token);
        // Log

        if(token != null && !token.isEmpty()) {
            Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show();
            mFcmKey.setText(token);
        }
    }

    BroadcastReceiver tokenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String token = intent.getStringExtra("token");
            if(token != null) {
                //send token to your server or what you want to do
                Toast.makeText(getApplicationContext(),token,Toast.LENGTH_LONG).show();
                mFcmKey.setText(token);
            }
        }
    };

}
