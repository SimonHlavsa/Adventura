import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 *
 * Třída sektor složí pro vytváření sektorů ve kterých se hráč pohybuje
 *
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */
public class Sektor {

    private final String nazev;
    private final String popis;

    /***
     * pro ukládání sousedních místností je použit set, aby místnost mohla sousedit s jinou pouze jednou
     */
    private final Set<Sektor> sousediciSektory;

    /***
     * může být jen jeden regál na jeden typ věcí
     */
    private final Set<Regal> regaly;

    /***
     * list osob, nemusíme dopředu dávat přesný počet
     */
    private final List<Osoba> osoby;


    public Sektor(String nazev, String popis){
        this.nazev = nazev;
        this.popis = popis;
        sousediciSektory = new HashSet<>();
        regaly = new HashSet<>();
        osoby = new ArrayList<>();
    }

    /***
     * vrací true nebo false, pokud se nazvy sektoru rovnají
     */
    public boolean equals (Object o) {
        if (o instanceof Sektor) {
            Sektor druhy = (Sektor) o;
            return nazev.equals(druhy.nazev);
        }
        else {
            return false;
        }
    }

    /***
     * dlouhý popis sektoru a co se v něm nachází
     */
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

    /***
     * overuje, zda spolu sektory sousedi
     */
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

    /***
     * vrací výpis sousedních sektoru
     */
    public String seznamSousedicichSektoru(){
        String vychodyText = "Sousedici sektory:";
        for (Sektor sousediciSektor : sousediciSektory){
            vychodyText += " " + sousediciSektor.getNazev();
        }
        return vychodyText;
    }

    /***
     * vypisuje věci v regálu
     */
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

    /***
     * vypisuje seznam regalu v sektoru
     */
    private String seznamRegalu(){
        String seznam = "";
        for (Regal regal : regaly){
            seznam += regal.getUrceni() + " ";
        }
        return seznam;
    }

    /***
     * vypisuje seznam osob v sektoru
     */
    private String seznamOsob() {
        String seznam = "";
        for ( Osoba neco : osoby ) {
            seznam += neco.getRole()  + " ";
        }
        return seznam;
    }

    /***
     * pokud je v sektoru regal, který obsahuje požadovanou věc, tak ji vráti, jinak vrací null
     */
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

    public String getNazev(){
        return nazev;
    }

    public Set<Regal> getRegaly() {
        return regaly;
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
}


