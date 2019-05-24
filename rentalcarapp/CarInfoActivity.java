package edu.txstate.sdd65.rentalcarapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CarInfoActivity extends AppCompatActivity {
    //Private variables
    int intId;
    String strCarName;
    String strCarBrand;
    String strCarColor;
    double dblRentalPricePerDay;
    int intPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        //Retrieve variables from shared preferences.
        SharedPreferences sharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);
        intId = sharedPref2.getInt("id", 0);
        strCarName = sharedPref2.getString("name", "");
        strCarBrand = sharedPref2.getString("brand", "");
        strCarColor = sharedPref2.getString("color","");
        dblRentalPricePerDay = sharedPref2.getFloat("cost", 0);
        intPosition = sharedPref2.getInt("pos", 0);

        //Final statements for fields in the UI
        final TextView txtCarId = findViewById(R.id.txtId);
        final TextView txtCarName = findViewById(R.id.txtName);
        final TextView txtCarBrand = findViewById(R.id.txtBrand);
        final TextView txtCarColor = findViewById(R.id.txtColor);
        final TextView txtRentalCost = findViewById(R.id.txtCost);
        DecimalFormat currency = new DecimalFormat("$###,###.00");
        Button calcCost = findViewById(R.id.btnCalcTotalCost);  //Calc button
        final EditText numberOfDaysRented = findViewById(R.id.txtRentalDays);   //Input field

        //Set the text in the UI equal to the retrieved variables
        txtCarId.setText("Car Id: " + intId);
        txtCarName.setText("Car Name: " + strCarName);
        txtCarBrand.setText("Car Brand: " + strCarBrand);
        txtCarColor.setText("Car Color: " + strCarColor);
        txtRentalCost.setText("Rental Cost Per Day: " + currency.format(dblRentalPricePerDay)); //Formats to currency

        //On calc button click
        calcCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int intDaysRented = Integer.parseInt(numberOfDaysRented.getText().toString());
                    //If over daysRented >30 then message.
                    if(intDaysRented > 30){
                        Toast.makeText(CarInfoActivity.this, "Please call 512-777-2222.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        double dblTotalCost = dblRentalPricePerDay * intDaysRented;
                        DecimalFormat currency = new DecimalFormat("$###,###.##");
                        Toast.makeText(CarInfoActivity.this, "Total Cost:" +
                                currency.format(dblTotalCost) + ".", Toast.LENGTH_LONG).show();
                    }
                }catch(Exception ex){
                    Toast.makeText(CarInfoActivity.this, "Please input a valid number of days for rental.", Toast.LENGTH_LONG).show();
                }


            }
        });

        //Start updateActivity
        Button updateRental = findViewById(R.id.btnUpdate);
        updateRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarInfoActivity.this, UpdateActivity.class));
            }
        });


    }
}
