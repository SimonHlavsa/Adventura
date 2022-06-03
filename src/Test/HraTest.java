package Test;

import Main.Hra;
import Main.Prikaz;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HraTest {

    private Hra hra;

    @BeforeEach
    void setUp() {
        hra = new Hra();
    }


    @AfterAll
    static void afterAll() {
    }


    @Test
    public void zacatekHry(){
        assertEquals("\nVítejte v obchode, vaším úkolem je koupit si kofolu.\n" +
                "Pokud nebudete vědět, jak dál, napište 'napoveda'\n" +
                "\n" + hra.getAktualniSektor().dlouhyPopis(), hra.vratUvitani());
        assertFalse(hra.konecHry());
        assertEquals("vstup", hra.getAktualniSektor().getNazev());
    }

    @Test
    public void vratEpilog(){
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
}