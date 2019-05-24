package edu.txstate.sdd65.rentalcarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class UpdateActivity extends AppCompatActivity {

    //Private variables
    int intId;
    String strCarName;
    int intPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //Retrieve variables for text
        SharedPreferences sharedPref3 = PreferenceManager.getDefaultSharedPreferences(this);
        intId = sharedPref3.getInt("id", 0);
        strCarName = sharedPref3.getString("name", "");
        intPosition = sharedPref3.getInt("pos", 0);

        //Final textview and set to variables from sharedPref3
        final TextView txtCarId = findViewById(R.id.txtID);
        final TextView txtCarName = findViewById(R.id.txtName);
        txtCarId.setText("Car Id: " + intId);
        txtCarName.setText("Car Name: " + strCarName);

        //Button to return home
        Button homeButton = findViewById(R.id.btnHome);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateActivity.this, MenuActivity.class));
            }
        });


        final EditText txtNewCost = findViewById(R.id.txtRentalCost);

        //Button to update
        Button updateButton = findViewById((R.id.btnUpdateCost));
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "RentalCars/" +intPosition+ "/Cost.json"; //Json object + Position + JSON object field.json
                StringEntity entity = null;
                try {
                    String inputCost = txtNewCost.getText().toString();
                    if(inputCost.equals("")){
                        Toast.makeText(UpdateActivity.this, "Please input a valid rental cost to update.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    else{
                        double newCost = Double.parseDouble(inputCost);
                        entity = new StringEntity("" + newCost);
                    }
                }  catch(Exception ex) {
                    ex.printStackTrace();
                }
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/text"));
                RestAPIClient.put(UpdateActivity.this, url, entity,
                        "application/text", new TextHttpResponseHandler(){
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                Toast.makeText(UpdateActivity.this, "Cost Update: Success", Toast.LENGTH_LONG).show();

                            }
                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Toast.makeText(UpdateActivity.this, "Cost Update: Failed", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }

}
