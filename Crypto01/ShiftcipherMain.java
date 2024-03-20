package Crypto01;

public class ShiftcipherMain {
    public static void main(String[] args) {
        String text = "SEEGFLSYNGJVWESTWFVWKKWF";
        int s = 8;
        System.out.println("Text  : " + text);
        System.out.println("Shift : " + s);
        String chiffrat = Shiftcipher.encrypt(text, s);
        System.out.println();
        String chiffrat2 = Shiftcipher.encrypt(chiffrat, s);
        System.out.println("Chiffrat: " + chiffrat);
        System.out.println("Plaintext: " + Shiftcipher.decrypt(chiffrat, s));
    }
}
class Shiftcipher {
    public static String encrypt(String text, int s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) + s - 65) % 26 + 65);
                result.append(ch);
            } else {
                char ch = (char) (((int) text.charAt(i) + s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int s) {
        return encrypt(text, 26 - s);
    }
}
