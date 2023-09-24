package organizm;


public interface Organizm {
    Organizm breed(Organizm partner);
    void resetBreed();

    boolean eat(Organizm organizm);



    void move();

    boolean isNotMoved();
    boolean isCanBreed();
}
