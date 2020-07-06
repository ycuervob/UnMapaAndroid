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
    public <T> void InsertarNodo(T nodo){
        Node nuevoNodo = new Node(nodo);
        nuevoNodo.siguiente = Cabeza;
        Cabeza = nuevoNodo;
        this.tamano++;
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
