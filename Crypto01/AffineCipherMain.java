package Crypto01;

public class AffineCipherMain {
    public static void main(String[] args) {
        String text = "OYLWQ CFVSI YWYXI GCFHN YIOQL NGSJU YPGCM H";
        int a = 11;
        int b = 6;
        System.out.println("Text  : " + text);
        System.out.println("a : " + a);
        System.out.println("b : " + b);
       // String chiffrat = AffineCipher.encrypt(text, a, b);
        System.out.println();
        // System.out.println("Chiffrat: " + chiffrat);
        System.out.println("Plaintext: " + AffineCipher.decrypt(text, a, b));
    }
}
class AffineCipher {
    public static String encrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((((ch - base) * a + b) % 26) + base);
            }
            result.append(ch);
        }
        return result.toString();
    }

    public static String decrypt(String chiffrat, int a, int b) {
        // Berechnung der multiplikativen Inversen von a
        int aInverse = 0;
        for (int i = 0; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                aInverse = i;
                break;
            }
        }
        // Entschlüsselung des Chiffrats
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chiffrat.length(); i++) {
            char ch = chiffrat.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                int decoded = (aInverse * (ch - base - b)) % 26;
                if (decoded < 0) decoded += 26; // Korrektur für negative Zahlen
                ch = (char) (decoded + base);
            }
            result.append(ch);
        }
        return result.toString();
    }
}Text  : OYLWQ CFVSI YWYXI GCFHN YIOQL NGSJU YPGCM H
        a : 11
        b : 6

        Plaintext: WERSI CHZUM ESELM ACHTD EMWIR DAUFG EPACK T