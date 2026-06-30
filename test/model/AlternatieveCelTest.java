package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlternatieveCelTest {

    @Test
    void testAlternatieveCel_Geboorte() {
        AlternatieveCel cel = new AlternatieveCel(true);

        assertTrue(cel.moetGeborenWorden(4));
        assertFalse(cel.moetGeborenWorden(3));
        assertFalse(cel.moetGeborenWorden(5));
    }

    @Test
    void testAlternatieveCel_Overleven() {
        AlternatieveCel cel = new AlternatieveCel(true);

        assertTrue(cel.moetOverleven(2));
        assertTrue(cel.moetOverleven(3));
        assertTrue(cel.moetOverleven(4));
    }

    @Test
    void testAlternatieveCel_Sterven() {
        AlternatieveCel cel = new AlternatieveCel(true);

        assertFalse(cel.moetOverleven(0));
        assertFalse(cel.moetOverleven(1));
        assertFalse(cel.moetOverleven(5));
        assertFalse(cel.moetOverleven(6));
    }
}