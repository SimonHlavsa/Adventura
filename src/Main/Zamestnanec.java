package Main;

public class Zamestnanec extends Osoba{

    public Zamestnanec() {
        super.role = "zamestnanec";
    }

    @Override
    public String getHlaska() {
        return "Dobrý den, pokud už kofola není v regálu, zkuste se podívat do skladu.";
    }
}
