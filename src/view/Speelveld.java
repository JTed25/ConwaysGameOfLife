package view;

import model.AlternatieveCel;
import model.Celtype;
import model.ConwayCel;
import model.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Speelveld extends JPanel implements MouseListener {
    private final Grid grid;
    private final int cellSize = 15;

    //Scroll-offsets (welke cel is te zien linksboven)
    private final int viewRow = 0;
    private final int viewCol = 0;

    public Speelveld(Grid grid) {
        this.grid = grid;

        setFocusable(true);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int rowsVisible = getHeight() / cellSize;
        int colsVisible = getWidth() / cellSize;

        for (int r = 0; r < rowsVisible; r++) {
            for (int c = 0; c < colsVisible; c++) {

                int gridR = viewRow + r;
                int gridC = viewCol + c;

                Celtype cel = grid.getCell(gridR, gridC);

                //Achtergrond
                g.setColor(Color.WHITE);
                g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);

                //Cell tekenen
                if (cel != null && cel.isLevend()) {
                    if (cel instanceof ConwayCel) {
                        g.setColor(Color.BLUE);
                        g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                    }
                    else if (cel instanceof AlternatieveCel) {
                        g.setColor(Color.RED);
                        g.fillRect(c * cellSize, r * cellSize, cellSize, cellSize);
                    }
                }

                //Gridlijnen
                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(c * cellSize, r * cellSize, cellSize, cellSize);
            }
        }
    }

    /*
    MouseInput
     */

    @Override
    public void mouseClicked(MouseEvent e) {
        requestFocusInWindow();

        int col = e.getX() / cellSize;
        int row = e.getY() / cellSize;

        int gridR = viewRow + row;
        int gridC = viewCol + col;

        if (e.isControlDown()) {
            grid.clearCell(gridR, gridC);
        }
        else if (SwingUtilities.isLeftMouseButton(e)) {
            grid.setCell(gridR, gridC, new ConwayCel(true));
        }
        else if (SwingUtilities.isRightMouseButton(e)) {
            grid.setCell(gridR, gridC, new AlternatieveCel(true));
        }

        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
