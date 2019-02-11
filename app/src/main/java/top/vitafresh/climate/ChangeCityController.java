package top.vitafresh.climate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ChangeCityController extends AppCompatActivity {
    private ImageButton backButton;
    private TextView txtGetWeather;
    private EditText txtCityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_city_layout);

        backButton = (ImageButton)findViewById(R.id.backButton);
        txtGetWeather = (TextView)findViewById(R.id.txtGetWeather);
        txtCityName = (EditText)findViewById(R.id.txtCityName);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();   //End Activity (close current view)
            }
        });

        txtCityName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String city = txtCityName.getText().toString();
                Intent cityIntent = new Intent(ChangeCityController.this,WeatherController.class);
                cityIntent.putExtra("city",city);
                startActivity(cityIntent);
                return false;
            }
        });


    }

}
