package com.PEDG1.unmapa.structures;


/**
 * 
 * 
 * @param <T> Valor generico T
 */

public class Stack<T>{
    
    // Atributos
    private Node Cabeza;
    public int tamano = 0;

    
    public Node ObtenerCabeza(){
        return this.Cabeza;
    }
    
    //Constructor
    public Stack(){
        Cabeza = null;
        tamano = 0;      
    }
    
    //Metodo: Saber si la pila esta vacia
    public boolean PilaVacia(){
        return Cabeza == null;
    }
    
    //Metodo: Insertar nodo
    public <T> void InsertarNodo(T Objeto){
        Node<T> nuevoNodo = new Node<>(Objeto);
        nuevoNodo.siguiente = Cabeza;
        Cabeza = nuevoNodo;
        this.tamano++;
    }

    //Medodo:Eliminar un objeto buscado
    public void EliminarNodo(T Objeto){
        Node pivote =  this.ObtenerCabeza();
        Node PivoteAnterior =  null;


        if (Objeto.equals(pivote.informacion)) {
            this.EliminarNodo();
        }else {
            PivoteAnterior =  pivote;
            pivote =  pivote.siguiente;

            for (int i = 1; i < this.tamano; i++) {

                if (Objeto.equals(pivote.informacion)) {
                    PivoteAnterior.siguiente = pivote.siguiente;
                    this.tamano--;
                }

                PivoteAnterior =  pivote;
                pivote =  pivote.siguiente;

            }
        }

    }

    
    //Metodo: Eliminar nodo de la pila
    public Node EliminarNodo(){
        Node bandera = Cabeza;
        Cabeza = Cabeza.siguiente;
        tamano--;
        return bandera;
    }
    
    //Metodo: Tamaño de pila
    public int TamanoPila(){
        return tamano;
    }
    
    //Metodo: Vaciar pila
    public void VaciarPila(){
        while (!PilaVacia()){
            EliminarNodo();
        }
    }

    
            
}
