package edu.txstate.sdd65.rentalcarapp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class CarListActivity extends ListActivity { //Change to ListActivity
    //List named rentalCars
    List<RentalCar> rentalCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DeleteLine
        getRentalCars();    //Method created below
    }
    //Method to retrieve RentalCars via RestAPI
    void getRentalCars(){
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Accept", "application/json"));     //application is retrieving JSON object
        RestAPIClient.get(CarListActivity.this, "RentalCars.json", headers.toArray(new Header[headers.size()]), //RentalCars is a JSON object
                null, new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        rentalCars = new ArrayList<RentalCar>();
                        for (int i=0; i <response.length(); i++){
                            try {
                                rentalCars.add(new RentalCar(response.getJSONObject(i)));
                            } catch (Exception ex ) {ex.printStackTrace();}
                        }
                        setListAdapter(new ArrayAdapter<RentalCar>(CarListActivity.this, R.layout.activity_car_list,
                                R.id.txtVehicle , rentalCars));
                    }
                });


    }

    //Clicking ListItem retrieve and sets values to SharedPref and starts the new activity
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //get selected vehicle from list
        RentalCar selectedCar = rentalCars.get(position);

        //We need to put all variables from the object we will need, including position.
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(CarListActivity.this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("id", selectedCar.getId());
        editor.putString("name", selectedCar.getName());
        editor.putString("brand", selectedCar.getBrand());
        editor.putString("color", selectedCar.getColor());
        editor.putFloat("cost", (float) selectedCar.getCost());
        editor.putInt("pos", position);
        editor.commit();

        startActivity(new Intent(CarListActivity.this, CarInfoActivity.class));
    }


}
