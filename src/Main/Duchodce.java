package Main;

/***
 *
 * důchodce je zákazník, který má navíc u sebe uloženo, kolik má peněz a dá se okradnout
 *
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */
public class Duchodce extends Osoba {
    private int penize;

    public Duchodce(){
        penize = 200;
        super.role = "duchodce";
    }

    public int okradni(){
        int penizeKVraceni = penize;
        penize = 0;
        return penizeKVraceni;
    }

    @Override
    public String getHlaska() {
        return "Na tohle mi důchod fakt nestačí";
    }

}
