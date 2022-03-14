/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramapi;

import Liberiatelegram.*;
import APIopenstreetmap.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

/**
 *
 * @author lauria_luca
 */
public class BotTelegram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException {
        LibreriaTelegram n = new LibreriaTelegram();
        UserTelegram utente = new UserTelegram();
        
        MyXMLOperations openstreet=new MyXMLOperations();
        FileCSV fileUsers = new FileCSV("users.csv");

        String all = n.getUpdates();
        JSONObject json = new JSONObject(all);
        JSONArray jArray = json.getJSONArray("result");
        for (int i = 0; i < jArray.length(); i++) {
            if (jArray.getJSONObject(i).getJSONObject("message").has("entities") && "/citta".equals(jArray.getJSONObject(i).getJSONObject("message").get("text").toString().substring(0, 6))) {
                
                //salvo i dati dell'utente di telegram
                utente.setMessage_id(Integer.parseInt(jArray.getJSONObject(i).getJSONObject("message").get("message_id").toString()));
                utente.setChatId(Integer.parseInt(jArray.getJSONObject(i).getJSONObject("message").getJSONObject("chat").get("id").toString()));
                utente.setFirst_name(jArray.getJSONObject(i).getJSONObject("message").getJSONObject("chat").get("first_name").toString());
                utente.setUsername(jArray.getJSONObject(i).getJSONObject("message").getJSONObject("chat").get("username").toString());
                utente.setText(jArray.getJSONObject(i).getJSONObject("message").get("text").toString());
                          
                //System.out.println(utente.toString());
                SearchResults sr=openstreet.searchPlace(utente.getText().substring(7));
                String s=utente.getChatId()+ ";"+utente.getUsername()+";"+sr.places.get(0).getLat()+";"+sr.places.get(0).getLon();
                //System.out.println(utente.getChatId()+ ";"+utente.getUsername()+";"+sr.places.get(0).getLat()+";"+sr.places.get(0).getLon());
                fileUsers.append(s);
            }
        }
        System.out.println(fileUsers.read());
        //n.sendMessage("ciao",757687710);
    }
    
    

}
