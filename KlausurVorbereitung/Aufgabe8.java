package KlausurVorbereitung;



import task4.TextFileFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//loadmethode aus der Übung!
public class Aufgabe8 {

    static List<String> liste = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        liste = load("C:\\Users\\ReneW\\IdeaProjects\\Practise\\src\\Java2\\KlausurVorbereitung\\Aufgabe8Test");
        System.out.println(liste);
    }
    public static List<String> load (String filename) throws IOException{
        List<String> geladen = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                if (!line.trim().isEmpty()) {
                    //ich weiß nicht ob dies so besprochen ist oder soll wir die Module tatsächlich speichern?
                    geladen.add(line);
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException exception) {
            throw new TextFileFormatException("Liste fehlerhaft!");
        }
        return geladen;
    }
}
