package Test;

import Main.Prikaz;
import Main.Regal;
import Main.Sektor;
import Main.Vec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SektorTest {

    private Sektor sektor;

    @BeforeEach
    void setUp() {
        sektor = new Sektor("nazev", "popis");
        Sektor sousedniSektor = new Sektor("nazevS", "popisS");
        sektor.setSousediciSektory(sousedniSektor);
        Regal regal = new Regal("regal");
        sektor.setRegaly(regal);
        Vec vec = new Vec("vec");
        regal.vlozVec(vec);
    }

    @Test
    void dlouhyPopis() {
        assertEquals("Jsi v sektoru popis.\nSousedici sektory: nazevS\nRegaly: regal ", sektor.dlouhyPopis());
    }

    @Test
    void seznamSousedicichSektoru() {
        assertEquals("Sousedici sektory: nazevS", sektor.seznamSousedicichSektoru());
    }

    @Test
    void prohledejRegal() {
        assertEquals("Věci v regálu: vec ", sektor.prohledejRegal("regal"));
    }


    @Test
    void seberVec() {
        Prikaz prikaz = new Prikaz("regal vec");
        assertNotNull(sektor.seberVec(prikaz));
    }
}