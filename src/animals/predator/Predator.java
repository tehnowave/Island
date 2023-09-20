package animals.predator;

import animals.Animal;
import animals.Organizm;
import animals.herbivorouse.Herbivorous;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Predator extends Animal {
    protected HashMap<Class<? extends Animal>, Float> eatProbability;

    public Predator(int x, int y, double weight, int maxMoveDistance, double satiety, double maxSatiety) {
        super(x, y, weight, maxMoveDistance, satiety, maxSatiety);
    }



    @Override
    public boolean eat(Organizm organizm) {
        if (organizm instanceof Herbivorous) {
            float probability = eatProbability.getOrDefault(organizm.getClass(), 0.0f);
            float randomValue = ThreadLocalRandom.current().nextFloat() * 100.0f;

            if (randomValue < probability) {

          //      System.out.println(this.getClass().getSimpleName() + " successfully ate the " + organizm.getClass().getSimpleName() + ".");
                if (this.getSatiety() < this.getMaxSatiety()) {
                    increaseSatiety(1);
                }
                return true;

            } else {

            //    System.out.println(this.getClass().getSimpleName() + " failed to eat the " + organizm.getClass().getSimpleName() + ".");
                return false;
            }
        }
        return false;

    }


    @Override
    public void die() {

    }

    protected abstract Animal createChild(Animal partner);
}
