package Crypto01;
import java.util.*;

public class SubstitutionCipherMain {

    public static void main(String[] args) {
        SubstitutionCipher cipher = new SubstitutionCipher();


        String chiffrat = "UXMQO CMUXY TKFUX TXBC M.FUX BYECY HHXKP YVAZO FQCZI XYRYZ O.FUX GQZVX WYEYO OQMXK .ZF E YZK:" +
                            "\"WUYFK QMQDH XYOMQ D'GXO XGXTP XXOFQ YCBUY VXOFY DTZ?I QT UX YGXO' EEYAX HYOAZ OK,ZF 'EQOC MIQDT CZRUF MXYTE YWYMM QDAOQ W.Z'H" +
                " EQTT M,PDF ZIMQD VYO'F PXPQF UXTXK FQFYA XYOZO FXTXE FZOCQ VYC Y IIYZT EFUYF 'EMQD TQWOC QQAQD F.\"XO XTRZN XFUXK XHQCZ FZQOP XYHE. \" CZR " +
                "UFBQD TXKQD FZOFQ FUXUY FVUWY ME.\"Z KQO'F AOQW, \"EYZK FUXGQ ZVXQO FUXB Y,\"YB YFUXF ZVPCQ QKMBC YOXF. Z'GXO QEMHB YFUMY FYCC. \"ZFVD FQII." +
                " FUXT XWYEY FXTTZ PCXRU YEFCM EZCXO VX.FU XTXWY EYFXT TZPCX RUYEF CM OQ ZEX.F UXTXW YEYFX TTZPC XRUYE FCMEZ CXOVX .FUXG QRQOV QOEFT DVFQT ICXX FVQYE" +
                " FXKYW YMZOF QFUXZ OAMEF YTTMG QZK.V UYBFX T4IYT YWYMQ O FUX QBBQE ZFXEB ZTYCY THQIF UXRYC YJM,I ZGXUD OKTXK FUQDE YOKCZ RUF M XYTEI TQHFU XEFYT EQC,N" +
                " YBUQK PXXPC XPTQJ ,BTXE ZKXOF QIFUX ZHBXT ZYC R YCYVF ZVRQG XTOHX OF,EB XKYVT QEEFU XEXYE QIKYH QRTYO ,UZEZ QOKTZ GX KX CFYPQ YFWZO AZORY OKICY EUZOR ZOFUX KYHQR" +
                " TYOED O.KYH QRTYO FUXUQ F; KY HQRTY OFUXT XHQFX ;KYHQ RTYOF UXYCH QEFFQ FYCCM DOUXY TKQI. KYHQR TYO, EXVTX FUQHX QIFUX UXYTF QIRQC K.FUX PQYFE BXKQO YVTQE EFUXW YFXT." +
                " ZF WQ DCKPX EQHXF ZHXPX IQTXZ FTXYV UXKZF EKXEF ZOYFZ QOPXV YDEXK YHQRT YO ZE EDVUY OZOVQ OGXOZ XOFCM YTTYO RXKBC YOXF. ZFVQO EZEFE QIOQF UZORP DF HZ KKCZO RFQCY TRXKX" +
                " EXTFZ ECYOK EEXBY TYFXK PMGXT MBTXF FMPDF YOOQ MZORC MWZKX EFTXF VUXEQ IQVXY O.FUX PQYFE BXKQO .PXVY DEXQI FUZE FQBQC QRZVY CYWAW YTKOX EEKYH QRTYO UYEYC" +
                " WYMET XHYZO XKYKX EXTFX KBCYO XF. F UZEZE WUMFU XZHBX TZYCR YCYVF ZVRQG XTOHX OFVUQ EXKYH QRTYO IQTFU X UXY TFQIR QCKBT QLXVF ,PXVY DEXZF WYEEQ KXEXT FXKYO" +
                " KFUXU XYTFQ IRQCK WYEE QEXVT XF.FU XPQYF NZBBX KYOKE AZBBX KYVTQ EEFUX EXY,F UXEXY FUYF CYMPX FWXXO FUXHY ZOZEC YOKEQ IFUXQ OCMYT VUZBX CYRQQ IYOMD EXIDC EZNX" +
                " QOFUX WUQCX BCYOX F.NYB UQKPX XPCXP TQJWY EQOUZ EWYMI TQHFU X FZO MEBYV XBQTF QOXYE FXTZE CYOK( FUXOY HXWYE YOXOF ZTXCM HXYOZ ORCXE E VQZ OVZKX OVX-Z ORYCY VFZVE" +
                " BXAX, XYEFX THXYO EEHYC CICYF YOKCZ RUF P TQWO) FQFUX UXYTF QIRQC KZECY OK,WU ZVUPM YOQFU XTHXY OZORC XEE V QZOVZ KXOVX WYEVY CCXKI TYOVX .QOXQ IFUXE ZKXXI" +
                " IXVFE QIWQT AQOFU X UXY TFQI S";

        /*
        String chiffrat = "YZWVZ LXYZH TEOEO N YHY OP.NT UHOKX TUTYE EKYHH UEYXV PHNVN TUCZB IONTT OQTYE HKVIP NV XV YNTUT OQEUY KXDVG UKNTU XYLNT VZKVK" +
                " LV,YP EONIV ZXECK VBYBX DTYGU BUUP YHKUY NLYNO LFYWN OVPNV TUKNV JPVIN TYNOP YQOPZ NUYPE YTYXF VK LV TUIVZ XELZE EUPXD UGYCV KYNUO PNVYI" +
                " TOFFV FTDEK VHUP, VRVPU YPE W YKBVP QVPVA OEU.F XVIUG UK,IT UPNTU QVQUP NWYQU LTUIV ZXEBU NVVBZ LD UG YCVKY NOPHT UKLUX FNVPV NOWUO N.NTU" +
                " BYKQY PWXUY KUETO LNTKV YN.FO U TUY KETOQ LUXFL YD:\"X YLNVK EUKL, CXUYL U.\"NT UTZHU DUXXV IQYWT OPUL BUHYP NVLOP JEVIP IYKEY PENVQ VGUFY LNUK." +
            " FVKEJ PUINT UDIUK UNTUK U. NT OLIYL P'NNT UIYDT UTYEI YPNUE ON.KZ PPOPH ZCNTU XYPU, YKNTZ KTYE PUYKX DKUYW TUETO LTVZL U.FOU EOEP' NPVNO WUTVI" +
            " WVXEO NTYEL ZEEUP XD BU WVQU, TUEOE P'NPV NOWUN TUIOP E,TUE OEP'N PVNOW UNTUL ZEEUP OKKY NOVPY XLSZY XXVFK YOP.F OUEOE P'NPV NOWUY PDNTO PHBZN NTU" +
            " W YNUKC OXXYK BZXXE VRUKL WKYIX OPHVG UKNTU KZBBX UNTYN TYEBU UPTOL TVQU. \"DVZ BYKBY KOYPL !\"TUD UXXUE .\"O'X XLZUN TUWVZ PWOXF VKUGU KDCUP PDON'" +
                " L HVN !O'XX TYGUD VZ M";
         */

        Map<Character, Integer> absoluteFrequencyMap = SubstitutionCipher.calculateFrequency(chiffrat);
        Map<Character, Character> substitutionMap = SubstitutionCipher.createSubstitutionMap(absoluteFrequencyMap);

        System.out.println("\nSubstitutionsmap:");
        substitutionMap.forEach((key, value) -> System.out.println(key + " -> " + value));

        // Entschlüsselung
        String plaintext = cipher.decrypt(substitutionMap, chiffrat);

        // Ausgabe
        System.out.println("Chiffrat: " + chiffrat);
        System.out.println("Plaintext: " + plaintext);
    }
}

class SubstitutionCipher {

    public SubstitutionCipher() {
        Map<Character, Character> substitutionMap = new HashMap<>();
    }
public static Map<Character, Integer> calculateFrequency(String text) {

    int totalLetters = 0;

    // Zähle die absolute Häufigkeit jedes Buchstabens und die Gesamtanzahl der Buchstaben im Text
    Map<Character, Integer> absoluteFrequencyMap = new HashMap<>();
    for (char ch : text.toCharArray()) {
        if (Character.isLetter(ch)) {
            ch = Character.toUpperCase(ch); // Kleinbuchstaben in Großbuchstaben umwandeln
            absoluteFrequencyMap.put(ch, absoluteFrequencyMap.getOrDefault(ch, 0) + 1);
            totalLetters++;
        }
    }
    // Berechne die relativen Häufigkeiten
    Map<Character, Double> relativeFrequencyMap = new HashMap<>();
    for (Map.Entry<Character, Integer> entry : absoluteFrequencyMap.entrySet()) {
        double relativeFrequency = ((double) entry.getValue() / totalLetters)*100;
        relativeFrequencyMap.put(entry.getKey(), relativeFrequency);
    }

    // Sortiere die Einträge nach den absoluten Häufigkeiten
    List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(absoluteFrequencyMap.entrySet());
    sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

    // Gib die absolute und relative Häufigkeitsanalyse aus
    System.out.println("Absolute und relative Häufigkeitsanalyse:");
    for (Map.Entry<Character, Integer> entry : sortedEntries) {
        char letter = entry.getKey();
        int absoluteFrequency = entry.getValue();
        double relativeFrequency = relativeFrequencyMap.get(letter);
        System.out.println(letter + ": Absolute Häufigkeit: " + absoluteFrequency + ", Relative Häufigkeit: " + relativeFrequency);
    }

    return absoluteFrequencyMap;
}

    public static Map<Character, Character> createSubstitutionMap(Map<Character, Integer> frequencyMap) {
        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
        //sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<Character, Character> substitutionMap = new HashMap<>();
        // char[] mostFrequentLetters = {'E', 'A', 'T', 'O', 'I', 'N', 'S', 'H', 'R', 'D', 'L', 'C', 'U', 'M', 'W', 'F', 'G', 'Y', 'P', 'B', 'V', 'K', 'X', 'J', 'Q', 'Z'};
        char[] mostFrequentLetters = {'K', 'P', 'L', 'U', 'S', 'T', 'V', 'M', 'F', 'J', 'D', 'X', 'Y', 'Z', 'N', 'B', 'O', 'G', 'Q', 'R', 'H', 'C', 'W', 'E', 'A', 'I'};
        for (int i = 0; i < sortedEntries.size(); i++) {
            if (i < mostFrequentLetters.length) {
                substitutionMap.put(sortedEntries.get(i).getKey(), mostFrequentLetters[i]);
            } else {
                substitutionMap.put(sortedEntries.get(i).getKey(), '*'); // Fallback für weniger häufige Buchstaben
            }
        }
        return substitutionMap;
    }


    public String decrypt(Map<Character, Character> substitutionMap,String chiffrat) {
        StringBuilder plaintext = new StringBuilder();

        for (char ch : chiffrat.toCharArray()) {
            // Überprüfe, ob der Buchstabe in der Substitutionsmap vorhanden ist
            if (substitutionMap.containsKey(ch)) {
                // Füge den entschlüsselten Buchstaben zum Klartext hinzu
                plaintext.append(substitutionMap.get(ch));
            } else {
                // Füge Sonderzeichen oder Zahlen unverändert hinzu
                plaintext.append(ch);
            }
        }

        return plaintext.toString();
    }
}
