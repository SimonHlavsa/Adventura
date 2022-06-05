package Main;

/***
 *     v této tříde se inicializují sektory, věci, osoby a regály
 *     také jsou zde metody k příkazům
 * @author Šimon Hlavsa
 * @version 1.0
 * @created 15.5.2022
 */
public class Hra {

    private final SeznamPrikazu platnePrikazy;
    private Sektor aktualniSektor;
    private boolean konecHry = false;
    Batoh batoh;

    public Hra(){
        vytvorSektor();
        platnePrikazy = new SeznamPrikazu();
    }

/***
 * vytváří všechny instance sektorů, věcí, osob a regálů a ukládá je
 */
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
        napoje.setSousediciSektory(ovoce);
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


        regalNaOvoce.vlozVec(jablko);
        regalNaOvoce.vlozVec(hruska);
        regalNaNealko.vlozVec(dzus);
        regalNaNealko.vlozVec(malinovka);
        regalNaNealko.vlozVec(cocacola);
        regalNaAlkohol.vlozVec(pivo);
        regalNaAlkohol.vlozVec(vodka);
        regalNaPecivo.vlozVec(houska);
        regalNaPecivo.vlozVec(chleba);
        regalNaKlobasy.vlozVec(grilovaci);
        regalNaSunky.vlozVec(kureci);
        regalNaSunky.vlozVec(veprova);

        batoh = new Batoh();

    }

    /***
     * vrací uvítání na začátku hry
     */
    public String vratUvitani(){
        return "\nVítejte v obchode, vaším úkolem je koupit si kofolu.\n" +
                "Pokud nebudete vědět, jak dál, napište 'napoveda'\n" +
                "\n" +
                aktualniSektor.dlouhyPopis();
    }

    /***
     *vrací epilog na konci hry
     */
    public String vratEpilog(){
        return """
                Gratuluju, podařilo se ti sehnat kofolu\s
                KONEC\s
                Dík, že jste hru dohráli""";
    }


    /***
     * vrací, zda-li má hra pokračovat
     */
    public boolean konecHry() {
        return konecHry;
    }

    /***
     *zpracévává příkazy a nasledně zvolí metodu, která je v příkazu
     */
    public String zpracujPrikaz(Prikaz prikaz){
        String textKVypsani = "";
        if (platnePrikazy.jePlatnyPrikaz(prikaz.getSlovoPrikazu())){
            String povel = prikaz.getSlovoPrikazu();
            switch (povel) {
                case "napoveda" -> textKVypsani = napoveda();
                case "jdi" -> textKVypsani = jdi(prikaz);
                case "konec" -> textKVypsani = konec(prikaz);
                case "mluv" -> textKVypsani = mluv(prikaz);
                case "prohledej" -> textKVypsani = prohledej(prikaz);
                case "seber" -> textKVypsani = seber(prikaz);
                case "inventar" -> textKVypsani = inventar();
                case "poloz" -> textKVypsani = poloz(prikaz);
                case "okradni" -> textKVypsani = okradni(prikaz);
                case "uplat" -> textKVypsani = uplat();
            }
        }
        else  {
            textKVypsani = "Nevim co tim myslis, tento prikaz neznam?";
        }
        return textKVypsani;
    }

    /***
     * příkaz ukončí hru
     */
    public String konec(Prikaz prikaz){
        if (prikaz.maDruheSlovo()){
            return "Ukoncit co? Nechapu, proc jste zadal druhe slovo.";
        }
        else {
            konecHry = true;
            return "hra ukončena příkazem konec";
        }
    }

    /***
     * příkaz vypíše nápovědu
     */
    public String napoveda(){
        return "Jsi v obchodě a snažíš se koupit kofolu\n" +
                "\n" +
                "Můžeš zadat tyto příkazy:\n" +
                platnePrikazy.vratSeznamPrikazu() +
                "\n" +
                aktualniSektor.dlouhyPopis();

    }

    /***
     * příkaz hráče přesune do jiného sektoru
     */
    public String jdi(Prikaz prikaz){
        if (!prikaz.maDruheSlovo()){
            return  "Kam mám jít? Musíš zadat jméno sektoru";
        }
        String smer = prikaz.getDruheSlovo();
        Sektor sousedniSektor = aktualniSektor.sousedniSektor(smer);
        if (sousedniSektor == null){
            return "Tam se odsud jit neda!";
        }
        aktualniSektor = sousedniSektor;
        if (aktualniSektor.getNazev().equals("pokladny") && batoh.obsahuje("kofola")){
            konecHry = true;
            return vratEpilog();
        }

        if (aktualniSektor.getNazev().equals("napoje") && batoh.obsahuje("kofola")){
            Souboj souboj = new Souboj();
            if (!souboj.prubehSouboje()){
                konecHry = true;
                return "Prohrál jsi";
            }
            System.out.println("Podařilo se ti zvítězit, nyní máš volný průchod");
    }
        return aktualniSektor.dlouhyPopis();
    }

    /***
     * hráč zvolí s kým chce mluvit a daná osoba, pokud je v sektoru, tak vypíše svou hlášku
     */
    public String mluv(Prikaz prikaz){
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

    /***
     * příkaz k prohledání regálu
     */
    public String prohledej(Prikaz prikaz){
        if (!prikaz.maDruheSlovo()){
            return "Nevím do jakého regálu se podívat";
        }
        if (aktualniSektor.getRegaly().isEmpty()){
            return "V tomto sektoru není žádný regál";
        }
        return aktualniSektor.prohledejRegal(prikaz.getDruheSlovo());
    }

    /***
     * příkaz slouží k sebrání věci z regálu
     */
    public String seber(Prikaz prikaz){
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

    /***
     * příkaz vypíše množství peněz a věci v batohu
     */
    public String inventar(){
        return batoh.inventar();
    }

    /***
     * příkaz slouží k vložení věci do regálu
     */
    public String poloz(Prikaz prikaz){
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

    /***
     * hráč okradne zvolenou osobu
     */
    public String okradni(Prikaz prikaz){
        if (!prikaz.maDruheSlovo()){
           return "Nevím, koho okradnout";
        }
        String osobaKOkradnuti = prikaz.getDruheSlovo();
        if (!osobaKOkradnuti.equals("duchodce")){
            return "tato osoba se nedá okradnout";
        }
        for (Osoba osoba : aktualniSektor.getOsoby()){
            if (osoba.getRole().equals(osobaKOkradnuti)){
                Duchodce duchodce = (Duchodce) osoba;
                int castka = duchodce.okradni();
                if (castka == 0){
                    return "Důchodce nemá peníze";
                }
                batoh.pridejPenize(castka);
                return "Důchodce byl okraden";
            }
        }
        return "V tomto sektoru není žádná osoba, co se dá okrást";

    }

    /***
     * hráč se pokusí uplatit skladníka
     */
    public String uplat(){
        if (!aktualniSektor.getNazev().equals("sklad")){
            return "Zde nemůžeš nikoho uplatit";
        }
        if (!(batoh.getPenize() >= 600 || batoh.obsahuje("pivo"))){
            return "Pokud fakt chceš tu kofolu, budeš muset přitvrdit";
        }
        Vec kofola = new Vec("kofola");
        batoh.pridatVec(kofola);
        batoh.pridejPenize(-600);
        return "Tady to máš a vypadni";
    }

    public SeznamPrikazu getPlatnePrikazy() {
        return platnePrikazy;
    }
    

    public Sektor getAktualniSektor() {
        return aktualniSektor;
    }

    public boolean isKonecHry() {
        return konecHry;
    }

    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    public void setAktualniSektor(Sektor aktualniSektor) {
        this.aktualniSektor = aktualniSektor;
    }

    public void pridatVecDoBatohu(Vec vec) {
        batoh.pridatVec(vec);
    }

    public void pridatPenize(){
        batoh.pridejPenize(600);
    }


}
