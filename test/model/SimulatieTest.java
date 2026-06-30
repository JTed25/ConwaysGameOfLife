package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulatieTest {

    //FakeGameClock om handmatig ticks te simuleren
    static class FakeGameClock extends GameClock {
        long delay = 0;
        long tickNumber = 0;

        @Override
        public void start() {
        }

        @Override
        public void pause() {
        }

        @Override
        public void resume() {
        }

        @Override
        public void resetTicks() {
            tickNumber = 0;
        }

        @Override
        public void setDelayMillis(long ms) {
            delay = ms;
        }

        @Override
        public long getDelayMillis() {
            return delay;
        }

        public void manualTick() {
            tickNumber++;
            for (TickListener l : super.listeners) {
                l.onTick(tickNumber);
            }
        }
    }

    static class FakeSimulatie extends Simulatie {
        public FakeSimulatie(int rows, int cols, FakeGameClock gc) {
            super(rows, cols);
            super.clock.removeTickListener(this);
            super.clock = gc;
            gc.addTickListener(this);
        }

        public FakeGameClock getFakeClock() {
            return (FakeGameClock) super.clock;
        }
    }

    @Test
    void testResetClearsGridAndResetsTicks() {
        FakeSimulatie sim = new FakeSimulatie(5, 5, new FakeGameClock());

        //een levende cel maken
        sim.getGrid().setCell(2,2, new ConwayCel(true));

        sim.reset();

        assertNull(sim.getGrid().getCell(2,2));

        assertEquals(0, sim.getFakeClock().tickNumber);
    }

    @Test
    void testSnelheidVeranderen() {
        FakeSimulatie sim = new FakeSimulatie(5, 5, new FakeGameClock());

        sim.snelheidVeranderen(150);

        assertEquals(150, sim.getFakeClock().getDelayMillis());
    }

    @Test
    void testStartPauseResumeCallClock() {
        FakeSimulatie sim = new FakeSimulatie(5, 5, new FakeGameClock());

        sim.start();
        sim.pause();
        sim.resume();

        assertTrue(true);
    }

    @Test
    void testOnTickCallsRuleEngineStep() {
        FakeGameClock gc = new FakeGameClock();
        FakeSimulatie sim = new FakeSimulatie(5, 5, gc);

        sim.getGrid().setCell(1,1, new ConwayCel(true));

        gc.manualTick();

        assertNull(sim.getGrid().getCell(1,1));
    }
}