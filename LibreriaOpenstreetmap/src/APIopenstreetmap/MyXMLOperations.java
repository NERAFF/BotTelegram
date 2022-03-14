/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIopenstreetmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author lauria_luca
 */
public class MyXMLOperations {
    
    private String baseURL;
    private String addonURL;

    public MyXMLOperations() {
        this.baseURL = "https://nominatim.openstreetmap.org/search?q=";
        this.addonURL = "&format=xml&addressdetails=1Compito";
    }

    public String getXML(String search) throws FileNotFoundException, IOException {
        URL url = new URL(baseURL + URLEncoder.encode(search, StandardCharsets.UTF_8) + addonURL);
        Scanner scanner = new Scanner(url.openStream());
        scanner.useDelimiter("\u001a");
        String file = scanner.next();
        System.out.println(url);
        return file;
    }

    public SearchResults searchPlace(String place) throws ParserConfigurationException, SAXException, IOException {//prendo il primo place
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        String xml = getXML(place);
        Document doc = builder.parse(new InputSource(new StringReader(xml)));
        SearchResults sr = new SearchResults((Element) doc.getElementsByTagName("searchresults").item(0));
        return sr;
    }
}
