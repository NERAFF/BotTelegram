/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liberiatelegram;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author lauria_luca
 */
public class LibreriaTelegram {

    private static BufferedReader in;
    String BotName = "PUBBLICITA_lauria5b";
    String BotKey = "5188264080:AAHx17MHf5pSVzNxT3NdTg4ZsifqGJZEjk8";
    int offset;

    public void LibreriaTelegram() {
    }

    public String getBotName() {
        return BotName;
    }

    public String getBotKey() {
        return BotKey;
    }

    public String getUpdates() {
        
        String allJSON="";
        try {
            URL url = new URL("https://api.telegram.org/bot" + BotKey + "/getUpdates?offset="+offset);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                allJSON += line+"\n";
            }
            in.close();
            System.out.println("https://api.telegram.org/bot"+BotKey+"/getUpdates?offset="+offset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allJSON;
    }

    public void sendMessage(String message, int id) throws MalformedURLException, IOException {
        URL url = new URL("https://api.telegram.org/bot" + getBotKey() + "/sendMessage?chat_id=" + id + "&text=" + message);
        URLConnection con = url.openConnection();
        InputStream invia = new BufferedInputStream(con.getInputStream());
    }
    public void setOffset(int  offset) throws MalformedURLException, IOException
    {
        this.offset=offset;
        URL url = new URL("https://api.telegram.org/bot" + BotKey + "/getUpdates?offset="+offset);
        System.out.println("nuovo url:"+url);
        URLConnection con = url.openConnection();
        InputStream invia = new BufferedInputStream(con.getInputStream());
    }
}
