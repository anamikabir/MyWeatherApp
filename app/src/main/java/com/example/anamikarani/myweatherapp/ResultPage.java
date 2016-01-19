package com.example.anamikarani.myweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class ResultPage extends AppCompatActivity implements View.OnClickListener{
    public String StateN="",temp_unit="",JSON_data="";
    final String DEGREE  = "\u00b0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(this);
        TextView t1=(TextView)findViewById(R.id.textView10);
        TextView t2=(TextView)findViewById(R.id.textView12);
        TextView t3=(TextView)findViewById(R.id.textView13);
        TextView t4=(TextView)findViewById(R.id.textView22);
        TextView t5=(TextView)findViewById(R.id.textView23);
        TextView t6=(TextView)findViewById(R.id.textView24);
        TextView t7=(TextView)findViewById(R.id.textView25);
        TextView t8=(TextView)findViewById(R.id.textView26);
        TextView t9=(TextView)findViewById(R.id.textView27);
        TextView t10=(TextView)findViewById(R.id.textView28);
        TextView t11=(TextView)findViewById(R.id.textView29);
        ImageView v1=(ImageView)findViewById(R.id.imageView3);
        String JSON= getIntent().getStringExtra("json_str");
        JSON_data=JSON;
        String stateAbb="", stateName=getIntent().getStringExtra("State"),cityName=getIntent().getStringExtra("City");
        String deg=getIntent().getStringExtra("Degree"), wind_unit="",visibility_unit="";
        if(deg.equals("Fahrenheit"))
        {
            temp_unit="F";
            wind_unit="mph";
            visibility_unit="mi";

        }
        else {
            temp_unit="C";
            wind_unit="m/s";
            visibility_unit="km";

        }
        try {
            JSONObject mainObject = new JSONObject(JSON);
            String tmzn=mainObject.getString("timezone");
            JSONObject currentlyObj = mainObject.getJSONObject("currently");
            String icon=currentlyObj.getString("icon");
            switch(icon)
            {
                case  "clear-day": v1.setBackgroundResource(R.drawable.clear); break;
                case  "clear-night": v1.setBackgroundResource(R.drawable.clear_night); break;
                case  "rain": v1.setBackgroundResource(R.drawable.rain); break;
                case  "snow": v1.setBackgroundResource(R.drawable.snow); break;
                case  "sleet": v1.setBackgroundResource(R.drawable.sleet); break;
                case  "wind": v1.setBackgroundResource(R.drawable.wind); break;
                case  "fog": v1.setBackgroundResource(R.drawable.fog); break;
                case  "cloudy": v1.setBackgroundResource(R.drawable.cloudy); break;
                case  "partly-cloudy-day": v1.setBackgroundResource(R.drawable.cloud_day); break;
                case  "partly-cloudy-night": v1.setBackgroundResource(R.drawable.cloud_night); break;
            }
            switch(stateName)
            {
                case "Alabama": stateAbb="AL"; break;
                case "Alaska": stateAbb="AK"; break;
                case "Alberta": stateAbb="AB"; break;
                case "American Samoa": stateAbb="AS"; break;
                case "Arizona": stateAbb="AZ"; break;
                case "Arkansas": stateAbb="AR"; break;
                case "Armed Forces (AE": stateAbb="AE"; break;
                case "Armed Forces Americas": stateAbb="AA"; break;
                case "Armed Forces Pacific": stateAbb="AP"; break;
                case "British Columbia": stateAbb="BC"; break;
                case "California": stateAbb="CA"; break;
                case "Colorado": stateAbb="CO"; break;
                case "Connecticut": stateAbb="CT"; break;
                case "Delaware": stateAbb="DE"; break;
                case "District Of Columbia": stateAbb="DC"; break;
                case "Florida": stateAbb="FL"; break;
                case "Georgia": stateAbb="GA"; break;
                case "Guam": stateAbb="GU"; break;
                case "Hawaii": stateAbb="HI"; break;
                case "Idaho": stateAbb="ID"; break;
                case "Illinois": stateAbb="IL"; break;
                case "Indiana": stateAbb="IN"; break;
                case "Iowa": stateAbb="IA"; break;
                case "Kansas": stateAbb="KS"; break;
                case "Kentucky": stateAbb="KY"; break;
                case "Louisiana": stateAbb="LA"; break;
                case "Maine": stateAbb="ME"; break;
                case "Manitoba": stateAbb="MB"; break;
                case "Maryland": stateAbb="MD"; break;
                case "Massachusetts": stateAbb="MA"; break;
                case "Michigan": stateAbb="MI"; break;
                case "Minnesota": stateAbb="MN"; break;
                case "Mississippi": stateAbb="MS"; break;
                case "Missouri": stateAbb="MO"; break;
                case "Montana": stateAbb="MT"; break;
                case "Nebraska": stateAbb="NE"; break;
                case "Nevada": stateAbb="NV"; break;
                case "New Brunswick": stateAbb="NB"; break;
                case "New Hampshire": stateAbb="NH"; break;
                case "New Jersey": stateAbb="NJ"; break;
                case "New Mexico": stateAbb="NM"; break;
                case "New York": stateAbb="NY"; break;
                case "Newfoundland": stateAbb="NF"; break;
                case "North Carolina": stateAbb="NC"; break;
                case "North Dakota": stateAbb="ND"; break;
                case "Northwest Territories": stateAbb="NT"; break;
                case "Nova Scotia": stateAbb="NS"; break;
                case "Nunavut": stateAbb="NU"; break;
                case "Ohio": stateAbb="OH"; break;
                case "Oklahoma": stateAbb="OK"; break;
                case "Ontario": stateAbb="ON"; break;
                case "Oregon": stateAbb="OR"; break;
                case "Pennsylvania": stateAbb="PA"; break;
                case "Prince Edward Island": stateAbb="PE"; break;
                case "Puerto Rico": stateAbb="PR"; break;
                case "Quebec": stateAbb="PQ"; break;
                case "Rhode Island": stateAbb="RI"; break;
                case "Saskatchewan": stateAbb="SK"; break;
                case "South Carolina": stateAbb="SC"; break;
                case "South Dakota": stateAbb="SD"; break;
                case "Tennessee": stateAbb="TN"; break;
                case "Texas": stateAbb="TX"; break;
                case "Utah": stateAbb="UT"; break;
                case "Vermont": stateAbb="VT"; break;
                case "Virgin Islands": stateAbb="VI"; break;
                case "Virginia": stateAbb="VA"; break;
                case "Washington": stateAbb="WA"; break;
                case "West Virginia": stateAbb="WV"; break;
                case "Wisconsin": stateAbb="WI"; break;
                case "Wyoming": stateAbb="WY"; break;
                case "Yukon Territory": stateAbb="YT"; break;
            }
            StateN=cityName+", "+stateAbb;
            String temp1=currentlyObj.getString("summary") + " in "+cityName+", "+stateAbb;
            t1.setText(temp1);
            String temp2=Math.round(currentlyObj.getDouble("temperature"))+DEGREE+temp_unit;
            t2.setText(temp2);
            JSONObject daily_det=mainObject.getJSONObject("daily");
            JSONArray daily_data = daily_det.getJSONArray("data");
            JSONObject day1=daily_data.getJSONObject(0);
            String temp3="L:"+Math.round(day1.getDouble("temperatureMin"))+DEGREE+" | H:"+Math.round(day1.getDouble("temperatureMax"))+DEGREE;
            t3.setText(temp3);

            //precipitation
            Double precipVal=currentlyObj.getDouble("precipIntensity");
            String precipText="";
            if (precipVal>=0 && precipVal<0.002) precipText="None";
            else if(precipVal>=0.002 && precipVal<0.017) precipText="Very Light";
            else if(precipVal>=0.017 && precipVal<0.1) precipText="Light";
            else if(precipVal>=0.1 && precipVal<0.4) precipText="Moderate";
            else if(precipVal>=0.4) precipText="Heavy";
            t4.setText(precipText);
            //rain Probability
            Double rainProb=currentlyObj.getDouble("precipProbability")*100;
            String temp4=Math.round(rainProb)+"%";
            t5.setText(temp4);
            //Wind Speed
            Double WindSpeed=currentlyObj.getDouble("windSpeed");
            String temp5=WindSpeed+wind_unit;
            t6.setText(temp5);
            //Dew Point
            Double DewPoint=currentlyObj.getDouble("dewPoint");
            String temp6=Math.round(DewPoint)+DEGREE+temp_unit;
            t7.setText(temp6);
            //Humidity
            Double Humid=currentlyObj.getDouble("humidity")*100;
            String temp7=Math.round(Humid)+"%";
            t8.setText(temp7);
            //Visibility visibility
            Double visibility=currentlyObj.getDouble("visibility");
            String temp8=visibility+visibility_unit;
            t9.setText(temp8);
            //Sunrise and Sunset
            long SunriseTime = day1.getLong("sunriseTime");
            long SunsetTime = day1.getLong("sunsetTime");
            Date date1 = new Date(SunriseTime*1000L);
            Date date2 = new Date(SunsetTime*1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
            sdf.setTimeZone(TimeZone.getTimeZone(tmzn));
            String SunRise = sdf.format(date1);
            String SunSet = sdf.format(date2);
            t10.setText(SunRise);
            t11.setText(SunSet);

        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.button4:{
                String tempr=DEGREE+temp_unit;
                Intent DetRes= new Intent(ResultPage.this,DetailsActivity.class);
                DetRes.putExtra("Place",StateN);
                DetRes.putExtra("Temperature",tempr);
                DetRes.putExtra("JSON", JSON_data);
                startActivity(DetRes);
                break;
            }
            case R.id.button5:{
                Intent MapRes= new Intent(ResultPage.this,MapActivity.class);
                startActivity(MapRes);
                break;
            }
        }
    }

}
