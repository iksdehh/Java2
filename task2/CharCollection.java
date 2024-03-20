package task2;

import java.util.*;

public class CharCollection
{
    List <Character> letter = new ArrayList<>();

    public static void main(String[] args)
    {
        CharCollection cc = new CharCollection('A', 'B', 'R', 'A', 'K', 'A', 'D', 'A', 'B', 'R', 'A');
        CharCollection a = new CharCollection('A', 'N', 'A', 'N', 'A', 'S');
        CharCollection c = new CharCollection("NASA");
        CharCollection b = new CharCollection('H', 'O', 'C', 'H', 'S', 'C', 'H', 'U', 'L', 'E');
        CharCollection f = new CharCollection("AAAA");
        a.isSubset(f);

    }

    public CharCollection(char... cc)
    {
        for (int i = 0; i < cc.length; i++)
        {
            if(Character.isUpperCase(cc[i]))        // prüft, ob es sich um einen Großbuchstaben handelt.
            {
                letter.add(cc[i]);
            }
        }
    }
    public CharCollection(String s)
    {
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if(Character.isUpperCase(c[i]))      // prüft, ob es sich um einen Großbuchstaben handelt.
            {
                letter.add(c[i]);
            }
        }
    }

    public int size()
    {

        return letter.size();       //.size() gibt die länge der Liste zurück
    }

    public int count(char c)
    {
        int count = 0;
        for (Character character : letter)
        {
            if (character.equals(c))        //ist in letter das Zeichen c enthalten, wird der Counter um einen erhöht
            {
                count++;
            }
        }
        System.out.println(c + " = " + count);      //gibt die Häufigkeit des übergebenen Zeichens sowie die das Zeichen selbst aus
        return count;
    }
    public int different()
    {
        int size = 0;
        Set <Character> s = new HashSet<>();        //in einem Set ist jedes Element einzigartig
        s.addAll(letter);     //alle, in letter, enthaltenen Elemente werden im Set "s" gespeichert, doppelte werden nicht übernommen
        size = s.size();       //die nun entstandene Länge gibt die Anzahl verschieder Elemente wieder.
        //System.out.println(size);
        return size;
    }

    public char top()
    {
        Map <String, Integer> mLetter = new HashMap<>(); //hier wird eine neue Hashmap erstellt.

        for (Character c : letter)
        {
            String key = String.valueOf(c); //in key wird das aktuelle Zeichen an der Stelle c gespeichert

            if (mLetter.containsKey(key)) //Fall 1, die Hashmap besitzt das aktuelle Element bereits
            {
                mLetter.put(key, mLetter.get(key)+1); //Das Zeichen key wird aufgerufen und der hier gespeicherte Wert um +1 erhöht.
            }
            else //Element ist noch nicht vorhanden
            {

                mLetter.put(key, 1); //das Zeichen in key wird mit dem Wert 1 gespeichert
            }
            break;
        }

        if (mLetter.isEmpty())
        {
            System.out.println((char) mLetter.size());
            return (char) mLetter.size();
        }


        //mit Map.Entry ist es möglich den Schlüssel & den Wert abzurufen und zu speichern -> im mLetter entrySet, welches eine Funtkion der Hasmap-Klasse ist und eine Setansicht zurückgibt.
        for (Map.Entry<String, Integer> item: mLetter.entrySet())
        {
            //System.out.println(item.getKey() + ": " + item.getValue()); //getKey: der Schlüssel & getValue: der Wert, in diesem Fall die Häufigkeit

        }
        char c='0';
        for (Map.Entry<String, Integer> topChar : mLetter.entrySet()) //selbiges wie darüber
        {
            String s = topChar.getKey(); //speichert das erste Zeichen in einem String
            c = s.charAt(0); //wandelt String in einen char um.
            break; //unterbricht nach dem ersten Wert, da in einem Set das höchste Element oben steht
        }
        return c;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        sb.append("(");

        for (Character s : letter)
        {
            sb.append(s);
            sb.append(", ");
        }
         sb.append(")");
        if(sb.length() >= 4)
        {
            sb.replace(sb.length()-3, sb.length()-1, ""); //ersetzt das letzte "," durch ""
        }
        if(!sb.toString().equals("")) {
           // System.out.println(sb.toString());
            return sb.toString();   //hier endet die Methode sofern der string nicht leer ist.
        }

        String leer ="()";      //ist der String leer, wird ein () ausgegeben.
        return leer;
    }

    public CharCollection moreThan(int m)
    {
        List<String> moreThan = new ArrayList<>();
        Map <String, Integer> map = new HashMap<>(); //für den Char und den Wert

        for (Character c : letter)
        {
            String key = String.valueOf(c);                 //in key wird das aktuelle Zeichen an der Stelle c gespeichert
            if (map.containsKey(key))                       //Fall 1, die Hashmap besitzt das aktuelle Element bereits
            {
                map.put(key, map.get(key) + 1);                 //Das Zeichen key wird aufgerufen und der hier gespeicherte Wert um +1 erhöht.
            }
            else                                                //Element ist noch nicht vorhanden
            {
                map.put(key, 1);                                //das Zeichen in key wird mit dem Wert 1 gespeichert
            }

        }
        //mit Map.Entry ist es möglich den Schlüssel & den Wert abzurufen und zu speichern -> im map entrySet
        // welches eine Funtkion der Hasmap-Klasse ist und eine Setansicht zurückgibt.
        for (Map.Entry <String, Integer> item: map.entrySet())
        {
            if(item.getValue() > m)                                         //wenn die Häufigkeit des Zeichens höher als m ist
            {
                for(int i = 0; i <=item.getValue()-1; i++)                  //durchlaufen wir solange wie die Häufigkeit ist
                {
                    moreThan.add(item.getKey());                            // und speichern das Zeichen i mal in die Liste "moreThan"
                }
            }
        }
        String s = moreThan.toString();                                 //umwandeln der Liste in einen String
        return new CharCollection(s);                 //Rückgabe des Strings an den Konstruktor der CharCollection
    }


   public boolean equals(Object x)
    {
        if (x instanceof CharCollection other)      //prüft ob x ein Objekt dieser Klasse ist (Instanz), ist dies der fall, wird eine CharCollection "other" erstellt
        { //prüft ob x ein Argument von CharCollection ist
            // Typkonvertierung durchführen, Casten

            if (this.letter.size() != other.letter.size())          // Prüft, ob beide ungleich lang sind, wenn ja --> dann ungleich
            {
                return false;
            }
            Set<Character> set1 = new HashSet<>(this.letter);          //Set1 wird erstellt mit den Elementen aus this.letter
            Set<Character> set2 = new HashSet<>(other.letter);          //Set2 wird erstellt mit den Elementen aus other.letter
            return set1.equals(set2);                                    //wenn beide gleich sind, wird es zurückgegeben (egel welche Reihenfolge)

        }
        return false;
    }

    public CharCollection except(CharCollection cc)
    {
        ArrayList <Character> filtered = new ArrayList<>(letter); //neue Lister die identisch mit der this Liste ist

        for (char c : cc.letter)
        {
            filtered.remove(Character.valueOf(c));      //entfernt aus der neuen Liste alle Elemente, welche in cc.letter ebenfalls enthalten sind
        }
        CharCollection k = new CharCollection();    //neue CharCollection mit neuer Liste (k.letter)
        k.letter = filtered;                        //k.letter wird definiert mit der Liste, aus der die Elemente gelöscht wurden

        return k;
    }
    
    public boolean isSubset(CharCollection cc)
    {
        Map <Character, Integer> thisMap = new HashMap<>();
        Map <Character, Integer> bMap = new HashMap<>();

        for (Character c : this.letter)
        {
            thisMap.put(c, thisMap.getOrDefault(c, 0)+1); //gibt den Wert des Schlüssels c zurück -> wenn nicht vorhanden dann denn default wert (hier 0) -> wenn vorhanden wird dieser um 1 erhöht
        }
        for(Character c : cc.letter)
        {
            bMap.put(c, bMap.getOrDefault(c, 0) +1);    //gibt den Wert des Schlüssels c zurück -> wenn nicht vorhanden dann denn default wert (hier 0) -> wenn vorhanden wird dieser um 1 erhöht
        }
        System.out.println(thisMap.keySet());   //keySet -> gibt die Setansicht der Schlüssel wieder
        System.out.println("hier " + thisMap.entrySet()); //entrySet -> gibt ein Set von Schlüssel-Wert-Paare zurück z.B. [A=3, B=2, C=4]
        System.out.println(bMap.keySet());
        System.out.println(bMap.entrySet());
        //mit Map.Entry ist es möglich den Schlüssel & den Wert abzurufen und zu speichern -> im entrySet, welches eine Funktion der Hashmap-Klasse ist und eine Setansicht zurückgibt.
        for(Map.Entry<Character, Integer> entry : thisMap.entrySet())
        {
            char c = entry.getKey();    //Der Schlüssel vom aktuellen entry-Objekt wird in einem Char gespeichert
            int count = entry.getValue();      //Der Wert vom aktuellen entry-Objekt wird in einem int gespeichert
            if (bMap.isEmpty() || thisMap.isEmpty())    //sind beide Maps leer, so wird true wieder gegeben.
            {
                return true;
            }
            if(thisMap.keySet().equals(bMap.keySet()))  //Hier wird geprüft ob das this-keySet das "b"-keySet enthält, ist dies der Fall, wird true zurückgegeben
            {
                return true;
            }
        }
        return false;
    }
}
