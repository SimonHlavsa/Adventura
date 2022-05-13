public class Vec {
    private String nazevVeci;
    private boolean jePrenositelna;

    public Vec(String nazevVeci, boolean jePrenositelna) {
        this.nazevVeci = nazevVeci;
        this.jePrenositelna = jePrenositelna;
    }

    public String getNazev() {
        return nazevVeci;
    }

    public boolean jePrenositelna() {
        return jePrenositelna;
    }


}
