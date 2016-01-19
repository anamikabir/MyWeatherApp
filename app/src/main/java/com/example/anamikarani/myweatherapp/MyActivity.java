package com.example.anamikarani.myweatherapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MyActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1, button2, button3;
    ImageView img;
    EditText textbox1, textbox2, streetAdd, city;
    Spinner state_spin;
    RadioButton deg;
    TextView error_msg;
    String url,s1,s2,s3,s4;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        img = (ImageView) findViewById(R.id.imageView);
        img.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void button3click() {
        startActivity(new Intent(MyActivity.this,aboutMe.class));
    }

    private void ForeCastClick() {
        Uri uri = Uri.parse("http://forecast.io/");
        Intent intentOpenPage = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intentOpenPage);

    }

    private void searchForm() {
        error_msg = (TextView) findViewById(R.id.textView7);
        error_msg.setText("");
        streetAdd = (EditText) findViewById(R.id.editText);
        city = (EditText) findViewById(R.id.editText2);
        deg = (RadioButton) findViewById(R.id.radioButton);
        state_spin = (Spinner) findViewById(R.id.spinner);
        s3 = state_spin.getSelectedItem().toString();
        s1 = streetAdd.getText().toString().trim();
        s2 = city.getText().toString().trim();
        if (s1.equals("")) {
            error_msg.setText("Please enter Street Address");
            return;
        }
        if (s2.equals("")) {
            error_msg.setText("Please enter the city");
            return;
        }
        if (s3.equals("Select...")) {
            error_msg.setText("Please select a state");
            return;
        }
        if(error_msg.getText().toString().equals(""))
        {
            RadioButton fah = (RadioButton)findViewById(R.id.radioButton);
            if(fah.isChecked()){s4="Fahrenheit";}
            else {s4="Celsius";}
            try {
                String url1 = "http://webtechapplicationpradeept-env.elasticbeanstalk.com/Assignment-8ap.php?";
                String url2 = "addr="+URLEncoder.encode(s1, "UTF-8")+"&city="+URLEncoder.encode(s2,"UTF-8")+"&state="+URLEncoder.encode(s3,"UTF-8")+"&Temp="+URLEncoder.encode(s4,"UTF-8");
                url =url1+url2;
                new ResPage().execute(url);
            }catch (Exception e){}
            /*Intent IntentRes=new Intent(MyActivity.this,ResultPage.class);
            IntentRes.putExtra("Street",s1);
            IntentRes.putExtra("City",s2);
            IntentRes.putExtra("State",s3);
            IntentRes.putExtra("Degree",s4);
            startActivity(IntentRes);*/
        }
    }

    private void clearForm() {
        state_spin = (Spinner) findViewById(R.id.spinner);
        textbox1 = (EditText) findViewById(R.id.editText);
        textbox2 = (EditText) findViewById(R.id.editText2);
        textbox1.setText("");
        textbox2.setText("");
        deg = (RadioButton) findViewById(R.id.radioButton);
        deg.setChecked(true);
        state_spin.setSelection(0);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                button3click();
                break;
            case R.id.imageView:
                ForeCastClick();
                break;
            case R.id.button:
                searchForm();
                break;
            case R.id.button2:
                clearForm();
                break;
        }

    }

    private class ResPage extends AsyncTask<String, Integer, String> {
        protected String doInBackground(String... urls) {
            try
            {
                URL urlPhp=new URL(url);
                HttpURLConnection conn = (HttpURLConnection)urlPhp.openConnection();
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                int responseCode = conn.getResponseCode();
                if(responseCode==200)
                {
                    InputStream is = new BufferedInputStream(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuffer res = new StringBuffer();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        res.append(line);
                    }
                    conn.disconnect();
                    return res.toString();
                }

            }
            catch (Exception e)
            {
                Toast.makeText(MyActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                return null;
            }

            return null;
        }
        protected void onPostExecute(String result) {
            Intent resPageInt = new Intent(MyActivity.this, ResultPage.class);
            resPageInt.putExtra("Street", s1);
            resPageInt.putExtra("City", s2);
            resPageInt.putExtra("State",s3);
            resPageInt.putExtra("Degree",s4);
            resPageInt.putExtra("json_str", result);
            startActivity(resPageInt);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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

   /* @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "My Page", // TODO: Define a title for the content shown.
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
                "My Page", // TODO: Define a title for the content shown.
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
