package com.example.himanshu.ambula;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;
import static com.example.himanshu.ambula.MapsActivity.REQUEST_PERMISSION;

public class MainScreenFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnCameraMoveListener
        , GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraIdleListener, View.OnClickListener {
    private GoogleMap mMap;
    TextView tvLocationName;
    FusedLocationProviderClient fusedLocationProviderClient;
    ImageButton imageCurrentLocation;
    GoogleApiClient googleApiClient;
    androidx.appcompat.widget.Toolbar toolbar;
    LocationRequest locationRequest;
    String placeName;
    CircleOptions circleOptions;
    MapFragment mapFragment;
    public static final String TAG = "MYCAM";
    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private int REQUEST_CODE = 12345;

    private static final int PLACE_SEARCH_REQUEST_CODE = 54321;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        imageCurrentLocation = view.findViewById(R.id.imageCurrentLocation);
        ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_CODE);

        tvLocationName = view.findViewById(R.id.tvLocationName);




        mapFragment = MapFragment.newInstance();
        getActivity().getFragmentManager().beginTransaction().replace(R.id.map, mapFragment)
                .commit();


        mapFragment.getMapAsync(this);
        toolbar = view.findViewById(R.id.toolbar);

        DrawerLayout drawerLayout = view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        /**when currentLocation button is clicked*/
        imageCurrentLocation.setOnClickListener(this);
        tvLocationName.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.imageCurrentLocation:
                Log.d(TAG, "onClick: " + "I am clicked here");
                  getGoogleServiceLocation();
                break;
            case R.id.tvLocationName:
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(getActivity());

                    this.startActivityForResult(intent, PLACE_SEARCH_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }


        }

    }

    @Override
    public void onCameraIdle() {

        Log.d(TAG, "onCameraIdle: " + getPlaceName(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude));

        tvLocationName.setText(getPlaceName(mMap.getCameraPosition().target.latitude, mMap.getCameraPosition().target.longitude));

    }

    @Override
    public void onCameraMoveCanceled() {

    }

    @Override
    public void onCameraMove() {

    }

    @Override
    public void onCameraMoveStarted(int i) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

      getGoogleServiceLocation();
        mMap.getUiSettings().setAllGesturesEnabled(true);

        mMap.setOnCameraIdleListener(this);

       // getGoogleServiceLocation();

        Log.d("OMR", "onMapReady: " + mMap.getCameraPosition());
    }

    public void getGoogleServiceLocation() {


        googleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        Log.d(TAG, "onConnected: ");

                        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
                        if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(),new String []{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_PERMISSION);

                            return;
                        }
                        fusedLocationProviderClient.getLastLocation()
                                .addOnSuccessListener(new OnSuccessListener<Location>() {
                                    @Override
                                    public void onSuccess(Location location) {


                                        Log.d(TAG, "onSuccess: " + location);
                                        if (location != null) {
                                            Log.d(TAG, "onSuccess: " + location.getLongitude());
                                            Log.d(TAG, "onSuccess: " + location.getLatitude());
//                                                tvLocationName.setText(getPlaceName(location.getLatitude(),location.getLongitude()));
                                            LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16));

                                            Log.d(TAG, "onSuccess: " + mMap.getCameraPosition());
                                        }
                                    }
                                });

                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                        Log.d(TAG, "onConnectionSuspended: "+"The connection is suspended");

                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getContext(), "Location is not on", Toast.LENGTH_SHORT).show();
                    }
                }).addApi(LocationServices.API)
                .build();
        googleApiClient.connect();

    }

    public String getPlaceName(double latitude, double longitude) {
        Address address = null;


        Log.d(TAG, "getPlaceName: "+latitude+"   "+longitude);
        Geocoder geocoder = new Geocoder(getContext());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
           if (addresses.size()!=0)
           {address = addresses.get(0);
            placeName= address.getAddressLine(0);}
            else
               placeName= "";

        } catch (IOException e) {
            e.printStackTrace();
        }
       return placeName;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == PLACE_SEARCH_REQUEST_CODE) {
                Place place = PlaceAutocomplete.getPlace(getContext(), data);
                tvLocationName.setText(place.getAddress());

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 16));

            }
        } else {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getGoogleServiceLocation();
    }
}
