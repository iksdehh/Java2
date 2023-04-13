package Java2;

import java.util.Arrays;
public class GrosseZahl
{
    public int[] arr;
    public static void main(String[] args)
    {
        GrosseZahl a = new GrosseZahl(55);
        GrosseZahl b = new GrosseZahl("60");
        System.out.println(a.mult(b));
        a.mult(b);
    }
    public GrosseZahl(String d)
    {
        arr = new int[d.length()]; //Erstellt ein Array der länge des Strings d.
        for (int i = 0; i < d.length(); i++)
        {
            arr[i] = Character.getNumericValue(d.charAt(i)); //gibt den numerischen Wert an der i-ten Stelle des Strings d zurück & speichert diesen in arr an der Stelle i.
        }
    }

    private int[] GetZahlVonString(String d) //Hilfsmethode für GrosseZahl(int i)
    {
        int[] arr = new int[d.length()]; //Erstellt ein Array der länge des Strings d.
        for (int i = 0; i < d.length(); i++)
        {
            arr[i] = Character.getNumericValue(d.charAt(i)); //gibt den numerischen Wert an der i-ten Stelle des Strings d zurück & speichert diesen in arr an der Stelle i.
        }
        return arr;
    }

    public GrosseZahl(int i)
    {
        String x = String.valueOf(i); //wandelt den übergebenen Parameter i in einen String um
        arr = GetZahlVonString(x);
    }
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (int j : arr) {
            sb.append(j);
        }
        return sb.toString();
    }
    public boolean less(GrosseZahl b) //prüft welcher der beiden Objekt-Arrays kleiner ist oder ob sie gleich groß sind
    {
        if (arr.length < b.arr.length) //länge wird verglichen
        {
            return true;
        }
        else if (arr.length > b.arr.length) //länge wird verglichen
        {
            return false;
        }
        else
        {
            for (int i = 0; i < arr.length; i++)
            {
                if (arr[i] < b.arr[i]) //vergleich den wert an der ersten Stelle der Arrays um zu schauen welches größer ist.
                {
                    return true;
                } else if (arr[i] > b.arr[i])
                {
                    return false;
                }
            }
            return false;
        }
    }

    public GrosseZahl sub(GrosseZahl b)
    {

        if (b.arr.length < this.arr.length)
        {
            String zero = "0".repeat(this.arr.length-b.arr.length); //es werden Nullen an den Anfang des Strings zero gesetzt, für die größe der Längendifferenz.
            b = new GrosseZahl(zero + b.toString()); //neues Objekt, welches die Nullen im String Zero mit dem "b" Objekt verbindet.
        }
        else if (b.arr.length > this.arr.length)
        {
            return (b.sub(this)); //Hier wird die Methode andersrum aufgerufen.
        }

        int rest = 0;
        String diff ="";

        for (int i=arr.length-1; i >= 0; i--) //die beiden, nun gleich langen Arrays werden von hinten durchlaufen
        {

            if (arr[i] - b.arr[i] - rest < 0)   //wenn ein negativer Wert herauskommt
            {
                diff = (arr[i]-rest -b.arr[i]+10) + diff; //wird hier +10 gerechnet und der Übertrag bzw rest auf 1 gesetzt
                rest = 1;
            }
            else
            {
                diff = (arr[i]-rest -b.arr[i]) + diff; //standard subtraktion ohne übertrag bzw rest
                rest = 0;
            }

        }
        diff = diff.replaceAll("^0*", ""); //alle führenden Nullen werden entfernt.
        return new GrosseZahl(diff);
    }



    public GrosseZahl add(GrosseZahl b)
    {
        if(b.toString().equals("0")) //wenn b 0 ist, wird keine Addition durchgeführt und das this-Objekt zurückgegeben.
        {
            return this;
        }
        else if(this.toString().equals("0"))  //wenn this 0 ist, wird keine Addition durchgeführt und das b-Objekt zurückgegeben.
        {
            return b;
        }
        nullErgaenzen(b); //Sind die Arrays unterschiedlich lang, so werden vorne Nullen ergänzt, bis diese gleich lang sind


        int[] c = new int[b.arr.length + 1]; //Es wird ein neues Array erstellt, das genau um 1 länger ist, als die beiden anderen Arrays.
        int rest = 0;

        for (int i = c.length - 2; i >= 0; i--) //durchlaufen des neuen c-Arrays, von hinten nach vorne
        {
            if (arr[i] + b.arr[i] + rest > 9) //sollten das Ergebnis der addition der Ziffern an der i-ten Stelle der Arrays und des Restes größer 9 sein.
            {
                c[i + 1] = arr[i] + b.arr[i] + rest - 10; //dann wird an der letzten Stelle des c-Arrays dieser Wert-10 gespeichert, i+1 aufgrund der länge des neuen Arrays (um eins größer)
                rest = 1; //da in diesem Fall ein Wert größer 9 entstand, muss der rest auf 1 gesetzt werden

            }
            else
            {
                c[i + 1] = arr[i] + b.arr[i] + rest;
                rest = 0;
            }
        }
        if (rest == 1) //ist nach der Addition der rest noch vorhanden, wird er an vorderster stelle des Arrays gesetzt.
        {
            c[0] = rest;
        }

        String s = "";
        for (int i = 0; i < c.length; i++) //das neu enstandene Array wird in ein String s gespeichert
        {
            s = s + c[i];
        }

        s = s.replaceAll("^0*", ""); //"^0*" sorgt dafür, das alle führenden Nullen entfernt werden (^für Anfang des Strings; 0*für Nullen)

        b = new GrosseZahl(b.toString().replaceAll("^0*", "")); //neues objekt für b erstellen (011->11)

        String t = this.toString().replaceAll("^0*", ""); //this objekt wird in einen String t gespeichert um anschließend ein neues Array daraus zu entwickeln
        arr = new int[t.length()];
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = Character.getNumericValue(t.charAt(i)); //weil sonst ascii zeichen nimmt
        }
        return new GrosseZahl(s);
    }
    public GrosseZahl ggT(GrosseZahl b)
    {
        boolean equal = true; //erstmal true, falls beide identisch sind, ist der ggT gefunden.
        if (this.arr.length != b.arr.length) //Ist die länger der Arrays unterschiedlich? => sind nicht gleich
        {
            equal = false;
        }
        else    //Arrays sind gleich lang, aber nicht der gleiche Wert
        {
            for (int i = 0; i < this.arr.length; i++)
            {
                if (this.arr[i] != b.arr[i]) //Erste Stelle des Arrays wird verglichen und geprüft ob diese gleich sind
                {
                    equal = false;
                    break; //Nach der Überprüfung des ersten Wertes, wird diese Schleife sofort unterbrochen
                }
            }
        }
        if (!equal) //solange die beiden Arrays nicht identisch sind
        {
            if (this.less(b))   //a kleiner b
            {
                return this.ggT(b.sub(this));   //ggT erneut mit der Differenz von b - a Aufrufen (b.sub(this))
            }
            else    //b kleiner a
            {
                return b.ggT(this.sub(b)); // ggT erneut aufrufen mit der Different von a - b (this.sub(b))
            }
        }
        System.out.println(b.toString() + " <- ist der ggT");
        return b;
    }

    public void nullErgaenzen(GrosseZahl b) //Hilfsmethode für add
    {
        if (arr.length < b.arr.length) //prüft welches der beiden Array länger ist
        {
            int x = b.arr.length - arr.length; //erstellt einen int mit dem Wert des Unterschieds der länge
            int[] arr2 = new int[arr.length + x]; //erstellt ein neues Array der Länge des alten + der Differenz x

            for (int i= 0; i < x; i++) //durchläuft das neue Array für den Wert der Differenz und fügt an den jeweilligen Stellen 0 hinzu.
            {
                arr2[i] = 0;
            }

            for (int i=0; i<arr.length;i++) //befüllt die restlichen Plätze mit den Werten des alten Arrays
            {
                arr2[i+x] = arr[i]; //+x damit die Nullen nicht überschrieben werden.
            }
            arr = arr2; //überschreibt arr mit dem neu entstanden Array

        }
        else if (arr.length > b.arr.length) //selbiges wie oben nur, dass das b Array größer ist
        {
            int x = arr.length - b.arr.length;
            int[] b2 = new int[b.arr.length + x];

            for (int i= 0; i <= x; i++)
            {
                b2[i] = 0;
            }

            for (int i=0; i < b.arr.length;i++)
            {
                b2[i+x] = b.arr[i];
            }
            b.arr = b2;
        }
    }

    public GrosseZahl mult(GrosseZahl x)
    {
        GrosseZahl g = new GrosseZahl(0);
        for (int i = 0; i < x.arr.length; i++) //durchläuft die länge des übergeben Arrays des Parameters.
        {
            GrosseZahl k = new GrosseZahl(0); //Startwert der vorherigen For-schleife
            GrosseZahl z = x.getPow(i); //endwert der vorherigen For-Schleife + Aufruf der getPow-Methode
            while(k.less(z))    //Abbruchbedingung der Schleife
            {
                k = k.add(new GrosseZahl(1));   //hier wird die "laufvariable" immer um ein erhöht      for (int k = 0; k < z; k++)
                g = g.add(new GrosseZahl(this.toString())); //toString, da sonst das this-Objekt verändert wird & ein falscher Wert gespeichert wird.
            }
        }
        return g;
    }

    public GrosseZahl getPow(int zahl)
    {
        String s = "";
        s = s + this.arr[zahl]; //Hier wird der erste Wert, der zu multiplizierenden Zahl im String s gespeichert bspw. 1234 -> 1 wird im String gespeichert
        for (int i = zahl; i < this.arr.length - 1; i++)   //von der Stelle Zahl des Arrays wird der Rest des Arrays durchlaufen
        {
            s = s + 0; //und Nullen angehängt. bspw. 1234 -> 1 wurde gespeichert und wird zu 1000.
        }
        return new GrosseZahl(s);
    }

}
