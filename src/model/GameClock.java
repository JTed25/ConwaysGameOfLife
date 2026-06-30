package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameClock {
    private long tickNumber = 0;
    private long delayMillis = 200;
    private boolean running = false;

    protected final List<TickListener> listeners = new CopyOnWriteArrayList<>();

    public void addTickListener(TickListener l) {
        listeners.add(l);
    }

    public void removeTickListener(TickListener l) {
        listeners.remove(l);
    }

    public void setDelayMillis(long ms) {
        this.delayMillis = ms;
    }

    public void start() {
        if (running) return;
        running = true;

        Thread t = new Thread(() -> {
            while (running) {
                tickNumber++;

                for (TickListener l : listeners) {
                    l.onTick(tickNumber);
                }
                try {
                    Thread.sleep(delayMillis);
                }
                catch (InterruptedException ignored) {}
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void pause() {
        running = false;
    }

    public void resume() {
        start();
    }

    public void resetTicks() {
        tickNumber = 0;
    }

    public long getDelayMillis() {
        return delayMillis;
    }
}
