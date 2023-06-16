package Java2.KlausurVorbereitung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Schreiben Sie eine Klasse public void delDup (List l), die aus einer Liste Duplikate entfernt. Die Reihenfolge darf dabei nicht verändert werden.
public class Aufgabe1 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,4,5,5,6,7,7,8);
        list.stream().distinct().forEach(System.out::println);
    }
}
