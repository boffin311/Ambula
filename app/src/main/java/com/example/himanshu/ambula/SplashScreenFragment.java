package com.example.himanshu.ambula;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.print.PrintAttributes;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;




public class SplashScreenFragment extends Fragment {
    ProgressBar onProgressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
       onProgressBar=view.findViewById(R.id.onProgessBar);
        GetLocationStatus getLocationStatus=new GetLocationStatus(getContext(),getActivity(),getActivity().getSupportFragmentManager(),onProgressBar);
        getLocationStatus.getLocationStatus();
//        final LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        boolean isGpsProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//        boolean isNetworkProviderEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//        Log.d("OCV", "onCreateView: " + locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER));
//        Log.d("OCV", "onCreateView: " + locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
//
//        if (!isGpsProviderEnabled && !isNetworkProviderEnabled) {
//
//        }

//        LocationRequest locationRequest=LocationRequest.create().setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//        LocationSettingsRequest locationSettingsRequest=new LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest)
//                .build();
//
//
//        final Task<LocationSettingsResponse>locationSettingsResponseTask=LocationServices.getSettingsClient(getContext()).checkLocationSettings(locationSettingsRequest);
//
//        locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
//            @Override
//            public void onComplete(Task<LocationSettingsResponse> task) {
//                try {
//                    LocationSettingsResponse response=task.getResult(ApiException.class);
//                    Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                MainScreenFragment mainScreenFragment=new MainScreenFragment();
//        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.containerFrame,mainScreenFragment)
//        .commit();
//            }
//        },5000);
//
//                } catch (ApiException e) {
//
//                   switch (e.getStatusCode())
//                   {
//                       case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                           ResolvableApiException apiException= (ResolvableApiException) e;
//                           try {
//                               apiException.startResolutionForResult(getActivity(),987);
//                           } catch (IntentSender.SendIntentException e1) {
//                               e1.printStackTrace();
//                           }
//                           break;
//                       case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                       {
//
//                       }
//                   }
//                }
//            }
//        });
//
      return view;
    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    if (requestCode==LOCATION_STATUS_REQUEST_CODE);
//       if (resultCode==RESULT_OK)
//       {
//           MainScreenFragment mainScreenFragment=new MainScreenFragment();
//           FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
//           fragmentManager.beginTransaction().replace(R.id.containerFrame,mainScreenFragment)
//                   .commit();
//
//       }
//       else
//       {
//           GpsTurnedOffFragment gpsTurnedOffFragment=new GpsTurnedOffFragment();
//           FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
//           fragmentManager.beginTransaction().replace(R.id.containerFrame,gpsTurnedOffFragment)
//                   .commit();
//       }
//    }

}
