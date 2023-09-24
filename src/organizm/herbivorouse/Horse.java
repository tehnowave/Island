package organizm.herbivorouse;

import organizm.Animal;
import organizm.Organizm;

public class Horse extends Herbivorous {
    public Horse(int y, int x) {
        super(y, x, 400, 4, 40, 60);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Horse horse = new Horse(partner.getY(), partner.getX());
        horse.setCanBreed(false);
        return horse;

    }

    @Override
    public boolean eat(Organizm organizm) {
        return super.eat(organizm);
    }
}

