package com.example.trip_for_everyone;

import android.location.Address;
import android.location.Geocoder;
import android.net.http.HttpResponseCache;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigationFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationFragment1 extends Fragment implements MapView.MapViewEventListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    MapView mapView;
    DatabaseReference mDatabase;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NavigationFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationFragment1 newInstance(String param1, String param2) {
        NavigationFragment1 fragment = new NavigationFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment

        mDatabase = FirebaseDatabase.getInstance().getReference();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_navigation1, container, false);
//        init(mDatabase);
        EditText searchEditText = rootView.findViewById(R.id.searchEditText);
        ImageButton imgButton = rootView.findViewById(R.id.NF1_search_button);

        Log.d("asdf","asdf");
        mapView = new MapView(getActivity());
        mapView.setMapViewEventListener(this);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = searchEditText.getText().toString();
                if(!keyword.isEmpty()){
                    Data data = search(keyword);
                    if(data==null){Log.e("null","search 실패");}
                    else {
                        getMarker(data);
                        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(data.lat,data.lon), true);
//                        marker.setMapPoint(mapPoint);
//                        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
//                        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
//
//                        mapView.addPOIItem(marker);
                    }
                }
                else{
                    //아무것도 입력 안하면?
                }
            }
        });

        ViewGroup mapViewContainer = (ViewGroup) rootView.findViewById(R.id.mapView);
        mapViewContainer.addView(mapView);



        return rootView;
    }
    private void getMarker(Data data){

//                        MapPOIItem marker = new MapPOIItem();
//                        marker.setItemName("Default Marker");
//                        marker.setTag(0);
        Log.d("move","success");

        DatabaseReference spotRef = mDatabase.child("basicInfo");
        spotRef.orderByChild("latitude").startAt(data.lat-0.0083).endAt(data.lat+0.0083).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot c : snapshot.getChildren()){
                    String key = c.getKey();
                    Log.d("key",c.toString());
                    BasicInfo tmp = c.getValue(BasicInfo.class);
                    if(tmp.getLongitude()>data.lon-0.016&&tmp.getLongitude()<data.lon+0.016){
                        Log.d("spotList",c.toString());
                        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(tmp.getLatitude(), tmp.getLongitude());

                        MapPOIItem marker = new MapPOIItem();
                        marker.setItemName(key);
                        marker.setTag(0);

                        marker.setMapPoint(mapPoint);
                        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
                        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양
                        mapView.addPOIItem(marker);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("order","noooooooooo");
            }
        });
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        Log.d("MapView","CenterMoved");
//        mapView.removeAllPOIItems();
//        getMarker(new Data(mapPoint.getMapPointGeoCoord().latitude,mapPoint.getMapPointGeoCoord().longitude));
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        Log.d("MapView","ZoomChanged");
    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        Log.d("MapView","SingleTapped");
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        Log.d("MapView","DragStarted");
    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        Log.d("MapView","DragEnd");
        mapView.removeAllPOIItems();
        getMarker(new Data(mapPoint.getMapPointGeoCoord().latitude,mapPoint.getMapPointGeoCoord().longitude));
    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    //    private void init(DatabaseReference mDatabase){
//        mDatabase.child("basicInfo").orderByChild("basicInfo").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot data : snapshot.getChildren()){
//                    String key = data.child("주소").toString();
//                    Data tmp = search(key);
//                    if(tmp==null){ Log.e("gyu","searchFail");}
//                    else {
//                        data.getRef().child("latitude").setValue(tmp.lat);
//                        data.getRef().child("longitude").setValue(tmp.lon);
//                    }
//                    Log.d("key",key+"end");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                    Log.d("data",error.toString());
//            }
//        });
//    }
    private static class Data{ //위도경도 저장하는 용도
        double lat;
        double lon;
        public Data(double lat, double lon){
            this.lat = lat; this.lon = lon;
        }
    }
    private Data search(String keyword){
        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> list = null;
        try {
            list = geocoder.getFromLocationName( keyword, 5);
        } catch(IOException e){
            e.printStackTrace();

        }
        if(list==null || list.size()==0) {
            Log.e("fail","list is null");
            return null;
        }
        else {
            Log.d("success", list.get(0).toString());

            Log.e("success",list.get(0).getLongitude()+" ");
            Log.e("success",list.get(0).getLatitude()+" ");
            return new Data(list.get(0).getLatitude(), list.get(0).getLongitude());
        }
    }
}