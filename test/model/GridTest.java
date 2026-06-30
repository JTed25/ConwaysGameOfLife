package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {

    @Test
    void testGetRowsAndCols() {
        Grid grid = new Grid(10, 15);

        assertEquals(10, grid.getRows());
        assertEquals(15, grid.getCols());
    }

    @Test
    public void testSetCellAndGetCell() {
        Grid grid = new Grid(5, 5);
        Celtype cel = new ConwayCel(true);

        grid.setCell(2, 3, cel);

        assertEquals(cel, grid.getCell(2, 3));
    }

    @Test
    public void testSetCellOverwrite() {
        Grid grid = new Grid(5, 5);
        Celtype cel1 = new ConwayCel(true);
        Celtype cel2 = new AlternatieveCel(true);

        grid.setCell(1, 1, cel1);
        grid.setCell(1, 1, cel2);

        assertEquals(cel2, grid.getCell(1, 1));
    }

    @Test
    public void testSetCellNull() {
        Grid grid = new Grid(5, 5);
        Celtype cel = new ConwayCel(true);

        grid.setCell(0, 0, cel);
        grid.setCell(0, 0, null);

        assertNull(grid.getCell(0, 0));
    }

    @Test
    public void testGetCellOutOfBounds() {
        Grid grid = new Grid(5, 5);

        assertNull(grid.getCell(-1, 0));
        assertNull(grid.getCell(0, -1));
        assertNull(grid.getCell(5, 0));
        assertNull(grid.getCell(0, 5));
    }

    @Test
    public void testSetCellOutOfBounds() {
        Grid grid = new Grid(5, 5);
        ConwayCel cel = new ConwayCel(true);

        grid.setCell(-1, 0, cel);
        grid.setCell(0, -1, cel);
        grid.setCell(5, 0, cel);
        grid.setCell(0, 5, cel);

        //grid mag niet veranderd zijn
        assertNull(grid.getCell(0, 0));
        assertNull(grid.getCell(4, 4));
    }

    @Test
    public void testClearCell() {
        Grid grid = new Grid(3, 3);

        grid.setCell(0, 0, new ConwayCel(true));
        grid.setCell(1, 1, new AlternatieveCel(true));
        grid.setCell(2, 2, new ConwayCel(true));

        grid.clearCell(0,0);
        grid.clearCell(1,1);
        grid.clearCell(2,2);

        assertNull(grid.getCell(0,0));
        assertNull(grid.getCell(1,1));
        assertNull(grid.getCell(2,2));
    }

    @Test
    public void testReplaceAll() {
        Grid grid = new Grid(2, 2);

        Celtype[][] next = new Celtype[2][2];
        next[0][0] = new ConwayCel(true);
        next[0][1] = new AlternatieveCel(true);
        next[1][0] = null;
        next[1][1] = new ConwayCel(true);

        grid.replaceAll(next);

        assertTrue(grid.getCell(0, 0).isLevend());
        assertTrue(grid.getCell(0, 1).isLevend());
        assertNull(grid.getCell(1, 0));
        assertTrue(grid.getCell(1, 1).isLevend());
    }
}