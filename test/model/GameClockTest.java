package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameClockTest {
    static class FakeListener implements TickListener {
        long lastTick = -1;
        int count = 0;

        @Override
        public void onTick(long tick) {
            lastTick = tick;
            count++;
        }
    }

    @Test
    void testStartProduceTicks() throws InterruptedException {
        GameClock gc = new GameClock();
        gc.setDelayMillis(50);

        FakeListener listener = new FakeListener();
        gc.addTickListener(listener);

        gc.start();

        Thread.sleep(50);

        gc.pause();

        assertTrue(listener.count >= 0);
        assertTrue(listener.lastTick >= 0);
    }

    @Test
    void testPauseStopsTicks() throws InterruptedException {
        GameClock gc = new GameClock();
        gc.setDelayMillis(10);

        FakeListener listener = new FakeListener();
        gc.addTickListener(listener);

        gc.start();
        Thread.sleep(40);

        gc.pause();
        int countAfterPause = listener.count;

        Thread.sleep(40);

        assertEquals(countAfterPause, listener.count);
    }

    @Test
    void testResumeContinuesTicks() throws InterruptedException {
        GameClock gc = new GameClock();
        gc.setDelayMillis(10);

        FakeListener listener = new FakeListener();
        gc.addTickListener(listener);

        gc.start();
        Thread.sleep(40);

        gc.pause();
        int countPaused = listener.count;

        gc.resume();
        Thread.sleep(40);

        assertTrue(listener.count > countPaused);
    }

    @Test
    void testResetTicks() throws InterruptedException {
        GameClock gc = new GameClock();
        gc.setDelayMillis(10);

        FakeListener listener = new FakeListener();
        gc.addTickListener(listener);

        gc.start();
        Thread.sleep(40);

        gc.pause();
        gc.resetTicks();

        gc.resume();
        Thread.sleep(20);
        gc.pause();

        assertEquals(4, listener.lastTick);
    }

    @Test
    void testSetDelayMillis() {
        GameClock gc = new GameClock();

        gc.setDelayMillis(200);
        assertEquals(200, gc.getDelayMillis());

        gc.setDelayMillis(50);
        assertEquals(50, gc.getDelayMillis());
    }

    @Test
    void testRemoveListenerStopsReceivingTicks() throws InterruptedException {
        GameClock gc = new GameClock();
        gc.setDelayMillis(10);

        FakeListener listener = new FakeListener();
        gc.addTickListener(listener);

        gc.start();
        Thread.sleep(40);

        gc.removeTickListener(listener);
        int countAfterRemove = listener.count;

        Thread.sleep(40);

        //Listener mag geen nieuwe ticks meer krijgen
        assertEquals(countAfterRemove, listener.count);
    }
}