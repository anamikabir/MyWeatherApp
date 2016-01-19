package com.example.anamikarani.myweatherapp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DetailsActivity extends AppCompatActivity {

    public String Desc;
    public static JSONObject json_main, hourly_data;
    public int num = 0, times = 0, j = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Button next7days = (Button) findViewById(R.id.button8);
        next7days.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Log.i("LOg7", "laaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                TableLayout table_1 = (TableLayout) findViewById(R.id.DetTab);
                table_1.setVisibility(View.GONE);
                TableLayout table_2 = (TableLayout) findViewById(R.id.DetTab2);
                table_2.setVisibility(View.VISIBLE);
            }

        });
        Button next24hours = (Button) findViewById(R.id.button7);
        next24hours.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TableLayout table_1 = (TableLayout) findViewById(R.id.DetTab);
                table_1.setVisibility(View.VISIBLE);
                TableLayout table_2 = (TableLayout) findViewById(R.id.DetTab2);
                table_2.setVisibility(View.GONE);

            }
        });

        try {
            json_main = new JSONObject(getIntent().getStringExtra("JSON"));
            String day_n = "", img_day = "";
            for (int i = 1; i <= 7; i++) {
                SimpleDateFormat tmzone = new SimpleDateFormat("EEEE,MMM dd");
                tmzone.setTimeZone(TimeZone.getTimeZone(json_main.getString("timezone")));
                JSONObject weeklyObj = json_main.getJSONObject("daily");
                long day_name = (weeklyObj.getJSONArray("data").getJSONObject(i).getLong("time")) * 1000;
                day_n = tmzone.format(new Date(day_name));
                //Log.i("testt:", day_n);
                TextView day = (TextView) findViewById(getResources().getIdentifier("day" + i, "id", getPackageName()));
                day.setText(day_n);

                img_day = weeklyObj.getJSONArray("data").getJSONObject(i).getString("icon");

                ImageView pic = (ImageView) findViewById(getResources().getIdentifier("imgDay" + i, "id", getPackageName()));

                switch (img_day) {
                    case "clear-day":
                        pic.setImageResource(R.drawable.clear);
                        break;
                    case "clear-night":
                        pic.setImageResource(R.drawable.clear_night);
                        break;
                    case "rain":
                        pic.setImageResource(R.drawable.rain);
                        break;
                    case "snow":
                        pic.setImageResource(R.drawable.snow);
                        break;
                    case "sleet":
                        pic.setImageResource(R.drawable.sleet);
                        break;
                    case "wind":
                        pic.setImageResource(R.drawable.wind);
                        break;
                    case "fog":
                        pic.setImageResource(R.drawable.fog);
                        break;
                    case "cloudy":
                        pic.setImageResource(R.drawable.cloudy);
                        break;
                    case "partly-cloudy-day":
                        pic.setImageResource(R.drawable.cloud_day);
                        break;
                    case "partly-cloudy-night":
                        pic.setImageResource(R.drawable.cloud_night);
                        break;
                }

                pic.setAdjustViewBounds(true);
                pic.setMaxHeight(80);
                pic.setMaxWidth(80);

                double mintemp = Math.round(weeklyObj.getJSONArray("data").getJSONObject(i).getDouble("temperatureMin"));
                double maxtemp = Math.round(weeklyObj.getJSONArray("data").getJSONObject(i).getDouble("temperatureMax"));
                TextView temp_t = (TextView) findViewById(getResources().getIdentifier("tempDay" + i, "id", getPackageName()));
                temp_t.setText("Min: " + ((int) mintemp) + " " + getIntent().getStringExtra("Temperature") + " | Max: " + ((int) maxtemp) + " " + getIntent().getStringExtra("Temperature"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            //return;
        }
        try {
            json_main = new JSONObject(getIntent().getStringExtra("JSON"));
            hourly_data = json_main.getJSONObject("hourly");
            TextView t1 = (TextView) findViewById(R.id.Short_Text);
            String temp = "More details for " + getIntent().getStringExtra("Place");
            t1.setText(temp);

            nexthours24();

        } catch (Exception e) {
            e.printStackTrace();
            //return;
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void nexthours24() {
        //display 24 hour info
        TableLayout table_24 = (TableLayout) findViewById(R.id.DetTab);
        TableRow TabRow;

        TableRow.LayoutParams layoutp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        if (times == 0) {
            num = 0;
            TabRow = new TableRow(this);
            TabRow.setBackgroundColor(Color.parseColor("#40E0D0"));
            TextView col1 = new TextView(this);
            TextView col2 = new TextView(this);
            TextView col3 = new TextView(this);
            col1.setText("Time");
            col2.setText("Summary");
            String tempr = "Temp(" + getIntent().getStringExtra("Temperature") + ")";
            col3.setText(tempr);
            layoutp.gravity = Gravity.LEFT;
            col1.setLayoutParams(layoutp);
            TabRow.addView(col1);
            layoutp.gravity = Gravity.CENTER_HORIZONTAL;
            col2.setLayoutParams(layoutp);
            TabRow.addView(col2);
            layoutp.gravity = Gravity.RIGHT;
            col3.setLayoutParams(layoutp);
            TabRow.addView(col3);
            table_24.addView(TabRow);
        } else {
            j = 24;
        }
        for (int i = 0; i < 24; i++) {
            TabRow = new TableRow(this);

            if ((i % 2) == 0)
                TabRow.setBackgroundColor(Color.parseColor("#CBCBCB"));
            else
                TabRow.setBackgroundColor(Color.parseColor("#FFFFFF"));

            Long temp_24;

            String time;
            try {
                SimpleDateFormat tmz = new SimpleDateFormat("h:mm a");
                tmz.setTimeZone(TimeZone.getTimeZone(json_main.getString("timezone")));

                Desc = hourly_data.getJSONArray("data").getJSONObject(i + j).getString("icon");
                temp_24 = Math.round(hourly_data.getJSONArray("data").getJSONObject(i + j).getDouble("temperature"));
                long hour_time = (hourly_data.getJSONArray("data").getJSONObject(i + j).getLong("time")) * 1000;
                time = tmz.format(new Date(hour_time));

                TextView timeview = new TextView(this);
                ImageView v1 = new ImageView(this);
                TextView tempview = new TextView(this);

                timeview.setText(time);

                switch (Desc) {
                    case "clear-day":
                        v1.setImageResource(R.drawable.clear);
                        break;
                    case "clear-night":
                        v1.setImageResource(R.drawable.clear_night);
                        break;
                    case "rain":
                        v1.setImageResource(R.drawable.rain);
                        break;
                    case "snow":
                        v1.setImageResource(R.drawable.snow);
                        break;
                    case "sleet":
                        v1.setImageResource(R.drawable.sleet);
                        break;
                    case "wind":
                        v1.setImageResource(R.drawable.wind);
                        break;
                    case "fog":
                        v1.setImageResource(R.drawable.fog);
                        break;
                    case "cloudy":
                        v1.setImageResource(R.drawable.cloudy);
                        break;
                    case "partly-cloudy-day":
                        v1.setImageResource(R.drawable.cloud_day);
                        break;
                    case "partly-cloudy-night":
                        v1.setImageResource(R.drawable.cloud_night);
                        break;
                }


                v1.setAdjustViewBounds(true);
                v1.setMaxHeight(100);
                v1.setMaxWidth(100);

                layoutp.gravity = Gravity.LEFT;
                timeview.setLayoutParams(layoutp);
                TabRow.addView(timeview);

                layoutp.gravity = Gravity.CENTER_HORIZONTAL;
                v1.setLayoutParams(layoutp);
                TabRow.addView(v1);

                layoutp.gravity = Gravity.RIGHT;
                tempview.setText(Long.toString(temp_24));
                tempview.setLayoutParams(layoutp);
                TabRow.addView(tempview);
                table_24.addView(TabRow);
                num++;

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        if (num == 24) {
            String n = num + ":";
            Log.i("num", n);
            final Button extrainfo = new Button(this);
            extrainfo.setText("+");
            TabRow = new TableRow(this);
            layoutp.gravity = Gravity.CENTER;

            extrainfo.setLayoutParams(layoutp);
            TabRow.addView(extrainfo);
            table_24.addView(TabRow);

            extrainfo.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    times = 1;
                    extrainfo.setVisibility(View.GONE);
                    nexthours24();

                }
            });
        }

    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
     /*   client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Details Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.anamikarani.myweatherapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Details Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.anamikarani.myweatherapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }*/
    }
}
