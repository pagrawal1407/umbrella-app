package com.foo.umbrella.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import com.foo.umbrella.AppPreferences;
import com.foo.umbrella.R;


public class SettingsActivity extends AppCompatActivity {

    private AppPreferences appPreferences;
    private EditText locationText, unitsText;
    String location, unit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        appPreferences = new AppPreferences(getApplicationContext());

        locationText = (EditText) findViewById(R.id.location);
        unitsText = (EditText) findViewById(R.id.units);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                location = locationText.getText().toString();
                unit = unitsText.getText().toString();

                appPreferences.saveLocation(location);
                appPreferences.saveUnit(unit);

                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
