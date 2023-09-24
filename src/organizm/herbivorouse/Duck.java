package organizm.herbivorouse;

import organizm.Animal;
import organizm.Organizm;


public class Duck extends Herbivorous {
    public Duck(int y, int x) {
        super(y, x, 1, 4, 7, 15);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Duck duck = new Duck(partner.getY(), partner.getX());
        duck.setCanBreed(false);
        return duck;

    }

    @Override
    public boolean eat(Organizm organizm) {
        return super.eat(organizm);
    }

}