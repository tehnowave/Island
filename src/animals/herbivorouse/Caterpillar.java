package animals.herbivorouse;

import animals.Animal;
import animals.Organizm;


public class Caterpillar extends Herbivorous {
    public Caterpillar(int y, int x) {
        super(y, x, 1,2,2,3);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Caterpillar caterpillar = new Caterpillar(partner.getY(), partner.getX());
        caterpillar.setCanBreed(false);
        return caterpillar;

    }

    @Override
    public boolean eat(Organizm organizm) {
        return super.eat(organizm);
    }
}