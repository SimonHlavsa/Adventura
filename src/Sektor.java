import java.util.HashSet;
import java.util.Set;

public class Sektor {

    private String nazev;
    private String popis;
    private Set<Sektor> sousediciSektory;

    public Sektor(String nazev, String popis){
        this.nazev = nazev;
        this.popis = popis;
        sousediciSektory = new HashSet<Sektor>();
    }

    public void setSousediciSektory(Sektor sousediciSektor){
        sousediciSektory.add(sousediciSektor);
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

    public String dlouhyPopis(){
        return "Právě se nacházíte na místě " + popis + "/n" + seznamSousedicichSektoru();
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

}


