Documentation
--------------------------

Tsp-ea project revision 125, Group 9, 31/08/2014.
Tsp-ea project is the implementation of different evolutionary algorithms for the traveling salesman problem (TSP). It is written and compiled using Java SDK(1.6.0 65) and Ant(1.9.4).


GENERAL USAGE NOTES
--------------------------

- Checkout the source code from subversion

	Open terminal, and type this command to anonymously check out the latest project source code:
	svn checkout http://tsp-ea.googlecode.com/svn/trunk/ tsp-ea


- Config.txt				Edit it to adjust several parameters to before first running the program

	# TSPLIB File			Accept one or more TSPLIB file names in this line, separated by commas. Make sure the files are in the data directory. E.g. tsp=eil51.tsp, lin105.tsp

	# Population Size		Accept an integer number to specify the size of population, the recommended values are: 10, 20, 50, 100

	# Generations			Accept an integer number to specify how many generations to evolve (also the terminate condition), the recommended values are: 5000, 10000, 20000

	# Repeat			Used to control how many repeats you want to run a single TSPLIB file, the values can vary from 1 to 10

	# InverOver Rate		Accept a floating-point value for running inver-over algorithm, ranges from 0.0 to 1.0, usually small

	# InverOver			Accept a boolean value (true or false) to specify whether the inver-over algorithm is going to use, this will disable other options below

	# Mutation Rate			Accept a floating-point value to control the frequency mutation happens when evolving, from 0.0 to 1.0

	# Crossover Rate		Accept a floating-point value to control the frequency crossover happens when evolving, from 0.0 to 1.0

	# Mutation Type			1 - Insert, 2 - Swap, 3 - Inversion, 4 - Scramble, choose any one of the numbers to specify a mutation algorithm

	# Crossover Type		1 - Order, 2 - PMX, 3 - Cycle, 4 - Edge Recombination, choose any one of the numbers to specify a crossover algorithm

	# Selection Type		1 - FPS, 2 - Tournament, choose any one of the numbers to specify a selection method

	# Use Elitism			Accept a boolean value (true or false) to specify whether elitism selection is going to be activated

	# Elitism Size			Accept an integer value to set the number of elites chosen from the population, ranges from 0 to the size of the population, usually smaller is better


- Build and Run

	There is a "build.xml" file in the root folder. All the source code are in the "src" folder.

	- To compile the source code and run the program, open terminal and jump to the root folder, then type:

		ant

	  The object files will be in the "build" folder, whilst the jar file can be found in the "lib" folder.

	- After first compiling, if you edit the config.txt file, just type:

		ant run

	  The program will read from the config file and run the algorithms.

	- To cleanup all the object files and generated jar file , open terminal and jump to the root folder, then type:

		ant clean



Group Information
----------------------
Zhu Zheng		1633928
Haijin Lin		1613340
Tao Wang		1638414
Yuankang Zhao		1640844

