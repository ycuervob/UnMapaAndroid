package com.PEDG1.unmapa.ui.edificio;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.text.Collator;
import java.text.Normalizer;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.PEDG1.unmapa.R;
import com.PEDG1.unmapa.data.Comment;
import com.PEDG1.unmapa.data.Edificio;
import com.PEDG1.unmapa.structures.ListDinamic;
import com.PEDG1.unmapa.structures.Node;
import com.PEDG1.unmapa.structures.Stack;
import com.PEDG1.unmapa.ui.Adaptador;
import com.PEDG1.unmapa.data.HandleFile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class EdificioFragment extends Fragment {

    View vista;
    ListView listaEdificios;
    Button botonBusqueda;
    BaseAdapter adapter;
    Edificio[] datas;
    EditText entradaTexto;
    EdificioFragment ed = this;
    ListDinamic listaEdif;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_edificio, container, false);
        listaEdificios = vista.findViewById(R.id.listEdificios);
        entradaTexto = vista.findViewById(R.id.texteditablebusquedaEdificio);
        botonBusqueda = vista.findViewById(R.id.BusquedaEdificio);

        HandleFile file =  new HandleFile("/data/data/com.PEDG1.unmapa/files/10k_Edificio.csv");
        Stack<Edificio> rutilla =  file.LeeArchivo("edificio");

        entradaTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new Adaptador(ed.getContext(), datas);
                listaEdificios.setAdapter(adapter);
            }
        });

        //Toast toast = Toast.makeText(this.getActivity(), "Esto es: "+rutilla+" cambió", Toast.LENGTH_SHORT);
        //toast.show();

        datas = new Edificio[rutilla.tamano];
        Node<Edificio> pivot = rutilla.ObtenerCabeza();
        for (int i = 0; i < rutilla.tamano; i++) {
            datas[i] = pivot.informacion;
            pivot = pivot.siguiente;
        }


        botonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaEdif = new ListDinamic();
                if(entradaTexto.getText().toString().equals(" ")
                        || entradaTexto.getText().toString().equals("")
                        || entradaTexto.getText() == null
                        || entradaTexto.getText().toString().contains("  ")){
                    Toast toast = Toast.makeText(ed.getActivity(), "Ingrese algún texto", Toast.LENGTH_SHORT);
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
                            listaEdif.add(datas[i].toString());
                            //encontrado = encontrado + datas[i].getNombre()+"\n";
                        }
                    }

                    Edificio[] resultado = new Edificio[listaEdif.length()];
                    String[] split;
                    for (int i = 0; i < listaEdif.length(); i++) {
                        split = listaEdif.get(i).split(";");
                        resultado[i] = new Edificio(Integer.parseInt(split[0]),split[1],split[2],Double.parseDouble(split[3]),Double.parseDouble(split[4]));
                    }

                    adapter = new Adaptador(ed.getContext(), resultado);
                    listaEdificios.setAdapter(adapter);


                    HandleFile file = new HandleFile("/data/data/com.PEDG1.unmapa/files/historialBusqueda.txt");
                    ListDinamic Historial = file.LeeArchivoDinamic();
                    Historial.add(entradaTexto.getText().toString());
                    file.EscribeArchivo(Historial);


                    //Toast toast = Toast.makeText(ed.getActivity(), encontrado, Toast.LENGTH_SHORT);
                    //toast.show();

                }
            }
        });

        adapter = new Adaptador(this.getContext(), datas);
        listaEdificios.setAdapter(adapter);
        
        return vista;
    }
}