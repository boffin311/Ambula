package com.example.himanshu.ambula;

import android.Manifest;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;


import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.util.Log;

import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


public class MapsActivity extends FragmentActivity
//        implements OnMapReadyCallback,GoogleMap.OnCameraMoveListener
//        ,GoogleMap.OnCameraMoveStartedListener,GoogleMap.OnCameraMoveCanceledListener,
//        GoogleMap.OnCameraIdleListener,View.OnClickListener
{
    public static final int REQUEST_PERMISSION =567 ;
    FragmentManager fragmentManager;
    public static int LOCATION_STATUS_REQUEST_CODE = 987;
    //    private GoogleMap mMap;
//    TextView tvLocationName;
//    FusedLocationProviderClient fusedLocationProviderClient;
//    ImageButton imageCurrentLocation;
//    GoogleApiClient googleApiClient;
//    androidx.appcompat.widget.Toolbar toolbar;
//    LocationRequest locationRequest;
//      CircleOptions circleOptions;
//    MapFragment mapFragment;
//    public static final String TAG = "MYCAM";
//    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
//    private int REQUEST_CODE = 12345;
//    private static final int PLACE_SEARCH_REQUEST_CODE=54321;
    FrameLayout containerFrame;
    public static final String TAG = "MSA";
    private static final int PLACE_SEARCH_REQUEST_CODE = 54321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        fragmentManager = getSupportFragmentManager();

        SplashScreenFragment splashScreenFragment = new SplashScreenFragment();

        fragmentManager.beginTransaction().replace(R.id.containerFrame, splashScreenFragment)
                .commit();


//        imageCurrentLocation = findViewById(R.id.imageCurrentLocation);
//        ActivityCompat.requestPermissions(MapsActivity.this, permissions, REQUEST_CODE);
//
//        tvLocationName=findViewById(R.id.tvLocationName);
//
//        getPlaceName(28.6967,77.2058);
//
//
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
////        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
////                .findFragmentById(R.id.map);
//
//        mapFragment = MapFragment.newInstance();
//        getFragmentManager().beginTransaction().replace(R.id.map,mapFragment)
//                .commit();
//       mapFragment.getMapAsync(this);
//
//        toolbar=findViewById(R.id.toolbar);
//
//        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//
//
//        /**when currentLocation button is clicked*/
//        imageCurrentLocation.setOnClickListener(this);
//           tvLocationName.setOnClickListener(this);
//
//
//
//
//
//
//
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
////   mMap.getUiSettings().setZoomControlsEnabled(true);
//        mMap.getUiSettings().setAllGesturesEnabled(true);
////   mMap.get
//
//
////        mMap.setOnCameraMoveStartedListener(this);
////        mMap.setOnCameraMoveCanceledListener(this);
//
//        mMap.setOnCameraIdleListener(this);
//        mMap.setOnCameraMoveStartedListener(this);
//
//
//
//
//
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(12.9716, 77.5946);
//      //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")));
//
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
//
//       mMap.getUiSettings().setAllGesturesEnabled(true);
//        Log.d("OMR", "onMapReady: " +mMap.getCameraPosition());
//    }
////
//
////    public void getCurrentLocation() {
////        Log.d(TAG, "getCurrentLocation: ");
////        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
////        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
////
////            ActivityCompat.requestPermissions(MapsActivity.this, permissions, REQUEST_CODE);
////            Log.d(TAG, "getCurrentLocation: ");
////        } else {
////            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, new LocationListener() {
////                @Override
////                public void onLocationChanged(Location location) {
////                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
////                    Log.d(TAG, "onLocationChanged: " + location.getLongitude());
////                    mMap.addMarker(new MarkerOptions().position(latLng).title(""));
////                    mMap.addCircle(new CircleOptions().center(latLng).fillColor(Color.rgb(0, 255, 0)).radius(25).visible(true));
////
////                }
////
////                @Override
////                public void onStatusChanged(String provider, int status, Bundle extras) {
////
////                }
////
////                @Override
////                public void onProviderEnabled(String provider) {
////
////                }
////
////                @Override
////                public void onProviderDisabled(String provider) {
////
////                }
////            });
////        }
////    }
//
///**  when ever you want to get location call this funtion  at that place */
//
//    public void getGoogleServiceLocation() {
//        Log.d(TAG, "getGoogleServiceLocation: ");
//
//        googleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
//                    @Override
//                    public void onConnected(@Nullable Bundle bundle) {
//                        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                            return;
//                        }
//                        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapsActivity.this);
//                        fusedLocationProviderClient.getLastLocation()
//                                .addOnSuccessListener(new OnSuccessListener<Location>() {
//                                    @Override
//                                    public void onSuccess(Location location) {
//                                        if (location != null) {
//                                            Log.d(TAG, "onSuccess: " + location.getLongitude());
//                                            Log.d(TAG, "onSuccess: " + location.getLatitude());
//                                            LatLng currentLatLng=new LatLng(location.getLatitude(),location.getLongitude());
//                                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,16));
////                                            mMap.addCircle(new CircleOptions().
////                                                    center(currentLatLng).radius(20).
////                                                    fillColor(R.color.colorPrimary).
////                                                    strokeColor(Color.rgb(255,255,255)).
////                                                    strokeWidth(2));
//                                            Log.d(TAG, "onSuccess: "+mMap.getCameraPosition());
//                                        }
//                                    }
//                                });
//
//
//                    }
//
//                    @Override
//                    public void onConnectionSuspended(int i) {
//
//                    }
//                })
//                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//                        Toast.makeText(MapsActivity.this, "Location is not on", Toast.LENGTH_SHORT).show();
//                    }
//                }).addApi(LocationServices.API)
//                .build();
//        googleApiClient.connect();
//
//    }
//
//
//
//
//
//
//
//    @Override
//    public void onCameraMove() {
////        Toast.makeText(this, ""+mMap.getCameraPosition(), Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "onCameraMove: "+mMap.getCameraPosition());
//    }
//
//
//    @Override
//    public void onCameraMoveStarted(int i) {
//        Toast.makeText(this,    "I am started ", Toast.LENGTH_SHORT).show();
////        circleOptions=new CircleOptions();
//
//
////        mMap.addCircle(circleOptions.center(new LatLng(28.6967,77.2058))
////                      .radius(10)
////        .fillColor(R.color.colorPrimary)
////        .visible(true)
////        );
////        mMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
////            @Override
////            public void onCircleClick(Circle circle) {
////                circle.remove();
////            }
////        });
//    }
//
//    @Override
//    public void onCameraMoveCanceled() {
//        Toast.makeText(this, "I am cancelled", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onCameraIdle() {
//
//        Log.d("OCI", "onCameraIdle: "+mMap.getCameraPosition().target);
//
//       tvLocationName.setText(getPlaceName(mMap.getCameraPosition().target.latitude,mMap.getCameraPosition().target.longitude));
//
//    }
//
//
//
//    public String getPlaceName(double latitude,double longitude)
//    {  Address address=null;
//        Geocoder geocoder=new Geocoder(this);
//        try {
//            List<Address>  addresses=geocoder.getFromLocation(latitude,longitude,1);
//            address = addresses.get(0);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return address.getAddressLine(0);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId())
//        {
//            case R.id.imageCurrentLocation:
//                Toast.makeText(this, "Inside onCLick", Toast.LENGTH_SHORT).show();
//
//                getGoogleServiceLocation();break;
//            case R.id.tvLocationName:
//                try {
//                    Intent intent=new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
//                            .build(this);
//
//                   this.startActivityForResult(intent,PLACE_SEARCH_REQUEST_CODE);
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
//
//
//        }
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode==RESULT_OK)
//        {
//
//            if (requestCode==PLACE_SEARCH_REQUEST_CODE)
//            {
//                Place place=PlaceAutocomplete.getPlace(this,data);
//                tvLocationName.setText(place.getAddress());
//
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(),16));
//
//            }
//        }
//        else
//        {
//
//        }
    }
//
// public static void getLocationStatus(){
//     LocationRequest locationRequest=LocationRequest.create().setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
//     LocationSettingsRequest locationSettingsRequest=new LocationSettingsRequest.Builder()
//             .addLocationRequest(locationRequest)
//             .build();
//
//
//      Task<LocationSettingsResponse> locationSettingsResponseTask=LocationServices.getSettingsClient(this).checkLocationSettings(locationSettingsRequest);
//
//     locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
//         @Override
//         public void onComplete(Task<LocationSettingsResponse> task) {
//             try {
//                 LocationSettingsResponse response=task.getResult(ApiException.class);
//                 Handler handler=new Handler();
//                 handler.postDelayed(new Runnable() {
//                     @Override
//                     public void run() {
//                         MainScreenFragment mainScreenFragment=new MainScreenFragment();
//                        fragmentManager.beginTransaction().replace(R.id.containerFrame,mainScreenFragment)
//                        .commit();
//
//                     }
//                 },5000);
//
//             } catch (ApiException e) {
//
//                 switch (e.getStatusCode())
//                 {
//                     case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
//                         ResolvableApiException apiException= (ResolvableApiException) e;
//                         try {
//                             apiException.startResolutionForResult(MapsActivity.this,987);
//                         } catch (IntentSender.SendIntentException e1) {
//                             e1.printStackTrace();
//                         }
//                         break;
//                     case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
//                     {
//
//                     }
//                 }
//             }
//         }
//     });


    //}


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {


            if (requestCode == LOCATION_STATUS_REQUEST_CODE) {


                 LocationRequest locationRequest = LocationRequest.create()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setFastestInterval(2000)
                        .setInterval(1000);

                FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
                if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,new String []{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION);
                    return;
                }
                fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                        new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                super.onLocationResult(locationResult);
                                if (locationResult==null)
                                    Log.d(TAG, "onLocationResult: "+locationResult.getLocations());
                            }
                        },null);
                Intent intent=new Intent(MapsActivity.this,MainActivity.class);
//               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               startActivity(intent);

            } else if (requestCode == PLACE_SEARCH_REQUEST_CODE) {
                Log.d("OAR", "onActivityResult: "+PLACE_SEARCH_REQUEST_CODE);
            }
            else if (requestCode==REQUEST_PERMISSION)
            {

            }
        }
        else { Log.d("OAR", "onActivityResult: "+"Inside GPSTurned on Fragement" );
            GpsTurnedOffFragment gpsTurnedOffFragment = new GpsTurnedOffFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.containerFrame, gpsTurnedOffFragment)
                    .commit();
        }
 }
}
