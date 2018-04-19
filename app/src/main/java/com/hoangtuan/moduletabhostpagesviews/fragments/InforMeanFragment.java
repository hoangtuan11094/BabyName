package com.hoangtuan.moduletabhostpagesviews.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hoangtuan.moduletabhostpagesviews.R;
import com.hoangtuan.moduletabhostpagesviews.model.InforMeanModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class InforMeanFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private InforMeanModel inforMeanModel;
    TextView txtInforMeanName, txtInforMeanOrigin, txtInforMeanMeaning;

    public InforMeanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_infor_mean, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        txtInforMeanName = (TextView) view.findViewById(R.id.txtInforMeanName);
        txtInforMeanOrigin = (TextView) view.findViewById(R.id.txtInforMeanOrigin);
        txtInforMeanMeaning = (TextView) view.findViewById(R.id.txtInforMeanMeaning);


        Bundle bundle =getArguments();
        inforMeanModel = (InforMeanModel) bundle.getSerializable("Mean");

        txtInforMeanMeaning.setText(inforMeanModel.getMean());
        txtInforMeanName.setText(bundle.getString("NameMean"));
        txtInforMeanOrigin.setText(inforMeanModel.getOrigin());
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(inforMeanModel.getLati(), inforMeanModel.getLongti());
        mMap.addMarker(new MarkerOptions().position(sydney).title(inforMeanModel.getOrigin()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
