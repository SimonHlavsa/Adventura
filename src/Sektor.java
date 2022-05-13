import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sektor {

    private String nazev;
    private String popis;
    private Set<Sektor> sousediciSektory;
    private List<Vec> seznamVeci;
    private List<Osoba> osoby;

    public Sektor(String nazev, String popis){
        this.nazev = nazev;
        this.popis = popis;
        sousediciSektory = new HashSet<>();
        seznamVeci =new ArrayList<>();
        osoby = new ArrayList<>();
    }

    public void setSousediciSektory(Sektor sousediciSektor){
        sousediciSektory.add(sousediciSektor);
    }

    public void setSeznamVeci(Vec vec) {
        seznamVeci.add(vec);
    }

    public void setOsoby(Osoba osoba) {
        osoby.add(osoba);
    }

    public boolean equals (Object o) {
        if (o instanceof Sektor) {
            Sektor druhy = (Sektor) o;
            return nazev.equals(druhy.nazev);
        }
        else {
            return false;
        }
    }

    public int hashCode(){
        return nazev.hashCode();
    }

    public String getNazev(){
        return nazev;
    }

    public String dlouhyPopis() {
        popis = "Jsi v mistnosti/prostoru " + popis + ".\n"
                + seznamSousedicichSektoru();
        if  (!seznamVeci.isEmpty()){
            popis += "\nVeci: " + seznamVeci();
        }
        if (!osoby.isEmpty()){
            popis += "\nOsoby: " + seznamOsob();
        }
        return popis;
    }

    private String seznamSousedicichSektoru(){
        String vychodyText = "Sousedici sektory:";
        for (Sektor sousediciSektor : sousediciSektory){
            vychodyText += " " + sousediciSektor.getNazev();
        }
        return vychodyText;
    }

    public Sektor sousedniSektor(String  jmenoSousedni){
        if (jmenoSousedni == null){
            return null;
        }
        for (Sektor sousedni : sousediciSektory){
            if (sousedni.getNazev().equals(jmenoSousedni)){
                return sousedni;
            }
        }
        return null;
    }

    public void vlozVec(Vec neco) {
        seznamVeci.add(neco);
    }
    public boolean obsahujeVec(String nazevVeci) {
        for ( Vec neco : seznamVeci ) {
            if (neco.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }
    public Vec vyberVec(String nazevVeci) {
        Vec vybranaVec = null;
        for ( Vec neco : seznamVeci ) {
            if (neco.getNazev().equals(nazevVeci)) {
                vybranaVec=neco;
            }
        }
        if (vybranaVec != null) {
            if (vybranaVec.jePrenositelna()) {
                seznamVeci.remove(vybranaVec);
            }
            else {
                vybranaVec=null;
            }
        }
        return vybranaVec;
    }

    private String seznamVeci() {
        String seznam = "";
        for ( Vec neco : seznamVeci ) {
            seznam = seznam + neco.getNazev()+" ";
        }
        return seznam;
    }


    private String seznamOsob() {
        String seznam = "";
        for ( Osoba neco : osoby ) {
            seznam = seznam + neco.getRole()+" ";
        }
        return seznam;
    }

    public List<Osoba> getOsoby() {
        return osoby;
    }
}


