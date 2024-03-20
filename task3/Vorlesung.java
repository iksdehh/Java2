package task3;

import java.util.List;

public class Vorlesung
{
   public String gruppe;
   public String modul;
   public String prof;
   public String anzahl;

    public Vorlesung(List<String> list)
    {
        gruppe = list.get(0);
        modul = list.get(1);
        prof=list.get(2);
        anzahl= list.get(3);
    }
}
