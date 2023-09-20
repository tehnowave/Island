package core;

import animals.Organizm;

import java.util.ArrayList;



public class Location {
    private int x;
    private int y;
    private ArrayList<Organizm> organizms;

    public Location(int x, int y) {

        this.x = x;
        this.y = y;
        this.organizms = new ArrayList<>();
    }

    public void addOrganizm(Organizm organizm) {
        if (organizm != null) {
            organizms.add(organizm);
        //    Collections.shuffle(organizms);
        } else {
            //    System.out.println("Warning: Tried to add a null Organizm.");
        }
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

    public ArrayList<Organizm> getOrganizms() {
        return organizms;
    }

    public void setOrganizms(ArrayList<Organizm> organizms) {
        this.organizms = organizms;
    }


    public void removeAnimal(Organizm organizm) {
        this.organizms.remove(organizm);
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
