/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
 */
package com.PEDG1.unmapa.data;

import android.util.Log;

import  com.PEDG1.unmapa.structures.ListDinamic;
import  com.PEDG1.unmapa.structures.Stack;
import java.io.*;
import java.io.FileOutputStream;
import  com.PEDG1.unmapa.structures.Avltree;
import  com.PEDG1.unmapa.structures.TreeNode;

/**
 * Crea un objeto tipo HandleFile que permite leer un archivo de texto plano y
 * lo puede escribir, con ayuda de las clases del paquete java.util.
 *
 *
 * @param <T>Para tipos de datos genericos T
 * @see File
 * @see FileReader
 * @see BufferedReader
 * @see FileWriter
 * @see PrintWriter
 * @author yeison
 * @version 0.1
 */


/*
*   Esta clase se tiene que rehacer desde cero pues el sistema de obtencion de datos en android no funciona igual que en java para pc
* */


public class HandleFile<T> {

    private File archivo;
    private String ruta;
    private FileReader fr;
    private BufferedReader br;

    private FileWriter fichero;
    private PrintWriter pw;

    /**
     * Crea un tipo ArchivoLeido similar a un tipo File del paquete
     * Java.io.File.*
     * @param ruta String con ruta del archivo a manipular
     * @see File
     */
    public HandleFile(String ruta) {
        this.ruta = ruta;
        try {
            this.archivo = new File(this.ruta);
        } catch (Exception e) {
            Log.d("r","error al generar el objeto "+e.getMessage());
        }
    }

    public Avltree<Comment> leeArchivoAVL() throws FileNotFoundException, IOException {
        this.fr = new FileReader(this.archivo);
        this.br = new BufferedReader(fr);
        String linea;
        String[] split;

        linea = br.readLine();
        split = linea.split(";");
        int i = 0;

        Comment firstComent = new Comment(Double.valueOf(split[0]), Double.valueOf(split[1]), split[2], split[3]);
        TreeNode<Comment> raiz = new TreeNode<>(firstComent);
        Avltree<Comment> Tree = new Avltree<>(raiz);

        while ((linea = br.readLine()) != null) {
            split = linea.split(";");

            Tree.AVLinsert(new Comment(
                    Double.valueOf(split[0]),
                    Double.valueOf(split[1]),
                    split[2],
                    split[3]),
                    Tree.root);
            i++;
            if(i%10000 == 0){
                System.out.println("va en "+ i);
            }
        }

        return Tree;
    }

    /**
     * Permite la lectura de un archivo de texto plano y almacena por filas la
     * informacion en un Stack usa las clases FileReader y BufferedReader
     *
     * @param tipoStack Para indicar si el tipo de stack a ingresar es
     * "edificio" o "Oficina"
     * @return Stack con los datos almacenados en archivo de texto plano
     * separados por el caracter ";"
     * @see FileReader
     * @see BufferedReader
     * @see Stack
     */
    public <T> Stack LeeArchivo(String tipoStack) {
        Stack<T> datos = new Stack();

        try {
            this.fr = new FileReader(this.archivo);
            this.br = new BufferedReader(fr);
            String linea;
            String[] split;

            switch (tipoStack) {
                case "oficina":
                    while ((linea = br.readLine()) != null) {

                        split = linea.split(";");

                        datos.InsertarNodo(new Oficina(
                                Integer.valueOf(split[0]),
                                Integer.valueOf(split[1]),
                                split[2])
                        );
                    }
                    break;
                case "edificio":
                    while ((linea = br.readLine()) != null) {

                        split = linea.split(";");

                        datos.InsertarNodo(new Edificio(
                                Integer.valueOf(split[0]),
                                split[1],
                                split[2],
                                Double.valueOf(split[3]),
                                Double.valueOf(split[4]))
                        );
                    }
                    break;
                case "comentario":
                    while ((linea = br.readLine()) != null) {

                        split = linea.split(";");

                        datos.InsertarNodo(new Comment(
                                Double.valueOf(split[0]),
                                Double.valueOf(split[1]),
                                split[2],
                                split[3])
                        );
                    }
                    break;
                default:
                    break;
            }

        } catch (IOException leer) {
            System.out.println(leer.getMessage());
        } finally {
            try {
                this.fr.close();
            } catch (Exception cerr) {
                System.err.println(cerr.getMessage());
            }
        }

        return datos;
    }


    /**
     * Este metodo escribe el archivo desde cero, borrando lo que había y
     * escribiendo todo lo que se le ingrese, usa las clases FileWriter,
     * PrintWriter y ListDinamic
     *
     * @see FileWriter
     * @see PrintWriter
     * @see ListDinamic
     * @return ListDinamic lista dinamica con los datos guardados
     */
    public ListDinamic LeeArchivoDinamic() {

        ListDinamic datos = new ListDinamic();
        try {
            this.fr = new FileReader(this.archivo);
            this.br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                datos.add(linea);
            }

        } catch (IOException leer) {
            System.out.println(leer.getMessage());
        } finally {
            try {
                this.fr.close();
            } catch (IOException cerr) {
                System.out.println(cerr.getMessage());
            }
        }

        return datos;
    }

    /**
     * Este metodo escribe el archivo desde cero, borrando lo que había y
     * escribiendo todo lo que se le ingrese, usa las clases FileWriter y
     * PrintWriter
     *
     * @param StackEscribir stack a ser escrito en el archivo de texto plano con
     * la ruta seleccionada al crear el objeto tipo HandleFile
     * @see FileWriter
     * @see PrintWriter
     */
    public void EscribeArchivo(Stack<T> StackEscribir) {

        try {
            this.archivo.delete();
            this.fichero = new FileWriter(this.ruta);
            pw = new PrintWriter(this.fichero);
            int tam = StackEscribir.tamano;

            for (int i = 0; i < tam; i++) {
                pw.println(StackEscribir.EliminarNodo().informacion.toString());
            }

        } catch (IOException esc) {
            System.out.println(esc.getMessage());
        } finally {
            try {
                fichero.close();
            } catch (IOException cerr) {
                System.out.println(cerr.getMessage());
            }
        }

    }

    /**
     * Escribe en archivo los datos contenidos en un tipo ListDinamic
     *
     * @param Lista lista tipo ListaDinamic
     * @see ListDinamic
     */
    public void EscribeArchivo(ListDinamic Lista) {
        try {
            this.archivo.delete();
            this.fichero = new FileWriter(this.ruta);
            pw = new PrintWriter(this.fichero);
            int tam = Lista.length();
            for (int i = 0; i < tam; i++) {
                pw.println(Lista.get(i));
            }

        } catch (IOException esc) {
            System.out.println(esc.getMessage());
        } finally {
            try {
                fichero.close();
            } catch (IOException cerr) {
                System.out.println(cerr.getMessage());
            }
        }

    }

}
