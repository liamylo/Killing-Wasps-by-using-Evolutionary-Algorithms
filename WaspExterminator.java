/*
 * Copyright 2017.
 * Lia.
 */
package waspexterminator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Lia
 * @see Utilities for more info about the functions used inside the main class.
 */
public class WaspExterminator {

    public static void main(String[] args) {

        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        ArrayList<Chromosome> tournamentChromosome = new ArrayList();
        ArrayList<Chromosome> rouletteWheelChromosome = new ArrayList();
        ArrayList<Integer> fitness = new ArrayList();
        ArrayList<Integer> newfitness = new ArrayList();
        ArrayList<Chromosome> chromosome = new ArrayList();

        TreeMap<Integer, Integer> chromosomeWithFitness = new TreeMap();

        Chromosome bestChromoOfGeneration = new Chromosome();

        int maxFitnessOfGeneration = -99999,
                max = -99999,
                generationOfMax = 0,
                generation = 0,
                tournamentSelected,
                rouletteWheelSelected,
                selection_method;

        HouseMap map = new HouseMap();

        System.out.print("Insert the amount of population (it must be positive) : ");
        int population = input.nextInt();

        while (population <= 0) {
            System.out.print("Please insert a positive amount of population : ");
            population = input.nextInt();
        }

        System.out.print("Insert the amount of generations (it must be positive) : ");
        int maxGenerations = input.nextInt();

        while (maxGenerations <= 0) {
            System.out.print("Please insert a positive amount of generations : ");
            maxGenerations = input.nextInt();
        }

        System.out.println("Selection method:"
                + "\n1.Tournament Selection"
                + "\n2.Roulette Wheel Selection"
                + "\n\n Choose between 1-2 : ");
        selection_method = input.nextInt();

        while (selection_method < 1 || selection_method > 2) {
            System.out.print("Please choose between 1-2 : ");
            selection_method = input.nextInt();
        }

        System.out.println();

        /**
         * Initialization of the ArrayList<Chromosome> with chromosomes which
         * contains random values.
         */
        for (int pop = 0; pop < population; pop++) {

            Chromosome chromo = new Chromosome(rand.nextInt((100 - 1) + 1) + 1,
                    rand.nextInt((100 - 1) + 1) + 1,
                    rand.nextInt((100 - 1) + 1) + 1,
                    rand.nextInt((100 - 1) + 1) + 1,
                    rand.nextInt((100 - 1) + 1) + 1,
                    rand.nextInt((100 - 1) + 1) + 1);

            chromosome.add(chromo);
        }

        /**
         * Start of the evolution procedure. The termination conditions are (1)
         * reaching the max amount of generations (2) the amount of generations
         * passed since the last change of the chromosome with the best fitness
         * gets the value of 500.
         */
        while (generation < maxGenerations && generation - generationOfMax < 500) {
            generation++;

            /**
             * Clearing the data from previous iterations which are saved inside
             * the ArrayLists.
             */
            rouletteWheelChromosome.clear();
            tournamentChromosome.clear();
            fitness.clear();
            newfitness.clear();

            /**
             * Calculating fitness for all the chromosomes using the
             * Utilities.CalculateFitness.
             */
            fitness = Utilities.CalculateFitness(chromosome, map);

            /**
             * Inserting the values of the fitness and the index of it inside
             * the TreeMap.
             */
            for (int pop = 0; pop < population; pop++) {
                chromosomeWithFitness.put(fitness.get(pop), pop);
            }

            /**
             * Iteration used to choose the parents for the crossover procedure.
             *
             * Using the tournamentSelection/RouletteWheelSelection function we
             * get the index of a parent and we store the parent inside the
             * ArrayList tournamentChromosome/RouletteWheelChromosome. Also we
             * insert the fitness of the parent inside the ArrayList newfitness.
             */
            switch (selection_method) {
                case 1:
                    for (int pop = 0; pop < population; pop++) {
                        tournamentSelected = Utilities.tournamentSelection(chromosomeWithFitness);
                        tournamentChromosome.add(chromosome.get(tournamentSelected));
                        newfitness.add(fitness.get(tournamentSelected));
                    }
                    break;

                case 2:

                    for (int pop = 0; pop < population; pop++) {
                        rouletteWheelSelected = Utilities.rouletteWheelSelection(chromosomeWithFitness);
                        rouletteWheelChromosome.add(chromosome.get(rouletteWheelSelected));
                        newfitness.add(fitness.get(rouletteWheelSelected));
                    }
                    break;
            }

            /**
             * This iteration is responsible for setting the value of the
             * variable generationOfMax, which helps calculating the second
             * termination condition (2).
             */
            for (int pop = 0; pop < population; pop++) {
                if (newfitness.get(pop) > max) {
                    max = newfitness.get(pop);
                    generationOfMax = generation;
                }
            }

            /**
             * Using the Utilities.crossOver we get the population calculated by
             * doing crossover to the ArrayList that contains the parents for
             * this generation.
             */
            switch (selection_method) {
                case 1:
                    chromosome = Utilities.crossOver(tournamentChromosome);
                    break;
                case 2:
                    chromosome = Utilities.crossOver(rouletteWheelChromosome);
                    break;
            }

            int index;

            /**
             * Iteration responsible for doing mutation to the population with
             * possibility of 0.1.
             */
            for (Chromosome chromo : chromosome) {

                index = chromosome.indexOf(chromo);

                if (Math.random() < 0.1) {
                    chromosome.set(index, Utilities.mutation(chromo));
                }
            }
        }

        /**
         * Iteration responsible for getting the chromosome with the best
         * fitness of the last generation.
         */
        switch (selection_method) {
            case 1:
                for (int chromo = 0; chromo < tournamentChromosome.size(); chromo++) {

                    if (newfitness.get(chromo) > maxFitnessOfGeneration) {
                        maxFitnessOfGeneration = newfitness.get(chromo);
                        bestChromoOfGeneration = tournamentChromosome.get(chromo);
                    }
                }
                break;
            case 2:
                for (int chromo = 0; chromo < rouletteWheelChromosome.size(); chromo++) {

                    if (newfitness.get(chromo) > maxFitnessOfGeneration) {
                        maxFitnessOfGeneration = newfitness.get(chromo);
                        bestChromoOfGeneration = rouletteWheelChromosome.get(chromo);
                    }
                }

                break;
        }

        System.out.println("Generation : " + generationOfMax
                + "\nBest Chromosome : " + bestChromoOfGeneration
                + "\nFitness : " + maxFitnessOfGeneration);

    } // end Main class

}
