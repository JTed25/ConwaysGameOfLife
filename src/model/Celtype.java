package model;

public abstract class Celtype {
    private boolean levend;

    public Celtype(boolean levend) {
        this.levend = levend;
    }

    public boolean isLevend() {
        return levend;
    }

    public void setLevend(boolean levend) {
        this.levend = levend;
    }

    //Elke subklasse implementeert op zijn eigen manier
    public abstract boolean moetOverleven(int buren);
    public abstract boolean moetGeborenWorden(int buren);
}
