package Java2.KlausurVorbereitung;

public class Aufgabe5a extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Aufgabe5a test = new Aufgabe5a();
        test.start();
    }
}
