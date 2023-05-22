package Java2;

import java.io.*;
import java.util.*;

public class Try {
    private  Map<String, List<Integer>> anmeldungen = new HashMap<>();
    private static Map<String, List<Integer>> anmeldungen22 = new HashMap<>();
    public static void main(String[] args) {
      /*  Try test = new Try();
        test.anfragenVerabeitung("put mail1 22,24");
        test.anfragenVerabeitung("put mail2 22,23,24");
        test.anfragenVerabeitung("put mail3 2");

        for (Map.Entry<String, List<Integer>> stringListEntry : test.anmeldungen.entrySet()) {
            System.out.println(stringListEntry);
        }

        test.saveState("C:\\Users\\ReneW\\IdeaProjects\\Practise\\src\\Java2\\task5Test\\Anfragen.txt");

        loadState();
        for (Map.Entry<String, List<Integer>> stringListEntry : anmeldungen22.entrySet()) {
            System.out.println("geloadet brother: "+stringListEntry);
        }

       */

        List<String> stringList = new ArrayList<>();
        stringList.add("42");
        stringList.add("455");
        stringList.add("363");



        System.out.println(stringList);
        for (String s : stringList) {
            System.out.println(s);
        }
    }



    private static Map<String, List<Integer>> loadState() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\ReneW\\IdeaProjects\\Practise\\src\\Java2\\task5Test\\Anfragen.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String key = parts[0];

                String[] x = parts[1].replace("[", "").replace("]", "").split(",");

                List<Integer> list = new ArrayList<>();
                for (String s : x) {
                    list.add(Integer.parseInt(s.trim()));
                }
                anmeldungen22.put(key, list);
            }
            System.out.println("Daten erfolgreich eingelesen");
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }

        return anmeldungen22;
    }


    private void saveState(String pfad){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pfad))){
            for (Map.Entry<String, List<Integer>> entry : anmeldungen.entrySet()) {
                String line = entry.getKey() + ": " + entry.getValue();
                writer.write(line);
                writer.flush();
                writer.newLine(); //Zeilenumbruch für die nächste Zeile
            }

        } catch (IOException e ){
            System.out.println("Fehler beim Schreiben der Datei " + e.getMessage());
        }
    }





    private void anfragenVerabeitung(String inputString) {
        String request = inputString.split(" ")[0].toLowerCase(); //inputString wird am ersten Leerzeichen gesplittet und der vordere Teil in request in Kleinbuchstaben gespeichert
        String[] args = inputString.split(" "); //args enthält alle weiteren Informationen

        String s="";
        for (int i = 2; i < args.length; i++) {

            s += args[i];
        }


        if(request.equals("put")){
            put(args[1], s);

        } else if (request.equals("get")) {
            //get(args[1]);
        } else if (request.equals("del")) {
            del(args[1]);
        } else if (request.equals("getall")) {
            getAll();
        } else if (request.equals("stop")) {
            //stopServer();
        }

    }

    private void del(String key) {
        List<List<Integer>> deleted = new ArrayList<>();

        if (anmeldungen.containsKey(key) && (!anmeldungen.get(key).equals(""))){
            deleted.add(anmeldungen.get(key));
            anmeldungen.remove(key);
            System.out.println("1");
            System.out.println(deleted);
        } else {
            System.out.println("0");
        }
    }

    private void get(String key) {
        if (anmeldungen.containsKey(key)) {
            List<Integer> list = new ArrayList<>();
            for (Integer value : anmeldungen.get(key)) {
                list.add(value);
            }
            Collections.sort(list);
            System.out.println(list);

        } else {
            System.out.println("get Methode");
            System.out.println("0");
        }
    }

    private  void put(String key, String newValue) {
        String oldValue ="";
        if(anmeldungen.containsKey(key)){
            oldValue = anmeldungen.get(key).toString();
        }

        List<Integer> list = new ArrayList<>();
        String[] klausuren = newValue.split(",");
        if(klausuren.length >= 1) {
            for (String s : klausuren) {
                list.add(Integer.parseInt(s.trim()));
            }
        }

        anmeldungen.put(key, list);
        System.out.println("1" );
        System.out.println(oldValue);
    }

    private void getAll() {
        List <List<Integer>> exams = new ArrayList<>();
        if (!anmeldungen.isEmpty()){
            for (Map.Entry<String, List<Integer>> entry : anmeldungen.entrySet()) {
                exams.add(entry.getValue());
            }
            List<List<Integer>> filteredList = new ArrayList<>();
            for (List<Integer> currentList : exams) {
                boolean isSubList = false;

                for (List<Integer> otherList : exams) {
                    if (!currentList.equals(otherList) && otherList.containsAll(currentList)) {
                        isSubList = true;
                        break;
                    }
                }

                if (!isSubList) {
                    filteredList.add(currentList);
                }
            }
            String s = "";
            for (List<Integer> integers : filteredList) {
                s += integers;
            }
            // 1 [[2],[22,23,24]]
            System.out.println("1 " + s);
        }else{
            System.out.println("0");
        }

    }


}
