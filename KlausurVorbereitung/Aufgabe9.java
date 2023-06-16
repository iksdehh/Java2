package Java2.KlausurVorbereitung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Implementieren Sie eine Methode zum Sortieren einer Liste von nicht leeren Zeichenketten, wobei diese absteigend nach dem letzten Zeichen in der Zeichenkette sortiert werden soll: List sortString(List list)
// Falls das letzte Zeichen gleich ist, soll als weiteres Kriterium die Länge der Zeichenkette (ebenfalls absteigend) herangezogen werden.
// Falls auch diese gleich ist, gelten die Zeichenketten insgesamt als gleich.
public class Aufgabe9 {
    public static void main(String[] args) {
        List<String> test = Arrays.asList("Mein","Name","ist", "komisch","komisch","test","testet");
        System.out.println(sortString(test));
    }
    public static List<String> sortString(List<String> list){
        List<String> sorted = list;
        return sorted.stream()
                .sorted(Comparator.comparing((String s) -> s.charAt(s.length() - 1))
                        .reversed()
                        .thenComparing(Comparator.comparing(String::length).reversed())
                        .thenComparing(Comparator.naturalOrder()))
                        .collect(Collectors.toList()); //Danke an GPT
    }
}
