package Java2.task5Test;


import java.io.*;
import java.net.Socket;
import java.util.*;

public class KlausurenServerThread extends Thread {
    public Map<String, List<Integer>> anmeldungen = new HashMap<>();


    private Socket clientSo;
    private int clientNr;

    public KlausurenServerThread(Socket clientSo, int clientNr, File anfragen) {
        this.clientSo = clientSo;
        this.clientNr = clientNr;
        if(anfragen.exists() && anfragen.length()!=0) {
            anmeldungen = loadState();
        }
    }

    public void run() {
        try {
            BufferedReader einSo = new BufferedReader(new InputStreamReader(clientSo.getInputStream()));
            PrintWriter ausSo = new PrintWriter(clientSo.getOutputStream(), true);
            String zeile;
            while ((zeile = einSo.readLine()) != null) {
                //ausSo.println("Client "+clientNr+": "+zeile);
                //ausSo.println("0");
                String answer = anfragenVerabeiten(zeile);
                ausSo.println(answer);
            }
            einSo.close();
            ausSo.close();
            this.saveState();
            clientSo.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("Client "+clientNr+" beendet");
        }
    }

    private String anfragenVerabeiten(String inputString) {
        String answer = "";
        String request = inputString.split(" ")[0].toLowerCase(); //inputString wird am ersten Leerzeichen gesplittet und der vordere Teil in request in Kleinbuchstaben gespeichert
        String[] args = inputString.split(" "); //args enthält alle weiteren Informationen

        String s = "";
        for (int i = 2; i < args.length; i++) {
            s += args[i];
        }
        if (request.equals("put")) {
           answer = put(args[1], s);
        } else if (request.equals("get")) {
            answer = get(args[1]);
        } else if (request.equals("del")) {
            answer = del(args[1]);
        } else if (request.equals("getall")) {
            answer = getAll();
        } else if (request.equals("stop")) {
            answer = stopServer();
        }
        return answer;
    }


    private String stopServer()  {

        try {
            clientSo.close();
            return "1";
        } catch (IOException e){
            e.printStackTrace();
            return "0";
        }
    }

    private String getAll() {

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
                s += ","+ integers ;
            }
            s = s.replaceFirst(",", "");

            return "1 " + s.trim();
        }else{
            return "0";
        }

    }

    private String del(String key) {
        List<List<Integer>> deleted = new ArrayList<>();

        if (anmeldungen.containsKey(key) && (!anmeldungen.get(key).equals(""))){
            deleted.add(anmeldungen.get(key));
            anmeldungen.remove(key);
            System.out.println("1" + deleted);
            return "1" + deleted;

        } else {
            return "0";
        }

    }

    private String get(String key) {

        if (anmeldungen.containsKey(key)) {
            List<Integer> list = new ArrayList<>();
            for (Integer value : anmeldungen.get(key)) {
                list.add(value);
            }
            Collections.sort(list);
            return "1 " + list;
        } else {
            return "0";
        }
    }

    private String put(String key, String newValue) {
        String oldValue ="";
        if(anmeldungen.containsKey(key)){
            oldValue = anmeldungen.get(key).toString();
        }

        List<Integer> list = new ArrayList<>();
        String[] klausuren = newValue.split(",");
        for (String s : klausuren) {
            list.add(Integer.parseInt(s.trim()));
        }


        anmeldungen.put(key, list);
        System.out.println("1" + oldValue);
        return "1" + oldValue;
    }

    private void saveState(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\ReneW\\IdeaProjects\\Practise\\src\\Java2\\task5Test\\Anfragen.txt", true))){
            for (Map.Entry<String, List<Integer>> entry : anmeldungen.entrySet()) {
                String line = entry.getKey() + ": " + entry.getValue();
                writer.write(line);
                writer.newLine(); //Zeilenumbruch für die nächste Zeile
                writer.flush();

            }

        } catch (IOException e ){
            System.out.println("Fehler beim Schreiben der Datei " + e.getMessage());
        }
    }

   private Map<String, List<Integer>> loadState() {


       try (BufferedReader reader = new BufferedReader(new FileReader("C:/Users/ReneW/IdeaProjects/Practise/src/Java2/task5Test/Anfragen.txt"))){
           String line;
           while ((line = reader.readLine()) != null) {
               System.out.println("load Line: " + line);
               String[] parts = line.split(":");
               String key = parts[0];

               String[] x = parts[1].replace("[", "").replace("]", "").split(",");

               List<Integer> list = new ArrayList<>();
               for (String s : x) {
                   list.add(Integer.parseInt(s.trim()));
               }
               anmeldungen.put(key, list);
           }
           System.out.println("Daten erfolgreich eingelesen");
       } catch (IOException e) {
           System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
       }

        return anmeldungen;
   }
}

