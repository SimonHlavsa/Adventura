package Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrikazTest {

    @Test
    void maDruheSlovo() {
        String druheSlovo = "druheSlovo";
        assertNotNull(druheSlovo);
    }
    @Test
    void maTretiSlovo() {
        String tretiSlovo = "tretiSlovo";
        assertNotNull(tretiSlovo);
    }
}