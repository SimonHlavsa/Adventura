public class Hra {

    private SeznamPrikazu platnePrikazy;
    private Sektor aktualniSektor;
    private boolean konecHry = false;
    Batoh batoh;

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


        Duchodce duchodce1 = new Duchodce();
        Duchodce duchodce2 = new Duchodce();
        Duchodce duchodce3 = new Duchodce();
        Duchodce duchodce4 = new Duchodce();

        Zamestnanec zamestnanec = new Zamestnanec();
        Skladnik skladnik = new Skladnik();

        ovoce.setOsoby(duchodce1);
        maso.setOsoby(duchodce2);
        napoje.setOsoby(duchodce3);
        pecivo.setOsoby(duchodce4);
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

        Vec jablko = new Vec("jablko");
        Vec hruska = new Vec("hruska");
        Vec pivo = new Vec("pivo");
        Vec vodka = new Vec("vodka");
        Vec dzus = new Vec("dzus");
        Vec cocacola = new Vec("cocacola");
        Vec malinovka = new Vec("malinovka");
        Vec chleba = new Vec("chleba");
        Vec houska = new Vec("houska");
        Vec kureci = new Vec("kureci");
        Vec veprova = new Vec("veprova");
        Vec grilovaci = new Vec("grilovaci");


        regalNaOvoce.setSeznamVeci(jablko);
        regalNaOvoce.setSeznamVeci(hruska);
        regalNaNealko.setSeznamVeci(dzus);
        regalNaNealko.setSeznamVeci(malinovka);
        regalNaNealko.setSeznamVeci(cocacola);
        regalNaAlkohol.setSeznamVeci(pivo);
        regalNaAlkohol.setSeznamVeci(vodka);
        regalNaPecivo.setSeznamVeci(houska);
        regalNaPecivo.setSeznamVeci(chleba);
        regalNaKlobasy.setSeznamVeci(grilovaci);
        regalNaSunky.setSeznamVeci(kureci);
        regalNaSunky.setSeznamVeci(veprova);

        batoh = new Batoh();

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
            else if (povel.equals("seber")){
                textKVypsani = seber(prikaz);
            }
            else if (povel.equals("inventar")){
                textKVypsani = inventar(prikaz);
            }
            else if (povel.equals("poloz")){
                textKVypsani = poloz(prikaz);
            }
            else if (povel.equals("okradni")){
                textKVypsani = okradni(prikaz);
            }
            else if (povel.equals("uplat")){
                textKVypsani = uplat(prikaz);
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
        aktualniSektor = sousedniSektor;
        if (aktualniSektor.getNazev().equals("pokladny") &&
                batoh.obsahuje("kofola")){
            konecHry = true;
            return vratEpilog();
        }
        return aktualniSektor.dlouhyPopis();
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

    private String seber(Prikaz prikaz){
        Vec vecKSebrani;
        if (!prikaz.maDruheSlovo()){
            return "Nevím, jaký předmět sebrat.";
        }
        if (aktualniSektor.getRegaly().isEmpty()){
            return "V tomto sektoru není žádný regál se zbožím";
        }
        vecKSebrani = aktualniSektor.seberVec(prikaz);
        if (vecKSebrani == null){
            return "požadovaná věc se v tomto sektoru nenachází";
        }
        else {
            return batoh.pridatVec(vecKSebrani);
        }
    }

    private String inventar(Prikaz prikaz){
        return batoh.inventar();
    }

    private String poloz(Prikaz prikaz){
        if (!prikaz.maDruheSlovo()){
            return "Nevím, jaký předmět položit.";
        }
        if (!prikaz.maTretiSlovo()){
            return "Nevím, kam předmět položit.";
        }
        if (batoh.jePrazdny()){
            return "Nemáš u sebe žádné předměty";
        }
        Vec vec = batoh.odebratVec(prikaz);
        if (vec == null){
            return "Požadovaná věc není v batohu";
        }
        for (Regal regal : aktualniSektor.getRegaly()){
            if (regal.getUrceni().equals(prikaz.getTretiSlovo())){
                regal.vlozVec(vec);
                return "Předmět byl přidán do regálu.";
            }
        }
        return "Takový regál tu není";
    }

    private String okradni(Prikaz prikaz){
        if (!prikaz.maDruheSlovo()){
           return "Nevím, koho okradnout";
        }
        String osobaKOkradnuti = prikaz.getDruheSlovo();
        for (Osoba osoba : aktualniSektor.getOsoby()){
            if (osoba.getRole().equals(osobaKOkradnuti)){
                Duchodce duchodce = (Duchodce) osoba;
                int castka = duchodce.okradni();
                batoh.pridejPenize(castka);
                return "Důchodce byl okraden";
            }
        }
        return "V tomto sektoru není žádná osoba, co se dá okrást";

    }

    private String uplat(Prikaz prikaz){
        if (!aktualniSektor.getNazev().equals("sklad")){
            return "Zde nemůžeš nikoho uplatit";
        }
        if (!(batoh.getPenize() < 600 || batoh.obsahuje("pivo"))){
            return "Pokud fakt chceš tu kofolu, budeš muset přitvrdit";
        }
        Vec kofola = new Vec("kofola");
        batoh.pridatVec(kofola);
        batoh.pridejPenize(-600);
        return "Tady to máš a vypadni";
    }
}
