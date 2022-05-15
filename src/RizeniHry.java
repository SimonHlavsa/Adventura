import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 *
 * tato třída řídí hru
 *
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */
public class RizeniHry {

    private Hra hra;

    /***
     * vytváří instanci Třídy hra
     */
    public RizeniHry(){
        hra = new Hra();
    }

    /***
     * while loop, který běží tak dlouho, dokud hráč hru neukončí, nebo nedohraje
     */
    public void hraj(){
        System.out.println(hra.vratUvitani());
        while (!hra.konecHry()){
            String radek = prectiString();
            Prikaz prikaz = new Prikaz(radek);
            System.out.println(hra.zpracujPrikaz(prikaz));
        }
    }

    /***
     * získává input od uživatele
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
