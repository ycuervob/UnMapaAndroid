package com.PEDG1.unmapa.structures;



public class ListDinamic implements DinamicList{
    
    private  String List[];
    private  String copiedArray[];
    private  int size;
    private  int capacity;
    
    public ListDinamic (){
        this.List = new String[1];
        this.size = 0;
        this.capacity = 1;
    }

    
    public int size(){
        return this.capacity;
    }
    
    
    @Override
    public void add(String element) {
        if(this.size == this.capacity){
            this.resize(element); 
        }else{
            this.List[this.size] =  element;
            this.size++;
        }
      
    }
    
    @Override
    public void resize(String element) {
        this.capacity= this.capacity * 2;
        copiedArray = new String[this.capacity];
        
        int i = 0;
        for (String each:this.List){
            copiedArray[i] = each;
            i++;  
        }
        this.List = this.copiedArray;
        this.List[size] = element;
        this.size++;
    }
    
    @Override
    public String remove(int index) {
        String retorno;
        if (index < this.size && index >= 0){
            retorno = this.List[index];
            this.List[index] = null;
            size--;
            for(int j = index ; j < this.size ; j++){
                this.List[j] = this.List[j+1];
            }
        }else{
            retorno = null;
        }
        return retorno;
    }

    @Override
    public int length() {
        return this.size;
    }

    @Override
    public void set(int index, String value) {
        if(index >= 0 && index <= this.size){
            this.List[index] =  value;
        }
    }

    @Override
    public String get(int index) {
        if(index >= 0 && index <= this.size){
            return this.List[index];
        }else{
            return null;
        }  
    }

    @Override
    public String[] toArray() {
        String[] retorno = new String[this.size];

        for (int i = 0; i < this.size; i++) {
            retorno[i] = this.List[i];
        }

        return retorno;
    }

}
