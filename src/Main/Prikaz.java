package Main;

/***
 *
 * zde se vezme hráčův input a rozdělí na jednotlivá slova
 *
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */
public class Prikaz {

    private String slovoPrikazu = null;
    private String druheSlovo = null;
    private String tretiSlovo = null;


    /***
     * zde se dělí a ukládá input
     */
    public Prikaz(String vstupniRadek){
        String[] slova = vstupniRadek.split("[ \t]");
        if (slova.length > 0) {
            slovoPrikazu = slova[0];
        }
        if (slova.length > 1){
            druheSlovo = slova[1];
        }
        if (slova.length > 2){
            tretiSlovo = slova[2];
        }
    }

    public String getSlovoPrikazu(){
        return slovoPrikazu;
    }

    public String getDruheSlovo(){
        return druheSlovo;
    }
    public String getTretiSlovo(){
        return tretiSlovo;
    }

    public boolean maDruheSlovo(){
        return (druheSlovo != null);
    }

    public boolean maTretiSlovo(){
        return (tretiSlovo != null);
    }


}
