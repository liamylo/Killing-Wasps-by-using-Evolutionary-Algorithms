/*
 * Copyright 2017.
 * Lia.
 */
package waspexterminator;

/**
 *
 * @author Lia
 */
public class Nest {

    private int xCoordinate, yCoordinate, wasps;

    public Nest(int xCoordinate, int yCoordinate, int wasps) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.wasps = wasps;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getWasps() {
        return wasps;
    }

    public void setWasps(int wasps) {
        this.wasps = wasps;
    }

    @Override
    public String toString() {
        return "Nest{" + "xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate + ", wasps=" + wasps + '}';
    }

}
