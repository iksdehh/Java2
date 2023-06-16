package Java2.KlausurVorbereitung;
// Implementieren Sie eine rekursive Methode, die für eine positive Zahl die duale Repräsentation der Zahl als Zeichenkette zurückgibt.
// Beispiel: Für die Zahl 23 soll die Zeichenkette „10111“ zurückgegeben werden. Falls eine negative Zahl als Parameter übergeben wird, soll eine IllegalArgumentException geworfen werden.
// Sie dürfen keine Schleifenanweisungen (while, for, do etc.) benutzen.
// Folgende Methode ist zu implementieren: String getBinaer(int zahl). Die Klasse IllegalArgumentException erbt von der Klasse RuntimeException. Was folgern Sie daraus?
public class Aufgabe17 {
    public static void main(String[] args) {
        String binary = getBinaer(23);
        System.out.println(binary);
    }
    public static String getBinaer(int zahl) {
        if (zahl < 0) {
            throw new IllegalArgumentException("Negative Zahl ist nicht erlaubt.");
        }
        if (zahl == 0) {
            return "0";
        } else if (zahl == 1) {
            return "1";
        } else {
            int rest = zahl % 2;
            int quotient = zahl / 2;
            return getBinaer(quotient) + rest;
        }
    }

}
