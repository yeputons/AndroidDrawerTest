package com.example.DrawerTest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MyActivity extends Activity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("MyView");
        menu.add("MySurfaceView");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("MyView")) {
            setContentView(new MyView(this));
            return true;
        } else if (item.getTitle().equals("MySurfaceView")) {
            setContentView(new MySurfaceView(this));
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
}
