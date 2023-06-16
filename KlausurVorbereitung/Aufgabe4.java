package Java2.KlausurVorbereitung;

import javax.swing.*;
import java.awt.*;

//Schreiben Sie eine Swing-Anwendung mit 2 Eingabefeldern für Name und Vorname, 2 Buttons für die Ausgabe und 2 Ausgabefeldern.
public class Aufgabe4 extends JFrame {

    public static JFrame fenster = new JFrame("Aufgabe4");
    public static JPanel panel = new JPanel(new BorderLayout());
    public Aufgabe4(){
        //fenster konfigurieren
        fenster.setSize(500,500);
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.add(panel);


        //zwei Eingabefelder erstellen und im South platzieren
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        JTextField vorname = new JTextField( 20);
        JTextField name = new JTextField( 20);
        southPanel.add(vorname);
        southPanel.add(name);
        fenster.add(southPanel, BorderLayout.SOUTH);


        //zwei Buttons erstellen, für die jeweilige Ausgabe
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton vNameButton = new JButton("Vorname");

        JButton nameButton = new JButton("Nachname");
        centerPanel.add(vNameButton);
        centerPanel.add(nameButton);


        //zwei Ausgabefelder erzeugen
        JTextField vornameOutputField = new JTextField(20);
        vornameOutputField.setEditable(false);
        JTextField nameOutputField = new JTextField(20);
        nameOutputField.setEditable(false);
        centerPanel.add(vornameOutputField);
        centerPanel.add(nameOutputField);
        fenster.add(centerPanel, BorderLayout.CENTER);

        //erzeugen des Outputs bei Drücken des Buttons
        vNameButton.addActionListener(e -> {
            String inputText = vorname.getText();
            vornameOutputField.setText(inputText);
        });
        nameButton.addActionListener(e -> {
            String inputText = name.getText();
            nameOutputField.setText(inputText);
        });


        fenster.setVisible(true);
    }
    public static void main(String[] args) {
        Aufgabe4 aufgabe4 = new Aufgabe4();
    }

}
