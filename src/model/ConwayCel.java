package model;

public class ConwayCel extends Celtype {
    public ConwayCel(boolean levend) {
        super(levend);
    }
    @Override
    public boolean moetOverleven(int buren) {
        return buren == 2 || buren == 3;
    }

    @Override
    public boolean moetGeborenWorden(int buren) {
        return buren == 3;
    }
}
