package core;

import java.util.Random;

public class RandomUtil {
    private static Random random = new Random();
    public static int randomInt(int max) {

    return random.nextInt(max);
    }

    public static int getNumber(int min, int max) {
       return random.nextInt(max-min);
    }
}