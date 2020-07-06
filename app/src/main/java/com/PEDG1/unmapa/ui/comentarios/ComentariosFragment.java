package com.PEDG1.unmapa.ui.comentarios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.PEDG1.unmapa.structures.Avltree;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.PEDG1.unmapa.R;
import com.PEDG1.unmapa.data.Comment;
import com.PEDG1.unmapa.data.Edificio;
import com.PEDG1.unmapa.data.HandleFile;
import com.PEDG1.unmapa.data.Oficina;
import com.PEDG1.unmapa.structures.ListDinamic;
import com.PEDG1.unmapa.structures.Node;
import com.PEDG1.unmapa.structures.Stack;
import com.PEDG1.unmapa.structures.TreeNode;
import com.PEDG1.unmapa.ui.Adaptador;

import java.text.Normalizer;

public class ComentariosFragment extends Fragment {

    View vista;
    ListView listaComentarios;
    BaseAdapter adapter;
    Comment[] datas;
    Button botonBusqueda;
    EditText entradaTexto;
    ComentariosFragment com = this;
    Avltree<Comment> rutillaAvl;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_comentarios, container, false);
        listaComentarios = vista.findViewById(R.id.listComentarios);
        botonBusqueda = vista.findViewById(R.id.buttonBusquedaComentario);
        entradaTexto = vista.findViewById(R.id.texteditablebusquedaComentario);

        HandleFile file =  new HandleFile("/data/data/com.PEDG1.unmapa/files/comentarios_10k.csv");
        Stack<Comment> rutilla =  file.LeeArchivo("comentario");

        try{
            rutillaAvl =  file.leeArchivoAVL();
        }catch (Exception e){
            rutillaAvl = new Avltree<>(new TreeNode(new Comment(0,0,"nada","nada")));
        }

        //Toast toast = Toast.makeText(this.getActivity(), "Esto es: "+rutilla+" cambió", Toast.LENGTH_SHORT);
        //toast.show();

        datas = new Comment[rutilla.tamano];
        Node<Comment> pivot = rutilla.ObtenerCabeza();
        for (int i = 0; i < rutilla.tamano; i++) {
            datas[i] = pivot.informacion;
            pivot = pivot.siguiente;
        }

        entradaTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter = new Adaptador(com.getContext(), datas);
                listaComentarios.setAdapter(adapter);
            }
        });

        botonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entradaTexto.getText().toString().equals(" ")
                        || entradaTexto.getText().toString().equals("")
                        || entradaTexto.getText() == null
                        || entradaTexto.getText().toString().contains("  ")){
                    Toast toast = Toast.makeText(com.getActivity(), "Ingrese algún texto", Toast.LENGTH_SHORT);
                    toast.show();
                }else{

                    try {
                        Comment comentario = rutillaAvl.find(new Comment(Double.valueOf(entradaTexto.getText().toString()), 0, "", ""), rutillaAvl.root).key;

                        HandleFile files = new HandleFile("/data/data/com.PEDG1.unmapa/files/historialBusqueda.txt");
                        ListDinamic Historial = files.LeeArchivoDinamic();
                        Historial.add(entradaTexto.getText().toString());
                        files.EscribeArchivo(Historial);

                        Comment[] respuesta = {comentario};

                        adapter = new Adaptador(com.getContext(), respuesta);
                        listaComentarios.setAdapter(adapter);

                    }catch (Exception e){
                        Toast toast = Toast.makeText(com.getActivity(), "Debe ser un dato númerico", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });



        adapter = new Adaptador(this.getContext(), datas);
        listaComentarios.setAdapter(adapter);

        return vista;
    }
}