package task6;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.awt.Point;
import java.util.List;

public class GraphicMaze extends JFrame {

        List<Point> getSolution = new ArrayList<>();
        private final char[][] maze;
        JFrame fenster = new JFrame("GrapicMaze");
        public static JPanel panel = new JPanel(new GridLayout(6, 6, 1, 1));

        public GraphicMaze(char[][] maze) {
            fenster.setSize(500, 500);
            fenster.setLocationRelativeTo(null); // Um das Fenster in der Mitte des Bildschirms zu öffnen
            fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // beendet das Programm beim Schließen des Fensters
            panel.setBackground(Color.BLACK); //Panelhintergrundfarbe schwarz setzen, sodass für die Kästchen ein schwarzer Rand sichtbar wird.
            fenster.add(panel);
            fenster.setVisible(true);
            this.maze = maze;
        }

    public static void main(String[] args) {

        char[][] maze =
                        {{' ','X',' ','X',' ',' '},
                        {' ','X',' ',' ',' ','X'},
                        {' ',' ','X','X',' ','X'},
                        {'X',' ',' ',' ',' ','X'},
                        {' ',' ',' ','X',' ','X'},
                        {'X','X',' ',' ',' ',' '}};

        GraphicMaze mymaze = new GraphicMaze(maze); //neue Maze

        mymaze.canExit(0,0); //Erstellung der Maze

        mymaze.printMaze(); //Labyrinth wird erstellt und die Liste<Point> gefüllt

        mymaze.getSolution.forEach(System.out::println); //Ausgabe der Liste<Point>
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

                maze[i][j] = '+'; //Weißes Feld mit Kreis
                return true;
            }

            return false;
        }

        public void printMaze() {
                for (int i = 0; i < maze.length; i++) { // Y-Achse
                    for (int j = 0; j < maze.length; j++) { // X-Achse
                        JPanel jpanel = new JPanel();
                        char symbol = maze[i][j];

                        if (symbol == '+') {
                            jpanel.setLayout(new BorderLayout()); //neuer Kreis soll mittig sein, daher Borderlayout
                            jpanel.add(new KreisPanel(), BorderLayout.CENTER); //neuer Kreis wird in der Mitte hinzugefügt
                            Point point = new Point(j, i); //Erstellt einen neuen Punkt mit den Koordinaten des jeweiligen Punktes.
                            getSolution.add(point); //der Liste wird dieser Punkt hinzugefügt
                        }
                        if (symbol == ' ') {
                            jpanel.setBackground(Color.WHITE); //Weiße Felder werden erstellt
                        }
                        if (symbol == 'X') {
                            jpanel.setBackground(Color.BLACK); //Schwarze Felder werden erstellt
                        }
                        panel.add(jpanel);
                    }
                }
                fenster.setVisible(true);
        }

        static class KreisPanel extends JPanel {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setColor(Color.blue);

                int durchmesser = Math.min(getWidth(), getHeight()); //Der Durchmesser wird als die kleinere Seitenlänge des Panels festgelegt
                int radius = durchmesser / 2; //Radius des Kreises berechnen
                int centerX = getWidth() / 2 - radius; //Die x-Koordinate des Mittelpunkts des Kreises wird berechnet
                int centerY = getHeight() / 2 - radius; //Die y-Koordinate des Mittelpunkts des Kreises wird berechnet

                Ellipse2D.Double circle = new Ellipse2D.Double(centerX, centerY ,durchmesser , durchmesser); //Ein Ellipse2D-Objekt wird erstellt, das den Kreis repräsentiert
                graphics2D.fill(circle); //Der Kreis wird mit der aktuellen Farbe gefüllt und auf dem JPanel gezeichnet
            }
        }
}
