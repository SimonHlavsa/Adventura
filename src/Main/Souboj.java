/***
 *     v této tříde se inicializují sektory, věci, osoby a regály
 *     také jsou zde metody k příkazům
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 5.6.2022
 */
package Main;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/***
 *
 * V této třídě probíhá souboj s zaměstnancem Ochranky
 *
 */
public class Souboj {

    private int tvojeZivoty = 100;
    private int zamestnanecOchrankyZivoty = 100;
    private boolean maOchrankaEnergii = true;
    private boolean masTyEnergii = true;
    private boolean konecSouboje = false;

    /***
     *while cyklus, kde probíhá souboj
     */
    public boolean prubehSouboje(){
        boolean vyhralJsi = false;
        System.out.println(zacatekSouboje());
        while (!konecSouboje) {
            String radek = prectiString();
            Prikaz prikaz = new Prikaz(radek);
            System.out.println(souboj(prikaz));
            zivoty();
            vyhralJsi = jeKonec();
        }
        return vyhralJsi;
    }


    /***
     *vyhodnocuje input a následně podle toho provede akci
     */
    private String souboj(Prikaz prikaz){

        if (!(prikaz.getSlovoPrikazu().equals("utok") || prikaz.getSlovoPrikazu().equals("obrana") || prikaz.getSlovoPrikazu().equals("cekej")))
            return "Zadal jsi chybnou akci";

        if (prikaz.getSlovoPrikazu().equals("utok") && !masTyEnergii){
            return "na útok nemáš energii";
        }
        String tyAkce = prikaz.getSlovoPrikazu();
        String ochrankaAkce;

        do {
            ochrankaAkce = ochrankaAkce();
        } while (ochrankaAkce.equals("utok") && !maOchrankaEnergii);

        if (ochrankaAkce.equals(tyAkce)){
            if (ochrankaAkce.equals("utok")){
                maOchrankaEnergii = false;
                masTyEnergii = false;
                return "oba jste minuli";
            }
            if (ochrankaAkce.equals("cekej")){
                masTyEnergii = true;
                maOchrankaEnergii = true;
                return "oba odpočíváte";
            }

            else{
                return  "ani jeden nezaůtočil";
            }
        }

        else if (ochrankaAkce.equals("utok") && tyAkce.equals("obrana")){
            maOchrankaEnergii = false;
            return "podařilo se ti ubránit";
        }

        else if (tyAkce.equals("utok") && ochrankaAkce.equals("obrana")){
            masTyEnergii = false;
            return "Zaměstnanec ochranky se ubránil";
        }

        else if (ochrankaAkce.equals("utok") && tyAkce.equals("cekej")){
            maOchrankaEnergii = false;
            masTyEnergii = true;
            tvojeZivoty -= 20;
            return "dostal jsi zásah!";
        }

        else if (tyAkce.equals("utok") && ochrankaAkce.equals("cekej")){
            masTyEnergii = false;
            maOchrankaEnergii = true;
            zamestnanecOchrankyZivoty -= 20;
            return "zasáhl jsi ho!";
        }

        else if (tyAkce.equals("cekej") && ochrankaAkce.equals("obrana")){
            masTyEnergii = true;
            return "odpočíváš a soupeř se brání";
        }

        else if (tyAkce.equals("obrana") && ochrankaAkce.equals("cekej")){
            maOchrankaEnergii = true;
            return "ty se bráníš, ale soupeř odpočívá";
        }

        return "chyba";

    }

    /***
     * Náhodně vygeneruje číslo, a podle toho pak vráti jakou akci provede ochranka
     *
     */
    private String ochrankaAkce(){
        int min = 1;
        int max = 3;
        int akce = (int)Math.floor(Math.random()*(max-min+1)+min);
        if (akce == 1){
            return "utok";
        }
        else if(akce == 2){
            return "obrana";
        }
        else {
            return "cekej";
        }
    }


    /***
     * vypisuje životy
     */
    public void zivoty(){
        System.out.println();
        System.out.println("tvoje životy: " + tvojeZivoty);
        System.out.println("žávoty zaměstnance ochranky: " + zamestnanecOchrankyZivoty);
        System.out.println();
    }


    /***
     *kontola jestli je někdo na 0 životech, pokud ano, konec souboje
     */
    public boolean jeKonec(){
        if (zamestnanecOchrankyZivoty == 0){
            konecSouboje = true;
            return true;
        }
        if (tvojeZivoty == 0){
            konecSouboje = true;
        }
        return false;
    }


    /***
     *Úvodní informace při začátku souboje
     */
    private String zacatekSouboje(){
        return "Cestou jsi narazil na pěkně naštvaného zaměstnance ochranky, kterému bylo nahlášeno, že bylo okradeno pár důchodců.\n" +
                "Můžeš zadat tyto akce: utok obrana cekej";


    }


    /***
     *Pomocí BufferedReaderu získává input od hráče
     */
    private String prectiString(){
        String vstupniRadek = "";
        System.out.println("> ");
        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));
        try {
            vstupniRadek = vstup.readLine();
        } catch (java.io.IOException exc){
            System.out.println("Vyskytla se chyba během čtení příkazu:" + exc.getMessage());
        }
        return vstupniRadek;
    }
}