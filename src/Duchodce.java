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
        super.role = "duchodce";
        super.hlaska = "Na tohle mi důchod fakt nestačí";
        penize = 200;
    }

    public int okradni(){
        int penizeKVraceni = penize;
        penize = 0;
        return penizeKVraceni;
    }

}
