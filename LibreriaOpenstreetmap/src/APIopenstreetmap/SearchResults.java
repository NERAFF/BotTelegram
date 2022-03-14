/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APIopenstreetmap;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author lauria_luca
 */
public class SearchResults {
    
    public String timestamp;
    public String attribution;
    public String querystring;
    public String excludePlaceIds;
    public String more_url;
    public boolean hasChilds;
    public List<Place> places;

    public SearchResults(Element e) {
        timestamp = e.hasAttribute("timestamp") ? e.getAttribute("timestamp") : null;
        attribution = e.hasAttribute("attribution") ? e.getAttribute("attribution") : null;
        querystring = e.hasAttribute("querystring") ? e.getAttribute("querystring") : null;
        excludePlaceIds = e.hasAttribute("exclude_place_ids") ? e.getAttribute("exclude_place_ids") : null;
        more_url = e.hasAttribute("more_url") ? e.getAttribute("more_url") : null;
        hasChilds = e.hasChildNodes();
        places = null;

        NodeList nPlaces = e.hasChildNodes() ? e.getElementsByTagName("place") : null;
        if (nPlaces.getLength() > 0) {
            places = new ArrayList();
            for (int i = 0; i < nPlaces.getLength(); i++) {
                places.add(new Place((Element) nPlaces.item(i)));
            }
        }
    }
}
