package Main;

/***
 *
 * Třída skladník, rozšiřuje třídu osoba
 *
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */
public class Skladnik extends Osoba {

    public Skladnik() {
        super.role = "skladnik";
    }

    @Override
    public String getHlaska() {
        return "Já tuhle práci tak nesnáším, každý pořád jen otravuje. Jestli chceš kofolu, tak tě to bude něco stát. \n"
                + "Dones skladníkovi nějaký úplatek";
    }
}
