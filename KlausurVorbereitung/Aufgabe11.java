package Java2.KlausurVorbereitung;

import java.util.ArrayList;
import java.util.List;

//Schreiben Sie ein Programm, das unter Verwendung von Collections-Klassen die Ziehung der Lottozahlen simuliert.
// Lassen Sie dazu wiederholt eine ganzzahlige Zufallszahl im Bereich 1-49 (über Math.random()) generieren und geben Sie dann das Ergebnis (sechs Zahlen) sortiert auf dem Bildschirm aus.
// Die Verwendung von Klassen, die zu Set kompatibel sind (zB HashSet), ist nicht zulässig!
public class Aufgabe11 {
    public static void main(String[] args) {
        lottoZahlen();
    }
    public static void lottoZahlen(){
        List<Integer> zahlen = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int randomNumber = (int) (Math.random()*49);
            zahlen.add(randomNumber);
        }
        zahlen.stream().sorted().forEach(System.out::println);
    }
}
