package Test;

import Main.SeznamPrikazu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeznamPrikazuTest {

    @Test
    void jePlatnyPrikaz() {
        SeznamPrikazu seznamPrikazu = new SeznamPrikazu();
        String[] prikazy = {"jdi", "konec", "napoveda", "mluv", "okradni", "prohledej", "seber", "inventar", "poloz", "uplat"};
        for (String prikaz : prikazy){
            assertTrue(seznamPrikazu.getPlatnePrikazy().contains(prikaz));
        }
        assertEquals(prikazy.length, seznamPrikazu.getPlatnePrikazy().size());
    }
}