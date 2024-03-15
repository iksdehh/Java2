package Crypto01;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Substitutionschiffre {
    List<String> chiffrat = new ArrayList<>();
    List<String> plainText = new ArrayList<>();

    static LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
    static Map<Character, Integer> englishLetterFrequencies = new HashMap<>();

    static {
        englishLetterFrequencies.put('a', 8170);
        englishLetterFrequencies.put('b', 1490);
        englishLetterFrequencies.put('c', 2780);
        englishLetterFrequencies.put('d', 4250);
        englishLetterFrequencies.put('e', 12702);
        englishLetterFrequencies.put('f', 2230);
        englishLetterFrequencies.put('g', 2020);
        englishLetterFrequencies.put('h', 6090);
        englishLetterFrequencies.put('i', 6970);
        englishLetterFrequencies.put('j', 153);
        englishLetterFrequencies.put('k', 770);
        englishLetterFrequencies.put('l', 4030);
        englishLetterFrequencies.put('m', 2410);
        englishLetterFrequencies.put('n', 6750);
        englishLetterFrequencies.put('o', 7510);
        englishLetterFrequencies.put('p', 1930);
        englishLetterFrequencies.put('q', 100);
        englishLetterFrequencies.put('r', 5999);
        englishLetterFrequencies.put('s', 6333);
        englishLetterFrequencies.put('t', 9600);
        englishLetterFrequencies.put('u', 2760);
        englishLetterFrequencies.put('v', 980);
        englishLetterFrequencies.put('w', 2360);
        englishLetterFrequencies.put('x', 150);
        englishLetterFrequencies.put('y', 1970);
        englishLetterFrequencies.put('z', 74);

    englishLetterFrequencies = englishLetterFrequencies.entrySet().stream()
            .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue,
                    LinkedHashMap::new));
}

    public List<String> load (String filename) throws IOException {
        List<String> loaded = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.trim().isEmpty()) {
                    loaded.add(line);
                }
            }
        } catch (IOException | ArrayIndexOutOfBoundsException exception) {
            System.out.println("Fehler beim Lesen der Datei" + exception.getMessage());
        }
        return loaded;
    }

    public void top() {
        Map<String, Integer> mapChiffrat = new HashMap<>();
        for (String s : chiffrat) {
            for (int i = 0; i < s.length(); i++) {
                String key = String.valueOf(s.charAt(i));
                if (key.equals(" ") || key.equals("") ||
                        key.equals(",") || key.equals("!") || key.equals("?")
                        || key.equals(":") || key.equals(";") || key.equals("'")
                        || key.equals("\"") || key.equals("(") || key.equals(")") || key.equals("-") ) {
                    continue;
                }
                if (mapChiffrat.containsKey(key)) {
                    mapChiffrat.put(key, mapChiffrat.getOrDefault(key, 0) + 1);
                } else {
                    mapChiffrat.put(key, 1);
                }
            }
        }
        sortedMap = mapChiffrat.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }
    public void substitute() {

        Map<String, String> map = new HashMap<>();
        int i = 0;
        for (String s : sortedMap.keySet()) {
            map.put(s, String.valueOf(englishLetterFrequencies.keySet().toArray()[i]));
            i++;
        }
        for (String s : chiffrat) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < s.length(); j++) {
                String key = String.valueOf(s.charAt(j));
                if (key.equals(" ") || key.equals("") ||
                        key.equals(",") || key.equals("!") || key.equals("?")
                        || key.equals(":") || key.equals(";") || key.equals("'")
                        || key.equals("\"") || key.equals("(") || key.equals(")") || key.equals("-") ) {
                    sb.append(key);
                    continue;
                }
                sb.append(map.get(key));
            }
            plainText.add(sb.toString());
            saveState(plainText);
        }
    }

    public static void saveState(List<String> list){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:/Users/ReneW/IdeaProjects/Java2/src/decrypted.txt"))){
            for (String s : list) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

