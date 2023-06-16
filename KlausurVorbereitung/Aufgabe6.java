package Java2.KlausurVorbereitung;

//Schreiben Sie eine Methode zaehler(int n), die bis 10 jede 2te Zahl ausgibt. Schreiben Sie diese Methode in a) Itterativ und in b) Rekrusiv.
public class Aufgabe6 {
    public static void main(String[] args) {
        zaehlerIt(10);
        zaehlerRe(0);
    }
    //Iterativ
    public static void zaehlerIt(int n){
        for (int i = 0; i <= n; i+=2) {
            System.out.println(i);
        }
    }
    //rekursiv
    public static void zaehlerRe(int n){
        if (n==10){
            System.out.println(n);
        } else {
            System.out.println(n);
            zaehlerRe(n+2);
        }
    }

}
