package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConwayCelTest {

    @Test
    void testConwayCel_Geboorte() {
        ConwayCel cel  = new ConwayCel(false);

        assertTrue(cel.moetGeborenWorden(3));
        assertFalse(cel.moetGeborenWorden(2));
        assertFalse(cel.moetGeborenWorden(4));
    }

    @Test
    void testConwayCel_Overleven() {
        ConwayCel cel = new ConwayCel(true);

        assertTrue(cel.moetOverleven(2));
        assertTrue(cel.moetOverleven(3));
    }

    @Test
    void testConwayCel_StervenDoorOnderbevolking() {
        ConwayCel cel = new ConwayCel(true);

        assertFalse(cel.moetOverleven(0));
        assertFalse(cel.moetOverleven(1));
    }

    @Test
    void testConwayCel_StervenDoorOverbevolking() {
        ConwayCel cel = new ConwayCel(true);

        assertFalse(cel.moetOverleven(4));
        assertFalse(cel.moetOverleven(5));
        assertFalse(cel.moetOverleven(8));
    }
}