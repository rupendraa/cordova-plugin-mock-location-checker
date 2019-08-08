package com.diegorribeiro;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class MockLocationChecker extends CordovaPlugin{

    private int MY_PERMISSIONS_REQUEST = 0;

    // flag for GPS status
    boolean isGPSEnabled = false;

    // flag for network status
    boolean isNetworkEnabled = false;
    private String LOCATION_PROVIDER = "";
    LocationListener locationListener;
    private boolean listenerON = false;
    private String statusMock = "";
    private JSONArray arrayGPS = new JSONArray();
    private JSONObject objGPS = new JSONObject();

    @Override
    public boolean execute(String action, JSONArray data, final CallbackContext callbackContext) throws JSONException {

        if (action.equals("check")) {
            if (android.os.Build.VERSION.SDK_INT < 18) {
                if (Secure.getString(this.cordova.getActivity().getContentResolver(), Secure.ALLOW_MOCK_LOCATION).equals("0")){
                    objGPS.put("info","mock-false");
                  statusMock = "mock-false";
                }else{
                    objGPS.put("info","mock-true");
                  statusMock = "mock-true";
                }

            } else {

                if (location.isFromMockProvider() == true) {
                    objGPS.put("info","mock-true");
                    statusMock = "mock-true";
                } else {
                    objGPS.put("info","mock-false");
                    statusMock = "mock-false";
                }

            }
          return true;
        }else {
          return false;
        }

    }

    private String formatDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String format = formatter.format(date);

        return format;
    }

}
