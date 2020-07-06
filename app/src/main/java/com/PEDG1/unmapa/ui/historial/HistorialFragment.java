package com.PEDG1.unmapa.ui.historial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.PEDG1.unmapa.R;
import com.PEDG1.unmapa.data.Edificio;
import com.PEDG1.unmapa.data.HandleFile;
import com.PEDG1.unmapa.structures.ListDinamic;
import com.PEDG1.unmapa.structures.Node;
import com.PEDG1.unmapa.structures.Stack;
import com.PEDG1.unmapa.ui.Adaptador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.Normalizer;

public class HistorialFragment extends Fragment {

    View vista;
    ListView listaHistorial;
    BaseAdapter adapter;
    EditText entradaTexto;
    String[] Historial;
    Button busquedaHisto;
    HistorialFragment his = this;
    FloatingActionButton eliminarHistorial;
    HandleFile file;
    ListDinamic listaHisto;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_historial, container, false);
        listaHistorial = vista.findViewById(R.id.listHistorial);
        entradaTexto = vista.findViewById(R.id.texteditablebusquedaHISTORIAL);
        busquedaHisto = vista.findViewById(R.id.BusquedaHISTORIAL);
        eliminarHistorial = vista.findViewById(R.id.floatingActionButton);

        file =  new HandleFile("/data/data/com.PEDG1.unmapa/files/historialBusqueda.txt");
        ListDinamic rutilla =  file.LeeArchivoDinamic();
        Historial = rutilla.toArray();

        eliminarHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListDinamic newHistorial = new ListDinamic();
                file.EscribeArchivo(newHistorial);
                Historial = newHistorial.toArray();
                adapter = new Adaptador(his.getContext(), Historial);
                listaHistorial.setAdapter(adapter);
            }
        });

        entradaTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new Adaptador(his.getContext(), Historial);
                listaHistorial.setAdapter(adapter);
            }
        });

        busquedaHisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entradaTexto.getText().toString().equals(" ")
                        || entradaTexto.getText().toString().equals("")
                        || entradaTexto.getText() == null
                        || entradaTexto.getText().toString().contains("  ")){
                    Toast toast = Toast.makeText(his.getActivity(), "Ingrese algún texto", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    listaHisto = new ListDinamic();
                   //String encontrado = "";

                    for (int i = 0; i < Historial.length; i++) {
                        String anormalizar = Normalizer.normalize(Historial[i],  Normalizer.Form.NFD);
                        String normalizado = anormalizar.replaceAll("[^\\p{ASCII}]", "");

                        normalizado = normalizado.toLowerCase();
                        //Toast toast = Toast.makeText(his.getActivity(), normalizado, Toast.LENGTH_SHORT);
                        //toast.show();

                        String anormalizar2 = Normalizer.normalize(entradaTexto.getText().toString(),  Normalizer.Form.NFD);
                        String normalizado2 = anormalizar2.replaceAll("[^\\p{ASCII}]", "");

                        if(normalizado.contains(normalizado2.toLowerCase())){
                            listaHisto.add(Historial[i]);
                            //encontrado = encontrado + Historial[i]+";";
                        }
                    }

                    adapter = new Adaptador(his.getContext(), listaHisto.toArray());
                    listaHistorial.setAdapter(adapter);

                    //Toast toast = Toast.makeText(his.getActivity(), encontrado, Toast.LENGTH_SHORT);
                    //toast.show();

                }
            }
        });

        adapter = new Adaptador(this.getContext(), Historial);
        listaHistorial.setAdapter(adapter);



        return vista;
    }

}
