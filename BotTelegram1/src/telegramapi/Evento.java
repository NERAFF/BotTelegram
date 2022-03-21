/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telegramapi;

/**
 *
 * @author lauria_luca
 */
public class Evento {

    Double raggio, lat, lon;
    String Citta;

    public Evento() {
    }

    public Evento(Double raggio, String Citta, Double lat, Double lon) {
        this.raggio = raggio;
        this.Citta = Citta;
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public Double getRaggio() {
        return raggio;
    }

    public String getCitta() {
        return Citta;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public void setRaggio(Double raggio) {
        this.raggio = raggio;
    }

    public void setCitta(String Citta) {
        this.Citta = Citta;
    }

    public double CalcolaDistanza(Double lat2, Double lon2) 
    {
        
        double earthRadius = 6371;//raggio della terra in km
        double deltaLat = deg2rad(lat2- lat);
        double deltaLon = deg2rad(lon2-lon);
        var a
                = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(deg2rad(lat)) * Math.cos(deg2rad(lat2))
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = earthRadius * c; // Distanza in km
        
        return d ;//in km

    }
    public static double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }

}
