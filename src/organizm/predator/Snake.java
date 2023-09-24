package organizm.predator;

import organizm.Animal;
import organizm.herbivorouse.*;

import java.util.HashMap;


public class Snake extends Predator {
    public Snake(int y, int x) {
        super(y, x, 15, 3, 4, 8);
        eatProbability = new HashMap<>();
        eatProbability.put(Horse.class, 0.0f);
        eatProbability.put(Deer.class, 0.0f);
        eatProbability.put(Rabbit.class, 20.0f);
        eatProbability.put(Mouse.class, 40.0f);
        eatProbability.put(Goat.class, 0.0f);
        eatProbability.put(Sheep.class, 0.0f);
        eatProbability.put(Boar.class, 0.0f);
        eatProbability.put(Buffalo.class, 0.0f);
        eatProbability.put(Duck.class, 10.0f);
        eatProbability.put(Caterpillar.class, 0.0f);
    }
    @Override
    protected Animal createChild(Animal partner) {
        Snake snake = new Snake(partner.getY(),partner.getX());
        snake.setCanBreed(false);
        return snake;
    }
}
