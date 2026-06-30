package model;

public class Grid {
    private final int rows;
    private final int cols;
    private Celtype[][] cells;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Celtype[rows][cols];
    }

    public Celtype getCell(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return null;
        }
        return cells[r][c];
    }

    public void setCell(int r, int c, Celtype type) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return;
        }
        cells[r][c] = type;
    }

    public void clearCell(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return;
        }
        cells[r][c] = null;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void replaceAll(Celtype[][] next) {
        this.cells = next;
    }
}
