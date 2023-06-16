package Java2.KlausurVorbereitung;

//Schreiben Sie eine Rekursive Klasse print(n), die die Ziffern von n in umgekehrter Reihenfolge ausgibt. Bsp: print(123) gibt 3 dann 2 dann 1 aus.
public class Aufgabe2 {
    public static void main(String[] args) {
        print(1234);
    }
    public static void print(int n){
        if (n < 10){
            System.out.println(n);
        } else {
            System.out.println(n % 10);
            print(n/10);
        }
    }
}
