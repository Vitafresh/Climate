package top.vitafresh.climate;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataModel {

    //TODO: Declare member variables
    private String mCity;
    private String mTemperature;
    private String mWeatherCondition;
    private String mIconName;


    public static WeatherDataModel getFromJson(JSONObject json){
        WeatherDataModel weatherObject = new WeatherDataModel();

        try {
            weatherObject.mCity = json.getString("name");
            weatherObject.mTemperature = Long.toString(Math.round(json.getJSONObject("main").getDouble("temp")));

            weatherObject.mWeatherCondition = json.getJSONArray("weather").getJSONObject(0).getString("main").toString();
            int conditionID = json.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherObject.mIconName=getWeatherIconName(conditionID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Just for testing
//        weatherObject.mCity="Kharkiv";
//        weatherObject.mTemperature ="33";
//        weatherObject.mWeatherCondition="Snow";
//        weatherObject.mIconName=getWeatherIconName(501);

        return weatherObject;
    }

    private static String getWeatherIconName(int condition){
        if (condition >= 0 && condition < 300) {
            return "tstorm1";
        } else if (condition >= 300 && condition < 500) {
            return "light_rain";
        } else if (condition >= 500 && condition < 600) {
            return "shower3";
        } else if (condition >= 600 && condition <= 700) {
            return "snow4";
        } else if (condition >= 701 && condition <= 771) {
            return "fog";
        } else if (condition >= 772 && condition < 800) {
            return "tstorm3";
        } else if (condition == 800) {
            return "sunny";
        } else if (condition >= 801 && condition <= 804) {
            return "cloudy2";
        } else if (condition >= 900 && condition <= 902) {
            return "tstorm3";
        } else if (condition == 903) {
            return "snow5";
        } else if (condition == 904) {
            return "sunny";
        } else if (condition >= 905 && condition <= 1000) {
            return "tstorm3";
        }

        return "dunno";
    }

    public String getCity() {
        return mCity;
    }

    public String getTemperature() {
        return mTemperature;
    }

    public String getWeatherCondition() {
        return mWeatherCondition;
    }

    public String getIconName() {
        return mIconName;
    }
}
