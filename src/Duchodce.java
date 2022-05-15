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
