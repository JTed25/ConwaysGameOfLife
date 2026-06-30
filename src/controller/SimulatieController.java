package controller;

import model.Simulatie;
import model.TickListener;
import view.Speelveld;
import view.Weergave;

public class SimulatieController implements TickListener {
    private final Simulatie model;
    private final Speelveld speelveld;
    private final Weergave weergave;

    public SimulatieController(Simulatie model, Speelveld speelveld, Weergave weergave) {
        this.model = model;
        this.speelveld = speelveld;
        this.weergave = weergave;

        model.getClock().addTickListener(this);
    }

    public void start() {
        model.start();
    }

    public void pause() {
        model.pause();
    }

    public void resume() {
        model.resume();
    }

    public void reset() {
        model.reset();
        weergave.updateStats(model.getGrid(), 0);
        speelveld.repaint();
    }

    public void snelheidVeranderen(long ms) {
        model.snelheidVeranderen(ms);
    }

    public long getDelayMillis() {
        return model.getClock().getDelayMillis();
    }

    @Override
    public void onTick(long ticks) {
        weergave.updateStats(model.getGrid(), ticks);
        speelveld.repaint();
    }
}
