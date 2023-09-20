package core;

import animals.Animal;
import animals.Organizm;
import animals.herbivorouse.*;
import animals.predator.*;
import plant.Plant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Field {
    public static int rows;
    public static int colls;
    private final Location[][] locations;


    public Field(int rows, int colls) {
        Field.rows = rows;
        Field.colls = colls;
        this.locations = new Location[rows][colls];

        initialize();
        generateGrass();
        generateAnimals();
        shuffle();


    }


    public void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colls; j++) {
                this.locations[i][j] = new Location(i, j);
            }
        }
    }

    public void generateGrass() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colls; j++) {
                for (int k = 0; k < RandomUtil.randomInt(200) + 1; k++) {
                    locations[i][j].addOrganizm(new Plant());
                }
            }
        }
    }

    private void addAnimal(AnimalTypes animalTypes) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colls; j++) {


                int amount = RandomUtil.randomInt(animalTypes.getAmount()) + 1;


                for (int k = 0; k < amount; k++) {
                    Animal currentAnimal = null;
                    switch (animalTypes) {
                        case WOLF -> currentAnimal = new Wolf(i, j);
                        case HORSE -> currentAnimal = new Horse(i, j);
                        case FOX -> currentAnimal = new Fox(i, j);
                        case EAGLE -> currentAnimal = new Eagle(i, j);
                        case SNAKE -> currentAnimal = new Snake(i, j);
                        case BEAR -> currentAnimal = new Bear(i, j);
                        case CATERPILLAR -> currentAnimal = new Caterpillar(i, j);
                        case SHEEP -> currentAnimal = new Sheep(i, j);
                        case RABBIT -> currentAnimal = new Rabbit(i, j);
                        case MOUSE -> currentAnimal = new Mouse(i, j);
                        case GOAT -> currentAnimal = new Goat(i, j);
                        case DEER -> currentAnimal = new Deer(i, j);
                        case DUCK -> currentAnimal = new Duck(i, j);
                        case BUFFALO -> currentAnimal = new Buffalo(i, j);
                        case BOAR -> currentAnimal = new Boar(i, j);

                    }
                    locations[i][j].addOrganizm(currentAnimal);
                }

            }
        }

    }


    public void generateAnimals() {
        AnimalTypes[] animalTypes = AnimalTypes.values();
        for (AnimalTypes currentType : animalTypes) {
            addAnimal(currentType);
        }
    }

    private void moveAnimals(Location currentLocation) {

        ArrayList<Organizm> animalList = currentLocation.getOrganizms();

        for (int i = animalList.size() - 1; i >= 0; i--) {
            Organizm organizm = animalList.get(i);

            if (organizm instanceof Animal currentAnimal) {
                if (currentAnimal.isNotMoved()) {

                    if (currentAnimal.getSatiety() < 1) {
                        currentLocation.removeAnimal(currentAnimal);
                        continue;
                    }
                    int oldY = currentAnimal.getY();
                    int oldX = currentAnimal.getX();


                    currentAnimal.move();

                    if (checkAmount(currentAnimal)) {
                        locations[currentAnimal.getY()][currentAnimal.getX()].addOrganizm(currentAnimal);
                        currentLocation.removeAnimal(currentAnimal);
                        //         System.out.println(currentAnimal + " from : " + oldY + "," + oldX + " moved to : " + currentAnimal.getY() + "," + currentAnimal.getX() + ".");
                    } else {
                        currentAnimal.setY(oldY);
                        currentAnimal.setX(oldX);
                    }
                }
            }
        }
    }

    private void resetFlag() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colls; j++) {
                Location currentLocation = locations[i][j];

                ArrayList<Organizm> animalList = currentLocation.getOrganizms();

                for (Organizm organizm : animalList) {
                    if (organizm instanceof Animal) {
                        ((Animal) organizm).resetMovedFlag();
                    }
                }
            }
        }
    }

    private void reproduceSimulation() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colls; j++) {
                locations[i][j].lookingForCouple();


            }
        }
    }

    private boolean checkAmount(Animal organizm) {

        AnimalTypes type = AnimalTypes.valueOf(organizm.getClass().getSimpleName().toUpperCase());  // Преобразование в enum

        Location location = locations[organizm.getY()][organizm.getX()];

        return location.countOrgranizm(organizm) > type.getAmount();
    }

    private void shuffle() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colls; j++) {
                Collections.shuffle(locations[i][j].getOrganizms());
            }
        }
    }


    public void symulate() throws InterruptedException {
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(this::printStatistic, 0, 1, TimeUnit.SECONDS);
        while (endOfSimulation()) {



            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colls; j++) {
                    final Location currentLocation = locations[i][j];
                    printStatistic();
                    currentLocation.simulateHunt();

                    moveAnimals(currentLocation);
                //    reproduceSimulation();

                }
            }


            resetFlag();

        }

//        scheduler.shutdown();
//        scheduler.shutdownNow();
    }


    private boolean endOfSimulation() {
        int countOfPredators = 0;
        int countOfHebivorouses = 0;
        int countOfPlants = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colls; j++) {

                for (Organizm currentOrganizm : locations[i][j].getOrganizms()) {
                    if (currentOrganizm instanceof Predator) {
                        countOfPredators++;

                    } else if (currentOrganizm instanceof Herbivorous) {
                        countOfHebivorouses++;

                    } else if (currentOrganizm instanceof Plant) {
                        countOfPlants++;

                    }
                }

            }

        }
        if (countOfPredators == 0 && countOfHebivorouses == 0 && countOfPlants == 0) {
            System.out.println("All died");
            return false;
        } else if (countOfPredators > 0 && countOfHebivorouses == 0 && countOfPlants == 0) {
            System.out.println(countOfPredators + " Predators stay alive");
            return false;
        } else if (countOfPredators == 0 && countOfHebivorouses > 0 && countOfPlants == 0) {
            System.out.println(countOfHebivorouses + " Hebivorouses stay alive");
            return false;
        } else if (countOfPredators == 0 && countOfHebivorouses == 0 && countOfPlants > 0) {
            System.out.println(countOfPlants + " Plants stay alive");
            return false;


        } else if (countOfPredators > 0 && countOfHebivorouses == 0 && countOfPlants > 0) {
            System.out.println(countOfPredators + " Predators & plants stay alive");
            return false;

        } else
            return true;
    }

    public void printStatistic() {
        int countOfPredators = 0;
        int countOfHebivorouses = 0;
        int countOfPlants = 0;

//        int countOfLocalPredators = 0;
//        int countOfLocalHebivorouses = 0;
//        int countOfLocalPlants = 0;


        for (int i = 0; i < rows; i++) {


            for (int j = 0; j < colls; j++) {
//                System.out.println("Count of local predators : " + countOfLocalPredators + ".\n" + "Count of local hebivorouses : " + countOfLocalHebivorouses + ".\n" + "Count of local plants : " + countOfLocalPlants + ".");
//                System.out.println();
////                countOfLocalPredators = 0;
////                countOfLocalHebivorouses = 0;
////                countOfLocalPlants = 0;

                for (Organizm currentOrganizm : locations[i][j].getOrganizms()) {
                    if (currentOrganizm instanceof Predator) {
                        countOfPredators++;
                        //                          countOfLocalPredators++;
                    } else if (currentOrganizm instanceof Herbivorous) {
                        countOfHebivorouses++;
                        //                         countOfLocalHebivorouses++;
                    } else if (currentOrganizm instanceof Plant) {
                        countOfPlants++;
                        //                            countOfLocalPlants++;
                    }
                }

            }


        }
        System.out.println("Count of predators : " + countOfPredators + ".\n" + "Count of hebivorouses : " + countOfHebivorouses + ".\n" + "Count of plants : " + countOfPlants + ".");
        System.out.println();
        countOfPredators = 0;
        countOfHebivorouses = 0;
        countOfPlants = 0;
    }
}



