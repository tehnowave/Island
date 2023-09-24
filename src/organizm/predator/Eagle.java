package organizm.predator;

import organizm.Animal;
import organizm.herbivorouse.*;

import java.util.HashMap;

public class Eagle extends Predator {
    public Eagle(int y, int x) {
        super(y, x, 6, 3, 2, 2);
        eatProbability = new HashMap<>();
        eatProbability.put(Horse.class, 0.0f);
        eatProbability.put(Deer.class, 0.0f);
        eatProbability.put(Rabbit.class, 90.0f);
        eatProbability.put(Mouse.class, 90.0f);
        eatProbability.put(Goat.class, 0.0f);
        eatProbability.put(Sheep.class, 0.0f);
        eatProbability.put(Boar.class, 0.0f);
        eatProbability.put(Buffalo.class, 0.0f);
        eatProbability.put(Duck.class, 80.0f);
        eatProbability.put(Caterpillar.class, 0.0f);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Eagle eagle = new Eagle(partner.getY(), partner.getX());
        eagle.setCanBreed(false);
        return eagle;
    }
}
