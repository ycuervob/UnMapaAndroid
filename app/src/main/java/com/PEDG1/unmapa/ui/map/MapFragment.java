package com.PEDG1.unmapa.ui.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.PEDG1.unmapa.R;
import com.PEDG1.unmapa.data.Edificio;
import com.PEDG1.unmapa.data.HandleFile;
import com.PEDG1.unmapa.structures.Node;
import com.PEDG1.unmapa.structures.Stack;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import android.graphics.BitmapFactory;


public class MapFragment extends Fragment {

    private static final String SOURCE_ID = "SOURCE_ID";
    private static final String ICON_ID = "ICON_ID";
    private static final String LAYER_ID = "LAYER_ID";

    private MapView mapView;
    public MapboxMap mapboxMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Mapbox.getInstance(this.getContext(), "pk.eyJ1IjoieWN1ZXJ2b2IiLCJhIjoiY2tiOGswNmd0MDNpOTJzcGZ2MWJ4djJxNyJ9.4bdMz4iOyVYsIZAjkqWZKg");
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);
        mapView = (MapView) view.findViewById(R.id.mapView);



        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                MapFragment.this.establecerMapa(mapboxMap);
                mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41").withImage(ICON_ID, BitmapFactory.decodeResource(
                                MapFragment.this.getResources(), R.drawable.mapbox_marker_icon_default)));

                HandleFile<Edificio> archivo = new HandleFile<>("/data/data/com.PEDG1.unmapa/files/10k_Edificio.csv");
                Stack listaEdificios = archivo.LeeArchivo("edificio");
                Node<Edificio> pivote = listaEdificios.ObtenerCabeza();

                for (int i = 0; i < listaEdificios.tamano ; i++) {
                    Edificio edificioPivote = pivote.informacion;
                    mapboxMap.addMarker(new MarkerOptions()
                            .position(new LatLng(edificioPivote.getUbicaionX(),edificioPivote.getUbicaionY()))
                            .title(edificioPivote.getNombre()).snippet(edificioPivote.getApodo()));
                    pivote = pivote.siguiente;
                }

            }
        });

        return view;
    }

    public void prueba1(View v) {
        Toast toast = Toast.makeText(this.getActivity(), "Iniciando Mapa", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void establecerMapa(MapboxMap mapa) {
        this.mapboxMap = mapa;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        prueba1((View) mapView);
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mapView != null){
            mapView.onSaveInstanceState(outState);
        }

    }



}