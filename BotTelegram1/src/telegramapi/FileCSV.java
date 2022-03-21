/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telegramapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lauria_luca
 */
public class FileCSV {

    private File file;
    List<String> lineList = new ArrayList();

    public List<String> getLineList() {
        return lineList;
    }
    public void setLineList(List<String> lineList) {
        this.lineList = lineList;
    }
    
    public FileCSV(File file) throws FileNotFoundException {
        this.file = file;
    }

    public FileCSV(String path) throws FileNotFoundException {
        this.file = new File(path);
    }

    public File getFile() {
        return file;
    }

    public boolean exists() {
        return file.exists();
    }

    public void createFile() throws IOException {
        file.createNewFile();
    }

    public synchronized void write(String s) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(s);//Crea file  || sovrascirve
        bw.newLine();
        bw.close();
    }
    public synchronized void Rewrite(List<String> lineList) throws IOException {//riscrivi il file quando stesso utente fa di nuovo /citta
        this.lineList=lineList;
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < lineList.size(); i++) {
           System.out.println(lineList.get(i));
           bw.write(lineList.get(i));
           bw.newLine();
        }
        bw.close();
    }
    public synchronized void append(String s) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.append(s);
        bw.newLine();
        bw.close();
    }

    public List<String> read() throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        lineList.clear();
        while ((line = br.readLine()) != null) {
            lineList.add(line);
        }
        
        return lineList;//tutto il file
    }

    public static String getCSV(String... data) {
        String csv = "";
        for (int i = 0; i < data.length; i++) {
            csv += data[i];
            if (i != data.length - 1) {
                csv += ";";
            }
        }
        return csv;
    }
}
