package com.PEDG1.unmapa.ui.oficina;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.PEDG1.unmapa.R;
import com.PEDG1.unmapa.data.Edificio;
import com.PEDG1.unmapa.data.HandleFile;
import com.PEDG1.unmapa.data.Oficina;
import com.PEDG1.unmapa.structures.ListDinamic;
import com.PEDG1.unmapa.structures.Node;
import com.PEDG1.unmapa.structures.Stack;
import com.PEDG1.unmapa.ui.Adaptador;
import com.PEDG1.unmapa.ui.edificio.EdificioFragment;

import java.text.Normalizer;

public class OficinaFragment extends Fragment {

    View vista;
    ListView listaOficinas;
    BaseAdapter adapter;
    Oficina[] datas;
    Button botonBusqueda;
    EditText entradaTexto;
    OficinaFragment of = this;
    ListDinamic listaOfic;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_oficina, container, false);
        listaOficinas = vista.findViewById(R.id.listOficinas);
        entradaTexto = vista.findViewById(R.id.texteditablebusquedaOficina);
        botonBusqueda = vista.findViewById(R.id.buttonOficina);

        HandleFile file =  new HandleFile("/data/data/com.PEDG1.unmapa/files/10k_Oficina.csv");
        Stack<Edificio> rutilla =  file.LeeArchivo("oficina");

        //Toast toast = Toast.makeText(this.getActivity(), "Esto es: "+rutilla+" cambió", Toast.LENGTH_SHORT);
        //toast.show();

        datas = new Oficina[rutilla.tamano];
        Node<Oficina> pivot = rutilla.ObtenerCabeza();
        for (int i = 0; i < rutilla.tamano; i++) {
            datas[i] = pivot.informacion;
            pivot = pivot.siguiente;
        }

        entradaTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new Adaptador(of.getContext(), datas);
                listaOficinas.setAdapter(adapter);
            }
        });

        botonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listaOfic = new ListDinamic();
                if(entradaTexto.getText().toString().equals(" ")
                        || entradaTexto.getText().toString().equals("")
                        || entradaTexto.getText() == null
                        || entradaTexto.getText().toString().contains("  ")){
                    Toast toast = Toast.makeText(of.getActivity(), "Ingrese algún texto", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    String encontrado = "";

                    for (int i = 0; i < datas.length; i++) {
                        String anormalizar = Normalizer.normalize(datas[i].getNombre(),  Normalizer.Form.NFD);
                        String normalizado = anormalizar.replaceAll("[^\\p{ASCII}]", "");

                        normalizado = normalizado.toLowerCase();
                        //Toast toast = Toast.makeText(ed.getActivity(), normalizado, Toast.LENGTH_SHORT);
                        //toast.show();
                        String anormalizar2 = Normalizer.normalize(entradaTexto.getText().toString(),  Normalizer.Form.NFD);
                        String normalizado2 = anormalizar2.replaceAll("[^\\p{ASCII}]", "");

                        if(normalizado.contains(normalizado2.toLowerCase())){
                            listaOfic.add(datas[i].toString());
                            //encontrado = encontrado + datas[i].getNombre()+"\n";
                        }
                    }

                    HandleFile file = new HandleFile("/data/data/com.PEDG1.unmapa/files/historialBusqueda.txt");
                    ListDinamic Historial = file.LeeArchivoDinamic();
                    Historial.add(entradaTexto.getText().toString());
                    file.EscribeArchivo(Historial);

                    Oficina[] resultado = new Oficina[listaOfic.length()];
                    String[] split;
                    for (int i = 0; i < listaOfic.length(); i++) {
                        split = listaOfic.get(i).split(";");
                        resultado[i] = new Oficina(Integer.parseInt(split[0]),Integer.parseInt(split[1]),split[2]);
                    }

                    adapter = new Adaptador(of.getContext(), resultado);
                    listaOficinas.setAdapter(adapter);

                    //Toast toast = Toast.makeText(of.getActivity(), encontrado, Toast.LENGTH_SHORT);
                    //toast.show();

                }
            }
        });


        adapter = new Adaptador(this.getContext(), datas);
        listaOficinas.setAdapter(adapter);



        return vista;
    }
}