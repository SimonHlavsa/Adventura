package Main;

/***
 *
 * batoh slouží jako inventář, kam si hráč ukládá věci a peníze
 *
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */
public class Batoh {
    private final Vec[] batoh;
    private int penize = 0;
    public Batoh(){
        batoh = new Vec[5];
    }

    /***
     * přidá věc do batohu, pokud není plný
     */
    public String pridatVec(Vec vecKPridani){
        boolean jePlny = true;
        for (int num = 0; num < batoh.length; num++){
            if (batoh[num] == null){
                batoh[num] = vecKPridani;
                jePlny = false;
                break;
            }
        }
        if (jePlny){
            return "Main.Batoh je plný, nelze do něho nic přidat";
        }
        else {
            return "Věc byla sebrána a přidána do batohu";
        }
    }

    /***
     * vrátí věc, kterou chceme z batohu odebrat, pokud je prázdný, vrací null
     */
    public Vec odebratVec(Prikaz prikaz){
        for (int num = 0; num < batoh.length; num++){
            if (batoh[num] == null){
                continue;
            }
            if (batoh[num].getNazev().equals(prikaz.getDruheSlovo())){
                Vec vecKVraceni = batoh[num];
                batoh[num] = null;
                return vecKVraceni;
            }
        }
        return null;
    }

    /***
     * vypisuje obsah batohu, vpřípadě, že je prázdný, oznámí to
     */
    public String  inventar(){
        StringBuilder veci = new StringBuilder("Peníze: " + penize + "\n");
        boolean jePrazdny = true;
        for (Vec vec : batoh){
            if (vec != null){
                veci.append(vec.getNazev()).append(" ");
                jePrazdny = false;
            }
        }
        if (jePrazdny){

            return veci + "V batohu nejsou žádné předměty.";
        }
        else {
            return veci.toString();
        }
    }

    /***
     * kontroluje, zda požadovaná věc je v batohu
     */
    public boolean obsahuje(String nazevVeci){
        if (jePrazdny())
            return false;
        for (Vec vec : batoh){
            if (vec == null){
                continue;
            }
            if (vec.getNazev().equals(nazevVeci)){
                return true;
            }
        }
        return false;
    }

    public boolean jePrazdny(){
        boolean vrat = true;
        for (Vec vec : batoh){
            if (vec != null) {
                vrat = false;
                break;
            }
        }
        return vrat;
    }

    public int getPenize() {
        return penize;
    }

    public void pridejPenize(int penize) {
        this.penize += penize;
    }

    public void setBatoh(String nazevVeci) {
        for (int num = 0; num < batoh.length; num++){
            if (batoh[num] == null){
                continue;
            }
            if (batoh[num].getNazev().equals(nazevVeci)){
                batoh[num] = null;
            }
        }
    }
}
