package task3;

import task3.Vorlesung;

import java.io.*;
import java.util.*;

public class Vorlesungsverzeichnis
{
    Set <Vorlesung> lectures = new HashSet<>();
    public static void main(String[] args)
    {
        Vorlesungsverzeichnis a = new Vorlesungsverzeichnis("/Users/renewioska/IdeaProjects/Datentask3/Vorlesungsdaten.txt");
    }

    public Vorlesungsverzeichnis(String s)
    {
        Scanner a =null;
        try
        {
            a = new Scanner(new File(s));
            System.out.println(a);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Du idiot - die Datei gibs net");
        }
        try {
            load(a.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<List<String>> load (String filename) throws IOException
    {
            List<List<String>> result = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(filename));
            for (String line = br.readLine(); line != null; line = br.readLine())
            {
                result.add(Arrays.asList(line.split(":")));
            }
            br.close();
            return result;
    }



    public List<String> titles()
    {
        return new ArrayList<>();
    }

    public Set<String> workaholics()
    {

        Set<String> HashSet = null;
        return HashSet;
    }

    public Map<String, List<String>> multipleTitles()
    {
        Map <String, List<String>> x = new HashMap<>();
        return x;
    }

    public List<String> descendingTitles()
    {
        return new ArrayList<>();
    }
}
