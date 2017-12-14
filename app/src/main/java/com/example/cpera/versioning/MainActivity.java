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

        String version = "@string/undetectable";
        boolean thrown = false;

        try {
            // try catch to handle versionGetting, anything below 1.5 will crash if you try to grab sdk_int
            version = getVersion();
        } catch (Exception e) {
            e.printStackTrace();
            thrown = true;
        }

        if (!thrown){
            //set version textView
            TextView tv = findViewById(R.id.tvid);
            tv.setText(version);

            //set message textView via TerBool
            boolean message = Build.VERSION.SDK_INT > 25;
            TextView ms = findViewById(R.id.message);
            if (message) {
                ms.setText(R.string.current);
            } else {
                ms.setText(R.string.outdated);
            }
        }

        boolean layout = getResources().getConfiguration().orientation == 1;
        TextView lo = findViewById(R.id.layout);
        if (layout) {
            lo.setText(R.string.port);
        } else {
            lo.setText(R.string.land);
        }
    }
    // toString for version
    public String getVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release +")";
    }
}


























































// test