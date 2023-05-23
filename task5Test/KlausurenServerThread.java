package Java2.task5Test;


import java.io.*;
import java.net.Socket;
import java.util.*;

public class KlausurenServerThread extends Thread {



    private Socket clientSo;
    private int clientNr;

    public KlausurenServerThread(Socket clientSo, int clientNr, File anfragen) {
        this.clientSo = clientSo;
        this.clientNr = clientNr;
        if(anfragen.exists() && anfragen.length()!=0) {
            KlausurenServer.anmeldungen = loadState();
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
        if(request.equals("stop")) {
            try {
                System.out.println("1");
                clientSo.close();
                return "1";
            } catch (IOException e){
                e.printStackTrace();
                return "0";
            }
        }
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
            System.out.println("Test");
            this.clientSo.close();
            return "1";
        } catch (IOException e){
            e.printStackTrace();
            return "0";
        }
    }

    private String getAll() {

        List <List<Integer>> exams = new ArrayList<>();

        if (!KlausurenServer.anmeldungen.isEmpty()){
            for (Map.Entry<String, List<Integer>> entry : KlausurenServer.anmeldungen.entrySet()) {
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
            s = s.replaceFirst(",", "").replaceAll(" ", "");

            return "1 " + s;
        }else{
            return "0";
        }

    }

    private String del(String key) {
        List<List<Integer>> deleted = new ArrayList<>();

        if (KlausurenServer.anmeldungen.containsKey(key) && (!KlausurenServer.anmeldungen.get(key).equals(""))){
            deleted.add(KlausurenServer.anmeldungen.get(key));
            KlausurenServer.anmeldungen.remove(key);

            String str ="";
            for (List<Integer> integers : deleted) {
                str += integers;
            }
            str = str.replaceAll("\\[", "").replaceAll(" ", "").replaceAll("]", "");
            return "1 " + str;

        } else {
            return "0";
        }

    }

    private String get(String key) {

        if (KlausurenServer.anmeldungen.containsKey(key)) {
            List<Integer> list = new ArrayList<>();
            for (Integer value : KlausurenServer.anmeldungen.get(key)) {
                list.add(value);
            }
            Collections.sort(list);

            String str = "";
            for (Integer integer : list) {
                str += "," + integer;
            }


            str = str.replaceFirst(",","").replaceAll("\\[", "")
                    .replaceAll(" ", "").replaceAll("]", "");

            return "1 " + str;
        } else {
            return "0";
        }
    }

    private String put(String key, String newValue) {
        String oldValue ="";
        if(KlausurenServer.anmeldungen.containsKey(key)){
            oldValue = KlausurenServer.anmeldungen.get(key).toString();
        }

        List<Integer> list = new ArrayList<>();
        String[] klausuren = newValue.split(",");
        for (String s : klausuren) {
            list.add(Integer.parseInt(s.trim()));
        }

        KlausurenServer.anmeldungen.put(key, list);

        oldValue = oldValue.replaceAll("\\[", "").replaceAll(" ", "").replaceAll("]", "");

        return "1 " + oldValue;
    }

    private void saveState(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/renewioska/IdeaProjects/Java2/task5Test/Anfragen.txt", true))){
            for (Map.Entry<String, List<Integer>> entry : KlausurenServer.anmeldungen.entrySet()) {
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


       try (BufferedReader reader = new BufferedReader(new FileReader("/Users/renewioska/IdeaProjects/Java2/task5Test/Anfragen.txt"))){
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
               KlausurenServer.anmeldungen.put(key, list);
           }
           System.out.println("Daten erfolgreich eingelesen");
       } catch (IOException e) {
           System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
       }

        return KlausurenServer.anmeldungen;
   }
}

