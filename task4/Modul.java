package Java2.task4;

import java.util.ArrayList;
import java.util.List;

public class Modul {

    private String modulbezeichnung;
    private String kuerzel;
    private String studiengang;



    private String semester; //kann aber auch WPM sein (Whalpflichtmodul)
    private String art;
    private String ects_punkte;
    private String pruefungsform;
    private String modulverantwortlicher;

    private List<Veranstaltung> veranstaltungen;

    public Modul(String modulbezeichnung, String kuerzel, String studiengang, String semester, String art, String ects_punkte, String pruefungsform, String modulverantwortlicher) {
        this.modulbezeichnung = modulbezeichnung;
        this.kuerzel = kuerzel;
        this.studiengang = studiengang;
        this.semester = semester;
        this.art = art;
        this.ects_punkte = ects_punkte;
        this.pruefungsform = pruefungsform;
        this.modulverantwortlicher = modulverantwortlicher;
        this.veranstaltungen = new ArrayList<>();
    }

    public List<Veranstaltung> getVeranstaltung()
    {
        return veranstaltungen;
    }
    public void addVeranstaltung(Veranstaltung veranstaltung)
    {
        veranstaltungen.add(veranstaltung);
    }
    public String getModulbezeichnung() {
        return modulbezeichnung;
    }

    public void setModulbezeichnung(String modulbezeichnung) {
        this.modulbezeichnung = modulbezeichnung;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    public String getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getEcts_punkte() {
        return ects_punkte;
    }

    public void setEcts_punkte(String ects_punkte) {
        this.ects_punkte = ects_punkte;
    }

    public String getPruefungsform() {
        return pruefungsform;
    }

    public void setPruefungsform(String pruefungsform) {
        this.pruefungsform = pruefungsform;
    }

    public String getModulverantwortlicher() {
        return modulverantwortlicher;
    }

    public void setModulverantwortlicher(String modulverantwortlicher) {
        this.modulverantwortlicher = modulverantwortlicher;
    }
}
