package top.vitafresh.climate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherController extends AppCompatActivity {
    //TODO: Declare constants

    //TODO: Declare member variables
    ImageButton btnCityChange;
    TextView txtTemp;
    ImageView imgWeatherSymbol;
    TextView txtLocation;


    //TODO: Declare LocationManager and LocationListener


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_controller_layout);

        btnCityChange = (ImageButton)findViewById(R.id.btnCityChange);
        txtTemp = (TextView)findViewById(R.id.txtTemp);

        txtTemp.setText("111°");

    }

    //TODO: Add onResume()



    //TODO: Add Weather for current location

    //TODO: Add Weather for the city

    //TODO: Do API call to website (using prepared RequestParams)

    //TODO: Add UpdateUI (set data for views)

    //TODO: Add onPause()
}
