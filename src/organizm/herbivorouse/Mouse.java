package organizm.herbivorouse;

import organizm.Animal;
import organizm.Organizm;


public class Mouse extends Herbivorous {
    public Mouse(int y, int x) {
        super(y, x, 0.05, 2, 5, 10);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Mouse mouse = new Mouse(partner.getY(), partner.getX());
        mouse.setCanBreed(false);
        return mouse;

    }

    @Override
    public boolean eat(Organizm organizm) {
        return super.eat(organizm);
    }
}