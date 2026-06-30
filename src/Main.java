import view.ControlPanel;
import controller.SimulatieController;
import model.Simulatie;
import view.Speelveld;
import view.Weergave;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Simulatie sim = new Simulatie(1000, 1000);

        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Speelveld gui = new Speelveld(sim.getGrid());

        Weergave weergave = new Weergave();

        SimulatieController controller = new SimulatieController(sim, gui, weergave);
        ControlPanel controls = new ControlPanel(controller);

        frame.setLayout(new BorderLayout());
        frame.add(gui, BorderLayout.CENTER);
        frame.add(weergave, BorderLayout.EAST);
        frame.add(controls, BorderLayout.SOUTH);

        frame.setSize(900, 900);
        frame.setVisible(true);
    }
}
