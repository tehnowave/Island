package animals;


public interface Organizm {
    Organizm breed(Organizm partner);
    void resetBreed();

    boolean eat(Organizm organizm);

    void die();

    void move();

    boolean isNotMoved();
}
