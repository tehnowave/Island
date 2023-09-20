package core;

public enum AnimalTypes {
    WOLF(30),
    HORSE(20),
    PLANT(200),
    FOX(30),
    BOAR(50),
    BEAR(5),
    EAGLE(20),
    DEER(20),
    RABBIT(150),
    MOUSE(500),
    GOAT(140),
    SHEEP(140),
    SNAKE(30),
    BUFFALO(10),
    DUCK(200),
    CATERPILLAR(1000);


    private int amount;

    AnimalTypes(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
