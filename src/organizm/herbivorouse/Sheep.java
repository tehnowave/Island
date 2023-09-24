package organizm.herbivorouse;

import organizm.Animal;
import organizm.Organizm;


public class Sheep extends Herbivorous {
    public Sheep(int y, int x) {
        super(y, x, 70, 3, 7, 15);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Sheep sheep = new Sheep(partner.getY(), partner.getX());
        sheep.setCanBreed(false);
        return sheep;

    }

    @Override
    public boolean eat(Organizm organizm) {
        return super.eat(organizm);
    }
}