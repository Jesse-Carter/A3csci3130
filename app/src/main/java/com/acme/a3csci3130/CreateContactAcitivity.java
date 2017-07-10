package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText nameField, primaryBizField, addressField, locationField,bidField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());
        submitButton = (Button) findViewById(R.id.submitButton);
        bidField = (EditText) findViewById(R.id.businessID);
        nameField = (EditText) findViewById(R.id.name);
        locationField = (EditText) findViewById(R.id.location);
        addressField = (EditText) findViewById(R.id.address);
        primaryBizField= (EditText) findViewById (R.id.primaryBiz);
    }

    /**
     *
     * Creates a Contact object with the values inputted into the text fields
     * and sends it to the database
     */

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String uid = appState.firebaseReference.push().getKey();
        String businessID = bidField.getText().toString();
        String name = nameField.getText().toString();
        String location = locationField.getText().toString();
        String primaryBiz = primaryBizField.getText().toString();
        String address = addressField.getText().toString();

        Contact business = new Contact(uid, businessID, name, primaryBiz, address, location);

        appState.firebaseReference.child(uid).setValue(business);

        finish();

    }
}
