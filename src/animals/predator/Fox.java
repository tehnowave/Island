package animals.predator;

import animals.Animal;
import animals.herbivorouse.*;


import java.util.HashMap;

public class Fox extends Predator {
    public Fox(int y, int x) {
        super(y, x, 8, 2, 2, 2);
        eatProbability = new HashMap<>();
        eatProbability.put(Horse.class, 0.0f);
        eatProbability.put(Deer.class, 0.0f);
        eatProbability.put(Rabbit.class, 70.0f);
        eatProbability.put(Mouse.class, 90.0f);
        eatProbability.put(Goat.class, 0.0f);
        eatProbability.put(Sheep.class, 0.0f);
        eatProbability.put(Boar.class, 0.0f);
        eatProbability.put(Buffalo.class, 0.0f);
        eatProbability.put(Duck.class, 60.0f);
        eatProbability.put(Caterpillar.class, 40.0f);
    }

    @Override
    protected Animal createChild(Animal partner) {
        Fox fox = new Fox(partner.getY(), partner.getX());
        fox.setCanBreed(false);
        return fox;
    }
}