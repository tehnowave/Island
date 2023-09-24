package core;

import organizm.Animal;
import organizm.Organizm;
import organizm.Plant;

import java.util.*;


public class Location {
    private int x;
    private int y;
    // List to store all organisms in this location
    private ArrayList<Organizm> organizms;

    // Reference to the parent field this location is part of
    private Field parentField;

    // Constructor for the location specifying its coordinates and the field it belongs to
    public Location(int x, int y, Field field) {

        this.x = x;
        this.y = y;
        this.organizms = new ArrayList<>();
        this.parentField = field;
    }

    // Add an organism to this location
    protected void addOrganizm(Organizm organizm) {
        if (organizm != null) {
            organizms.add(organizm);
        }
    }

    // Get all organisms from this location
    protected ArrayList<Organizm> getOrganizms() {
        return organizms;
    }

    protected void shuffleAnimals() {
        Collections.shuffle(organizms);
    }

    //  Remove an organism from this location
    protected void removeAnimal(Organizm organizm) {
        this.organizms.remove(organizm);
    }

    // Generate random amount of grass in this location
    protected void generateGrass() {
        for (int k = 0; k < RandomUtil.randomInt(200) + 1; k++) {
            this.addOrganizm(new Plant());
        }
    }

    // Check if there are too many of a specific organism in this location
    private boolean checkAmount(Animal organizm) {
        AnimalTypes type = AnimalTypes.valueOf(organizm.getClass().getSimpleName().toUpperCase());  // Преобразование в enum
        return this.countOrgranizm(organizm) > type.getAmount();
    }

    // Reset the move flags of animals in this location after every simulation step
    protected void resetFlag() {


        for (Organizm organizm : organizms) {
            if (organizm instanceof Animal) {
                ((Animal) organizm).resetMovedFlag();
            }
        }
    }

    // Move animals from one location to another
    protected void moveAnimals() {

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

                    if (!currentAnimal.isOutOfBounds(parentField.getCols(), parentField.getRows()) && checkAmount(currentAnimal)) {
                        parentField.getLocation(currentAnimal.getY(), currentAnimal.getX()).addOrganizm(currentAnimal);
                        this.removeAnimal(currentAnimal);

                    } else {
                        currentAnimal.setY(oldY);
                        currentAnimal.setX(oldX);
                    }
                }
            }
        }
    }

    // Organisms in the location look for a partner to reproduce with
    protected void lookingForCouple() {

        HashMap<Class<?>, List<Organizm>> map = new HashMap<>();
        for (Organizm org : organizms) {
            map.computeIfAbsent(org.getClass(), k -> new ArrayList<>()).add(org);
        }


        for (List<Organizm> sameTypeOrganisms : map.values()) {
            for (int i = 0; i < sameTypeOrganisms.size(); i++) {
                Organizm currentOrganizm = sameTypeOrganisms.get(i);
                if (!currentOrganizm.isCanBreed()) continue;

                AnimalTypes type = AnimalTypes.valueOf(currentOrganizm.getClass().getSimpleName().toUpperCase());

                if (sameTypeOrganisms.size() > type.getAmount()) {
                    return;
                }

                for (int j = i + 1; j < sameTypeOrganisms.size(); j++) {
                    Organizm partner = sameTypeOrganisms.get(j);
                    if (!partner.isCanBreed()) continue;

                    Organizm child = currentOrganizm.breed(partner);
                    if (child != null) {
                        addOrganizm(child);
                        break;
                    }
                }
            }
        }

        for (Organizm organism : organizms) {
            organism.resetBreed();
        }
    }



    // Count how many of a specific organism type are in this location
    protected int countOrgranizm(Organizm organizm) {
        int currentCount = 0;
        for (Organizm org : organizms) {

            if (org.getClass().equals(organizm.getClass())) {
                currentCount++;
            }
        }
        return currentCount;
    }

    // Simulate the hunting behavior where predators hunt for prey
    protected void simulateHunt() {
        for (int i = 0; i < organizms.size(); i++) {
            Organizm organizm = organizms.get(i);

            if (organizm.isNotMoved()) {
                for (int j = 0; j < organizms.size(); j++) {
                    Organizm possibleOrganizm = organizms.get(j);
                    if (organizm.eat(possibleOrganizm)) {
                        this.removeAnimal(possibleOrganizm);
                        break;
                    }
                }
            }
        }
    }
}
