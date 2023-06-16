package Java2.KlausurVorbereitung;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//Schreiben Sie einen Programmteil der eine ArrayList mit 10 Einträgen in eine Datei schreibt.
public class Aufgabe3 {
    public static void main(String[] args) throws IOException {
        List<String> list = Arrays.asList("Hallo", "ich", "werde", "eingelesen");
        saveState(list);
    }
    private static void saveState(List<String> list) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\ReneW\\IdeaProjects\\Practise\\src\\Java2\\KlausurVorbereitung\\Aufgabe3Textfile", true))){
            for (String s : list) {
                bufferedWriter.write(s);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
