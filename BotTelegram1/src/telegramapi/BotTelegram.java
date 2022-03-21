/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegramapi;

import Liberiatelegram.*;
import APIopenstreetmap.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.SAXException;

/**
 *
 * @author lauria_luca
 */
public class BotTelegram extends JFrame {
    
    /**
     * @param args the command line arguments
     */
    JPanel jpanel;

    public BotTelegram() {//grafica
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jpanel = new JPanel();
        /*outPanel = new JPanel();
        textPanel = new JPanel();*/
        setLayout(null);

        JLabel lblCitta = new JLabel("Inserisci la citta evento");//aggiungo label
        lblCitta.setBounds(10, 20, 120, 25);
        lblCitta.setSize(400, 20);
        add(lblCitta);

        JTextField txtCitta = new JTextField(10);//aggiungo textbox
        txtCitta.setBounds(220, 20, 165, 25);
        add(txtCitta);

        JLabel lblraggio = new JLabel("Inserisci raggio (in km) ");//aggiungo label
        lblraggio.setBounds(10, 50, 120, 25);
        lblraggio.setSize(400, 20);
        add(lblraggio);

        JTextField txtRaggio = new JTextField(10);//aggiungo textbox
        txtRaggio.setBounds(220, 50, 165, 25);
        add(txtRaggio);

        JButton loginButton = new JButton("Aggiungi evento");//aggiungo bottone cerca
        loginButton.setBounds(50, 100, 80, 25);
        loginButton.setSize(300, 20);
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.addActionListener(new ActionListener() {//evento bottone
            @Override
            public void actionPerformed(ActionEvent e) {
                String Citta = txtCitta.getText();
                Double raggio = Double.parseDouble(txtRaggio.getText());
                Evento evento = new Evento();
                evento.setCitta(Citta);
                evento.setRaggio(raggio);
                MyXMLOperations openstreet = new MyXMLOperations();
                SearchResults sr = null;
                try {
                    sr = openstreet.searchPlace(Citta);//cerco in openstreetmap le cordinate
                    evento.setLat(Double.parseDouble(sr.places.get(0).getLat()));//setto latitudine
                    evento.setLon(Double.parseDouble(sr.places.get(0).getLon()));//setto longitudine
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(BotTelegram.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(BotTelegram.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BotTelegram.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    FileCSV fileUsers = new FileCSV("user.csv");
                    fileUsers.read();
                    LibreriaTelegram tg=new LibreriaTelegram();
                    List<String> lista=fileUsers.getLineList();//prendo il contenuto del file
                    for (int i = 0; i < lista.size(); i++) {
                        String[] campi=lista.get(i).split(";");
                        Double distanza=evento.CalcolaDistanza(Double.parseDouble(campi[2]), Double.parseDouble(campi[3]));
                        //System.out.println(distanza);
                        if(evento.getRaggio()>=distanza)//se l'utente è nel raggio invia la pubblicita
                        {
                            tg.sendMessage("nuovo evento->"+evento.getCitta(), Integer.parseInt(campi[0]));
                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BotTelegram.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BotTelegram.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        add(loginButton);

        jpanel.setBounds(0, 0, 600, 50);
        add(jpanel);

        /*outPanel.setBounds(0, 80, 600, 60);
        add(outPanel);
        
        textPanel.setBounds(0, 140, 600, 60);
        add(textPanel);*/
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException {

        BotTelegram view = new BotTelegram();
        view.setTitle("Pubblicita");
        view.setSize(500, 300);
        view.setVisible(true);

        FileCSV fileUsers = new FileCSV("user.csv");

        ThreadRicezione threadTelegram = new ThreadRicezione(fileUsers);
        threadTelegram.start();

    }

}
