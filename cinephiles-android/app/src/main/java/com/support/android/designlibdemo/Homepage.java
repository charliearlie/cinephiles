package com.support.android.designlibdemo;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

//import cinephiles.data.model.Media;

/**
 * Created by Lorenzo Koundouris on 06/11/2015.
 * University of Plymouth
 */
public class Homepage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String DEBUG_TAG = "HttpExample";
    ViewPager viewPager;
    CustomSwipeAdapter adapter;


    TextView test_txt44;



    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);


        test_txt44 = (TextView) findViewById(R.id.hottest_this_month_media1_textView);



        ImageView heart_of_the_sea_login_btn = (ImageView) findViewById(R.id.now_in_cinemas_imageView1);
        heart_of_the_sea_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent heartIntent = new Intent(Homepage.this, LoginActivity.class);
                startActivity(heartIntent);
            }
        });


        ImageView star_wars_homepage_test_btn = (ImageView) findViewById(R.id.now_in_cinemas_imageView3);
        star_wars_homepage_test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent starWarsIntent = new Intent(Homepage.this, MediaDetailActivity.class);
                startActivity(starWarsIntent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.homepage_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Go to my ratings", Snackbar.LENGTH_LONG)
                //.setAction("Action", null).show();
                Intent mediaIntent = new Intent(Homepage.this, WatchList.class);
                startActivity(mediaIntent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.inflateHeaderView(R.layout.nav_header);

        try {
            String stored_email = "";
            FileInputStream fileInputStream = openFileInput("email.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            while ((stored_email = bufferedReader.readLine()) != null) {
                stringBuffer.append((stored_email));
            }
            TextView email_textView = (TextView) header.findViewById(R.id.navHead_email_textView);
            email_textView.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        String stringURL = "https://cinephiles90.herokuapp.com/api/media/hotmovies";
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //fetch data
            new DownloadWebpageTask().execute(stringURL);
        } else {
            //display error
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("No network connection available!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
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
        getMenuInflater().inflate(R.menu.homepage, menu);


        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);

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

        if (id == R.id.menu_search) {
            onSearchRequested();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Nada
        } else if (id == R.id.nav_movies) {
            startActivity(new Intent(Homepage.this, Movies.class));
        } else if (id == R.id.nav_tv) {
            //startActivity(new Intent(Homepage.this, WatchList.class));
        } else if (id == R.id.nav_watchlist) {
            startActivity(new Intent(Homepage.this, WatchList.class));
        } else if (id == R.id.nav_browse) {
            startActivity(new Intent(Homepage.this, SearchMediaPage.class));
        } else if (id == R.id.nav_contact_us) {
            //startActivity(new Intent(Homepage.this, WatchList.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(Homepage.this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);

            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        //onPostExecute displays the results of the AsyncTask.
        protected void onPostExecute(String result) {

            String[] titles = result.split(";;");
            TextView hottest1 = (TextView) findViewById(R.id.hottest_this_month_media1_textView);
            hottest1.setText(titles[0]);

            TextView hottest2 = (TextView) findViewById(R.id.hottest_this_month_media2_textView);
            hottest2.setText(titles[1]);

            TextView hottest3 = (TextView) findViewById(R.id.hottest_this_month_media3_textView);
            hottest3.setText(titles[2]);

            TextView hottestSeries1 = (TextView) findViewById(R.id.now_in_cinemas_textView1);
            hottestSeries1.setText(titles[3]);

            TextView hottestSeries2 = (TextView) findViewById(R.id.now_in_cinemas_textView2);
            hottestSeries2.setText(titles[4]);

            TextView hottestSeries3 = (TextView) findViewById(R.id.now_in_cinemas_textView3);
            hottestSeries3.setText(titles[5]);

            TextView netflix1 = (TextView) findViewById(R.id.coming_out_soon_media1_textView);
            netflix1.setText(titles[6]);

            TextView netflix2 = (TextView) findViewById(R.id.coming_out_soon_media2_textView);
            netflix2.setText(titles[7]);

            TextView netflix3 = (TextView) findViewById(R.id.coming_out_soon_media3_textView);
            netflix3.setText(titles[8]);


        }

        // Given a URL, establishes an HttpUrlConnection and retrieves the webpage content as an
        // InputStream, which it returns as a string.
        private String downloadUrl(String urls) throws IOException {
            InputStream is = null;
//            // Only display the first 500 characters of the retrieved webpage content
//            //int len = 500;
            String titles;
//            String[] hotMovies = getMedia("https://cinephiles-api.herokuapp.com/api/media/hotmovies");
//            String[] hotSeries = getMedia("http://cinephiles-api.herokuapp.com/api/media/hotseries");
//            String[] netflix = getMedia("http://cinephiles-api.herokuapp.com/api/media/netflix");
//
//            titles = TextUtils.join(";;", hotMovies);
//            titles += ";;" + TextUtils.join(";;", hotSeries);
//            titles += ";;" + TextUtils.join(";;", netflix);

            return titles;

//
//            try {
//                URL url = new URL("https://cinephiles-api.herokuapp.com/api/media/hotmovies");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(10000 /* milliseconds */);
//                conn.setConnectTimeout(15000 /* milliseconds */);
//                conn.setRequestMethod("GET");
//                conn.setDoInput(true);
//                // Starts the query
//                conn.connect();
//                int response = conn.getResponseCode();
//                Log.d(DEBUG_TAG, "The response is: " + response);
//                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//                String contentAsString = readAll(br);
//                Gson gson = new Gson();
//                // Convert the inputStream into a string
////                String contentAsString = readAll(is, 500000 /*len*/); Kept for Lorenzo
//                Media[] media = gson.fromJson(contentAsString, Media[].class);
//                String title = "";
//                for(int i = 0; i < media.length; i++) {
//                    title += media[i].getTitle() + ";;";
//                }
//
//
//
//                new DownloadImageTask((ImageView) findViewById(R.id.hottest_this_month_media1_imageView)).execute(media[0].getPoster());
//                new DownloadImageTask((ImageView) findViewById(R.id.hottest_this_month_media2_imageView)).execute(media[1].getPoster());
//                new DownloadImageTask((ImageView) findViewById(R.id.hottest_this_month_media3_imageView)).execute(media[2].getPoster());
//
//                //test_txt44 = (TextView) findViewById(R.id.hottest_this_month_media1_textView);
//                //String test = media[0].getTitle();
//                //test_txt44.setText("b0ob");
////
////                TextView test_txt2 = (TextView) findViewById(R.id.hottest_this_month_media1_textView);
////                test_txt2.setText(media[1].getTitle());
////
////                TextView test_txt3 = (TextView) findViewById(R.id.hottest_this_month_media1_textView);
////                test_txt3.setText(media[2].getTitle());
//
//                return title;
//
//                // Makes sure that the inputStream is closed after the app is finished using it
//            } finally {
//                if (is != null) {
//                    is.close();
//                }
//            }
        }

        private String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        private String[] getMedia(String url) throws IOException {
            InputStream is = null;
            try {
                URL urlss = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) urlss.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                Log.d(DEBUG_TAG, "The response is: " + response);
                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
                String contentAsString = readAll(br);
                Gson gson = new Gson();
                // Convert the inputStream into a string
//                String contentAsString = readAll(is, 500000 /*len*/); Kept for Lorenzo
                Media[] media = gson.fromJson(contentAsString, Media[].class);
                String[] title = new String[3];
                for(int i = 0; i < media.length; i++) {
                    title[i] = media[i].getTitle();
                }

                if (url == "https://cinephiles-api.herokuapp.com/api/media/hotmovies") {
                    new DownloadImageTask((ImageView) findViewById(R.id.hottest_this_month_media1_imageView)).execute(media[0].getPoster());
                    new DownloadImageTask((ImageView) findViewById(R.id.hottest_this_month_media2_imageView)).execute(media[1].getPoster());
                    new DownloadImageTask((ImageView) findViewById(R.id.hottest_this_month_media3_imageView)).execute(media[2].getPoster());
                } else if (url == "http://cinephiles-api.herokuapp.com/api/media/hotseries") {
                    new DownloadImageTask((ImageView) findViewById(R.id.now_in_cinemas_imageView1)).execute(media[0].getPoster());
                    new DownloadImageTask((ImageView) findViewById(R.id.now_in_cinemas_imageView2)).execute(media[1].getPoster());
                    new DownloadImageTask((ImageView) findViewById(R.id.now_in_cinemas_imageView3)).execute(media[2].getPoster());
                } else if (url == "http://cinephiles-api.herokuapp.com/api/media/netflix") {
                    new DownloadImageTask((ImageView) findViewById(R.id.coming_out_soon_media1_imageView)).execute(media[0].getPoster());
                    new DownloadImageTask((ImageView) findViewById(R.id.coming_out_soon_media2_imageView)).execute(media[1].getPoster());
                    new DownloadImageTask((ImageView) findViewById(R.id.coming_out_soon_media3_imageView)).execute(media[2].getPoster());
                }



                //test_txt44 = (TextView) findViewById(R.id.hottest_this_month_media1_textView);
                //String test = media[0].getTitle();
                //test_txt44.setText("b0ob");
//
//                TextView test_txt2 = (TextView) findViewById(R.id.hottest_this_month_media1_textView);
//                test_txt2.setText(media[1].getTitle());
//
//                TextView test_txt3 = (TextView) findViewById(R.id.hottest_this_month_media1_textView);
//                test_txt3.setText(media[2].getTitle());

                return title;

                // Makes sure that the inputStream is closed after the app is finished using it
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }

        //Reads an inputStream and converts it to a String.
//        public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
//            Reader reader = null;
//            reader = new InputStreamReader(stream, "UTF-8");
//            char[] buffer = new char[len];
//            reader.read(buffer);
//            return new String(buffer);
//        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage){
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls){
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try{
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e){
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result){
            bmImage.setImageBitmap(result);
        }
    }

}
