/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramapi;
import Liberiatelegram.*;
import APIopenstreetmap.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
/**
 *
 * @author lauria_luca
 */
public class BotTelegram {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        LibreriaTelegram n=new LibreriaTelegram();
        n.getUpdates();
        n.sendMessage("ciao",757687710);
        n.Scomponi();
        

    }
   
    
}
