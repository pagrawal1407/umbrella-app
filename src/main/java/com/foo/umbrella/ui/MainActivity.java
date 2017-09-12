package com.foo.umbrella.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.foo.umbrella.AppPreferences;
import com.foo.umbrella.R;
import com.foo.umbrella.data.ApiServicesProvider;
import com.foo.umbrella.data.api.WeatherService;
import com.foo.umbrella.data.model.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.foo.umbrella.R.color.weather_cool;
import static com.foo.umbrella.R.color.weather_warm;

public class MainActivity extends AppCompatActivity {


    TextView location, temp, weather, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, time1, time2, time3, time4, time5, time6, time7, time8;
    View topBackgroud;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8;
    ImageButton settingsButton;

    private AppPreferences appPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appPreferences = new AppPreferences(getApplicationContext());

        if (appPreferences.getLocation().equals("")){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        initialize();
        ApiServicesProvider apiServicesProvider = new ApiServicesProvider(getApplication());

        WeatherService client = apiServicesProvider.getWeatherService();
        Call<WeatherData> call =
                client.forecastForZipCallable(appPreferences.getLocation());

        call.enqueue(new Callback<WeatherData>() {

            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                String  bodyString = response.body().toString();
                Log.i("Response: ", bodyString);

                currentWeather(response);
                hourlyForecast(response);

            }
            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Log.i("Error: ", t.getMessage());
            }
        });

    }

    private void hourlyForecast(Response<WeatherData> response) {

        Log.i("Hourly forecast size",String.valueOf(response.body().getForecast().size()));
        Log.i("0th index in forecast", String.valueOf(response.body().getForecast().get(0).getDateTime()));
        Log.i("temp at 0th index", String.valueOf(response.body().getForecast().get(0).getTempFahrenheit()));

        time1.setText(response.body().getForecast().get(0).getDisplayTime());
        Resources res = getResources();
        String mDrawableName = "weather_" + response.body().getForecast().get(0).getIcon();
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        image1.setImageResource(resID);
        temp1.setText(response.body().getForecast().get(0).getTempFahrenheit());

        time2.setText(response.body().getForecast().get(1).getDisplayTime());
        res = getResources();
        mDrawableName = "weather_" + response.body().getForecast().get(1).getIcon();
        resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        image2.setImageResource(resID);
        temp2.setText(response.body().getForecast().get(1).getTempFahrenheit());

        time3.setText(response.body().getForecast().get(2).getDisplayTime());
        res = getResources();
        mDrawableName = "weather_" + response.body().getForecast().get(2).getIcon();
        resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        image3.setImageResource(resID);
        temp3.setText(response.body().getForecast().get(2).getTempFahrenheit());

        time4.setText(response.body().getForecast().get(3).getDisplayTime());
        res = getResources();
        mDrawableName = "weather_" + response.body().getForecast().get(3).getIcon();
        resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        image4.setImageResource(resID);
        temp4.setText(response.body().getForecast().get(3).getTempFahrenheit());

        time5.setText(response.body().getForecast().get(4).getDisplayTime());
        res = getResources();
        mDrawableName = "weather_" + response.body().getForecast().get(4).getIcon();
        resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        image5.setImageResource(resID);
        temp5.setText(response.body().getForecast().get(4).getTempFahrenheit());


        time6.setText(response.body().getForecast().get(5).getDisplayTime());
        res = getResources();
        mDrawableName = "weather_" + response.body().getForecast().get(5).getIcon();
        resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        image6.setImageResource(resID);
        temp6.setText(response.body().getForecast().get(5).getTempFahrenheit());

        time7.setText(response.body().getForecast().get(6).getDisplayTime());
        res = getResources();
        mDrawableName = "weather_" + response.body().getForecast().get(6).getIcon();
        resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        image7.setImageResource(resID);
        temp7.setText(response.body().getForecast().get(6).getTempFahrenheit());

        time8.setText(response.body().getForecast().get(7).getDisplayTime());
        res = getResources();
        mDrawableName = "weather_" + response.body().getForecast().get(7).getIcon();
        resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        image8.setImageResource(resID);
        temp8.setText(response.body().getForecast().get(7).getTempFahrenheit());

    }

    private void currentWeather(Response<WeatherData> response) {

        location.setText(response.body().getCurrentObservation().getDisplayLocation().getFullName());
        temp.setText((response.body().getCurrentObservation().getTempFahrenheit()+ (char) 0x00B0));
        weather.setText(response.body().getCurrentObservation().getWeatherDescription());
        if (Double.parseDouble(response.body().getCurrentObservation().getTempFahrenheit()) > 60) {
            topBackgroud.setBackgroundColor(getResources().getColor(weather_warm));
            settingsButton.setBackgroundColor(getResources().getColor(weather_warm));
        }
        else {
            topBackgroud.setBackgroundColor(getResources().getColor(weather_cool));
            settingsButton.setBackgroundColor(getResources().getColor(weather_cool));
        }
    }


    private void initialize(){
        location = (TextView) findViewById(R.id.locationText);
        temp = (TextView) findViewById(R.id.temperatureText);
        weather = (TextView) findViewById(R.id.weatherText);
        topBackgroud = findViewById(R.id.topBackground);
        temp1 = (TextView) findViewById(R.id.temp1);
        temp2 = (TextView) findViewById(R.id.temp2);
        temp3 = (TextView) findViewById(R.id.temp3);
        temp4 = (TextView) findViewById(R.id.temp4);
        temp5 = (TextView) findViewById(R.id.temp5);
        temp6 = (TextView) findViewById(R.id.temp6);
        temp7 = (TextView) findViewById(R.id.temp7);
        temp8 = (TextView) findViewById(R.id.temp8);
        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);
        time3 = (TextView) findViewById(R.id.time3);
        time4 = (TextView) findViewById(R.id.time4);
        time5 = (TextView) findViewById(R.id.time5);
        time6 = (TextView) findViewById(R.id.time6);
        time7 = (TextView) findViewById(R.id.time7);
        time8 = (TextView) findViewById(R.id.time8);
        image1 = (ImageView) findViewById(R.id.image4PM);
        image2 = (ImageView) findViewById(R.id.image5PM);
        image3 = (ImageView) findViewById(R.id.image6PM);
        image4 = (ImageView) findViewById(R.id.image7PM);
        image5 = (ImageView) findViewById(R.id.image8PM);
        image6 = (ImageView) findViewById(R.id.image9PM);
        image7 = (ImageView) findViewById(R.id.image10PM);
        image8 = (ImageView) findViewById(R.id.image11PM);
        settingsButton = (ImageButton) findViewById(R.id.settingsbutton);

    }

    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
