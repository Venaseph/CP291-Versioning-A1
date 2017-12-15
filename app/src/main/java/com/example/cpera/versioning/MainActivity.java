package com.example.cpera.versioning;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.w3c.dom.Text;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        TextView tv = findViewById(R.id.tvid);
        TextView ms = findViewById(R.id.message);
        TextView cl = findViewById(R.id.countLabel);
        TextView oc = findViewById(R.id.count);
        TextView btn = findViewById(R.id.button);

        versioningStuff(tv, ms);
        rotationsStuff();
        sharedprefStuff(oc);
        buttonStuff();
        visiState(cl, btn, oc);
    }

    // versioning detection stuff
    private void versioningStuff(TextView tv, TextView ms) {
        String version = null;
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
            tv.setText(version);

            //set message textView via TerBool
            boolean message = Build.VERSION.SDK_INT > 22;
            if (message) {
                ms.setText(R.string.current);
                ms.setTextColor(Color.BLUE);
            } else {
                ms.setText(R.string.outdated);
                ms.setTextColor(Color.RED);
            }
        }
    }

    // toString for version
    private String getVersion() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Android SDK: " + sdkVersion + " (" + release +")";
    }

    // rotation detection and toast stuff
    private void rotationsStuff() {
        boolean layout = getResources().getConfiguration().orientation == 1;
        if (layout) {
            Toast.makeText(MainActivity.this, "Portrait", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Landscape", Toast.LENGTH_SHORT).show();
        }
    }


    private void sharedprefStuff(TextView oc) {
        //initialize SharedPref & create editor for sharedPref (non-fragment version)
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();

        //increment reloads getType only sets if key/value doesn't exist
        int num = sharedPref.getInt("reload", 0);
        num++;
        //update value
        edit.putInt("reload", num);
        //save it
        edit.commit();

        //set TextView to reload value
        oc.setText(valueOf(num));
    }

    private void buttonStuff() {
        //create onclick and instan button
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            //click control
            public void onClick(View v) {
                //sharedPrefStuff for visiState control
                SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPref.edit();
                TextView cl = findViewById(R.id.countLabel);
                TextView oc = findViewById(R.id.count);
                TextView btn = findViewById(R.id.button);

                boolean canYouSeeMe = sharedPref.getBoolean("visible", false);
                if (!canYouSeeMe) {
                    cl.setVisibility(View.VISIBLE);
                    btn.setText(R.string.less);
                    oc.setVisibility((View.VISIBLE));
                    edit.putBoolean("visible", true).commit();
                } else {
                    cl.setVisibility(View.INVISIBLE);
                    btn.setText(R.string.more);
                    oc.setVisibility((View.INVISIBLE));
                    edit.putBoolean("visible", false ).commit();
                }
            }
        });
    }

    private void visiState(TextView cl, TextView btn, TextView oc) {
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();

        boolean canYouSeeMe = sharedPref.getBoolean("visible", false);
        if(canYouSeeMe) {
            cl.setVisibility(View.VISIBLE);
            btn.setText(R.string.less);
            oc.setVisibility((View.VISIBLE));
            edit.putBoolean("visible", true).commit();
        } else {
            cl.setVisibility(View.INVISIBLE);
            btn.setText(R.string.more);
            oc.setVisibility((View.INVISIBLE));
            edit.putBoolean("visible", false ).commit();
        }
    }
}