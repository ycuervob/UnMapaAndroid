
package com.PEDG1.unmapa.data;
 
public class Oficina extends Locacion{
    private int NumOficina;

    public Oficina(int NumeroOficina, int NumeroEdificio, String NombreOficina) {
        super(NombreOficina, NumeroEdificio);
        this.NumOficina = NumeroOficina;
    }

    
    public int getNumOficina() {
        return NumOficina;
    }

    public void setNumOficina(int NumOficina) {
        this.NumOficina = NumOficina;
    }
 
    @Override
    public String toString(){
        return this.getNumOficina()+";"+super.toString();         
    }

    @Override
    public int compareTo(Locacion t){
        if(t == null){
            return -5;
        }
        
        Oficina of = (Oficina) t;
        int retorno = 0;
       
        if(of.NumOficina == this.NumOficina){
            retorno = 0;
        }else if(of.NumOficina < this.NumOficina){
            retorno = -1;
        }else if(of.NumOficina > this.NumOficina){
            retorno = 1;
        }
        
        return retorno;
    }
   
}
