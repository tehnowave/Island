package animals.predator;


import animals.Animal;
import animals.herbivorouse.*;

import java.util.HashMap;

public class Wolf extends Predator {

    public Wolf(int y, int x) {
        super(y, x, 50, 3, 3, 8);
        eatProbability = new HashMap<>();
        eatProbability.put(Horse.class, 10.0f);
        eatProbability.put(Deer.class, 15.0f);
        eatProbability.put(Rabbit.class, 60.0f);
        eatProbability.put(Mouse.class, 80.0f);
        eatProbability.put(Goat.class, 60.0f);
        eatProbability.put(Sheep.class, 70.0f);
        eatProbability.put(Boar.class, 15.0f);
        eatProbability.put(Buffalo.class, 10.0f);
        eatProbability.put(Duck.class, 40.0f);
        eatProbability.put(Caterpillar.class, 0.0f);
    }
    @Override
    protected Animal createChild(Animal partner) {
        Wolf wolf = new Wolf(partner.getY(),partner.getX());
        wolf.setCanBreed(false);
        return wolf;
    }
}




