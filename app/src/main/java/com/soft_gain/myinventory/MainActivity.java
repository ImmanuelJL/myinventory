package com.soft_gain.myinventory;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPrefs = getSharedPreferences("sg_shared_pref", MODE_PRIVATE);
        SharedPreferences.Editor ed;
        if(!sharedPrefs.contains("token")){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SharedPreferences sgSharedPref = getApplicationContext().getSharedPreferences("sg_shared_pref", getApplicationContext().MODE_PRIVATE);
        sgSharedPref.edit().clear().commit();

        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
