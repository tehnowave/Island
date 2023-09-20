package animals.herbivorouse;

import animals.Animal;
import animals.Organizm;

public class Deer extends Herbivorous {
    public Deer(int y, int x) {
        super(y, x, 300, 4, 25, 50);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Deer deer = new Deer(partner.getY(), partner.getX());
        deer.setCanBreed(false);
        return deer;

    }

    @Override
    public boolean eat(Organizm organizm) {
        return super.eat(organizm);
    }
}