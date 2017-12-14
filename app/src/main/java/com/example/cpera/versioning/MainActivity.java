package com.example.cpera.versioning;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // in onCreate, will not run when app is switched back to after initial startup

        String version = "";
        boolean thrown = false;

        try {
            version = getVersion();
        } catch (Exception e) {
            e.printStackTrace();
            thrown = true;
        }

        if (!thrown){
            TextView tv = findViewById(R.id.tvid);
            tv.setText(version);
            // tv.setText(R.string.outdated);
        }
    }

    private String getVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release +")";
    }
}


























































// test