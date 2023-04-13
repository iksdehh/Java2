package Java2.task3;


import java.io.*;
import java.util.*;

public class Vorlesungsverzeichnis implements Iterable<List<String>>
{
    List <List<String>> lectures = new LinkedList<>();
    public static void main(String[] args)
    {
        Vorlesungsverzeichnis a = new Vorlesungsverzeichnis ("C:/Users/ReneW/IdeaProjects/Practise/src/Java2/task3/Daten.txt");

        a.descendingTitles();

    }

    public Vorlesungsverzeichnis(String s)
    {
        try
        {
           lectures = load(s);
        }
        catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    public static List<List<String>> load (String filename) throws IOException
    {
        List<List<String>> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        for(String line = br.readLine(); line!=null; line = br.readLine())
        {
            result.add(Arrays.asList(line.split(":")));
        }
        br.close();
        return result;
    }

    public List <String> titles()
    {
        List <String> titles = new LinkedList<>();  //Neue Linkedlist (iteriert von einem zum nächsten Element) Macht das einfügen und löschen von Elementen in der Mitte besser möglich
        for (List<String> lecture : lectures)   //es wird durch lectures iteriert (mithilfe einer Listenvariable lecture)
        {
            titles.add(lecture.get(1));         //es wird jeweils das zweite element der aktuellen Liste zu titels hinzugefügt
        }
        Collections.sort(titles);
        return titles;
    }

    public List <String> getDozent()        //Hilfsmethode für workaholics
    {
        List<String> Dozenten = new LinkedList<>();
        for (List<String> lecture : lectures)
        {
            Dozenten.add(lecture.get(2));       //es wird der jeweilige Dozent der neuen Liste hinzugefügt!
        }
        Collections.sort(Dozenten);             //neue Liste wird sortiert
        System.out.println(Dozenten);
        return Dozenten;
    }
    public Set<String> workaholics()
    {
        Set<String> workaholics = new HashSet<>();
        Map<String, Integer> workers = new HashMap<>();
        for(String key : this.getDozent())              //es wird die Liste von getDozent durchlaufen
        {
            if(workers.containsKey(key))                //Fall 1, der Dozent ist bereits in der Map enthalten
            {
                workers.put(key, workers.get(key) + 1);     // dann wird der Value um 1 erhöht
            }
            else                                                //Element ist noch nicht vorhanden
            {
                workers.put(key, 1);                                //der Dozent in key wird mit dem Wert 1 gespeichert
            }
        }
        //mit Map.Entry ist es möglich den Schlüssel & den Wert abzurufen und zu speichern -> im map entrySet, welches eine Funtkion der Hasmap-Klasse ist und eine Setansicht zurückgibt
        for (Map.Entry <String, Integer> item: workers.entrySet())
        {
            if(item.getValue() >= 2)                                         //wenn die Häufigkeit des Zeichens höher als m ist
            {
                for(int i = 0; i <=item.getValue()-1; i++)                  //durchlaufen wir solange, wie die Häufigkeit ist
                {
                    workaholics.add(item.getKey());                            // und speichern den Dozenten i mal in die Liste workaholics
                }
            }
        }
        System.out.println(workaholics);
        return workaholics;
    }

    public Map<String, List<String>> groupToTitles()
    {
        Map<String, List<String>> group = new HashMap<>();

        for (List<String> lecture : lectures)
        {
            String key = lecture.get(0);
            String value = lecture.get(1);

            if (group.containsKey(key))
            {
                List<String> titles= group.get(key);
                titles.add(value);

            }
            else
            {
                List<String> titles = new ArrayList<>();
                titles.add(value);
                group.put(key, titles);
            }
        }
        System.out.println(group);
        return group;
    }

    public Map<String, List<String>> multipleTitles()
    {
        Map<String, List<String>> multTitles = new HashMap<>();
        Map<String, List<String>> mult = new HashMap<>();
        List<String> moreThan = new ArrayList<>();

        for (List<String> lecture : lectures)
        {
            String key = lecture.get(1);
            String value = lecture.get(2);

            if (mult.containsKey(key))
            {
                List<String> titles= mult.get(key);
                titles.add(value);

            }
            else
            {
                List<String> titles = new ArrayList<>();
                titles.add(value);
                mult.put(key, titles);
            }
        }
        for(Map.Entry<String, List<String>> item: mult.entrySet())
        {
            System.out.println("Item: "+ item);
            if(item.getValue().size() > 1)
            {
                multTitles.put(item.getKey(), item.getValue());
            }
        }
        System.out.println(multTitles);
        return multTitles;
    }


    public List<String> descendingTitles()
    {
        List<List<String>> reverse = new ArrayList<>();
        for (List<String> lecture : lectures)
        {
            List <String> sub = new ArrayList<>();
            sub.add(lecture.get(1));
            sub.add(lecture.get(3));
            reverse.add(sub);
        }
        Comparator<List<String>> comp = new Comparator<List<String>>()
        {
            @Override
            public int compare(List<String> o1, List<String> o2)
            {
                int value1 = o1.size()- 1;
                int value2 = o2.size()- 1;

                int teilnehmer1 = Integer.parseInt(o1.get(value1));
                int teilnehmer2 = Integer.parseInt(o2.get(value2));
                return Integer.compare(teilnehmer1, teilnehmer2);
            }
        };

        Collections.sort(reverse, comp);
        System.out.println(reverse);
        List<String> sorted = new ArrayList<>();
        for (List<String> list : reverse)
        {
            sorted.add(list.get(0));
        }

        return sorted;
    }

    @Override
    public Iterator<List<String>> iterator() {
        return lectures.iterator();
    }
}
