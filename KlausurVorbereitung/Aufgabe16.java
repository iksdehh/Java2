package Java2.KlausurVorbereitung;
//Implementieren Sie einen rekursiven Algorithmus, der für ein String-Objekt erkennt, ob es sich dabei um ein Palindrom handelt oder nicht.
// Ein Palindrom ist ein Wort, welches vorwärts und rückwärts gelesen identisch ist.
// Beispiele: „ABBA“, „lagerregal“, aber auch einzelne Zeichen (zB „a“) oder kein Zeichen. Folgende Methode ist zu implementieren: boolean istPalindrom(String satz).
public class Aufgabe16 {

    public static void main(String[] args) {
        String satz1 = "ABBA";
        String satz2 = "lagerregal";
        String satz3 = "Hallo";
        System.out.println(istPalindrom(satz1));
        System.out.println(istPalindrom(satz2));
        System.out.println(istPalindrom(satz3));

    }
    public static boolean istPalindrom(String satz) {
        if (satz.length() <= 1) {
            return true; // Basisfall: Einzeichen oder kein Zeichen sind Palindrome
        } else if (satz.charAt(0) == satz.charAt(satz.length() - 1)) {
            // Vergleiche erstes und letztes Zeichen, und überprüfe den Rest rekursiv
            String subString = satz.substring(1, satz.length() - 1);
            return istPalindrom(subString);
        } else {
            return false; // Zeichen stimmen nicht überein, kein Palindrom
        }
    }
}
