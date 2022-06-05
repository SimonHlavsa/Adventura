package Main;

/***
 *
 * abstraktní třída pro ostatní osoby. Každá osoba má svojí roly a hlášku
 *
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */
public abstract class Osoba {

    String role;
    public String getRole(){
        return role;
    }

    public abstract String getHlaska();

}
