/*
 * Copyright 2017.
 * Lia.
 */
package waspexterminator;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 *
 * @author Lia
 *
 */
public abstract class Utilities {

    /**
     * Array used to refill the ArrayList nests which contains the amount of
     * wasps that lives inside each nest.
     */
    private static int[] WaspPopulation = {100, 200, 327, 440, 450, 639, 650, 678, 750, 801, 945, 967};
    private static int numOfBombs = 3;
    private static double[][] distance = new double[12][3];
    private static Random rand = new Random();

    /**
     * Function CalculateFitness which calculates the fitness (the amount of the
     * killed wasps) for the whole population.
     *
     * @param chromosomes is an ArrayList that contains the chromosomes of the
     * generation.
     * @param map is a HouseMap Object which contains the position of the nests
     * inside the house and the amount of wasps that live inside each one.
     * @return an ArrayList that contains the amount of wasps that each
     * chromosome killed.
     */
    public static ArrayList<Integer> CalculateFitness(ArrayList<Chromosome> chromosomes, HouseMap map) {

        double maxDistance = map.getMaxDistance();

        ArrayList<Integer> deadWasps = new ArrayList();

        chromosomes.stream().forEach((_item) -> {
            deadWasps.add(0);
        });

        int wannabeKilledWasps = 0;

        ArrayList<Nest> nests = map.getMap();

        for (int i = 0; i < chromosomes.size(); i++) {

            distance = calculateDistance(chromosomes.get(i), nests);
            deadWasps.add(i, 0);

            for (int bomb = 0; bomb < numOfBombs; bomb++) {
                for (Nest nest : nests) {

                    wannabeKilledWasps = (int) (nest.getWasps() * (maxDistance / ((20 * distance[nests.indexOf(nest)][bomb])) + 0.00001));

                    if (nest.getWasps() - wannabeKilledWasps < 0) {

                        if (deadWasps.get(i) - ((deadWasps.get(i) * 20) / 100) > 0) {
                            deadWasps.add(i, deadWasps.get(i) - ((deadWasps.get(i) * 20) / 100));
                        }

                    } else {

                        nest.setWasps(nest.getWasps() - wannabeKilledWasps);
                        deadWasps.add(i, deadWasps.get(i) + wannabeKilledWasps);
                    }
                }
            }

            /**
             * Refilling with the correct amount of wasps that live inside the
             * nests.
             */
            for (int nest = 0; nest < nests.size(); nest++) {
                nests.get(nest).setWasps(WaspPopulation[nest]);
            }
        }

        return deadWasps;
    }

    /**
     * Calculates the distance between the bombs of a chromosome and the nests
     * inside the house.
     *
     * @param chromosome is the chromosome that contains the bombs. It is needed
     * to calculate the distance
     * @param nest is an ArrayList that contains all the nests which are inside
     * the house.
     *
     * @return a double array with the distance for each bomb of the chromosome
     * to every nest.
     */
    public static double[][] calculateDistance(Chromosome chromosome, ArrayList<Nest> nest) {

        int NestIndex = 0;

        for (Nest n : nest) {
            for (int bomb = 0; bomb < numOfBombs; bomb++) {

                switch (bomb) {

                    case 0:
                        distance[NestIndex][bomb] = (Math.sqrt(Math.pow(chromosome.getFirstXCoordinate() - n.getXCoordinate(), 2)
                                + Math.pow(chromosome.getFirstYCoordinate() - n.getYCoordinate(), 2)));
                        break;

                    case 1:
                        distance[NestIndex][bomb] = (Math.sqrt(Math.pow(chromosome.getSecondXCoordinate() - n.getXCoordinate(), 2)
                                + Math.pow(chromosome.getSecondYCoordinate() - n.getYCoordinate(), 2)));
                        break;

                    case 2:
                        distance[NestIndex][bomb] = (Math.sqrt(Math.pow(chromosome.getThirdXCoordinate() - n.getXCoordinate(), 2)
                                + Math.pow(chromosome.getThirdYCoordinate() - n.getYCoordinate(), 2)));
                        break;

                }
            }

            NestIndex++;
        }
        return distance;

    }

    /**
     * Function tournamentSelection picks a random amount (between 3 and 5) of
     * chromosomes and returns the index of the one with the highest fitness.
     *
     * @param chromosomeWithFitness is a TreeMap that the keySet is the fitness
     * of each chromosome and the valueSet is the index of each fitness in the
     * ArrayList at the main function.
     *
     * @return the chromosome with the highest fitness between the ones that
     * were picked randomly.
     */
    public static int tournamentSelection(TreeMap<Integer, Integer> chromosomeWithFitness) {

        TreeMap<Integer, Integer> tournament = new TreeMap();
        ArrayList<Integer> fitness = new ArrayList(chromosomeWithFitness.keySet());
        ArrayList<Integer> indexes = new ArrayList(chromosomeWithFitness.values());

        int pick;
        int tournamentSize = rand.nextInt(2) + 3;

        for (int tSize = 0; tSize < tournamentSize; tSize++) {

            pick = rand.nextInt(chromosomeWithFitness.size() - 3) + 3;
            tournament.put(fitness.get(pick), indexes.get(pick));
        }

        return tournament.lastEntry().getValue();
    }

    /**
     *
     * @param chromosomeWithFitness is a TreeMap that the keySet is the fitness
     * of each chromosome and the valueSet is the index of each fitness in the
     * ArrayList at the main function.
     * @return the index of the chromosome with the highest fitness 
     */
    public static int rouletteWheelSelection(TreeMap<Integer, Integer> chromosomeWithFitness) {

        ArrayList<Integer> fitness = new ArrayList(chromosomeWithFitness.keySet());
        ArrayList<Integer> indexes = new ArrayList(chromosomeWithFitness.values());

        int sum = 0;
        int index, i = -1, s = 0;
        int random;

        for (Integer fitnes : fitness) {
            sum += fitnes;
        }

        random = rand.nextInt(sum);

        do {
            i++;
            index = indexes.get(i);
            s += fitness.get(i);

        } while (s < random);

        return index;
    }

    /**
     * Function crossOver is responsible for recombining the population of
     * parents at each generation. The amount of cuts are two and the position
     * is choosen randomly but at a correct way, in order to avoid cutting the
     * chromosome at a position between the set of coordinates of a bomb.
     *
     * @param tournamentChromosome is the ArrayList that contains the parents
     * that were choosen for this generation.
     *
     * @return an ArrayList with the recombined chromosomes.
     */
    public static ArrayList<Chromosome> crossOver(ArrayList<Chromosome> tournamentChromosome) {

        int randFather, randMother, randCut;

        ArrayList<Chromosome> childrenChromosomes = new ArrayList();
        randCut = ((rand.nextInt(2) + 1) * 2) - 1;

        Chromosome child;

        int mask[] = new int[6];

        for (int chromo = 0; chromo < tournamentChromosome.size(); chromo++) {
            child = new Chromosome();
            for (int chromoCut = 0; chromoCut < 6; chromoCut++) {

                if (chromoCut <= randCut) {
                    mask[chromoCut] = 0;
                } else {
                    mask[chromoCut] = 1;
                }
            }

            randFather = rand.nextInt((tournamentChromosome.size() - 1) + 1);
            randMother = rand.nextInt((tournamentChromosome.size() - 1) + 1);

            for (int chromoCut = 0; chromoCut < 6; chromoCut++) {
                if (mask[chromoCut] == 0) {
                    child.setCoordinate(tournamentChromosome.get(randFather).getCoordinate(chromoCut), chromoCut);
                } else {
                    child.setCoordinate(tournamentChromosome.get(randMother).getCoordinate(chromoCut), chromoCut);
                }
            }

            childrenChromosomes.add(child);
        }

        return childrenChromosomes;
    }

    /**
     * Function mutation that mutates a chromosome at a random position.
     *
     * @param chromosome is the chromosome that needs to be mutated.
     *
     * @return the mutated chromosome.
     */
    public static Chromosome mutation(Chromosome chromosome) {

        chromosome.setCoordinate(rand.nextInt(100), rand.nextInt(6));
        return chromosome;
    }

}// end Utilities
