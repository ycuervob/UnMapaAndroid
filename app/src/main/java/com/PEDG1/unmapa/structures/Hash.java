package com.PEDG1.unmapa.structures;

public class Hash<T>{
    Stack<T>[] Array;
    Stack<T>[] ArrayCopia;
    public int Cardinalidad;
    public int Nelementos;
    int p = 10000019;
    int x = 151;

    public Hash(int cardinalidad){
        this.Cardinalidad =  cardinalidad;
        this.Array = new Stack[cardinalidad];
        Nelementos = 0;

        for (int i = 0; i < Array.length; i++) {
            Array[i] = new Stack<>();
        }
    }

    public int hashFunction(T Objeto){
        int a = 35;
        int b = 81;
        int h = -1;

        if(Objeto.getClass().equals(Integer.valueOf(21).getClass())){
            int y = (Integer) Objeto;
            h = ((a*y+b) % p) % this.Cardinalidad;
        }else if(Objeto.getClass().equals("".getClass())){
            String S = (String) Objeto;
            double hi = 0;


            for (int i = 0; i < S.length(); i++) {
                hi = hi + S.charAt(i)*Math.pow(x, i) ;
            }

            h =(int) hi % p;
        }

        return h;
    }

    public int[] PrecomputedHashes(String Total, int LongSubstringP, int primo, int x){
        int[] H = new int[Total.length() - LongSubstringP + 1];
        String S = Total.substring(Total.length()- LongSubstringP,Total.length());


        H[Total.length() - LongSubstringP] = hashFunction((T) S);


        int y = 1;

        for (int i = 0; i < LongSubstringP; i++) {
            y = (y*x) % primo;
        }

        for (int i = (Total.length()-LongSubstringP-1); i >= 0  ; i--) {
            H[i] = (x * H[i + 1] + Total.charAt(i)- y * Total.charAt(i + LongSubstringP)) % primo;
        }

        return H;
    }

    public boolean Rabinkarp(String Total, String Parte){

        if(Parte.length()> Total.length())return false;

        int[] H = PrecomputedHashes(Total,Parte.length(),p,x);
        int pHash = hashFunction((T) Parte);

        if(pHash == H[H.length-1]){
            return true;
        }

        for (int i = 0; i <= Total.length()-Parte.length(); i++) {

            if(pHash != H[i]){
                continue;
            }

            if(AreEqual(Total.substring(i,i+Parte.length()),Parte)){
                return true;
            }

        }

        return false;
    }

    public boolean AreEqual(String S1,String S2){
        if (S1.length() != S2.length()){
            return false;
        }
        for (int i = 0; i < S1.length(); i++) {
            if(S1.charAt(i) != S2.charAt(i)){
                return false;
            }
        }

        return true;
    }

    public void remove(T Objeto){
        if(find(Objeto)){
            Stack<T> L = this.Array[hashFunction(Objeto)];
            L.EliminarNodo(Objeto);
            Nelementos--;
        }
    }

    public void Rehash(){
        double load_factor = (Nelementos/(float)Cardinalidad);

        if(load_factor > 1){
            this.Cardinalidad = this.Cardinalidad*2;

            Hash<T> rehash = new Hash<>(this.Cardinalidad);

            for (int i = 0; i < this.Array.length; i++) {
                while (!Array[i].PilaVacia()){
                    rehash.add((T) Array[i].EliminarNodo().informacion);
                }
            }

            this.Array = rehash.Array;

        }

    }


    public T add(T Objeto){
        Rehash();
        Stack<T> L = this.Array[hashFunction(Objeto)%this.Cardinalidad];

        Node<T> pivot = L.ObtenerCabeza();
        for (int i = 0; i < L.tamano; i++) {
            if(Objeto.equals(pivot.informacion)){
                return pivot.informacion;
            }
            pivot = pivot.siguiente;
        }

        L.InsertarNodo(Objeto);
        Nelementos++;
        return Objeto;
    }

    public boolean find(T Objeto){
        Stack<T> L = this.Array[hashFunction(Objeto)%this.Cardinalidad];

        Node<T> pivot = L.ObtenerCabeza();
        for (int i = 0; i < L.tamano; i++) {
            if(Objeto.equals(pivot.informacion)){
                return true;
            }
            pivot = pivot.siguiente;
        }

        return false;
    }

}
