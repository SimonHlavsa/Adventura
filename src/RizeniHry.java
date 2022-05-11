import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RizeniHry {

    private Hra hra;

    public RizeniHry(){
        hra = new Hra();
    }

    public void hraj(){
        System.out.println(hra.vratUvitani());
        while (!hra.konecHry()){
            String radek = preectiString();
            Prikaz prikaz = new Prikaz(radek);
            System.out.println(hra.zpracujPrikaz(prikaz));
        }
    }

    private String preectiString(){
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
