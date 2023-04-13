package task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vorlesung
{
    public List<List<String>> load (String filename) throws IOException
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
}
