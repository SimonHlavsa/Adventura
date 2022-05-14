import java.util.ArrayList;
import java.util.List;

public class Regal {

    private String urceni;
    private List<Vec> seznamVeci;

    public Regal(String urceni){
        this.urceni = urceni;
        seznamVeci =new ArrayList<>();
    }

    public void setSeznamVeci(Vec vec) {
        seznamVeci.add(vec);
    }

    public void vlozVec(Vec neco) {
        seznamVeci.add(neco);
    }
    public boolean obsahujeVec(String nazevVeci) {
        for ( Vec neco : seznamVeci ) {
            if (neco.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }
    public Vec vyberVec(String nazevVeci) {
        Vec vybranaVec = null;
        for ( Vec neco : seznamVeci ) {
            if (neco.getNazev().equals(nazevVeci)) {
                vybranaVec=neco;
            }
        }
        if (vybranaVec != null) {
            if (vybranaVec.jePrenositelna()) {
                seznamVeci.remove(vybranaVec);
            }
            else {
                vybranaVec=null;
            }
        }
        return vybranaVec;
    }

    public String seznamVeci() {
        String seznam = "";
        for ( Vec neco : seznamVeci ) {
            seznam += " " + neco.getNazev() ;
        }
        return seznam;
    }

    public String getUrceni() {
        return urceni;
    }
}
