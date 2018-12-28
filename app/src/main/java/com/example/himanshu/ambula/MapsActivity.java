package com.example.himanshu.ambula;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;


import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,View.OnTouchListener ,View.OnDragListener {

    private GoogleMap mMap;
    FusedLocationProviderClient fusedLocationProviderClient;
    ImageButton imageCurrentLocation;
    GoogleApiClient googleApiClient;
    androidx.appcompat.widget.Toolbar toolbar;
    LocationRequest locationRequest;
    View view;
    MapFragment mapFragment;
    public static final String TAG = "MYCAM";
    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private int REQUEST_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        imageCurrentLocation = findViewById(R.id.imageCurrentLocation);
        ActivityCompat.requestPermissions(MapsActivity.this, permissions, REQUEST_CODE);


      view=findViewById(R.id.map);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);

        mapFragment = MapFragment.newInstance();
        getFragmentManager().beginTransaction().replace(R.id.map,mapFragment)
                .commit();
       mapFragment.getMapAsync(this);
//       MapView   mapView=findViewById(R.id.mapView);
//       mapView.onCreate(savedInstanceState);
//        mapView.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                mMap.getCameraPosition();
//                return false;
//            }
//        });
//        mapView.getMapAsync(this);
        toolbar=findViewById(R.id.toolbar);

DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        /**when currentLocation button is clicked*/
        imageCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGoogleServiceLocation();
            }
        });


         view.setOnTouchListener(this);
         view.setOnDragListener(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//   mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
//   mMap.get

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(12.9716, 77.5946);
      //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));

       mMap.getUiSettings().setAllGesturesEnabled(true);
        Log.d("OMR", "onMapReady: " +mMap.getCameraPosition());
    }


//    public void getCurrentLocation() {
//        Log.d(TAG, "getCurrentLocation: ");
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(MapsActivity.this, permissions, REQUEST_CODE);
//            Log.d(TAG, "getCurrentLocation: ");
//        } else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, new LocationListener() {
//                @Override
//                public void onLocationChanged(Location location) {
//                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                    Log.d(TAG, "onLocationChanged: " + location.getLongitude());
//                    mMap.addMarker(new MarkerOptions().position(latLng).title(""));
//                    mMap.addCircle(new CircleOptions().center(latLng).fillColor(Color.rgb(0, 255, 0)).radius(25).visible(true));
//
//                }
//
//                @Override
//                public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                }
//
//                @Override
//                public void onProviderEnabled(String provider) {
//
//                }
//
//                @Override
//                public void onProviderDisabled(String provider) {
//
//                }
//            });
//        }
//    }

/**  when ever you want to get location call this funtion  at that place */

    public void getGoogleServiceLocation() {
        Log.d(TAG, "getGoogleServiceLocation: ");

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(MapsActivity.this);
                        fusedLocationProviderClient.getLastLocation()
                                .addOnSuccessListener(new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {
                                        if (location != null) {
                                            Log.d(TAG, "onSuccess: " + location.getLongitude());
                                            Log.d(TAG, "onSuccess: " + location.getLatitude());
                                            LatLng currentLatLng=new LatLng(location.getLatitude(),location.getLongitude());
                                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,16));
                                            Log.d(TAG, "onSuccess: "+mMap.getCameraPosition());
                                        }
                                    }
                                });


                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                }).addApi(LocationServices.API)
                .build();
        googleApiClient.connect();

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(this, "Yes you are touched", Toast.LENGTH_SHORT).show();
        Log.d("IMT", "onTouch: "+"Hey I am touched and here is my camera position     "+mMap.getCameraPosition());
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        Toast.makeText(this, "Yes you are dragged ,I know", Toast.LENGTH_SHORT).show();
        Log.d("IMT", "onDrag: ");
        return false;
    }
}
