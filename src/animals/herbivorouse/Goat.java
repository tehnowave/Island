package animals.herbivorouse;

import animals.Animal;
import animals.Organizm;


public class Goat extends Herbivorous {
    public Goat(int y, int x) {
        super(y, x, 60, 3, 5, 10);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Goat goat = new Goat(partner.getY(), partner.getX());
        goat.setCanBreed(false);
        return goat;

    }

    @Override
    public boolean eat(Organizm organizm) {
        return super.eat(organizm);
    }
}