/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIopenstreetmap;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lauria_luca
 */
public class Location {
    List<String> nomi;
    List<String> valori;

    public Location(List<String> nomi, List<String> valori) {
        this.nomi = nomi;
        this.valori = valori;
    }
    
    public Location(){
        nomi=new ArrayList<>();
        valori = new ArrayList<>();
    }
    
    public void addNome(String s){
        nomi.add(s);
    }
    
    public void addValore(String s){
        valori.add(s);
    }
    
    public String getNome(int i){
        return nomi.get(i);
    }

   public String getValore(int i){
       return valori.get(i);
   }

   public int getSizeNomi(){
       return nomi.size();
   }
   
   public int getSizeValori(){
       return valori.size();
   }
}
