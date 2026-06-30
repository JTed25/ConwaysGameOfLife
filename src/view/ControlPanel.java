package view;

import controller.SimulatieController;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel(SimulatieController controller) {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause");
        JButton resumeButton = new JButton("Resume");
        JButton resetButton = new JButton("Reset");

        JButton slowerButton = new JButton("Slower");
        JButton fasterButton = new JButton("Faster");

        //Acties
        startButton.addActionListener(e -> controller.start());
        pauseButton.addActionListener(e -> controller.pause());
        resumeButton.addActionListener(e -> controller.resume());
        resetButton.addActionListener(e -> controller.reset());

        slowerButton.addActionListener(e -> controller.snelheidVeranderen(controller.getDelayMillis() + 50));
        fasterButton.addActionListener(e -> controller.snelheidVeranderen(Math.max(10, controller.getDelayMillis() - 50)));

        add(startButton);
        add(pauseButton);
        add(resumeButton);
        add(resetButton);
        add(slowerButton);
        add(fasterButton);
    }
}
