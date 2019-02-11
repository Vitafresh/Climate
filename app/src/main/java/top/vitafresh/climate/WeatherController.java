package top.vitafresh.climate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherController extends AppCompatActivity {
    //TODO: Declare constants

    //TODO: Declare member variables
    private ImageButton btnCityChange;
    private TextView txtTemp;
    private ImageView imgWeatherSymbol;
    private TextView txtLocation;
    private TextView textCity;


    //TODO: Declare LocationManager and LocationListener


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_controller_layout);

        txtTemp = (TextView)findViewById(R.id.txtTemp);
        txtTemp.setText("111°");
        textCity=findViewById(R.id.textCity);

        btnCityChange = (ImageButton)findViewById(R.id.btnCityChange);
        btnCityChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(WeatherController.this,ChangeCityController.class);
                startActivity(myIntent);
            }
        });



    }

    //TODO: Add onResume()
    @Override
    protected void onResume() {
        super.onResume();
        Intent myIntent = getIntent();
        String city = myIntent.getStringExtra("city");
        textCity.setText(city);
    }



    //TODO: Add Weather for current location

    //TODO: Add Weather for the city

    //TODO: Do API call to website (using prepared RequestParams)

    //TODO: Add UpdateUI (set data for views)

    //TODO: Add onPause()
}
