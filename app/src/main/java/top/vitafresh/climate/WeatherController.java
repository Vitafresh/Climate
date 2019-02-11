package top.vitafresh.climate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;

public class WeatherController extends AppCompatActivity {
    //TODO: Declare constants

    //URL to retrieve weather data
    private final String URL_SITE="http://api.openweathermap.org/data/2.5/weather";
    // App ID to use OpenWeather data
    private final String APP_ID = "5e8752a808837d9b64aa7990b885a9f9";
    //Set metric units constatnt
    private final String MEASURE_UNITS = "metric";

    private String mCity = "Unknown";


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
                //Send focus to the Activity with City selection (CahngeCityController.class)
                startActivity(myIntent);
            }
        });



    }

    //TODO: Add onResume()
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Climate","onResume");

        Intent myIntent = getIntent();
        //Get data from City selection Activity
        mCity = myIntent.getStringExtra("city");
        if(mCity != null) {
            textCity.setText(mCity);
            getWeatherForCity(mCity);
        }
        else{
            textCity.setText("Undefined");
            getWeatherByLocation();
        }
    }



    //TODO: Add Weather for current location
    private void getWeatherByLocation(){
        RequestParams params=new RequestParams();
        params.add("appid",APP_ID);
        params.add("lat","latitude");
        params.add("lon","longitude");
        params.add("units",MEASURE_UNITS);


        getJsonWeatherByApi(params);

    }

    //TODO: Add Weather for the city
    private void getWeatherForCity(String city){
        RequestParams params=new RequestParams();
        params.add("appid",APP_ID);
        params.add("q",city);
        params.add("units",MEASURE_UNITS);
        getJsonWeatherByApi(params);
    }

    //TODO: Do API call to website (using prepared RequestParams)
    private void getJsonWeatherByApi(RequestParams params){
        Log.d("Climate","params: " + params.toString());
    }

    //TODO: Add UpdateUI (set data for views)

    //TODO: Add onPause()

    //TODO: Refresh Weather data by Swipe from top to bottom (show "Refresh" text or icon)
}
