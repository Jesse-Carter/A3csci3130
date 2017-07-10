package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Map;

public class DetailViewActivity extends Activity {

    private EditText unameField, uprimaryBizField, uaddressField,ubidField, ulocationField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        appState = ((MyApplicationData)getApplication());

        ubidField = (EditText) findViewById(R.id.ubusinessId);
        unameField = (EditText) findViewById(R.id.uName);
        ulocationField = (EditText) findViewById(R.id.ulocation);
        uaddressField = (EditText) findViewById(R.id.uaddress);
        uprimaryBizField= (EditText) findViewById (R.id.uprimaryBiz);

        if(receivedPersonInfo != null){
            ubidField.setText(receivedPersonInfo.bid);
            unameField.setText(receivedPersonInfo.name);
            ulocationField.setText(receivedPersonInfo.location);
            uaddressField.setText(receivedPersonInfo.address);
            uprimaryBizField.setText(receivedPersonInfo.primaryBiz);
        }
    }

    /**
     * Pulls the current entry from the database and updates it
     *
     */
    public void updateContact(View v){

        String uid = appState.firebaseReference.push().getKey();
        String businessID = ubidField.getText().toString();
        String name = unameField.getText().toString();
        String location = ulocationField.getText().toString();
        String primaryBiz = uprimaryBizField.getText().toString();
        String address = uaddressField.getText().toString();

        Map<String, Object> businessUpdate = receivedPersonInfo.toMap();
        businessUpdate.put("uid", uid);
        businessUpdate.put("bid", businessID);
        businessUpdate.put("name", name);
        businessUpdate.put("primaryBiz", primaryBiz);
        businessUpdate.put("address", address);
        businessUpdate.put("location", location);

        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(businessUpdate);
        finish();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
