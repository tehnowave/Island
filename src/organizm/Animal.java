package organizm;

import core.Directions;
import core.RandomUtil;


public abstract class Animal implements Organizm {
    private int x;
    private int y;
    private boolean NotMoved;

    private double weight;
    protected int maxMoveDistance;
    private double satiety;
    private double maxSatiety;
    private boolean canBreed;
    public String name;

    public void setMaxSatiety(double maxSatiety) {
        this.maxSatiety = maxSatiety;
    }

    @Override
    public boolean isCanBreed() {
        return canBreed;
    }

    public void setCanBreed(boolean canBreed) {
        this.canBreed = canBreed;
    }

    public Animal(int y, int x, double weight, int maxMoveDistance, double satiety, double maxSatiety) {
        this.y = y;
        this.x = x;
        this.weight = weight;
        this.NotMoved = true;
        this.maxMoveDistance = maxMoveDistance;
        this.satiety = satiety;
        this.maxSatiety = maxSatiety;

        this.canBreed = true;
    }

    @Override
    public Organizm breed(Organizm partner) {
        if (partner == null || this.getClass() != partner.getClass()) {
            return null;
        }
        Animal animalPartner = (Animal) partner;

        if (!this.canBreed || !animalPartner.canBreed) {
            return null;
        }
        this.canBreed = false;
        animalPartner.canBreed = false;

        return createChild(animalPartner);

    }

    protected abstract Animal createChild(Animal partner);

    @Override
    public void resetBreed() {
        this.canBreed = true;
    }

    public void decreaseHealth(int amount) {
        satiety -= amount;
        if (satiety < 0) {
            satiety = 0;
        }
    }

    public void increaseSatiety(int value) {
        this.satiety += value;
    }

    public void resetMovedFlag() {
        NotMoved = true;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxMoveDistance() {
        return maxMoveDistance;
    }

    public void setMaxMoveDistance(int maxMoveDistance) {
        this.maxMoveDistance = maxMoveDistance;
    }


    public double getSatiety() {
        return satiety;
    }

    public double getMaxSatiety() {
        return maxSatiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isNotMoved() {
        return NotMoved;
    }

    public void setNotMoved(boolean notMoved) {
        this.NotMoved = notMoved;
    }


    public boolean isOutOfBounds(int maxX, int maxY){
        return this.x<0 || this.x>=maxX || this.y< 0 || this.y>=maxY;
    }


    public void move() {

        int randomDirIndex = RandomUtil.getNumber(0, Directions.values().length - 1);//FIXME!!!
        Directions direction = Directions.values()[randomDirIndex];

        int offsetX = 0;
        int offsetY = 0;
        int rndMove = RandomUtil.getNumber(1, this.maxMoveDistance);

        switch (direction) {
            case UP -> // Движение вверх
                    offsetY = -rndMove;
            case DOWN -> // Движение вниз
                    offsetY = rndMove;
            case LEFT -> // Движение влево
                    offsetX = -rndMove;
            case RIGHT -> // Движение вправо
                    offsetX = rndMove;
        }

        // Вычисляем новые координаты
        this.x += offsetX;
        this.y += offsetY;


        this.NotMoved = false;
        this.decreaseHealth(1);


    }


}
