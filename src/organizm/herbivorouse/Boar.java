package organizm.herbivorouse;

import organizm.Animal;

public class Boar extends Herbivorous {
    public Boar(int y, int x) {
        super(y, x, 400, 2, 25, 50);
        ;
    }

    @Override
    protected Animal createChild(Animal partner) {
        Boar boar = new Boar(partner.getY(), partner.getX());
        boar.setCanBreed(false);
        return boar;
    }
}