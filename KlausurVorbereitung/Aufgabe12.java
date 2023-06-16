package Java2.KlausurVorbereitung;

//Schreiben Sie eine rekursive Methode print(n) zur Ausgabe aller Ziffern der positiven ganzen Zahl n. Die Methode darf außer n keine zusätzlichen Variablen verwenden.
// print(123) soll nacheinander die Zeichen 1, 2 und 3 ausgeben.
public class Aufgabe12 {
    public static void main(String[] args) {
        print(123);
    }
    //Keine Ahnung ob ich das richtig verstanden habe ?
    private static void print(int n) {
        if (n < 10){
            System.out.println(n);
        } else {
            print(n/10);
            System.out.println(n % 10);
        }
    }
}
