package Crypto01;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AffineCipher2Main {
    public static void main(String[] args) {

        // 23830633547633122464,28133176829053562195,28133176829053562195 Each chitext character, coded as an integer, is separated from the next character by a comma. The modulus is 30418211649913743925. When decoding, you first receive numbers in the range 0-255, the integer representation of Extended-ASCII, and the characters must be converted accordingly
        String chitext = "23830633547633122464,28133176829053562195,28133176829053562195,16559591851942167643,25239780584775713557,12407252910507060816,10998105873364469723,10998105873364469723,6395153911973364184,6395153911973364184,6395153911973364184,8104709629086621085,12181946400529061460,13666195607664319005,608361423417666908,28133176829053562195,608361423417666908,6470256081966030636,15150444814799576550,8104709629086621085,9363652326243879274,13666195607664319005,7879403119108621729,10998105873364469723,6395153911973364184,3576859837688181998,28133176829053562195,9363652326243879274,23830633547633122464,26874234131896304006,3501757667695515546,21087441643340606730,16559591851942167643,16784898361920166999,25389984924761046461,16784898361920166999,3651962007680848450,8029607459093954633,16784898361920166999,10923003703371803271,16559591851942167643,15150444814799576550,2242814970538257357,";
        String[] chitextArray = chitext.split(",");
        List<BigInteger> chitextIntegers = new ArrayList<>();
        for (String s : chitextArray) {
            chitextIntegers.add(new BigInteger(s));
        }
        System.out.println("Chitext: " + chitext);
        System.out.println("Plaintext: " + AffineCipher2.decrypt(chitextIntegers, new BigInteger("30418211649913743925")));

    }
}

class AffineCipher2{
    public static String decrypt(List<BigInteger> chitextIntegers, BigInteger modulus) {
        BigInteger aInverse = new BigInteger("28133176829053562195").modInverse(modulus);
        if (aInverse == null) {
            throw new IllegalArgumentException("Inverse does not exist");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chitextIntegers.size(); i++) {
            BigInteger ch = chitextIntegers.get(i);
            BigInteger decrypted = ch.multiply(aInverse).mod(modulus);
            result.append((char) decrypted.intValue());
        }
        return result.toString();
    }
}
