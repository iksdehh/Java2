package Java2.KlausurVorbereitung;

import javax.swing.*;
import java.awt.*;

//Schreiben Sie eine Swing Anwendung. Wenn ein Button gedrückt wird soll „Hello Wolrd“ ausgegeben werden.
public class Aufgabe7 extends JFrame {

    JFrame fenster = new JFrame("Aufgabe 7");
    JPanel panel = new JPanel(new FlowLayout());
    public Aufgabe7(){
        //fenster einstellen
        fenster.setSize(400,400);
        fenster.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fenster.add(panel);

        //Ausgabefeld erzeugen
        JTextField outputField = new JTextField(20);
        panel.add(outputField);

        //Button erzeugen
        JButton ausgabeButton = new JButton("Hier klicken fuer Ausgabe");
        ausgabeButton.addActionListener(e -> {
            String output = "Hello World";
            outputField.setText(output);
        });
        panel.add(ausgabeButton);

        fenster.setVisible(true);
    }

    public static void main(String[] args) {
        Aufgabe7 aufgabe7 = new Aufgabe7();
    }
}
