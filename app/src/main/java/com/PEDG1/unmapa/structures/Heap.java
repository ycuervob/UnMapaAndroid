/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PEDG1.unmapa.structures;

/**
 *
 * @author yeison
 */
class Heap{
    public int[] array;
    public int indice;
    
    public Heap	(int tamaño){
        indice = 0;
        this.array = new int[tamaño+1];
    }
    
    public int getmin(){
        int retu = array[1];
        array[1] = array[indice];
        array[indice] = 0;
        indice--;
        siftDown(1);
        return retu;
    }
    
    public void siftDown(int i){
        int Dpivot = 2*i+1;
        int Ipivot = 2*i; 
        
        if(Dpivot <= indice && Ipivot <= indice){
            if(array[i] > array[Dpivot] || array[i] > array[Ipivot]){
                if(array[Dpivot] < array[Ipivot]){
                    i = swap(i,Dpivot);
                    siftDown(i);
                }else if(array[Ipivot] <= array[Dpivot]){
                    i = swap(i,Ipivot);
                    siftDown(i);
                }    
            }
        }else if(Dpivot > indice && Ipivot <= indice){
            if(array[i] > array[Ipivot]){
                i = swap(i,Ipivot);
                siftDown(i);
            }    
        }else if(Dpivot <= indice && Ipivot > indice){
            if(array[i] > array[Dpivot]){
                i = swap(i,Dpivot);
                siftDown(i);
            } 
        }
        
        
    }
                   
    public void shiftupp(int iPivot){
        int PPivot = (int) (iPivot/2);
        while(iPivot > 1 && array[iPivot] < array[PPivot]){
           
           iPivot = swap(iPivot,PPivot); 
            PPivot = (int) (iPivot/2);
        }
    }
    
    public void insert(int element){
        indice++;        
        int iPivot = indice;
        array[indice] =  element;
        shiftupp(iPivot);
    }
    
    public int swap(int aRotar,int porRotar){
        int aux;
        
        aux = array[aRotar];
        array[aRotar] = array[porRotar];
        array[porRotar] = aux;
         
        return porRotar;
    }
    
    public int showMin(){
        return array[1];
    }
    
}
