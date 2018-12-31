package com.example.himanshu.ambula;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.LOCATION_SERVICE;
import static com.example.himanshu.ambula.MapsActivity.LOCATION_STATUS_REQUEST_CODE;

public class GpsTurnedOffFragment extends Fragment {
    Button btnTurnOnGps;
    ProgressBar progessOn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_gps_turned_off,container,false);
        btnTurnOnGps=view.findViewById(R.id.btnTurnOnGps);
        progessOn=view.findViewById(R.id.progressOn);
        btnTurnOnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetLocationStatus getLocationStatus=new GetLocationStatus(getContext(),getActivity(),getActivity().getSupportFragmentManager(),progessOn);
                getLocationStatus.getLocationStatus();
            }
        });
        return view;

    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == LOCATION_STATUS_REQUEST_CODE) ;
//        {
//        if (resultCode == RESULT_OK) {
//            MainScreenFragment mainScreenFragment = new MainScreenFragment();
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.containerFrame, mainScreenFragment)
//                    .commit();
//
//        } else {
//            GpsTurnedOffFragment gpsTurnedOffFragment = new GpsTurnedOffFragment();
//            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.containerFrame, gpsTurnedOffFragment)
//                    .commit();
//        }
//        }
  //  }
}
