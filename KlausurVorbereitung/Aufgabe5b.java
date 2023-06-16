package Java2.KlausurVorbereitung;

public class Aufgabe5b implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000); //lässt den thread schlafen
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Aufgabe5b test = new Aufgabe5b();
        Thread thread = new Thread(test); //wichtig hier im Gegensatz zu extends Thread!
        thread.start();
    }
}
