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
     
       FileCSV fileUsers = new FileCSV("user.csv");
        

        ThreadRicezione threadTelegram;
        threadTelegram = new ThreadRicezione(fileUsers);
        threadTelegram.start();
 
    }

}
