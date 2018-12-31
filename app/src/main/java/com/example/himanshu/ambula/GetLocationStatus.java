package com.example.himanshu.ambula;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.ExecutionException;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import static android.app.Activity.RESULT_OK;
import static com.example.himanshu.ambula.MainScreenFragment.TAG;
import static com.example.himanshu.ambula.MapsActivity.LOCATION_STATUS_REQUEST_CODE;
import static com.example.himanshu.ambula.MapsActivity.REQUEST_PERMISSION;

public class GetLocationStatus {


    FragmentManager fragmentManager;
    Context context;
    Activity activity;
    ProgressBar progressBar;

    public GetLocationStatus(Context context, Activity activity, FragmentManager fragmentManager, ProgressBar progressBar) {
        this.context = context;
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.progressBar = progressBar;
    }

    public void getLocationStatus() {


        LocationRequest locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest locationSettingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .build();


        Task<LocationSettingsResponse> locationSettingsResponseTask = LocationServices.getSettingsClient(context).checkLocationSettings(locationSettingsRequest);

        locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    progressBar.setVisibility(View.VISIBLE);
                    FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
                    if (ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity,new String []{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION);

                    }
                    Log.d(TAG, "onComplete: " +fusedLocationProviderClient.getLastLocation());
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(activity,MainActivity.class);
                            activity.startActivity(intent);

                        }
                    },3000);

                } catch (ApiException e) {

                    switch (e.getStatusCode())
                    {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            ResolvableApiException apiException= (ResolvableApiException) e;
                            try {
                                apiException.startResolutionForResult(activity,LOCATION_STATUS_REQUEST_CODE);
                            } catch (IntentSender.SendIntentException e1) {
                                e1.printStackTrace();
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        {

                        }
                    }
                }
            }
        });


    }

}
