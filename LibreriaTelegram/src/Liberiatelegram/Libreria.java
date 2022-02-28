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
import org.json.*;

/**
 *
 * @author lauria_luca
 */
public class Libreria {

    private static BufferedReader in;
    public String allJSON = "";
    String BotName = "Pubblicita5blauria";
    String BotKey = "5208330863:AAEd3NvwpRRCuiBNaTWcCeq98Nv_Rjm3h0E";

    public void Libreria() {
    }

    public String getBotName() {
        return BotName;
    }

    public String getBotKey() {
        return BotKey;
    }

    public void getUpdates() {
        try {
            URL url = new URL("https://api.telegram.org/bot" + BotKey + "/getUpdates");
            System.out.println(url);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                allJSON += line;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Scomponi() {
        System.out.println(allJSON);
        JSONObject json = new JSONObject(allJSON);//riscontrati alcuni problemi con la libreria
        JSONArray jArray = json.getJSONArray("result");
        
        for (int i = 0; i < jArray.length(); i++) {
           System.out.println(jArray.getJSONObject(i).getJSONObject("message").get("text").toString());
        }
    }

    public void sendMessage(String message, int id) throws MalformedURLException, IOException {
        URL url = new URL("https://api.telegram.org/bot" + getBotKey() + "/sendMessage?chat_id=" + id + "&text=" + message);
        URLConnection con = url.openConnection();
        InputStream invia = new BufferedInputStream(con.getInputStream());

    }
}
