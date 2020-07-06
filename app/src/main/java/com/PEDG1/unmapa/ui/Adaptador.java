package com.PEDG1.unmapa.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.PEDG1.unmapa.R;
import com.PEDG1.unmapa.data.Comment;
import com.PEDG1.unmapa.data.Edificio;
import com.PEDG1.unmapa.data.Locacion;
import com.PEDG1.unmapa.data.Oficina;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    Edificio[] dataED;
    Oficina[] dataOF;
    Comment[] dataCo;
    String[] dataHis;
    String caso;

    public Adaptador(Context contexto, Edificio[] datas){
        this.context =  contexto;
        this.dataED = datas;
        this.caso = "1";
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    public Adaptador(Context contexto, Oficina[] datas){
        this.context =  contexto;
        this.dataOF = datas;
        this.caso = "2";
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    public Adaptador(Context contexto, Comment[] datas){
        this.context =  contexto;
        this.dataCo = datas;
        this.caso = "3";
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    public Adaptador(Context contexto, String[] datas){
        this.context =  contexto;
        this.dataHis = datas;
        this.caso = "4";
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        int retrorno = 0;

        switch (caso){
            case "1":
                retrorno = dataED.length;
                break;
            case "2":
                retrorno = dataOF.length;
                break;
            case "3":
                retrorno = dataCo.length;
                break;
            case "4":
                retrorno = dataHis.length;
                break;
        }

        return retrorno;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista;

        if(caso.equals("1")) {
            vista = inflater.inflate(R.layout.element_layout_edificio, null);
            TextView textoID = vista.findViewById(R.id.textelementoidedificio);
            TextView textoNombre = vista.findViewById(R.id.textidEdificio);
            TextView textoApodo = vista.findViewById(R.id.textApodoEdificio);

            textoID.setText(String.valueOf(dataED[position].getNumero()));
            textoNombre.setText(dataED[position].getNombre());
            textoApodo.setText(dataED[position].getApodo());
        }else if(caso.equals("2")){
            vista = inflater.inflate(R.layout.element_layout_oficina, null);
            TextView textoIDOficina = vista.findViewById(R.id.textidOficina);
            TextView textoIDEdificio = vista.findViewById(R.id.textidEdificioenoficina);
            TextView textoNombreOficina = vista.findViewById(R.id.textNombreOficina);

            textoIDOficina.setText(String.valueOf(String.valueOf(dataOF[position].getNumOficina())));
            textoIDEdificio.setText((String.valueOf(dataOF[position].getNumero())));
            textoNombreOficina.setText(dataOF[position].getNombre());
        }else if(caso.equals("3")){
            vista = inflater.inflate(R.layout.element_comentario, null);
            TextView textonumEdificioComen = vista.findViewById(R.id.textNumEdificioComentario);
            TextView textoFechaComen = vista.findViewById(R.id.textViewFechaComentario);
            TextView textComentarioGen = vista.findViewById(R.id.textViewComentarioGeneral);

            textonumEdificioComen.setText("Numero Edificio: "+ String.valueOf((int) dataCo[position].getBuildingComment()));
            textoFechaComen.setText("Fecha: "+(dataCo[position].getDateComment()));
            textComentarioGen.setText(dataCo[position].getComment());
        }else if(caso.equals("4")){
            vista = inflater.inflate(R.layout.element_layout_historial, null);
            TextView textoHistorial = vista.findViewById(R.id.textHistorialelemento);

            textoHistorial.setText("   " + dataHis[position]);
        }else{
            vista = inflater.inflate(R.layout.fragment_mapa, null);
        }

        return vista;
    }
}
