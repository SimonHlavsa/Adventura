public class Prikaz {

    private String slovoPrikazu = null;
    private String druheSlovo = null;

    public Prikaz(String prvniSlovo, String druheSlovo){
        slovoPrikazu = prvniSlovo;
        this.druheSlovo = druheSlovo;
    }

    public Prikaz(String vstupniRadek){
        String[] slova = vstupniRadek.split("[ \t]");
        if (slova.length > 0) {
            slovoPrikazu = slova[0];
        }
        if (slova.length > 1){
            druheSlovo = slova[1];
        }
    }

    public String getSlovoPrikazu(){
        return slovoPrikazu;
    }

    public String getDruheSlovo(){
        return druheSlovo;
    }

    public boolean maDruheSlovo(){
        return (druheSlovo != null);
    }
}
