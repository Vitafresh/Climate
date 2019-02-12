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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private TextView txtWeatherCondition;


    //TODO: Declare LocationManager and LocationListener


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_controller_layout);

        txtTemp = (TextView)findViewById(R.id.txtTemp);
        txtTemp.setText("111°");
        textCity=findViewById(R.id.textCity);
        txtLocation = findViewById(R.id.txtLocation);
        txtWeatherCondition = findViewById(R.id.txtWeatherCondition);
        imgWeatherSymbol = findViewById(R.id.imgWeatherSymbol);

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

        JSONObject jsonWeather = getJsonWeatherByApi(params);
        WeatherDataModel weatherData = WeatherDataModel.getFromJson(jsonWeather);
        updateUI(weatherData);

    }

    //TODO: Add Weather for the city
    private void getWeatherForCity(String city){
        RequestParams params=new RequestParams();
        params.add("appid",APP_ID);
        params.add("q",city);
        params.add("units",MEASURE_UNITS);

        JSONObject jsonWeather = getJsonWeatherByApi(params);
        WeatherDataModel weatherData = WeatherDataModel.getFromJson(jsonWeather);
        updateUI(weatherData);
    }

    //TODO: Do API call to website (using prepared RequestParams)
    private JSONObject getJsonWeatherByApi(RequestParams params){
        Log.d("Climate","params: " + params.toString());
        JSONObject jsonObject = new JSONObject();

        // Stub (заглушка) to get test JSON Object
        jsonObject = getTestJsonData();
        return jsonObject;
    }

    private JSONObject getTestJsonData(){
        // Stub (заглушка) to get test JSON Object
        /*
        Real answers for Kherson from http://api.openweathermap.org/data/2.5/weather
        Rain:
        {"coord":{"lon":32.61,"lat":46.64},"weather":[{"id":520,"main":"Rain","description":"light intensity shower rain","icon":"09d"}],"base":"stations",
        "main":{"temp":7,"pressure":1007,"humidity":81,"temp_min":7,"temp_max":7},"visibility":10000,"wind":{"speed":6,"deg":160},"clouds":{"all":75},
        "dt":1549958400,"sys":{"type":1,"id":8913,"message":0.0034,"country":"UA","sunrise":1549947501,"sunset":1549984192},"id":706448,"name":"Kherson","cod":200}
        Clouds:
        {"coord":{"lon":32.61,"lat":46.64},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}],"base":"stations","main":{"temp":7,"pressure":1007,"humidity":75,"temp_min":7,"temp_max":7},"visibility":10000,"wind":{"speed":5,"deg":160},"clouds":{"all":75},"dt":1549956600,"sys":{"type":1,"id":8913,"message":0.0036,"country":"UA","sunrise":1549947501,"sunset":1549984191},"id":706448,"name":"Kherson","cod":200}
        */

        // {"weather":[{"id":520,"main":"Rain"}],"main":{"temp":7},"name":"Kherson"}
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        JSONObject item = new JSONObject();
        try {
            item.put("id",520);
            item.put("main","Rain");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonArray.put(item);    // {"id":520,"main":"Rain"}

        item = new JSONObject();
        try {
            item.put("temp",7);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonObject.put("weather",jsonArray);    // "weather":[{"id":520,"main":"Rain"}]
            jsonObject.put("name","Kherson"); // "name":"Kherson"
            jsonObject.put("main",item);            // "main":{"temp":7}

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("Clima","getTestJsonData: " + jsonObject.toString());
        return jsonObject;
    }

    //TODO: Add UpdateUI (set data for views)
    private void updateUI(WeatherDataModel weatherObject){
        Log.d("Climate",weatherObject.getTemperature().toString() + "°");
        Log.d("Climate",weatherObject.getCity());
        Log.d("Climate",weatherObject.getWeatherCondition());

        txtTemp.setText(weatherObject.getTemperature().toString() + "°");
        txtLocation.setText(weatherObject.getCity().toString());
        txtWeatherCondition.setText(weatherObject.getWeatherCondition());

        int resourceID = getResources().getIdentifier(weatherObject.getIconName(),"drawable",getPackageName());
        Log.d("Climate",Integer.toString(resourceID));
        imgWeatherSymbol.setImageResource(resourceID);

    }

    //TODO: Add onPause()

    //TODO: Refresh Weather data by Swipe from top to bottom (show "Refresh" text or icon)
}
