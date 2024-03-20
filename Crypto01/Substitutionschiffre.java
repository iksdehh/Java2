package Crypto01;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Substitutionschiffre {
    List<String> chiffrat = new ArrayList<>();
    List<String> plainText = new ArrayList<>();

    public static LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();
    static Map<Character, Integer> englishLetterFrequencies = new HashMap<>();

    static {
        englishLetterFrequencies.put('A', 9170);
        englishLetterFrequencies.put('B', 2000);
        englishLetterFrequencies.put('C', 2780);
        englishLetterFrequencies.put('D', 4030);
        englishLetterFrequencies.put('E', 12702);
        englishLetterFrequencies.put('F', 2330);
        englishLetterFrequencies.put('G', 2310);
        englishLetterFrequencies.put('H', 5999);
        englishLetterFrequencies.put('I', 6333);
        englishLetterFrequencies.put('J', 153);
        englishLetterFrequencies.put('K', 770);
        englishLetterFrequencies.put('L', 4250);
        englishLetterFrequencies.put('M', 2410);
        englishLetterFrequencies.put('N', 6970);
        englishLetterFrequencies.put('O', 7510);
        englishLetterFrequencies.put('P', 2030);
        englishLetterFrequencies.put('Q', 100);
        englishLetterFrequencies.put('R', 6090);
        englishLetterFrequencies.put('S', 6750);
        englishLetterFrequencies.put('T', 8600);
        englishLetterFrequencies.put('U', 1490);
        englishLetterFrequencies.put('V', 980);
        englishLetterFrequencies.put('W', 1970);
        englishLetterFrequencies.put('X', 150);
        englishLetterFrequencies.put('Y', 2760);
        englishLetterFrequencies.put('Z', 101);

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



/*
    public void top() {
        Map<String, Integer> mapChiffrat = new HashMap<>();
        for (String s : chiffrat) {
            for (int i = 0; i < s.length(); i++) {
                String key = String.valueOf(s.charAt(i));
                if (key.equals(" ") || key.equals("") || key.equals(".") ||
                        key.equals(",") || key.equals("!") || key.equals("?")
                        || key.equals(":") || key.equals(";") || key.equals("'")
                        || key.equals("\"") || key.equals("(") || key.equals(")")
                        || key.equals("-") || key.equals("4") ) {
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
                .sorted(Map.Entry.<Character, Integer>comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

 */
public void top2() {
    Map<Character, Integer> mapChiffrat = new HashMap<>();
    for (String s : chiffrat) {
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            if (Character.isLetter(letter)) {
                letter = Character.toUpperCase(letter); // Wandelt Kleinbuchstaben in Großbuchstaben um
                mapChiffrat.put(letter, mapChiffrat.getOrDefault(letter, 0) + 1);
            }
        }
    }
    sortedMap = mapChiffrat.entrySet().stream()
            .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()) // Absteigend sortieren
            .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue,
                    LinkedHashMap::new
            ));
}

    /*
    public void substitute2() {
        for (String s : chiffrat) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < s.length(); j++) {
                    String key = String.valueOf(s.charAt(j));
                    if (key.equals(" ") || key.equals("") || key.equals(" .") ||
                            key.equals(",") || key.equals("!") || key.equals("?")
                            || key.equals(":") || key.equals(";") || key.equals("'")
                            || key.equals("\"") || key.equals("(") || key.equals(")") || key.equals("-")) {
                        sb.append(key);
                        continue;
                    }
                    else if(key.equals("X")){
                        sb.append("E");
                        continue;
                    } else if (key.equals("Y")) {
                        sb.append("T");
                        continue;
                    } else if (key.equals("Q")) {
                        sb.append("A");
                        continue;
                    } else if (key.equals("F")) {
                        sb.append("O");
                        continue;
                    } else if (key.equals("O")) {
                        sb.append("I");
                        continue;
                    } else if (key.equals("Z")) {
                        sb.append("N");
                        continue;
                    } else if (key.equals("T")) {
                        sb.append("S");
                        continue;
                    }
                    else {
                        sb.append(key);
                    }

                }
                plainText.add(sb.toString());
                saveState(plainText);
            }
    }

     */
    public void substitute() {
        Map<Character, Character> substitutionMap = new HashMap<>();

        // Erstelle eine Zuordnungstabelle basierend auf den relativen Häufigkeiten der Buchstaben
        int i = 0;
        for (Character ch : sortedMap.keySet()) {
            if (Character.isLetter(ch)) {
                Character englishLetter = (Character) englishLetterFrequencies.keySet().toArray()[i % 26];
                substitutionMap.put(ch, englishLetter);
                i++;
            }
        }
        for (Map.Entry<Character, Character> entry : substitutionMap.entrySet()) {
            System.out.println("Substitutionstabelle "+entry.getKey() + ": " + entry.getValue());
        }

        // Ersetze Buchstaben im Chiffretext
        for (String s : chiffrat) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < s.length(); j++) {
                char currentChar = s.charAt(j);
                if (Character.isLetter(currentChar)) {
                    char substituteChar = substitutionMap.getOrDefault(Character.toUpperCase(currentChar), currentChar);
                    // Bewahre die Groß-/Kleinschreibung bei
                    if (Character.isLowerCase(currentChar)) {
                        substituteChar = Character.toLowerCase(substituteChar);
                    }
                    sb.append(substituteChar);
                } else {
                    // Behalte Satzzeichen und Leerzeichen unverändert bei
                    sb.append(currentChar);
                }
            }
            plainText.add(sb.toString());
            saveState(plainText);
        }
    }


    public static void saveState(List<String> list){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:/Users/ReneW/IdeaProjects/Java2/Crypto01/decrypted.txt"))){
            for (String s : list) {
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

