package task4;
//import java.util.HashMap;
//import java.util.Map;

public class Veranstaltung {

    private String titel;
    private String dozent;
    private String sws;

    public Veranstaltung(String titel, String dozent, String sws) {
        this.titel = titel;
        this.dozent = dozent;
        this.sws = sws;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDozent() {
        return dozent;
    }

    public void setDozent(String dozent) {
        this.dozent = dozent;
    }

    public String getSws() {
        return sws;
    }

    public void setSws(String sws) {
        this.sws = sws;
    }

}
