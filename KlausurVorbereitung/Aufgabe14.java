package Java2.KlausurVorbereitung;

//Schreiben Sie eine Swing-Anwendung mit zwei Eingabefeldern für Zahlen und einem Button. Wenn der Button gedrückt wird, soll auf der Konsole (System.out) die Summe der beiden Zahlen ausgegeben werden.

import javax.swing.*;
import java.awt.*;

public class Aufgabe14 extends JFrame{
    JFrame fenster = new JFrame();
    JPanel panel = new JPanel(new FlowLayout());

    public Aufgabe14(){
        //fenster initialisieren
        fenster.setSize(1000,250);
        fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Eingabefelder erzeugen
        JTextField eingabe1 = new JTextField(20);
        JTextField eingabe2 = new JTextField(20);
        panel.add(eingabe1);
        panel.add(eingabe2);

        //Ausgabefeld
        JTextField output = new JTextField(20);
        output.setEditable(false);


        //Button erstellen
        JButton button = new JButton("Summe gefaellig?");
        button.addActionListener(e -> {
            int a = Integer.parseInt(eingabe1.getText());
            int b = Integer.parseInt(eingabe2.getText());
            int n = a+b;
            output.setText(n + "");
        });
        panel.add(button);
        panel.add(output);


        fenster.add(panel);
        fenster.setVisible(true);
    }

    public static void main(String[] args) {
        Aufgabe14 a = new Aufgabe14();
    }
}
