/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PEDG1.unmapa.structures;

public interface DinamicList{
    
    public void add(String element);
    public void resize(String element);
    public String remove(int index);
    public int length();
    public String[] toArray();
    public void set(int index, String value);
    public String get(int index);
    
}
