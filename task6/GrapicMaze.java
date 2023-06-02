package Java2.task6;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GrapicMaze extends JFrame {
        private char[][] maze;
        JFrame fenster = new JFrame("GrapicMaze");
      public static JPanel panel = new JPanel(new GridLayout(6, 6, 1, 1));

        public GrapicMaze(String title, char[][] maze) {
            fenster.setSize(500, 500);
            fenster.setLocationRelativeTo(null); // Um das Fenster in der Mitte des Bildschirms zu öffnen
            fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // beendet das Programm beim Schließen des Fensters
            panel.setBackground(Color.BLACK);
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

        GrapicMaze mymaze = new GrapicMaze("hallo", maze);

        mymaze.canExit(0,0);

        mymaze.printMaze();


    }


        public boolean canExit(int i, int j) {

            int n = maze.length;

            if (i<0 || j<0 || i >=n || j>=n)
                return false;  // ausserhalb

            if (maze[i][j] != ' ')
                return false; // Mauer oder schon geprueft  -> Ändern, wenn X, dann schwarzes Feld

            maze[i][j] = '.';

            if (i==n-1 && j==n-1 // Ziel
                    || canExit(i+1,j) /* unten */|| canExit(i,j+1) /* rechts */
                    || canExit(i-1,j) /* oben */ || canExit(i,j-1) /* links */
            ) {
                //Weißes Feld mit Kreis
                System.out.println("("+j+","+i+")");
                maze[i][j] = '+';
                return true;
            }

            return false;
        }

        public void printMaze() {
                for (int i = 0; i < maze.length; i++) {
                    for (int j = 0; j < maze.length; j++) {
                        JPanel jpanel = new JPanel();
                        char symbol = maze[i][j];

                        if (symbol == '+') {
                            jpanel.setLayout(new BorderLayout());
                            jpanel.add(new KreisPanel(), BorderLayout.CENTER);
                            System.out.println("Kreis");
                        }
                        if (symbol == ' ') {
                            jpanel.setBackground(Color.WHITE);
                            System.out.println("Weiß");
                        }
                        if (symbol == 'X') {
                            jpanel.setBackground(Color.BLACK);
                            System.out.println("schwarz");
                        }

                        System.out.print(maze[i][j] + " ");
                        System.out.println();
                        panel.add(jpanel);

                    }
                }

                fenster.setVisible(true);

        }

        class KreisPanel extends JPanel {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D graphics2D = (Graphics2D) g;
                graphics2D.setColor(Color.blue);

                int durchmesser = Math.min(getWidth(), getHeight());
                int radius = durchmesser / 2;
                int centerX = getWidth() / 2 - radius;
                int centerY = getHeight() / 2 - radius;

                Ellipse2D.Double circle = new Ellipse2D.Double(centerX, centerY ,durchmesser , durchmesser);
                graphics2D.fill(circle);
            }
        }
}
