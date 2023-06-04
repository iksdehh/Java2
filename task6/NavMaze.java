package Java2.task6;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavMaze extends JFrame  {
    JFrame fenster = new JFrame("GrapicMaze");
    List<Point> getSolution = new ArrayList<>();
    private final char[][] maze;
    int counter = 1;
    public static JPanel panel = new JPanel(new GridLayout(6, 6, 1, 1));
    public static JPanel panel2 = new JPanel(new GridLayout(6, 6, 1, 1));
    public  JPanel jpanel;
    public JButton zurueck_Button = new JButton( "zurück" );
    public  JButton vor_Button = new JButton("vor");

    public NavMaze(char[][] maze) {
        this.maze = maze;
        setFenster(); //Fenstereinstellung werden festgelegt

        panel.setBackground(Color.BLACK); //Panelhintergrundfarbe schwarz setzen, sodass für die Kästchen ein schwarzer Rand sichtbar wird.


        JPanel overlayPanel = new JPanel();
        overlayPanel.setLayout(new OverlayLayout(overlayPanel)); //erlaubt es mehrere "Schichten" zu erstellen
        panel2.setOpaque(false); //Hintergrundfarbe der obersten Schicht wird auf durchsichtig gesetzt.
        overlayPanel.add(panel); //untere Schicht wird hinzugefügt
        overlayPanel.add(panel2); //obere Schicht wird hinzugefügt


        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));    //JPanel für BorderLayout für den southBereich - FlowChart wird hinzugefügt

        setVor_Button(); //Aufruf für die Einstellungen des Vor-Buttons
        setZurueck_Button(); //Aufruf für die Einstellungen des Zurück-Buttons


        fenster.add(overlayPanel, BorderLayout.CENTER);//das GridLayout wird im Center platziert
        fenster.add(southPanel, BorderLayout.SOUTH);//das southPanel wird im South-Bereich platziert

        southPanel.add(zurueck_Button); //Hinzufügen des Buttons
        southPanel.add(vor_Button); //Hinzufügen des Buttons

        fenster.setVisible(true); //Fenster wird sichtbar gesetzt
    }

    private void setFenster(){
        fenster.setSize(500, 500);
        fenster.setLocationRelativeTo(null); // Um das Fenster in der Mitte des Bildschirms zu öffnen
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // beendet das Programm beim Schließen des Fensters
    }

    private void setVor_Button(){

        vor_Button.setSize(40,20); //Größe des Buttons wird festgesetzt
        vor_Button = new JButton("Vor");
        vor_Button.addActionListener(e -> { //definiert eine anonyme Funktion, nimmt ein ActionEvent-Objekt e als Eingabe und führt den darauf folgenden Code aus
            if (counter > 11){ //Es gibt nur 11 Kreise, danach beginnt es von vorne
                counter = 1;
            }
            plusAktion(counter); //Übergibt den Counter an die plusAktion-Methode
            counter++; //im Anschluss wird der Counter erhöht
        });
    }
    private void setZurueck_Button(){
        zurueck_Button.setSize(40,20); //Größe des Buttons wird festgesetzt
        zurueck_Button = new JButton("Zurück");
        zurueck_Button.addActionListener(e -> { //definiert eine anonyme Funktion, nimmt ein ActionEvent-Objekt e als Eingabe und führt den darauf folgenden Code aus
            if (counter==1){ //Ist der Counter auf 1, so gibt es keinen vorherigen Status
                plusAktion(counter); //Dadurch wird der Kreis an der ersten Position generiert.
                return; //verlässt die Methode
            }
            counter--; //falls der Counter höher als 1 ist, dann wird dieser um 1 verringert und ruft anschließend wieder die plusAktion-Methode auf
            plusAktion(counter);
        });
    }

    public boolean canExit(int i, int j) {

        int n = maze.length; // länger der Maze wird in n gespeichert

        if (i<0 || j<0 || i >=n || j>=n)
            return false;  // ausserhalb

        if (maze[i][j] != ' ')
            return false; // Mauer oder schon geprueft  -> Ändern, wenn X, dann schwarzes Feld

        maze[i][j] = '.';

        if (i==n-1 && j==n-1 // Ziel
                || canExit(i+1,j) /* unten */|| canExit(i,j+1) /* rechts */
                || canExit(i-1,j) /* oben */ || canExit(i,j-1) /* links */
        ) {

            maze[i][j] = '+';   //Weißes Feld mit Kreis
            return true;
        }
        return false;
    }

    public void printMaze() {
        for (int i = 0; i < maze.length; i++) { // Y-Achse der Maze
            for (int j = 0; j < maze.length; j++) { // X-Achse der Maze

                jpanel = new JPanel();
                char symbol = maze[i][j];

                if (symbol == '+') { //hier sollte eigentlich ein Kreis erstellt werden
                    Point point = new Point(j, (i)); //ein neuer Punkt wird mit diesen Koordinaten erstellt
                    getSolution.add(point); //der Liste wird dieser Punkt hinzugefügt
                } if (symbol == ' ') {
                    jpanel.setBackground(Color.WHITE); //Weiße Felder werden erstellt
                } if (symbol == 'X') {
                    jpanel.setBackground(Color.BLACK); //Schwarze Felder werden erstellt
                }
                panel.add(jpanel);
            }
        }
        fenster.setVisible(true);
    }

    private void plusAktion(int counter) { //Bearbeitung der oberen Schicht für das hinzufügen der Punkte

        panel2.removeAll(); //alles wird von Panel2 entfernt, um den vorherigen Punkt zu entfernen

        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 0); // Map wird mit den erstellten Punkten/Koordinaten befüllt
        hashMap.put(2, 6);
        hashMap.put(3, 12);
        hashMap.put(4, 13);
        hashMap.put(5, 19);
        hashMap.put(6, 25);
        hashMap.put(7, 26);
        hashMap.put(8, 32);
        hashMap.put(9, 33);
        hashMap.put(10, 34);
        hashMap.put(11, 35);

        int x = hashMap.getOrDefault(counter, -1); //Counter wird als Key an die Map übergeben und gibt den dazugehörigen Wert zurück - ist dieser nicht vorhanden wird der Wert auf -1 gesetzt.

        for (int i = 0; i < 36; i++) { // durch das Panel iterrieren
            JPanel jpanel = new JPanel();
            if(i == x){ //sollte das aktuelle i gleich des Wertes sein, der in x aus der Map gespeichert wurde, dann wird der Kreis an dieser Stelle hinzugefügt
                jpanel.setLayout(new BorderLayout()); //neuer Kreis soll mittig sein, daher Borderlayout
                jpanel.add(new KreisPanel(), BorderLayout.CENTER); //neuer Kreis wird in der Mitte hinzugefügt
            } else {
                jpanel.setOpaque(false); //alle anderen felder werden auf Transparent gesetzt
            }
            panel2.add(jpanel); //hinzufügen des Kreises, oder des transparenten Feldes in die obere Schicht
        }
        fenster.setVisible(true);
    }
    public static void main(String[] args) {
        char[][] maze =
                {{' ','X',' ','X',' ',' '},
                        {' ','X',' ',' ',' ','X'},
                        {' ',' ','X','X',' ','X'},
                        {'X',' ',' ',' ',' ','X'},
                        {' ',' ',' ','X',' ','X'},
                        {'X','X',' ',' ',' ',' '}};

        NavMaze mymaze = new NavMaze(maze); //neue Maze
        mymaze.canExit(0,0); //Erstellung der Maze
        mymaze.printMaze(); //Labyrinth wird erstellt und die Liste<Point> gefüllt
        mymaze.getSolution.forEach(System.out::println); //Ausgabe der Liste<Point>
    }
    static class KreisPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setColor(Color.blue);

            int durchmesser = Math.min(getWidth(), getHeight()); // Der Durchmesser wird als die kleinere Seitenlänge des Panels festgelegt
            int radius = durchmesser / 2;   //Radius des Kreises berechnen
            int centerX = getWidth() / 2 - radius; // Die x-Koordinate des Mittelpunkts des Kreises wird berechnet
            int centerY = getHeight() / 2 - radius; // Die y-Koordinate des Mittelpunkts des Kreises wird berechnet

            Ellipse2D.Double circle = new Ellipse2D.Double(centerX, centerY ,durchmesser , durchmesser); // Ein Ellipse2D-Objekt wird erstellt, das den Kreis repräsentiert
            graphics2D.fill(circle); // Der Kreis wird mit der aktuellen Farbe gefüllt und auf dem JPanel gezeichnet
        }
    }
}