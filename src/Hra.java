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
        Sektor ovoce = new Sektor("ovoce", "s ovocem");
        Sektor pecivo = new Sektor("pecivo", "s pecivem");
        Sektor maso =new Sektor("maso", "s masem");
        Sektor sklad = new Sektor("sklad", "Sklad potravin");
        Sektor napoje = new Sektor("napoje", "s nápoji");
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
        sklad.setSousediciSektory(maso);
        napoje.setSousediciSektory(maso);
        napoje.setSousediciSektory(pokladny);
        pokladny.setSousediciSektory(napoje);

        aktualniSektor =vstup;


        Duchodce duchodce = new Duchodce();
        Zamestnanec zamestnanec = new Zamestnanec();
        Skladnik skladnik = new Skladnik();

        ovoce.setOsoby(duchodce);
        maso.setOsoby(zamestnanec);
        sklad.setOsoby(skladnik);

        Regal regalNaOvoce = new Regal("ovoce");
        Regal regalNaKlobasy = new Regal("klobasy");
        Regal regalNaSunky = new Regal("sunky");
        Regal regalNaAlkohol = new Regal("alkohol");
        Regal regalNaNealko = new Regal("nealko");
        Regal regalNaPecivo = new Regal("pecivo");

        ovoce.setRegaly(regalNaOvoce);
        maso.setRegaly(regalNaKlobasy);
        maso.setRegaly(regalNaSunky);
        napoje.setRegaly(regalNaAlkohol);
        napoje.setRegaly(regalNaNealko);
        pecivo.setRegaly(regalNaPecivo);

        Vec jablko = new Vec("jablko", true);
        Vec hruska = new Vec("hruska", true);

        regalNaOvoce.setSeznamVeci(jablko);
        regalNaOvoce.setSeznamVeci(hruska);

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
            else if (povel.equals("mluv")){
                textKVypsani = mluv(prikaz);
            }
            else if (povel.equals("prohledej")){
                textKVypsani = prohledej(prikaz);
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
        return "Jsi v obchodě a snažíš se koupit kofolu\n" +
                "\n" +
                "Můžeš zadat tyto příkazy:\n" +
                platnePrikazy.vratSeznamPrikazu() +
                "\n" +
                aktualniSektor.dlouhyPopis();

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
            if (aktualniSektor.getNazev().equals("napoje")){
                return aktualniSektor.dlouhyPopis() +
                        "\nV regálu, kde by měla být kofola vidíš, že je vyprodaná";
            }
            return aktualniSektor.dlouhyPopis();
        }
    }

    private String mluv(Prikaz prikaz){
        if (!prikaz.maDruheSlovo()){
            return  "S kým mám mluvit? Musíš zadat nazev osoby.";
        }
        if (aktualniSektor.getOsoby().size() == 0){
            return "V tomto sektoru není žádná osoba";
        }
        String osobaKRozhovoru = prikaz.getDruheSlovo();
        for (Osoba osoba : aktualniSektor.getOsoby()){
            if (osoba.getRole().equals(osobaKRozhovoru)){
                 return osoba.getHlaska();
            }
        }
        return "Požadovaná osoba nebyla nalezena";

    }

    private String prohledej(Prikaz prikaz){
        if (!prikaz.maDruheSlovo()){
            return "Nevím do jakého regálu se podívat";
        }
        if (aktualniSektor.getRegaly().isEmpty()){
            return "V tomto sektoru není žádný regál";
        }
        return aktualniSektor.prohledejRegal(prikaz.getDruheSlovo());
    }
}
