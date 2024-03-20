package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore;
import java.util.*;

public class Modulbeschreibungen
{


    public List <Modul> module;



    public Modulbeschreibungen(String filename) throws IOException
    {
        module = load(filename);
    }

    public  List<Modul> load (String filename) throws IOException
    {
        module = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] modulInfo = line.split("\\|");
                    Modul modul = new Modul(modulInfo[0], modulInfo[1], modulInfo[2], modulInfo[3], modulInfo[4], modulInfo[5], modulInfo[6], modulInfo[7]);
                    line = br.readLine();
                    while (line != null && !line.trim().isEmpty()) {
                        String[] veranstaltungInfo = line.split("\\|");
                        Veranstaltung veranstaltung = new Veranstaltung(veranstaltungInfo[0], veranstaltungInfo[1], veranstaltungInfo[2]);
                        modul.addVeranstaltung(veranstaltung);
                        line = br.readLine();
                    }
                    module.add(modul);
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            throw new TextFileFormatException("Liste fehlerhaft");
        }
        return module;
    }
    public Set<String> getZertifikate(String studiengang)
    {
        Set<String> zertifikate = new HashSet<>(); //Neues Set erstellen
        for (Modul modul : module) //durch Modulbeschreibung iterieren
        {
            String currentStudiengang = modul.getStudiengang(); //aktuellen Studiengang in current... speichern (BI, BTM,..)
            String art = modul.getArt();  //in Art steht Zertikikat drin

            if (currentStudiengang.equals(studiengang) && art.contains("Zertifikat")) //wenn beides übereinstimmt
            {
                if(art.contains("und Zertifikat")){
                    String s = art.split("Wahlpflichtmodul Zertifikat ")[1];
                    String y = s.split(" und ")[0];
                    String x = art.split(" und Zertifikat ")[1];
                    zertifikate.add(y);
                    zertifikate.add(x);
                } else if (art.contains("Zertifikat")){
                    String s = art.split(" Zertifikat ")[1];
                    zertifikate.add(s);
                }
            }
        }
        return zertifikate;
    }

    public Set<String> getVerzahnteModule(){
        Set<String> result = new HashSet<>();
        for (Modul modulA : module) {
            int count = 0;
            for (Modul modulB : module) {
                if (modulB.getModulbezeichnung().equals(modulA.getModulbezeichnung()) && modulB.getModulverantwortlicher().equals(modulA.getModulverantwortlicher())) {
                    count++;
                }
            }
            if (count > 1) {
                result.add(modulA.getModulbezeichnung());
            }
        }
        return result;
    }

    /*public Set<String> getVerzahnteModule ()
    {

        Map<String, String> module2 = new HashMap<>(); //neue map

        for(Modul modul : module)
        {
            String studKey = modul.getModulbezeichnung() + "; " + modul.getKuerzel().split("-")[0] + "; " + modul.getModulverantwortlicher(); //Erstellung des Key´s mit der Bezeichnung, des Kürzels bis zum - und des Verantwortlichen.

            String studiengang = modul.getStudiengang();
            System.out.println("studiengang: "+ studiengang);
            System.out.println(modul.getModulbezeichnung());
            if(module2.containsKey(studiengang))
            {

                module2.put(studiengang, module2.get(studiengang) +", "+  modul.getModulbezeichnung());
            }
            else
            {
                module2.put(studiengang, modul.getModulbezeichnung());
            }



        }
        List<String> bezeichnungen;
        for (Map.Entry <String, String> item: module2.entrySet())
        {
            System.out.println("entry set: "+ item);
            String x = item.getValue();
            bezeichnungen= new ArrayList<>();
            bezeichnungen.add(x.trim());
            System.out.println(bezeichnungen);
        }
        Set <String> verzahnt = new HashSet<>();


        return verzahnt;
    }

     */
    //liefert die Anzahl der Module eines Studiengangs. Wenn pflicht true ist, werden nur die Pflichtmodule (siehe Art) gezählt,
    // bei false nur die Wahlpflichtmodule, bei null alle Module.
    public int getAnzahlModule(String studiengang, Boolean pflicht)
    {
        int counter = 0;
        for (Modul modul : module) //durch Module iterieren
        {
            String art = modul.getArt();
            if (modul.getStudiengang().equals(studiengang)) {
                if (pflicht == null) {
                    counter++;
                } else if (pflicht && modul.getArt().contains("Pflichtmodul")) {
                    counter++;
                } else if ((!pflicht && modul.getArt().startsWith("WPM")) || (!pflicht && modul.getArt().contains("Wahlpflicht"))) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
        return counter;
    }

    public int getAnzahlVeranstaltungen (String studiengang, Boolean pflicht)
    {
        int counter = 0;
        for (Modul modul : module)
        {
            System.out.println("modul: "+modul.getModulbezeichnung());
            if (modul.getStudiengang().equals(studiengang)){
                if (pflicht == null) {
                    counter += modul.getVeranstaltung().size();
                    System.out.println("if null");
                } else if (pflicht && modul.getArt().contains("Pflichtmodul")) {
                    counter += modul.getVeranstaltung().size();
                    System.out.println("if true");
                } else if ((!pflicht && modul.getArt().startsWith("WPM")) || (!pflicht && modul.getArt().contains("Wahlpflicht"))) {
                    counter += modul.getVeranstaltung().size();
                    System.out.println("if false");
                }
            }
        }
        return counter;
    }

    public Map<Integer, Integer> getECTS (String studiengang)
    {

        Map<Integer, Integer> ECTs = new HashMap<>();
        for (Modul modul : module) {
            if (modul.getStudiengang().equals(studiengang) && modul.getArt().equals("Pflichtmodul")) {
                int key = Integer.parseInt(modul.getSemester());
                System.out.println("ETC´s Getter: " + modul.getEcts_punkte());
                String s = modul.getEcts_punkte().replace(',', '.');
                double ects = Double.parseDouble(s);
                int value = 0;
                if(!(ects % 10 <= 5 )){
                    value = (int) Math.ceil(ects);
                }else{
                     value = (int) ects;
                }
                System.out.println("value: " + value);

                if (value > 0) {
                    if (ECTs.containsKey(key)) {
                        ECTs.put(key, ECTs.get(key) + value);
                    } else {
                        ECTs.put(key, value);
                    }
                }
            }
        }
        for (Map.Entry <Integer, Integer> entry : ECTs.entrySet()){
            System.out.println(entry);
        }
        return ECTs;
    }

        public Map<Integer, Integer> getSWS (String studiengang)
        {
            int swsValue = 0;
            int[] swsPerSemester = new int[8];
            Map<Integer, Integer> sws = new HashMap<>();
            for (Modul modul : module) {
                if(modul.getStudiengang().equals(studiengang) && modul.getArt().equals("Pflichtmodul")) {
                    List<Veranstaltung> veranstaltungen = modul.getVeranstaltung();
                    for (Veranstaltung veranstaltung : veranstaltungen){
                        swsValue += Integer.parseInt( veranstaltung.getSws());
                    }
                    int key = Integer.parseInt(modul.getSemester());
                    if (swsValue > 0) {
                        swsPerSemester[key-1] += swsValue;
                        swsValue = 0;
                    }
                    if(sws.containsKey(key)){
                        sws.put(key, sws.get(key) + swsPerSemester[key-1]);
                    } else {
                        sws.put(key, swsPerSemester[key-1]);
                    }
                }

            }
            for (int i = 0; i < swsPerSemester.length; i++) {
                //System.out.println("Semester "+ (i+1) +": " + swsPerSemester[i]);
            }

            for (Map.Entry<Integer, Integer> entry : sws.entrySet()) {
                //System.out.println(entry);
            }
            return sws;
        }

        public List<String> getSortierteStudiengaenge ()
        {
            List<String> sorted = new ArrayList<>();
            Map <String, Integer> sort = new HashMap<>();
            Map <Integer, Integer> swsMap = new HashMap<>();
            int sum = 0;
            for (Modul modul : module) {
                String studiengang = modul.getStudiengang();
                swsMap = getSWS(studiengang);
                for (Map.Entry<Integer, Integer> entry : swsMap.entrySet()) {
                    System.out.println(modul.getModulbezeichnung());
                    sum += entry.getValue();
                    break;
                }
                if(sort.containsKey(studiengang)){
                    sort.put(studiengang, sort.get(studiengang)+ sum);
                }else {
                    sort.put(studiengang, sum);
                }

            }
            System.out.println(sum);
            for (Map.Entry<String, Integer> entry : sort.entrySet()) {
                System.out.println(entry);

            }
            List<Map.Entry<String, Integer>> list = new ArrayList<>(sort.entrySet());
            list.sort(Map.Entry.comparingByValue());
            System.out.println("Sortiert "+list);

            for(Map.Entry<String, Integer> entry : list){
                sorted.add(entry.getKey());
            }
            return sorted;
        }
        public String getJSON (String studiengang)
        {
            StringBuilder sb = new StringBuilder("[");
            for (Modul modul : module) {
                sb.append("{").append("\n");
                sb.append("\"bezeichnung\": \"").append(modul.getModulbezeichnung()).append("\",").append("\n");
                sb.append("\"kuerzel\": \"").append(modul.getKuerzel()).append("\",").append("\n");
                sb.append("\"studiengang\": \"").append(modul.getStudiengang()).append("\",").append("\n");
                sb.append("\"semester\": \"").append(modul.getSemester()).append("\",").append("\n");
                sb.append("\"art\": \"").append(modul.getArt()).append("\",").append("\n");
                sb.append("\"ects\": ").append(modul.getEcts_punkte()).append(",").append("\n");
                sb.append("\"pruefungsform\": \"").append(modul.getPruefungsform()).append("\",").append("\n");
                sb.append("\"verantwortlicher\": \"").append(modul.getModulverantwortlicher()).append("\",").append("\n");
                sb.append("\"veranstaltungen\": \"").append("[{").append("\n");
                List<Veranstaltung> veranstaltungen = modul.getVeranstaltung();
                for (int i = 0; i < veranstaltungen.size();i++){
                    Veranstaltung veranstaltung = veranstaltungen.get(i);

                    sb.append("\"titel\": \"").append(veranstaltung.getTitel()).append("\",").append("\n");
                    sb.append("\"dozenten\": \"").append(veranstaltung.getDozent()).append("\",").append("\n");
                    sb.append("\"sws\": ").append(veranstaltung.getSws()).append("\n");
                    if (i < veranstaltungen.size()-1){
                        sb.append("}"+","+"{ "+"\n");
                    }
                }
                sb.append("]}");
            }

            return sb.toString();
        }
    }
