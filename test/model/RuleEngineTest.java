package model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class RuleEngineTest {

    @Test
    public void testBuurCellVanMidden() {
        Grid grid = new Grid(3,3);
        RuleEngine rules = new RuleEngine(grid);

        grid.setCell(0,1, new ConwayCel(true));
        grid.setCell(1, 0, new ConwayCel(true));
        grid.setCell(1, 2, new ConwayCel(true));
        grid.setCell(2, 1, new ConwayCel(true));

        int buren = invokeBurenTellen(rules,1,1);

        assertEquals(4, buren);
    }

    @Test
    public void testBurenTellenCorner() {
        Grid grid = new Grid(3,3);
        RuleEngine rules = new RuleEngine(grid);

        grid.setCell(0,1, new ConwayCel(true));
        grid.setCell(1, 0, new ConwayCel(true));

        int buren = invokeBurenTellen(rules, 0, 0);

        assertEquals(2, buren);
    }

    @Test
    public void testGeboorteConway() {
        Grid grid = new Grid(3,3);
        RuleEngine rules = new RuleEngine(grid);

        grid.setCell(0,1, new ConwayCel(true));
        grid.setCell(1, 0, new ConwayCel(true));
        grid.setCell(1, 2, new ConwayCel(true));

        Celtype nieuw = invokeBerekenGeboorte(rules, 1, 1, 3);

        assertNotNull(nieuw);
        assertTrue(nieuw instanceof ConwayCel);
        assertTrue(nieuw.isLevend());
    }

    @Test
    public void testGeenGeboorte() {
        Grid grid = new Grid(3,3);
        RuleEngine rules = new RuleEngine(grid);

        Celtype nieuw = invokeBerekenGeboorte(rules, 1, 1, 1);

        assertNull(nieuw);
    }

    @Test
    public void testStepBlinkerOscillator() {
        Grid grid = new Grid(3,3);
        RuleEngine rules = new RuleEngine(grid);

        //Verticale blinker
        grid.setCell(0,1, new ConwayCel(true));
        grid.setCell(1, 1, new ConwayCel(true));
        grid.setCell(2, 1, new ConwayCel(true));

        rules.step();

        //Horizontale blinker
        assertTrue(grid.getCell(1, 0).isLevend());
        assertTrue(grid.getCell(1, 1).isLevend());
        assertTrue(grid.getCell(1, 2).isLevend());

        assertNull(grid.getCell(0, 1));
        assertNull(grid.getCell(2, 1));
    }

    @Test
    public void testStilLifeBlock() {
        Grid grid = new Grid(2,2);
        RuleEngine rules = new RuleEngine(grid);

        grid.setCell(0,0, new ConwayCel(true));
        grid.setCell(0,1, new ConwayCel(true));
        grid.setCell(1, 0, new ConwayCel(true));
        grid.setCell(1, 1, new ConwayCel(true));

        rules.step();

        assertTrue(grid.getCell(0, 0).isLevend());
        assertTrue(grid.getCell(0, 1).isLevend());
        assertTrue(grid.getCell(1, 0).isLevend());
        assertTrue(grid.getCell(1, 1).isLevend());
    }

    /*
    Helpers om private methoden te testen
     */

    private int invokeBurenTellen(RuleEngine rules, int r, int c) {
        try {
            Method method = RuleEngine.class.getDeclaredMethod("burenTellen", int.class, int.class);
            method.setAccessible(true);
            return (int) method.invoke(rules, r, c);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Celtype invokeBerekenGeboorte(RuleEngine rules, int r, int c, int buren) {
        try {
            Method method = RuleEngine.class.getDeclaredMethod("berekenGeboorte", int.class, int.class, int.class);
            method.setAccessible(true);
            return (Celtype) method.invoke(rules, r, c, buren);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}