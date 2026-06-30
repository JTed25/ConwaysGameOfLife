package model;

public class AlternatieveCel extends Celtype {

    public AlternatieveCel(boolean levend) {
        super(levend);
    }

    @Override
    public boolean moetOverleven(int buren) {
        return buren == 2 || buren == 3 || buren == 4;
    }

    @Override
    public boolean moetGeborenWorden(int buren) {
        return buren == 4;
    }
}
