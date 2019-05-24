package edu.txstate.sdd65.rentalcarapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //Start button to start List Activity
        Button vehicleList = findViewById(R.id.btnVehicleList);
        vehicleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, CarListActivity.class));
            }
        });

        //Start button to start Add Vehicle Activity
        Button newVehicle = findViewById(R.id.btnAddVehicle);
        newVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, AddVehicleActivity.class));
            }
        });
    }
}
