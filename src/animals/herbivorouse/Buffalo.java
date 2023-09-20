package animals.herbivorouse;

import animals.Animal;
import animals.Organizm;


public class Buffalo extends Herbivorous {
    public Buffalo(int y, int x) {
        super(y, x, 700, 3, 50, 100);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Buffalo buffalo = new Buffalo(partner.getY(), partner.getX());
        buffalo.setCanBreed(false);
        return buffalo;

    }

    @Override
    public boolean eat(Organizm organizm) {
        return super.eat(organizm);
    }
}