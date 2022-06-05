package Test;

import Main.Batoh;
import Main.Prikaz;
import Main.Vec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatohTest {

    private Batoh batoh;
    private Vec vec;

    @BeforeEach
    void setUp() {
        batoh = new Batoh();
        vec = new Vec("neco");
    }

    @Test
    void pridatVec() {
        assertEquals("Věc byla sebrána a přidána do batohu", batoh.pridatVec(vec));
        batoh.pridatVec(vec);
        batoh.pridatVec(vec);
        batoh.pridatVec(vec);
        batoh.pridatVec(vec);
        assertEquals("Batoh je plný, nelze do něho nic přidat", batoh.pridatVec(vec));
    }

    @Test
    void odebratVec() {
        batoh.pridatVec(vec);
        Prikaz prikaz = new Prikaz("poloz neco");
        assertNotNull(batoh.odebratVec(prikaz));
    }

    @Test
    void jeInventarPrazdny() {
        assertEquals("Peníze: 0\nV batohu nejsou žádné předměty.", batoh.inventar());
    }

    @Test
    void obsahuje() {
        batoh.pridatVec(vec);
        batoh.obsahuje("neco");
    }
}