package core;

import organizm.Animal;
import organizm.Organizm;
import organizm.herbivorouse.*;
import organizm.predator.*;
import organizm.Plant;


public class Field {
    // Dimensions of the field
    private int rows;
    private int cols;
    // 2D array representing each location in the field
    private Location[][] locations;

    // Constructor to initialize field with given dimensions
    public Field(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.locations = new Location[rows][cols];

        // Initialization steps
        initialize();
        generateGrass();
        generateAnimals();
        shuffle();


    }

    // Initialize each location in the 2D array
    private void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.locations[i][j] = new Location(i, j, this);
            }
        }
    }
    // Generate grass in each location of the field
    private void generateGrass() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                locations[i][j].generateGrass();
            }
        }
    }

    // Add specific type of animal to the field
    private void addAnimal(AnimalTypes animalTypes) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
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

    // Generate animals and populate them in the field
    public void generateAnimals() {
        AnimalTypes[] animalTypes = AnimalTypes.values();
        for (AnimalTypes currentType : animalTypes) {
            addAnimal(currentType);
        }
    }

    // Simulate reproduction for the animals
    private void reproduceSimulation() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                locations[i][j].lookingForCouple();
            }
        }
    }

    // Shuffle the order of animals in each location
    private void shuffle() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                locations[i][j].shuffleAnimals();
            }
        }
    }
    // Simulate the entire ecosystem
    public void symulate() {
        while (endOfSimulation()) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Location currentLocation = locations[i][j];
                    try {
                        printStatistic();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    currentLocation.simulateHunt();
                    currentLocation.moveAnimals();
                   reproduceSimulation();

                }
            }
            runResetFlag();
        }
    }
    // Reset flags after each simulation step
    private void runResetFlag() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Location currentLocation = locations[i][j];
                currentLocation.resetFlag();
            }
        }
    }

    // Determine if the simulation should end based on the organisms remaining
    private boolean endOfSimulation() {
        int countOfPredators = 0;
        int countOfHebivorouses = 0;
        int countOfPlants = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

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

        } else return true;
    }

    // Print the statistics (number of each type of organism) to the console
    public void printStatistic() throws InterruptedException {
        int countOfPredators = 0;
        int countOfHebivorouses = 0;
        int countOfPlants = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                for (Organizm currentOrganizm : locations[i][j].getOrganizms()) {
                    if (currentOrganizm instanceof Predator) {
                        countOfPredators++;
                        ;
                    } else if (currentOrganizm instanceof Herbivorous) {
                        countOfHebivorouses++;

                    } else if (currentOrganizm instanceof Plant) {
                        countOfPlants++;

                    }
                }
            }
        }
        System.out.println("Count of predators : " + countOfPredators + ".\n" + "Count of hebivorouses : " + countOfHebivorouses + ".\n" + "Count of plants : " + countOfPlants + ".");
        System.out.println();
        countOfPredators = 0;
        countOfHebivorouses = 0;
        countOfPlants = 0;
        Thread.sleep(1000);
    }
    // Getter and Setter methods
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    // Get the location at a specific row and column
    public Location getLocation(int y, int x) {
        return locations[y][x];
    }

}



