public class Hra {

    private SeznamPrikazu platnePrikazy;
    private Sektor aktualniSektor;
    private boolean konecHry = false;

    public Hra(){
        vytvorSektor();
        platnePrikazy = new SeznamPrikazu();
    }

    private void vytvorSektor(){
        Sektor vstup = new Sektor("vstup", "Vstup do obchodu");
        Sektor ovoce = new Sektor("ovoce", "Sektor s ovocem");
        Sektor pecivo = new Sektor("pecivo", "Sektor s pecivem");
        Sektor maso =new Sektor("maso", "Sektor s masem");
        Sektor sklad = new Sektor("sklad", "Sklad potravin");
        Sektor napoje = new Sektor("napoje", "Sektor s nápoji");
        Sektor pokladny = new Sektor("pokladny", "Pokladny");

        vstup.setSousediciSektory(ovoce);
        ovoce.setSousediciSektory(vstup);
        ovoce.setSousediciSektory(pecivo);
        ovoce.setSousediciSektory(napoje);
        pecivo.setSousediciSektory(ovoce);
        pecivo.setSousediciSektory(maso);
        maso.setSousediciSektory(pecivo);
        maso.setSousediciSektory(sklad);
        maso.setSousediciSektory(napoje);
        napoje.setSousediciSektory(maso);
        napoje.setSousediciSektory(pokladny);
        pokladny.setSousediciSektory(napoje);

        aktualniSektor =vstup;
    }

    public String vratUvitani(){
        return "Vítejte v obchode, vaším úkolem je koupit si kofolu.\n" +
                "Pokud nebudete vědět, jak dál, napište 'napoveda'\n" +
                "\n" +
                aktualniSektor.dlouhyPopis();
    }

    public String vratEpilog(){
        return "KONEC \n" +
                "Dík, že jste hru dohráli";
    }

    public boolean konecHry() {
        return konecHry;
    }

    public String zpracujPrikaz(Prikaz prikaz){
        String textKVypsani = "";
        if (platnePrikazy.jePlatnyPrikaz(prikaz.getSlovoPrikazu())){
            String povel = prikaz.getSlovoPrikazu();
            if (povel.equals("napoveda")){
                textKVypsani = napoveda();
            }
            else if (povel.equals("jdi")){
                textKVypsani = jdi(prikaz);
            }
            else if (povel.equals("konec")){
                textKVypsani = konec(prikaz);
            }
        }
        else  {
            textKVypsani = "Nevim co tim myslis, tento prikaz neznam?";
        }
        return textKVypsani;
    }

    private String konec(Prikaz prikaz){
        if (prikaz.maDruheSlovo()){
            return "Ukoncit co? Nechapu, proc jste zadal druhe slovo.";
        }
        else {
            konecHry = true;
            return "hra ukončena příkazem konec";
        }
    }

    private String napoveda(){
        return "Jak ses mohl ztratit v obchodě...\n" +
                "\n" +
                "Muzes zadat tyto prikazy:\n" +
                platnePrikazy.vratSeznamPrikazu();
    }

    private String jdi(Prikaz prikaz){
        if (!prikaz.maDruheSlovo()){
            return  "Kam mám jít? Musíš zadat jméno sektoru";
        }
        String smer = prikaz.getDruheSlovo();
        Sektor sousedniSektor = aktualniSektor.sousedniSektor(smer);
        if (sousedniSektor == null){
            return "Tam se odsud jit neda!";
        }
        else {
            aktualniSektor = sousedniSektor;
            return aktualniSektor.dlouhyPopis();
        }
    }
}
