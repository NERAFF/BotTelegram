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
import java.util.ArrayList;
import java.util.List;
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

        MyXMLOperations openstreet = new MyXMLOperations();
        FileCSV fileUsers = new FileCSV("users.csv");
        List<String> linee = new ArrayList();

        String all = n.getUpdates();
        JSONObject json = new JSONObject(all);
        JSONArray jArray = json.getJSONArray("result");
        for (int i = 0; i < jArray.length(); i++) {

            //salvo i dati dell'utente di telegram
            utente.setUpdate_id(Integer.parseInt(jArray.getJSONObject(i).get("update_id").toString()));
            utente.setMessage_id(Integer.parseInt(jArray.getJSONObject(i).getJSONObject("message").get("message_id").toString()));
            utente.setChatId(Integer.parseInt(jArray.getJSONObject(i).getJSONObject("message").getJSONObject("chat").get("id").toString()));
            utente.setFirst_name(jArray.getJSONObject(i).getJSONObject("message").getJSONObject("chat").get("first_name").toString());
            utente.setUsername(jArray.getJSONObject(i).getJSONObject("message").getJSONObject("chat").get("username").toString());
            utente.setText(jArray.getJSONObject(i).getJSONObject("message").get("text").toString());
            if (utente.getText().startsWith("/citta")) {
                SearchResults sr = openstreet.searchPlace(utente.getText().substring(7));
                String s = utente.getChatId() + ";" + utente.getFirst_name() + ";" + sr.places.get(0).getLat() + ";" + sr.places.get(0).getLon();
                if (!fileUsers.exists()) {//se il file non esiste lo crea
                    System.out.println("prima registrazione");
                    fileUsers.write(s);
                } else {//altrimenti in append
                    linee = fileUsers.read();
                    String[] campi;
                    for (int j = 0; j < linee.size(); j++) {
                        campi = linee.get(j).split(";");
                        if (campi[1].equals(utente.getFirst_name()))//se un utente è già registrato modifica
                        {
                            System.out.println("riscrivo tutto");
                            linee.set(j, s);//modifico la linea    
                            fileUsers.Rewrite(linee);//riscrivo tutto su file 
                            utente.setRegistrato(true);
                        } else{   
                        }                 
                    }
                    if(utente.getRegistrato()==false)
                    {
                     //se l'utente non è registrato si registra
                     System.out.println("nuova registrazione");
                     fileUsers.append(s);
                     System.out.println(s);
                    }
                    
                }
            }
            else{
            n.sendMessage("fare /citta 'nome citta'", utente.getChatId());
            }
            n.setOffset(utente.getUpdate_id() + 1);//per non rileggere gli stessi messaggi
        }
        //System.out.println(fileUsers.read());
        //n.sendMessage("ciao",757687710);
    }

}
