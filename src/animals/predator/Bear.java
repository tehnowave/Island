package animals.predator;

import animals.Animal;
import animals.herbivorouse.*;


import java.util.HashMap;

public class Bear extends Predator{
    public Bear(int y, int x) {
        super(y, x, 500, 2, 40, 80);
        eatProbability = new HashMap<>();
        eatProbability.put(Horse.class, 40.0f);
        eatProbability.put(Deer.class, 80.0f);
        eatProbability.put(Rabbit.class, 80.0f);
        eatProbability.put(Mouse.class, 90.0f);
        eatProbability.put(Goat.class, 70.0f);
        eatProbability.put(Sheep.class, 70.0f);
        eatProbability.put(Boar.class, 50.0f);
        eatProbability.put(Buffalo.class, 20.0f);
        eatProbability.put(Duck.class, 10.0f);
        eatProbability.put(Caterpillar.class, 0.0f);
    }
    @Override
    protected Animal createChild(Animal partner) {
        Bear bear = new Bear(partner.getY(),partner.getX());
        bear.setCanBreed(false);
        return bear;
    }
}