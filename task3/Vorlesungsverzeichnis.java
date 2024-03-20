package task3;

import java.io.*;
        import java.util.*;
        import java.util.concurrent.TimeoutException;

public class Vorlesungsverzeichnis implements Iterable<List<String>>
{
    List <List<String>> lectures = new LinkedList<>();
    Set <Vorlesung> vorlesung = new HashSet<>();
    public static void main(String[] args)  {
        Vorlesungsverzeichnis a = new Vorlesungsverzeichnis("C:\\Users\\ReneW\\IdeaProjects\\Practise\\src\\Java2\\task3\\Daten.txt");
        a.descendingTitles();
    }

    public Vorlesungsverzeichnis(String s) {
        try {
            lectures = load(s);

            for (List<String> line : lectures) {

                if(line.get(0).equals("")){         //falls keine Gruppe angegeben ist, wirft das Programm eine Exception
                    throw new TextFileFormatException("Gruppenfeld leer");
                }
                String gruppe = line.get(0);
                String modul = line.get(1);
                String prof = line.get(2);

                if(!isNummeric(line.get(3))) {      //falls Anzahl keine Zahl ist, wirft das Programm eine Exception
                    throw new TextFileFormatException("keine Zahl");
                }
                String anzahl = line.get(3);

                if (line.size() ==4) {      // falls mehr oder weniger als die gegebene Daten angegeben sind, wirft das Programm eine Exception

                List<String> vorlesungsDaten = Arrays.asList(gruppe, modul, prof, anzahl);      //Liste wird erstellt aus allen Daten line.get(n)
                Vorlesung neueVorlesung = new Vorlesung(vorlesungsDaten);       //ein Vorlesungsobjekt wird erstellt und die Liste wird übergeben
                vorlesung.add(neueVorlesung);                   //neues Vorlesungsobjekt wird im Set gespeichert
                }
                else {
                    throw new TextFileFormatException("Fehler");
                }
            }
        }
            catch (IOException | ArrayIndexOutOfBoundsException e)
            {
                throw new TextFileFormatException("Liste fehlerhaft");
            }
        }


    public boolean isNummeric(String s) //Hilfsmethode zum Konstruktor - prüft ob Anzahl eine Zahl ist
    {
        int value;
        if(s == null || s.equals("")){
            return false;
        }
        try
        {
            value = Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e)
        {
        return false;
        }

    }


    public static List<List<String>> load (String filename) throws IOException
    {
        List<List<String>> result = new ArrayList<>();          //eine Liste wird erstellt
        BufferedReader br = new BufferedReader(new FileReader(filename));       //die Datei wird ausgelesen
        for (String line = br.readLine(); line != null; line = br.readLine()) { //durchläuft die Datei Zeile für Zeile
            result.add(Arrays.asList(line.split(":")));         //speichert alle Zeilen in "result" und trennt diese am  ":" mit einem Komma
        }
        br.close(); // das auslesen der Datei wird hier gestoppt
        return result;
    }

    public List <String> titles()
    {
        List <String> titles = new LinkedList<>();  //Neue Linkedlist (iteriert von einem zum nächsten Element) Macht das einfügen und löschen von Elementen in der Mitte besser möglich
        for (Vorlesung vorlesung1 : vorlesung)
        {
            if(!titles.contains(vorlesung1.modul))
            {
                titles.add(vorlesung1.modul);
            }
        }
        Collections.sort(titles);
        return titles;
    }
    public Set<String> workaholics()
    {
        Set<String> workaholics = new HashSet<>();
        List<String> dozenten =new ArrayList<>();
        List<String> alleDozenten = new ArrayList<>();

        for (Vorlesung vorlesung1 : vorlesung)
        {
            alleDozenten.add(vorlesung1.prof);  //alle Dozenten werden hier gespeichert
        }
        for (String s : alleDozenten)       //alle Dozenten werden durchlaufen
        {
            if(!dozenten.contains(s)) //jedes Element wird genau einmal hinzugefügt
            {
                dozenten.add(s);
            }
            else                    //Elemente, die >2 enthalten sind werden in workaholics gespeichert
            {
                workaholics.add(s);
            }
        }
        return workaholics;
    }

    public Map<String, List<String>> groupToTitles()
    {
        Map<String, List<String>> group = new HashMap<>();
        for (Vorlesung vorlesung1 : vorlesung)
        {
            String key = vorlesung1.gruppe;
            String value = vorlesung1.modul;
            if (group.containsKey(key))             //wenn die Map den Schlüssel bereits enthält, wird der Titel angehangen
            {
                List<String> titles= group.get(key);
                titles.add(value);
            }
            else                                //wenn die Map den Schlüssel nicht enthält, wird das Schlüssel-Werte-Paar an die Map angehangen
            {
                List<String> titles = new ArrayList<>();
                titles.add(value);
                group.put(key, titles);
            }
        }
        return group;
    }

    public Map<String, List<String>> multipleTitles()
    {
        Map<String, List<String>> multTitles = new HashMap<>();
        Map<String, List<String>> mult = new HashMap<>();
        List<String> moreThan = new ArrayList<>();

        for (Vorlesung vorlesung1 : vorlesung)
        {
            String key = vorlesung1.modul;
            String value = vorlesung1.prof;

            if (mult.containsKey(key))       //wenn die Map den Schlüssel bereits enthält, wird der Titel angehangen
            {
                List<String> titles= mult.get(key);
                titles.add(value);
            }
            else                            //wenn die Map den Schlüssel nicht enthält, wird das Schlüssel-Werte-Paar an die Map angehangen
            {
                List<String> titles = new ArrayList<>();
                titles.add(value);
                mult.put(key, titles);
            }
        }

        //mit Map.Entry ist es möglich den Schlüssel & den Wert abzurufen und zu speichern
        //im mult entrySet, welches eine Funktion der Hashmap-Klasse ist und eine Setansicht zurückgibt.
        for(Map.Entry<String, List<String>> item: mult.entrySet())
        {
            if(item.getValue().size() > 1)          //wenn der Wert >1 ist, wird der Value und der Schlüssel in der neuen Map gespeichert
            {
                multTitles.put(item.getKey(), item.getValue());
            }
        }
        return multTitles;
    }
    public List<String> descendingTitles()
    {
        List<List<String>> reverse = new ArrayList<>();
        for (Vorlesung vorlesung1 : vorlesung)
        {
            List <String> sub = new ArrayList<>();
            sub.add(vorlesung1.modul);
            sub.add(vorlesung1.anzahl);
            reverse.add(sub);
        }
        Comparator<List<String>> comp = new Comparator<List<String>>()
        {
            @Override
            public int compare(List<String> o1, List<String> o2)
            {

                int position1 = Integer.parseInt(o1.get(1));
                System.out.println("Wert 1: " + position1);
                int position2 = Integer.parseInt(o2.get(1));
                System.out.println("Wert 2: "+ position2);
                System.out.println("vergleich : " + Integer.compare(position2, position1));
                return Integer.compare(position2, position1);


            }
        };
        reverse.sort(comp);
        List<String> sorted = new ArrayList<>();
        for (List<String> list : reverse)
        {
            sorted.add(list.get(0));
        }
        System.out.println(reverse);
        return sorted;
    }
    @Override
    public Iterator<List<String>> iterator()
    {
        return lectures.iterator();
    }

}
