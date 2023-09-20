package animals.herbivorouse;

import plant.Plant;
import animals.Animal;
import animals.Organizm;

public abstract class Herbivorous extends Animal implements Organizm {
    public Herbivorous(int y, int x, double weight, int maxMoveDistance, double satiety, double maxSatiety) {
        super(y, x, weight, maxMoveDistance, satiety, maxSatiety);
    }




    @Override
    public void die() {

    }

    @Override
    public boolean eat(Organizm organizm) {
        if (organizm instanceof Plant) {
            if (this.getSatiety() < this.getMaxSatiety()) {
                increaseSatiety(1);
            }
     //       System.out.println(this.getClass().getSimpleName() + " successfully ate the " + organizm.getClass().getSimpleName() + ".");
            return true;
        } else
            return false;
    }


}
