package model;

public class RuleEngine {
    private final Grid grid;

    public RuleEngine(Grid grid) {
        this.grid = grid;
    }

    public void step() {
        int rows = grid.getRows();
        int cols = grid.getCols();

        Celtype[][] next = new Celtype[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Celtype current = grid.getCell(r, c);

                int buren = burenTellen(r, c);

                if (current == null) {
                    Celtype nieuw = berekenGeboorte(r, c, buren);
                    next[r][c] = nieuw;
                }
                else {
                    boolean leeftDoor = current.moetOverleven(buren);
                    next[r][c] = leeftDoor ? current : null;
                }
            }
        }

        grid.replaceAll(next);
    }

    private int burenTellen(int r, int c) {
        int count = 0;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;

                Celtype buur = grid.getCell(r + dr, c + dc);
                if (buur != null && buur.isLevend()) {
                    count++;
                }
            }
        }
        return count;
    }

    private Celtype berekenGeboorte(int r, int c, int buren) {
        //Conway
        if (new ConwayCel(false).moetGeborenWorden(buren)) {
            return new ConwayCel(true);
        }

        //Alternatief
        if (new AlternatieveCel(false).moetGeborenWorden(buren)) {
            return new AlternatieveCel(true);
        }

        return null;
    }
}
