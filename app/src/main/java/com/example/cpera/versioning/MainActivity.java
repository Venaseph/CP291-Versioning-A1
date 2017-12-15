package com.example.cpera.versioning;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;
import static java.lang.String.valueOf;

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
                ms.setTextColor(Color.BLUE);
            } else {
                ms.setText(R.string.outdated);
                ms.setTextColor(Color.RED);
            }
        }

        boolean layout = getResources().getConfiguration().orientation == 1;
        if (layout) {
            Toast.makeText(MainActivity.this, "Portrait", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Landscape", Toast.LENGTH_LONG).show();
        }

        //initialize SharedPref & create editor for sharedPref
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();

        //increment reloads get only sets if key/value doesn't exist
        int num = sharedPref.getInt("reload", 0);
        num++;
        edit.putInt("reload", num);
        edit.commit();

        //set TextView to reload value
        TextView oc = findViewById(R.id.count);
        oc.setText(valueOf(num));
    }

    // toString for version
    public String getVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release +")";
    }
}


























































// test