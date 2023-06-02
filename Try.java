package Java2;

import Java2.task5Test.KlausurenServer;

import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Try {

    public static void main(String[] args) {
        List<Integer> liste = new ArrayList<>();
        liste.add(6);
        liste.add(988);
        liste.add(67);
        liste.add(765);

        liste.stream()
                .map(Integer::doubleValue)
                .filter(x -> x >10)
                .forEach(System.out::println);
/*
        System.out.println(liste);

        liste.forEach(s -> System.out.println(s));
        System.out.println();
        liste.forEach(System.out::println);

        System.out.println();
        liste.sort(Integer::compareTo); //absteigend Sortiert a - b wäre aufsteigend.
        liste.forEach(s -> System.out.println(s));



 */
    }

}