package core;

import animals.Animal;
import animals.Organizm;
import plant.Plant;

import java.util.ArrayList;
import java.util.Collections;


public class Location extends Thread {
    private int x;
    private int y;
    private ArrayList<Organizm> organizms;
    private Field parentField;

    public Location(int x, int y, Field field) {

        this.x = x;
        this.y = y;
        this.organizms = new ArrayList<>();
        this.parentField = field;
    }

    public synchronized void addOrganizm(Organizm organizm) {
        if (organizm != null) {
            organizms.add(organizm);
            //    Collections.shuffle(organizms);
        } else {
            //    System.out.println("Warning: Tried to add a null Organizm.");
        }
    }

    public void shuffleAnimals(){
        Collections.shuffle(organizms);
    }

    public int[] getStat(){

    }



    public synchronized void removeAnimal(Organizm organizm) {
        this.organizms.remove(organizm);
    }

    public void generateGrass() {
        for (int k = 0; k < RandomUtil.randomInt(200) + 1; k++) {
            this.addOrganizm(new Plant());
        }
    }

    private boolean checkAmount(Animal organizm) {
        AnimalTypes type = AnimalTypes.valueOf(organizm.getClass().getSimpleName().toUpperCase());  // Преобразование в enum
        return this.countOrgranizm(organizm) > type.getAmount();
    }


    private void resetFlag() {


        for (Organizm organizm : organizms) {
            if (organizm instanceof Animal) {
                ((Animal) organizm).resetMovedFlag();
            }
        }

    }

    public void moveAnimals() {

        for (int i = organizms.size() - 1; i >= 0; i--) {
            Organizm organizm = organizms.get(i);

            if (organizm instanceof Animal currentAnimal) {
                if (currentAnimal.isNotMoved()) {

                    if (currentAnimal.getSatiety() < 1) {
                        this.removeAnimal(currentAnimal);
                        continue;
                    }
                    int oldY = currentAnimal.getY();
                    int oldX = currentAnimal.getX();

                    currentAnimal.move();

                    if (!currentAnimal.isOutOfBounds(parentField.getColls(), parentField.getRows()) && checkAmount(currentAnimal)) {
                        parentField.getLocation(currentAnimal.getY(), currentAnimal.getX()).addOrganizm(currentAnimal);
                        this.removeAnimal(currentAnimal);//FIXME Sync this action
                        //         System.out.println(currentAnimal + " from : " + oldY + "," + oldX + " moved to : " + currentAnimal.getY() + "," + currentAnimal.getX() + ".");
                    } else {
                        currentAnimal.setY(oldY);
                        currentAnimal.setX(oldX);
                    }
                }
            }
        }
    }

    public void lookingForCouple() {
        for (int i = 0; i < organizms.size(); i++) {
            for (int j = i + 1; j < organizms.size(); j++) {
                Organizm currentOrganizm = organizms.get(i);

                AnimalTypes type = AnimalTypes.valueOf(currentOrganizm.getClass().getSimpleName().toUpperCase());

                if (countOrgranizm(currentOrganizm) > type.getAmount()) {
                    return;
                }
                Organizm child = currentOrganizm.breed(organizms.get(j));
                if (child != null) {

                    addOrganizm(child);
                    //  System.out.println("New organism: of "+  child.getClass());
                    break;
                }
            }
        }

        for (Organizm organism : organizms) {

            organism.resetBreed();
        }
    }

    public int countOrgranizm(Organizm organizm) {
        int currentCount = 0;
        for (Organizm org : organizms) {

            if (org.getClass().equals(organizm.getClass())) {
                currentCount++;
            }
        }
        return currentCount;
    }


    public void simulateHunt() {
        for (int i = 0; i < organizms.size(); i++) {
            Organizm organizm = organizms.get(i);

            if (organizm.isNotMoved()) {
                for (int j = 0; j < organizms.size(); j++) {
                    Organizm possibleOrganizm = organizms.get(j);
                    if (organizm.eat(possibleOrganizm)) {
                        possibleOrganizm.die();
                        this.removeAnimal(possibleOrganizm);
                        break;
                    }
                }
            }
        }
    }

}
