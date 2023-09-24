package organizm;

public class Plant implements Organizm {

    private final int WEIGHT = 1;
    boolean canBreed;

    public Plant() {
        this.canBreed = true;
    }

    @Override
    public Organizm breed(Organizm partner) {
        if (partner == null || this.getClass() != partner.getClass()) {
            return null;
        }
        Plant plantPartner = (Plant) partner;

        if (!this.canBreed || !plantPartner.canBreed) {
            return null;
        }

        this.canBreed = false;
        plantPartner.canBreed = false;

        Plant child = new Plant();
        child.canBreed = false;
        return child;
    }

    @Override
    public void resetBreed() {
        this.canBreed = true;
    }


    @Override
    public boolean eat(Organizm organizm) {
        return false;

    }

    @Override
    public boolean isCanBreed() {
        return canBreed;
    }

    @Override
    public void move() {

    }

    @Override
    public boolean isNotMoved() {
        return false;
    }
}
