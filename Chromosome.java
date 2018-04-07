/*
 * Copyright 2017.
 * Lia.
 */
package waspexterminator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lia
 */
public class Chromosome {

    private int firstXCoordinate, firstYCoordinate,
            secondXCoordinate, secondYCoordinate,
            thirdXCoordinate, thirdYCoordinate;

    public Chromosome(int firstXCoordinate, int firstYCoordinate, int secondXCoordinate, int secondYCoordinate, int thirdXCoordinate, int thirdYCoordinate) {
        this.firstXCoordinate = firstXCoordinate;
        this.firstYCoordinate = firstYCoordinate;
        this.secondXCoordinate = secondXCoordinate;
        this.secondYCoordinate = secondYCoordinate;
        this.thirdXCoordinate = thirdXCoordinate;
        this.thirdYCoordinate = thirdYCoordinate;
    }

    public Chromosome() {
    }

    public void setCoordinate(int value, int coordinate) {
        switch (coordinate) {
            case 0:
                setFirstXCoordinate(value);
                break;

            case 1:
                setFirstYCoordinate(value);
                break;

            case 2:
                setSecondXCoordinate(value);
                break;

            case 3:
                setSecondYCoordinate(value);
                break;

            case 4:
                setThirdXCoordinate(value);
                break;

            case 5:
                setThirdYCoordinate(value);
                break;

        }
    }

    public int getCoordinate(int coordinate) {
        switch (coordinate) {
            case 0:
                return getFirstXCoordinate();

            case 1:
                return getFirstYCoordinate();

            case 2:
                return getSecondXCoordinate();

            case 3:
                return getSecondYCoordinate();

            case 4:
                return getThirdXCoordinate();

            case 5:
                return getThirdYCoordinate();

        }

        return 0;
    }

    public void copy(Chromosome chromo) {
        this.firstXCoordinate = chromo.firstXCoordinate;
        this.firstYCoordinate = chromo.firstYCoordinate;
        this.secondXCoordinate = chromo.secondXCoordinate;
        this.secondYCoordinate = chromo.secondYCoordinate;
        this.thirdXCoordinate = chromo.thirdXCoordinate;
        this.thirdYCoordinate = chromo.thirdYCoordinate;

    }

    public int getFirstXCoordinate() {
        return firstXCoordinate;
    }

    public void setFirstXCoordinate(int firstXCoordinate) {
        this.firstXCoordinate = firstXCoordinate;
    }

    public int getFirstYCoordinate() {
        return firstYCoordinate;
    }

    public void setFirstYCoordinate(int firstYCoordinate) {
        this.firstYCoordinate = firstYCoordinate;
    }

    public int getSecondXCoordinate() {
        return secondXCoordinate;
    }

    public void setSecondXCoordinate(int secondXCoordinate) {
        this.secondXCoordinate = secondXCoordinate;
    }

    public int getSecondYCoordinate() {
        return secondYCoordinate;
    }

    public void setSecondYCoordinate(int secondYCoordinate) {
        this.secondYCoordinate = secondYCoordinate;
    }

    public int getThirdXCoordinate() {
        return thirdXCoordinate;
    }

    public void setThirdXCoordinate(int thirdXCoordinate) {
        this.thirdXCoordinate = thirdXCoordinate;
    }

    public int getThirdYCoordinate() {
        return thirdYCoordinate;
    }

    public void setThirdYCoordinate(int thirdYCoordinate) {
        this.thirdYCoordinate = thirdYCoordinate;
    }

    @Override
    public String toString() {
        return "{" + firstXCoordinate + ", " + firstYCoordinate
                + ", " + secondXCoordinate + ", " + secondYCoordinate
                + ", " + thirdXCoordinate + ", " + thirdYCoordinate + '}';
    }

}
