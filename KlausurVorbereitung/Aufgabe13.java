package Java2.KlausurVorbereitung;
//Schreiben Sie eine recursive(!) Methode public int ggT(int zahl1, int zahl2), die zu den übergebenen Zahlen den größten gemeinsamen Teiler zurückgibt, wobei der Differenzalgorithmus verwendet werden muss.
// Nach dem Differenzalgorithmus wird in jedem Schritt die größere um die kleinere Zahl verringert. Wenn beide Zahlen gleich sind, ist dies der größte gemeinsame Teiler.
public class Aufgabe13 {
    public static void main(String[] args) {
        ggT(54, 24);
    }
    private static int ggT(int x, int y) {
        if (x == y){
            System.out.println("ggT ist " + x);
            return x;
        } else if (x > y) {
            return ggT(x-y, y);
        } else {
            return ggT(x, y -x);
        }
    }
}
