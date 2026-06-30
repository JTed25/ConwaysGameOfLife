package model;

public class Simulatie implements TickListener {
    private final Grid grid;
    private final RuleEngine rules;
    protected GameClock clock;

    public Simulatie(int rows, int cols) {
        this.grid = new Grid(rows, cols);
        this.rules = new RuleEngine(grid);
        this.clock = new GameClock();
        this.clock.addTickListener(this);
    }

    public Grid getGrid() {
        return grid;
    }

    public GameClock getClock() {
        return clock;
    }

    /*
    SimulatieControls
     */

    public void start() {
        clock.start();
    }

    public void pause() {
        clock.pause();
    }

    public void resume() {
        clock.resume();
    }

    public void reset() {
        clock.pause();
        clock.removeTickListener(this);
        clock.resetTicks();

        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                grid.clearCell(r, c);
            }
        }

        clock.addTickListener(this);
    }

    public void snelheidVeranderen(long ms) {
        clock.setDelayMillis(ms);
    }

    //Tick event
    @Override
    public void onTick(long tick) {
        //nieuwe generatie berekenen
        rules.step();
    }
}
