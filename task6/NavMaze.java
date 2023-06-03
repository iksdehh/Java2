package Java2.task6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class NavMaze extends JFrame  {

    List<Point> getSolution = new ArrayList<>();
    private char[][] maze;
    JFrame fenster = new JFrame("GrapicMaze");
    public static JPanel panel = new JPanel(new GridLayout(6, 6, 1, 1));
    BorderLayout borderLayout = new BorderLayout(); //norden, osten, süden,westen)
    JButton zurueck_Button = new JButton( "zurück" );

    JButton vor_Button = new JButton("vor");
    public  JPanel jpanel;


    public NavMaze(String title, char[][] maze) {

        fenster.setSize(500, 500);
        fenster.setLocationRelativeTo(null); // Um das Fenster in der Mitte des Bildschirms zu öffnen
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // beendet das Programm beim Schließen des Fensters
        panel.setBackground(Color.BLACK);

        //Einstellungen der Buttons
        vor_Button.setSize(40,20);

        // vor_Button.addActionListener(e -> System.out.println("ich bin der vor button"));
        zurueck_Button.setSize(40,20);
        // zurueck_Button.addActionListener(e -> System.out.println("ich bin der zurück button"));



        vor_Button = new JButton("Vor");
        vor_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wegVor();
            }
        });
        zurueck_Button = new JButton("Zurück");
        zurueck_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wegZurueck();
            }
        });



        //JPanel für BorderLayout für den southBereich - FlowChart wird hinzugefügt
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));

        //das GridLayout wird im Center platziert und das southPanel im South-Bereich
        fenster.add(panel, BorderLayout.CENTER);
        fenster.add(southPanel, BorderLayout.SOUTH);
        southPanel.add(vor_Button);
        southPanel.add(zurueck_Button);

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

        NavMaze mymaze = new NavMaze("hallo", maze);

        mymaze.canExit(0,0);


        mymaze.printMaze();

        mymaze.getSolution.forEach(System.out::println);

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
                jpanel = new JPanel();
                char symbol = maze[i][j];

                if (symbol == '+') {
                    System.out.println("Kreis: " + "("+j+","+i+")");
                    Point point = new Point(j, (i)); //Erstellt einen neuen Punkt mit den Koordinaten des jeweiligen Punktes.
                    getSolution.add(point);
                }


                if (symbol == ' ') {
                    jpanel.setBackground(Color.WHITE);
                    System.out.println("Weis" + "("+j+","+i+")");
                }
                if (symbol == 'X') {
                    jpanel.setBackground(Color.BLACK);
                    System.out.println("schwarz" + "("+j+","+i+")");
                }

                System.out.print(maze[i][j] + " ");
                System.out.println();
                panel.add(jpanel);

            }
        }

        fenster.setVisible(true);

    }

    private void wegVor() {
        JPanel glass = new JPanel();
        glass.setOpaque(false); // sorgt für Transparenz

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++){
                char symbol = maze[i][j];
                if (symbol == '+'){
                    if(glass != null){
                        panel.remove(glass);
                    }

                    glass.setLayout(new BorderLayout());
                    glass.add(new KreisPanel(), BorderLayout.CENTER);
                    jpanel.add(glass);
                    panel.add(jpanel);
                    panel.revalidate(); //panel aktualisieren
                    panel.repaint();    //panel neuzeichenn

                }


        }

    }







       /* for (int y = maze.length-1; y >= 0; y--) { //Y-Achse
            for (int x = maze.length-1; x >= 0; x--) { //X-Achse
                char symbol = maze[y][x];
                if (symbol == '+') {

                }
            }

        }

        */
    }

   /*               jpanel = new JPanel(new BorderLayout());
                    jpanel.add(new KreisPanel(), BorderLayout.CENTER);
                    panel.add(jpanel);
                    panel.revalidate(); //panel aktualisieren
                    panel.repaint();    //panel neuzeichenn

    */

    private void wegZurueck() {
        /*
        if (jpanel != null) {
            panel.remove(jpanel);
        }
        jpanel = new JPanel(new BorderLayout());
        jpanel.add(new KreisPanel(), BorderLayout.CENTER);
        panel.add(jpanel);
        panel.revalidate();
        panel.repaint();

         */

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