package com.PEDG1.unmapa.structures;

public class Node<T> {
    public T informacion;
    public Node siguiente;
    
    public Node(T valor){
        informacion = valor;
        siguiente = null;
    }

    public T getValor(){
        return this.informacion;
    }
}
