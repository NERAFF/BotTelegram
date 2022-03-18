/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telegramapi;

import APIopenstreetmap.MyXMLOperations;
import APIopenstreetmap.SearchResults;
import Liberiatelegram.LibreriaTelegram;
import Liberiatelegram.UserTelegram;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;
/**
 *
 * @author lauria_luca
 */
public class ThreadRicezione extends Thread {
    
    LibreriaTelegram n ;
    UserTelegram utente;

    MyXMLOperations openstreet;
    FileCSV fileUsers;
    String[] campi;
    List<String> linee;
    
    SearchResults sr=null;

    public ThreadRicezione(FileCSV fileUsers) {
        this.n = new LibreriaTelegram();
        this.utente=new UserTelegram();
        this.openstreet=new MyXMLOperations();
        this.fileUsers = fileUsers;
        this.campi = new String[MIN_PRIORITY];
        this.linee = linee;
    }


  

    @Override
    public void run() {
        while (true) {
             DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("yyyy/MM/dd HH:mm:ss-> "+dtf.format(LocalDateTime.now()));

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
                    try {
                        sr = openstreet.searchPlace(utente.getText().substring(7));
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SAXException ex) {
                        Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String forcsv = utente.getChatId() + ";" + utente.getFirst_name() + ";" + sr.places.get(0).getLat() + ";" + sr.places.get(0).getLon();
                    if (!fileUsers.exists()) {//se il file non esiste lo crea
                        System.out.println("prima registrazione");
                        try {
                            fileUsers.write(forcsv);
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {try {
                        //altrimenti in append
                        linee = fileUsers.read();
                        } catch (IOException ex) {
                            Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (int j = 0; j < linee.size(); j++) {
                            campi = linee.get(j).split(";");
                            if (campi[1].equals(utente.getFirst_name()))//se un utente è già registrato modifica
                            {
                                System.out.println("riscrivo tutto");
                                linee.set(j, forcsv);//modifico la linea    
                                try {
                                    fileUsers.Rewrite(linee);//riscrivo tutto su file 
                                } catch (IOException ex) {
                                    Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                utente.setRegistrato(true);
                            } else {
                            }
                        }
                        if (utente.getRegistrato() == false) {
                            //se l'utente non è registrato si registra
                            System.out.println("nuova registrazione");
                        }
                    }
                    try {
                        n.sendMessage(forcsv, utente.getChatId());
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        n.sendMessage("fare /citta 'nome citta'", utente.getChatId());
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    n.setOffset(utente.getUpdate_id() + 1);//per non rileggere gli stessi messaggi
                } catch (IOException ex) {
                    Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //System.out.println(fileUsers.read());
            //n.sendMessage("ciao",757687710);
            try {
                Thread.sleep(5000);//getupdates ogni 1s
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
