import java.util.ArrayList;
import java.util.List;

/***
 *
 * regal slouží k ukládání věcí v sektorech
 *
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */
public class Regal {

    private final String urceni;
    private List<Vec> seznamVeci;

    public Regal(String urceni){
        this.urceni = urceni;
        seznamVeci =new ArrayList<>();
    }

    /***
     * metoda, která vrací true nebo false, pokud se požadovaná věc nachází v regálu
     */
    public boolean obsahujeVec(String nazevVeci) {
        for ( Vec neco : seznamVeci ) {
            if (neco.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }

    /***
     * vrací požadovanou věc, pokud se nachází v regálu. Věc následně smaže z regálu
     */
    public Vec vyberVec(String nazevVeci) {
        Vec vybranaVec;
        for ( Vec vec : seznamVeci ) {
            if (vec.getNazev().equals(nazevVeci)) {
                vybranaVec=vec;
                seznamVeci.remove(vec);
                return vybranaVec;
            }
        }
        return null;
    }

    /***
     * vrací seznam věcí, co se nachází v regálu
     */
    public String seznamVeci() {
        String seznam = "";
        for ( Vec neco : seznamVeci ) {
            seznam += neco.getNazev() + " " ;
        }
        return seznam;
    }

    /***
     * přidávání věcí
     */
    public void vlozVec(Vec vec) {
        seznamVeci.add(vec);
    }

    public String getUrceni() {
        return urceni;
    }
}
