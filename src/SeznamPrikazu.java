import java.util.Set;
import java.util.TreeSet;

/***
 *
 * zde jsou uloženy všechny příkazy, které může hráč zadat (každý příkaz musí být originální)
 *
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */

public class SeznamPrikazu {

    private final Set<String> platnePrikazy;

    /***
     * příkazy
     */
    public SeznamPrikazu(){
        platnePrikazy = new TreeSet<>();
        platnePrikazy.add("jdi");
        platnePrikazy.add("konec");
        platnePrikazy.add("napoveda");
        platnePrikazy.add("mluv");
        platnePrikazy.add("okradni");
        platnePrikazy.add("prohledej");
        platnePrikazy.add("seber");
        platnePrikazy.add("inventar");
        platnePrikazy.add("poloz");
        platnePrikazy.add("uplat");
    }

    /***
     * kontoluje, zda je zadaný příkaz platný
     */
    public boolean jePlatnyPrikaz(String retezec){
        return platnePrikazy.contains(retezec);
    }

    /***
     * vrací seznam platných příkazů
     */
    public String vratSeznamPrikazu(){
        String seznam = "";
        for (String slovoPrikazu : platnePrikazy){
            seznam += slovoPrikazu + " ";
        }
        return seznam;
    }
}
