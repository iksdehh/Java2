package Java2.KlausurVorbereitung;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Implementieren Sie eine Methode zum Entfernen aller Mehrfach-Einträge aus der Liste mit folgender Signatur: public void delCopies(List l).
// Nach Aufruf der Methode enthält die als Parameter übergebene Liste nur unterschiedliche, nicht gleiche Objekte. Die Reihenfolge darf nicht verändert werden.
public class Aufgabe10 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hallo", "Hallo", "wie", "gehts?");
        delCopies(list);
    }
    public static void delCopies(List<String> l){
        l.stream().distinct().toList().forEach(System.out::println);
    }
}
