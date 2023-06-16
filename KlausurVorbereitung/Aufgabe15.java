package Java2.KlausurVorbereitung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Schreiben Sie eine Swing-Anwendung mit vier gleich großen Buttons, zwei je Zeile und zwei je Spalte.
// Die Größe der Buttons soll sich dynamisch an die Fenstergröße anpassen und die Buttons sollen zunächst schwarz dargestellt werden.
// Wenn mit dem Mauszeiger ein Button berührt wird, soll die Färbung dieses Buttons auf rot geändert werden, alle anderen werden schwarz dargestellt.
// Wenn ein Button gedrückt wird, soll in der Konsole die Meldung „Button gedrückt!“ ausgegeben werden.
public class Aufgabe15 extends JFrame {
    JFrame fenster = new JFrame("Aufgabe15");
    JPanel panel = new JPanel(new GridLayout(2,2));
    public Aufgabe15() {

        //fenster initialisieren
        fenster.setSize(500, 500);
        fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fenster.add(panel);

        //Button erzeugen

        JButton button1 = new JButton("Button1");
        button1.setSize(20, 10);
        button1.setBackground(Color.BLACK);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button1.setBackground(Color.RED);
            }
        });
        button1.addActionListener(e -> {
            System.out.println("Button1 gedrueckt");
        });
        JButton button2 = new JButton("Button2");
        button2.setSize(20, 10);
        button2.setBackground(Color.BLACK);
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button2.setBackground(Color.RED);
            }
        });
        button2.addActionListener(e -> {
            System.out.println("Button2 gedrueckt");
        });


        JButton button3 = new JButton("Button3");
        button3.setSize(20, 10);
        button3.setBackground(Color.BLACK);
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button3.setBackground(Color.RED);
            }
        });
        button3.addActionListener(e -> {
            System.out.println("Button3 gedrueckt");
        });
        JButton button4 = new JButton("Button4");
        button4.setSize(20, 10);
        button4.setBackground(Color.BLACK);
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button4.setBackground(Color.RED);
            }
        });
        button4.addActionListener(e -> {
            System.out.println("Button4 gedrueckt");
        });

        for (int i = 0; i <= 3; i++) {
            if (i == 0) {
                panel.add(button1);
            } else if (i == 1) {
                panel.add(button2);
            } else if (i == 2) {
                panel.add(button3);
            } else if (i == 3) {
                panel.add(button4);
            }
            fenster.setVisible(true);
        }
    }

    public static void main(String[] args) {
        Aufgabe15 a = new Aufgabe15();
    }
}
