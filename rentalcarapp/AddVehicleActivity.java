package edu.txstate.sdd65.rentalcarapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class AddVehicleActivity extends AppCompatActivity {
    int intId;
    String strName;
    String strBrand;
    String strColor;
    double dblCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        final EditText id = findViewById(R.id.txtId);
        final EditText name = findViewById(R.id.txtCarName);
        final EditText brand = findViewById(R.id.txtBrand);
        final EditText color = findViewById(R.id.txtColor);
        final EditText cost = findViewById(R.id.txtCost);

        Button newVehicle = findViewById(R.id.btnAdd);

        newVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intId = Integer.parseInt(id.getText().toString());
                strName = name.getText().toString();
                strBrand = brand.getText().toString();
                strColor = color.getText().toString();
                dblCost = Double.parseDouble(cost.getText().toString());


                String url = "RentalCars.json";
                JSONObject att = new JSONObject();
                try {
                    att.put("Id", intId);
                    att.put("Name", strName);
                    att.put("Brand", strBrand);
                    att.put("Color",strColor);
                    att.put("Cost", dblCost);
                } catch (Exception ex) {ex.printStackTrace();}

                StringEntity entity = null;
                try {

                    entity = new StringEntity(att.toString());
                }  catch(Exception ex) {
                    ex.printStackTrace();
                }
                entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                RestAPIClient.post(AddVehicleActivity.this, url, entity,
                        "application/json", new TextHttpResponseHandler(){
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                Toast.makeText(AddVehicleActivity.this, "New Vehicle Added", Toast.LENGTH_LONG).show();

                            }
                            @Override
                            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                Toast.makeText(AddVehicleActivity.this, "Failure adding vehicle.", Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });
    }
}
