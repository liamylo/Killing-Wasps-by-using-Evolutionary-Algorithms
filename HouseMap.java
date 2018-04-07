/*
 * Copyright 2017.
 * Lia.
 */
package waspexterminator;

import java.util.ArrayList;

/**
 * @author Lia
 */
public class HouseMap {

    private ArrayList<Nest> map = new ArrayList();

    private double maxDistance;

    private int x;
    private int y;

    public HouseMap() {
        fill();
        maxDistance = Math.sqrt(2) * 100;
    }

    public HouseMap(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ArrayList<Nest> getMap() {
        return map;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    /**
     * Funcion fill that fills the ArrayList with the nests that are inside the
     * house.
     */
    private void fill() {
        map.add(new Nest(25, 65, 100));
        map.add(new Nest(23, 8, 200));
        map.add(new Nest(7, 13, 327));
        map.add(new Nest(95, 53, 440));
        map.add(new Nest(3, 3, 450));
        map.add(new Nest(54, 56, 639));
        map.add(new Nest(67, 78, 650));
        map.add(new Nest(32, 4, 678));
        map.add(new Nest(24, 76, 750));
        map.add(new Nest(66, 89, 801));
        map.add(new Nest(84, 4, 945));
        map.add(new Nest(34, 23, 967));

    }
}
