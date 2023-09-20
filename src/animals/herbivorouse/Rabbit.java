package animals.herbivorouse;

import animals.Animal;
import animals.Organizm;


public class Rabbit extends Herbivorous {
    public Rabbit(int y, int x) {
        super(y, x, 2, 2, 3, 5);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Rabbit rabbit = new Rabbit(partner.getY(), partner.getX());
        rabbit.setCanBreed(false);
        return rabbit;

    }

    @Override
    public boolean eat(Organizm organizm) {
        return super.eat(organizm);
    }
}