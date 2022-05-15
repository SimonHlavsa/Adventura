import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sektor {

    private String nazev;
    private String popis;
    private Set<Sektor> sousediciSektory;

    private Set<Regal> regaly;

    private List<Osoba> osoby;

    public Sektor(String nazev, String popis){
        this.nazev = nazev;
        this.popis = popis;
        sousediciSektory = new HashSet<>();
        regaly = new HashSet<>();
        osoby = new ArrayList<>();
    }

    public void setSousediciSektory(Sektor sousediciSektor){
        sousediciSektory.add(sousediciSektor);
    }

    public void setRegaly(Regal regal){
        regaly.add(regal);
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
    public String dlouhyPopis() {
        String dlouhyPopis;
        dlouhyPopis = "Jsi v sektoru " + popis + ".\n"
                + seznamSousedicichSektoru();
        if  (!regaly.isEmpty()){
            dlouhyPopis += "\nRegaly: " + seznamRegalu();
        }
        if (!osoby.isEmpty()){
            dlouhyPopis += "\nOsoby: " + seznamOsob();
        }
        return dlouhyPopis;
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

    public String seznamSousedicichSektoru(){
        String vychodyText = "Sousedici sektory:";
        for (Sektor sousediciSektor : sousediciSektory){
            vychodyText += " " + sousediciSektor.getNazev();
        }
        return vychodyText;
    }

    public String prohledejRegal(String pozadvanyRegal){
        for (Regal regal : regaly){
            if (regal.getUrceni().equals(pozadvanyRegal)){
                if (regal.getUrceni().equals("nealko")){
                    return regal.seznamVeci() +
                            "\nVidíš, že v regálu není kofola";
                }
                return regal.seznamVeci();
            }
        }
        return "Požadovaný regál zde není";
    }

    private String seznamRegalu(){
        String seznam = "";
        for (Regal regal : regaly){
            seznam += regal.getUrceni() + " ";
        }
        return seznam;
    }


    private String seznamOsob() {
        String seznam = "";
        for ( Osoba neco : osoby ) {
            seznam += neco.getRole()  + " ";
        }
        return seznam;
    }

    public Vec seberVec(Prikaz prikaz){
        Vec vybranaVec = null;
        for (Regal regal : regaly){
            if (regal.obsahujeVec(prikaz.getDruheSlovo())){
                vybranaVec = regal.vyberVec(prikaz.getDruheSlovo());
                break;
            }
        }
        return vybranaVec;
    }


    public List<Osoba> getOsoby() {
        return osoby;
    }

    public int hashCode(){
        return nazev.hashCode();
    }

    public String getNazev(){
        return nazev;
    }

    public Set<Regal> getRegaly() {
        return regaly;
    }


}


