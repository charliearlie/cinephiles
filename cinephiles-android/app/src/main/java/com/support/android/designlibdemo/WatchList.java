package com.support.android.designlibdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Lorenzo Koundouris on 06/11/2015.
 * University of Plymouth
 */
public class WatchList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ImageView star_wars_watchList_test_button = (ImageView) findViewById(R.id.watch_list_imageView1_3);
        star_wars_watchList_test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starWarsIntent2 = new Intent(WatchList.this, MediaDetailActivity.class);
                startActivity(starWarsIntent2);
            }
        });

        ImageView mi5_watchList_test_button = (ImageView) findViewById(R.id.watch_list_imageView1_2);
        mi5_watchList_test_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent mi5Intent = new Intent(WatchList.this, SearchMediaPage.class);
                startActivity(mi5Intent);
            }
        });

        View header = navigationView.inflateHeaderView(R.layout.nav_header);
        try {
            String stored_email = "";
            FileInputStream fileInputStream = openFileInput("email.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while((stored_email = bufferedReader.readLine()) != null){
                stringBuffer.append((stored_email));
            }
            TextView email_textView = (TextView) header.findViewById(R.id.navHead_email_textView);
            email_textView.setText(stringBuffer.toString());
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.watch_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(WatchList.this, Homepage.class));
        } else if (id == R.id.nav_movies) {
            startActivity(new Intent(WatchList.this, Movies.class));
        } else if (id == R.id.nav_tv) {
            //Nada;
        } else if (id == R.id.nav_watchlist) {
            startActivity(new Intent(WatchList.this, WatchList.class));
        } else if (id == R.id.nav_browse){
            startActivity(new Intent(WatchList.this, SearchMediaPage.class));
        } else if (id == R.id.nav_contact_us) {
            startActivity(new Intent(WatchList.this, WatchList.class));
        } else if (id == R.id.nav_logout){
            startActivity(new Intent(WatchList.this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
