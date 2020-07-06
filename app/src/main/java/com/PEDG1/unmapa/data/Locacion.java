
package com.PEDG1.unmapa.data;

public abstract class Locacion implements Comparable<Locacion>{
    private String Nombre;
    private int Numero;

    public Locacion(String Nombre, int Numero) {
        this.Nombre = Nombre;
        this.Numero = Numero;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    
    @Override
    public String toString(){
        return this.getNumero()+";"+this.getNombre();
    }
    
    

}
