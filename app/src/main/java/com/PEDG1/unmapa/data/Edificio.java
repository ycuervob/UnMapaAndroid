package com.PEDG1.unmapa.data;

public class Edificio extends Locacion{
    private double ubicaionX;
    private double ubicaionY;
    private String Apodo;
 
    public Edificio(int Numero, String Nombre, String Apodo, double ubicaionX, double ubicaionY) {
        super(Nombre, Numero);
        this.ubicaionX = ubicaionX;
        this.ubicaionY = ubicaionY;
        this.Apodo = Apodo;
    }
  
    
    public String getApodo() {
        return Apodo;
    }

    public void setApodo(String Apodo) {
        this.Apodo = Apodo;
    }
    
    
    public double getUbicaionX() {
        return ubicaionX;
    }

    public void setUbicaionX(double ubicaionX) {
        this.ubicaionX = ubicaionX;
    }

    public double getUbicaionY() {
        return ubicaionY;
    }

    public void setUbicaionY(double ubicaionY) {
        this.ubicaionY = ubicaionY;
    }

    @Override
    public String toString(){
        return super.toString()+";"+this.getApodo()+";"+this.getUbicaionX()+";"+this.getUbicaionY();         
    }

    @Override
    public int compareTo(Locacion t) {
        int retorno = 0;
        
        if(t == null){
            return -5;
        }
       
        if(t.getNumero() == this.getNumero()){
            retorno = 0;
        }else if(t.getNumero() < this.getNumero()){
            retorno = -1;
        }else if(t.getNumero() > this.getNumero()){
            retorno = 1;
        }
        
        return retorno;    
    }

}
