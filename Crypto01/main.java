package Crypto01;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        Substitutionschiffre sc = new Substitutionschiffre();
        try {
            sc.chiffrat = sc.load("C:/Users/ReneW/IdeaProjects/Java2/src/chiffrat.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.top();
        System.out.println(sc.sortedMap);
        System.out.println(sc.englishLetterFrequencies);
        sc.substitute();
        sc.plainText.forEach(System.out::println);
    }
}
