package view;

import model.AlternatieveCel;
import model.Celtype;
import model.ConwayCel;
import model.Grid;

import javax.swing.*;
import java.awt.*;

public class Weergave extends JPanel{

    private final JLabel ticksLabel;
    private final JLabel conwayLabel;
    private final JLabel alternatieveLabel;

    public Weergave() {
        setLayout(new GridLayout(3, 1));

        ticksLabel = new JLabel("Aantal ticks: 0");
        conwayLabel = new JLabel("Aantal Conway: 0");
        alternatieveLabel = new JLabel("Aantal Alternatieve: 0");

        add(ticksLabel);
        add(conwayLabel);
        add(alternatieveLabel);
    }

    public void updateStats(Grid grid, long aantalTicks) {

        int conway = 0;
        int alternatief = 0;

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                Celtype cel = grid.getCell(r, c);
                if (cel != null && cel.isLevend()) {
                    if (cel instanceof ConwayCel) {
                        conway++;
                    } else if (cel instanceof AlternatieveCel) {
                        alternatief++;
                    }
                }
            }
        }

        ticksLabel.setText("Aantal ticks: " + aantalTicks);
        conwayLabel.setText("Aantal Conway: " + conway);
        alternatieveLabel.setText("Aantal Alternatieve: " + alternatief);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 0);
    }
}
