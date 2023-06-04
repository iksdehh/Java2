package Java2.task6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavMaze extends JFrame  {

    List<Point> getSolution = new ArrayList<>();
    private final char[][] maze;
    int counter = 1;
    JFrame fenster = new JFrame("GrapicMaze");
    public static JPanel panel = new JPanel(new GridLayout(6, 6, 1, 1));
    public static JPanel panel2 = new JPanel(new GridLayout(6, 6, 1, 1));
    public  JPanel jpanel;
    public JButton zurueck_Button = new JButton( "zurück" );
    public  JButton vor_Button = new JButton("vor");

    public NavMaze(String title, char[][] maze) {

        fenster.setSize(500, 500);
        fenster.setLocationRelativeTo(null); // Um das Fenster in der Mitte des Bildschirms zu öffnen
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // beendet das Programm beim Schließen des Fensters
        panel.setBackground(Color.BLACK);

        JPanel overlayPanel = new JPanel();
        overlayPanel.setLayout(new OverlayLayout(overlayPanel));
        panel2.setOpaque(false);
        overlayPanel.add(panel);
        overlayPanel.add(panel2);


        //Einstellungen der Buttons
        vor_Button.setSize(40,20);
        // vor_Button.addActionListener(e -> System.out.println("ich bin der vor button"));
        zurueck_Button.setSize(40,20);
        // zurueck_Button.addActionListener(e -> System.out.println("ich bin der zurück button"));


        vor_Button = new JButton("Vor");
        vor_Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter > 11){
                    counter = 1;
                }
                plusAktion(counter);
                counter++;
            }
        });
        zurueck_Button = new JButton("Zurück");
        zurueck_Button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter==1){
                    plusAktion(counter);
                    return;
                }
                counter--;
                plusAktion(counter);
            }
        });

        //JPanel für BorderLayout für den southBereich - FlowChart wird hinzugefügt
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));

        //das GridLayout wird im Center platziert und das southPanel im South-Bereich
        fenster.add(overlayPanel, BorderLayout.CENTER);
        fenster.add(southPanel, BorderLayout.SOUTH);
        southPanel.add(zurueck_Button);
        southPanel.add(vor_Button);

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
                    Point point = new Point(j, (i));
                    getSolution.add(point);
                } if (symbol == ' ') {
                    jpanel.setBackground(Color.WHITE);
                } if (symbol == 'X') {
                    jpanel.setBackground(Color.BLACK);
                }
                panel.add(jpanel);
            }
        }
        fenster.setVisible(true);
    }

    private void plusAktion(int counter) {

        panel2.removeAll();
        int x= 0;

        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, 0);
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

        if(hashMap.containsKey(counter)) {
            x = hashMap.get(counter);
        }


        for (int i = 0; i < 36; i++) {
            JPanel jpanel = new JPanel();
            if(i == x){
                jpanel.setLayout(new BorderLayout());
                jpanel.add(new KreisPanel(), BorderLayout.CENTER);
                panel2.add(jpanel);
            } else {
                jpanel.setOpaque(false);
            }
            panel2.add(jpanel);
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