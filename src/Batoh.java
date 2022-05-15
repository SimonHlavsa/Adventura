import java.lang.reflect.Array;

public class Batoh {

    private Vec[] batoh;

    private int penize = 0;
    public Batoh(){
        batoh = new Vec[5];
    }

    public String pridatVec(Vec vecKPridani){
        boolean jePlny = true;
        for (int num = 0; num < batoh.length; num++){
            if (batoh[num] == null){
                batoh[num] = vecKPridani;
                jePlny = false;
                break;
            }
        }
        if (jePlny){
            return "Batoh je plný, nelze do něho nic přidat";
        }
        else {
            return "Věc byla sebrána a přidána do batohu";
        }
    }

    public Vec odebratVec(Prikaz prikaz){
        for (int num = 0; num < batoh.length; num++){
            if (batoh[num] == null){
                continue;
            }
            if (batoh[num].getNazev().equals(prikaz.getDruheSlovo())){
                Vec vecKVraceni = batoh[num];
                batoh[num] = null;
                return vecKVraceni;
            }
        }
        return null;
    }

    public String  inventar(){
        String veci = "Peníze: " + penize + "\n";
        boolean jePrazdny = true;
        for (Vec vec : batoh){
            if (vec != null){
                veci += vec.getNazev() + " ";
                jePrazdny = false;
            }
        }
        if (jePrazdny){

            return veci + "V batohu nejsou žádné předměty.";
        }
        else {
            return veci;
        }
    }

    public boolean obsahuje(String nazevVeci){
        for (Vec vec : batoh){
            if (vec.getNazev().equals(nazevVeci)){
                return true;
            }
        }
        return false;
    }

    public boolean jePrazdny(){
        return batoh.length == 0;
    }

    public int getPenize() {
        return penize;
    }

    public void pridejPenize(int penize) {
        this.penize += penize;
    }



}
