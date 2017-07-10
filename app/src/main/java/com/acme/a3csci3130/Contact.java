package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String bid;
    public  String name;
    public  String primaryBiz;
    public  String address;
    public  String location;
    public  String uid;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     *
     * @param uid (Firebase Reference ID)
     * @param bid (Business ID)
     * @param name
     * @param primaryBiz
     * @param address
     * @param location
     */
    public Contact(String uid, String bid, String name, String primaryBiz, String address, String location){
        this.uid = uid;
        this.bid = bid;
        this.name = name;
        this.primaryBiz = primaryBiz.toUpperCase();
        this.address = address;
        this.location = location.toUpperCase();
    }


    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("bid", bid);
        result.put("name", name);
        result.put("primaryBiz", primaryBiz);
        result.put("address", address);
        result.put("location", location);

        return result;
    }
}
