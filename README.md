# Killing-Wasps-by-using-Evolutionary-Algorithms

A small project on how to implement an evolutionary algorithm and utilize it in order to eliminate some wasp nests that exist inside a house's attic.

## Problem Description
You just bought a house and you discover that the attic is full of wasps aou decide to eliminate them before moving in. In order to do so, you equip yourself with three insecticide bombs which have a specific range and must be placed really close to the nests.

Unfortunately, you only have three of these bombs and they are not enough to kill all the wasps, but there is a way to maximize the amount of wasps that will be killed.

#### You are given the following hints:

- A map that describes the location of the nests and also the amount of wasps that live in each one of them.

| Nest        | Wasps           |  Nest Coordinates   |  |
| :---        |      :---:      |     ---: | :---        |
|             |                 | X - Axes | Y - Axes    |
| 1           |	100	            | 25	     | 65          |
| 2	          | 200	            | 23	     | 8           |
| 3           |	327             |	7	       | 13          |
| 4           |	440             |	95	     | 53          |
| 5           |	450	            | 3	       | 3           |
| 6	          | 639             |	54	     | 56          |
| 7           |	650             |	67       | 78          |
| 8           |	678             |	32       | 4           |
| 9           |	750      	      | 24	     | 76          |
| 10          |	801      	      | 66	     | 89          |
| 11          |	945      	      | 84	     | 4           |
| 12          |	967      	      | 34	     | 23          |


- A formula that describes the relationship between the distance of the bomb from the nest and the killing rate: 

                              K = n * dmax / (20 * d + 0.00001)

     ###### where:
     
  - K: Dead wasps inside the nest

  - n: Amount of wasps inhabiting the nest

  - d: Distance between the bomb and the nest
              \[d = \sqrt{\left ( x1 - x2 \right )^{2} + \left ( y1 - y2 \right )^{2}}\]
  
  - dmax: Maximum distance between 2 nests

The goal of this project is to find out the optimum coordinates for positioning the bombs in order to kill the greatest amount of wasps.
