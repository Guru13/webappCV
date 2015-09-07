package lesson1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        System.out.format("Heeeello " + args[0] + " World!");
        Car raceCar = new RaceCar();
        Car simpleCar = new SimpleCar();
        raceCar.go();
        simpleCar.go();
        System.out.println(raceCar.getEngineVolume());
        System.out.println(simpleCar.getEngineVolume());
        List<String> list = new ArrayList<>();
        list.add("string");
        list.add("string1");
        list.add("string2");
        list.add("string3");

        String[] array = list.toArray(new String[list.size()]);

    }
}
