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


                if (this.getSatiety() < this.getMaxSatiety()) {
                    increaseSatiety(1);
                }
                return true;

            } else {


                return false;
            }
        }
        return false;

    }


    protected abstract Animal createChild(Animal partner);
}
