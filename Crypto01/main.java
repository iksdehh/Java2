package Crypto01;

import java.io.IOException;

public class main {
    public static void main(String[] args) {
        Substitutionschiffre sc = new Substitutionschiffre();
        try {
            sc.chiffrat = sc.load("C:/Users/ReneW/IdeaProjects/Java2/Crypto01/chiffrat.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.top2();
        System.out.println(Substitutionschiffre.sortedMap);
        System.out.println(Substitutionschiffre.englishLetterFrequencies);
        sc.substitute();
        sc.plainText.forEach(System.out::println);

        //char[] mostFrequentLetters = {'E', 'A', 'T', 'H', 'N', 'D', 'O', 'R', 'I', 'S', 'L', 'F', 'M', 'U', 'W', 'G', 'Y', 'C', 'B', 'V', 'P', 'K', 'X', 'Q', 'J', 'Z'};
    }
}
