package Test;

import Main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HraTest {

    private Hra hra;
    private Prikaz prikaz;
    @BeforeEach
    void setUp() {
        hra = new Hra();

    }

    @Test
    void zacatekHry(){
        assertEquals("\nVítejte v obchode, vaším úkolem je koupit si kofolu.\n" +
                "Pokud nebudete vědět, jak dál, napište 'napoveda'\n" +
                "\n" + hra.getAktualniSektor().dlouhyPopis(), hra.vratUvitani());
        assertFalse(hra.konecHry());
        assertEquals("vstup", hra.getAktualniSektor().getNazev());
    }

    @Test
    void vratEpilog(){
        assertEquals("""
                Gratuluju, podařilo se ti sehnat kofolu\s
                KONEC\s
                Dík, že jste hru dohráli""", hra.vratEpilog());
    }

    @Test
    void zpracujChybnyPrikaz() {
        Prikaz prikaz = new Prikaz("něco");
        assertEquals("Nevim co tim myslis, tento prikaz neznam?", hra.zpracujPrikaz(prikaz));
    }


    @Test
    void konec() {
        prikaz = new Prikaz("konec neco");
        assertEquals("Ukoncit co? Nechapu, proc jste zadal druhe slovo.", hra.konec(prikaz));
        prikaz = new Prikaz("konec");
        assertEquals("hra ukončena příkazem konec", hra.konec(prikaz));
    }

    @Test
    void napoveda() {
        assertEquals("""
                Jsi v obchodě a snažíš se koupit kofolu
                
                Můžeš zadat tyto příkazy:
                inventar jdi konec mluv napoveda okradni poloz prohledej seber uplat\s
                Jsi v sektoru Vstup do obchodu.
                Sousedici sektory: ovoce""", hra.napoveda());
    }

    @Test
    void jdi() {
        prikaz = new Prikaz("jdi");
        assertEquals("Kam mám jít? Musíš zadat jméno sektoru", hra.jdi(prikaz));

        prikaz = new Prikaz("jdi maso");
        assertEquals("Tam se odsud jit neda!", hra.jdi(prikaz));

        prikaz = new Prikaz("jdi ovoce");
        hra.jdi(prikaz);
        prikaz = new Prikaz("jdi napoje");
        hra.jdi(prikaz);
        prikaz = new Prikaz("jdi pokladny");

        Vec kofola = new Vec("kofola");
        hra.pridatVecDoBatohu(kofola);

        assertEquals("""
                Gratuluju, podařilo se ti sehnat kofolu\s
                KONEC\s
                Dík, že jste hru dohráli""", hra.jdi(prikaz));
    }

    @Test
    void mluv() {
        prikaz = new Prikaz("mluv");
        assertEquals("S kým mám mluvit? Musíš zadat nazev osoby.", hra.mluv(prikaz));

        prikaz = new Prikaz("mluv nekdo");
        assertEquals("V tomto sektoru není žádná osoba", hra.mluv(prikaz));

        prikaz = new Prikaz("jdi ovoce");
        hra.jdi(prikaz);

        prikaz = new Prikaz("mluv nekdo");
        assertEquals("Požadovaná osoba nebyla nalezena", hra.mluv(prikaz));
    }

    @Test
    void prohledej() {
        prikaz = new Prikaz("prohledej");
        assertEquals("Nevím do jakého regálu se podívat", hra.prohledej(prikaz));

        prikaz = new Prikaz("prohledej neco");
        assertEquals("V tomto sektoru není žádný regál", hra.prohledej(prikaz));
    }

    @Test
    void seber() {
        prikaz = new Prikaz("seber");
        assertEquals("Nevím, jaký předmět sebrat.", hra.seber(prikaz));

        prikaz = new Prikaz("seber neco");
        assertEquals("V tomto sektoru není žádný regál se zbožím", hra.seber(prikaz));

        prikaz = new Prikaz("jdi ovoce");
        hra.jdi(prikaz);

        prikaz = new Prikaz("seber neco");
        assertEquals("požadovaná věc se v tomto sektoru nenachází", hra.seber(prikaz));
    }

    @Test
    void poloz() {
        prikaz = new Prikaz("poloz");
        assertEquals("Nevím, jaký předmět položit.", hra.poloz(prikaz));

        prikaz = new Prikaz("poloz neco");
        assertEquals("Nevím, kam předmět položit.", hra.poloz(prikaz));

        prikaz = new Prikaz("poloz neco nekam");
        assertEquals("Nemáš u sebe žádné předměty", hra.poloz(prikaz));

        Vec predmet = new Vec("predmet");
        hra.pridatVecDoBatohu(predmet);

        prikaz = new Prikaz("jdi ovoce");
        hra.jdi(prikaz);

        prikaz = new Prikaz("poloz neco ovoce");
        assertEquals("Požadovaná věc není v batohu", hra.poloz(prikaz));

        prikaz = new Prikaz("poloz predmet ovoce");
        assertEquals("Předmět byl přidán do regálu.", hra.poloz(prikaz));
    }

    @Test
    void okradni() {
        prikaz = new Prikaz("okradni");
        assertEquals("Nevím, koho okradnout", hra.okradni(prikaz));

        prikaz = new Prikaz("okradni duchodce");
        assertEquals("V tomto sektoru není žádná osoba, co se dá okrást", hra.okradni(prikaz));

        prikaz = new Prikaz("jdi ovoce");
        hra.jdi(prikaz);
        prikaz = new Prikaz("jdi pecivo");
        hra.jdi(prikaz);

        prikaz = new Prikaz("okradni duchodce");
        assertEquals("Důchodce byl okraden", hra.okradni(prikaz));

        prikaz = new Prikaz("okradni duchodce");
        assertEquals("Důchodce nemá peníze", hra.okradni(prikaz));
    }

    @Test
    void uplat() {
        assertEquals("Zde nemůžeš nikoho uplatit", hra.uplat());

        prikaz = new Prikaz("jdi ovoce");
        hra.jdi(prikaz);
        prikaz = new Prikaz("jdi pecivo");
        hra.jdi(prikaz);
        prikaz = new Prikaz("jdi maso");
        hra.jdi(prikaz);
        prikaz = new Prikaz("jdi sklad");
        hra.jdi(prikaz);

        assertEquals("Pokud fakt chceš tu kofolu, budeš muset přitvrdit", hra.uplat());

        hra.pridatPenize();

        assertEquals("Tady to máš a vypadni", hra.uplat());
    }
}