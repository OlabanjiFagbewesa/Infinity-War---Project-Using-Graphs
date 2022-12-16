package avengers;

/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }


    	// WRITE YOUR CODE HERE

        //reads file name from command line:

        String LocateTitanInputFile = args[0];
        String LocateTitanOutputFile = args[1];

        // Set the input file:
        StdIn.setFile(LocateTitanInputFile);

        //Code to read from input file:
        int g = StdIn.readInt(); // reads the amount of lines (or generators or vertices in the graph). 

        double [][] newArray = new double[g][2];

        //loops through each vertice and gets either (int) generator number, or (double) functionality value:
        for (int i=0; i<g; i++){
            // int intReader = StdIn.readInt(); // reads the ints from the 6 lines.
            // double doubleReader = StdIn.readDouble(); // reads the doubles from the 6 lines.
            newArray[i][0] = StdIn.readInt();
            newArray[i][1] = StdIn.readDouble();
        }

        //loops through the adjacency matrix:

        int [][] adjacencyMatrix = new int[g][g];
        for (int i =0; i<adjacencyMatrix.length; i++){
            for (int j =0; j<adjacencyMatrix[i].length; j++){
                adjacencyMatrix[i][j] = StdIn.readInt(); // populates the adjacency matrix with edge values.
                // (the energy cost to travel from one generator to another).
            }
        }
        // now both arrays are populated.

        //STEP 2:

        // now we must update the adjacency matrix to change every edge weight:
        

        // make a duplicate array so we can manipulate our adjacency matrix array:

        int [][] adjacencyMatrixDuplicate = new int[adjacencyMatrix.length][adjacencyMatrix[0].length];
        for (int i =0; i <adjacencyMatrixDuplicate.length; i++){
            for (int j=0; j<adjacencyMatrixDuplicate[i].length; j++){
                adjacencyMatrixDuplicate[i][j] = adjacencyMatrix[i][j];
            }
        }

        //Now calculate energy cost (which would be an integer):
        int energyCost = 0;
    
        for (int i =0; i<adjacencyMatrixDuplicate.length; i++ ){
            for (int j=0; j<adjacencyMatrixDuplicate[i].length; j++){
                energyCost= adjacencyMatrixDuplicate[i][j]; // energy cost is really just the int in the a certain position in the identity
                // matrix.
                double changedWeight = energyCost/(newArray[i][1]*newArray[j][1]);
                int changedWeight1 = (int) changedWeight;
                adjacencyMatrixDuplicate[i][j] = changedWeight1;
                adjacencyMatrix[i][j] = adjacencyMatrixDuplicate[i][j];

            }
        }
        // now adjacencyMatrix (the original one) is changed.

        // StdOut.setFile(LocateTitanOutputFile);

        // // now we must print adjacency matrix to the output file:
        // for (int i = 0; i<adjacencyMatrix.length; i++){
        //     for (int j=0; j<adjacencyMatrix[i].length; j++){
        //         StdOut.print(adjacencyMatrix[i][j] + "\t");
        //     }
        //     StdOut.println();
        // }

        // STEP 2 WORKS

        // STEP 3:

        // Create an array that keeps track of the MINIMUM cost
        // to reach evry node FROM node 0.
        int [] minCost = new int[adjacencyMatrix.length];

        //Create an array that keeps track of which nodes are in the path already:
        boolean [] DijkstraSet = new boolean[adjacencyMatrix.length];

        // Set each minCost value to infinity (Integer.MAX_VALUE)
        // except node 0 since we are STARTING at node 0:


        //getMinCostNode() method
        // can make this into a method and have it return minCost

        for (int i =0; i<minCost.length; i++){
            if (i == 0){
                // if node is 0
                minCost[i] = 0;
            }
            else{
                minCost[i] = Integer.MAX_VALUE; // sets minCost value to infinity
            }
        }

        // for ( # of nodes - 1 iterations)

        // determine the node with the MINIMUM cost from node 0.
        // store this node in currentSource. 


        int max=0;


        for (int i = 0; i<adjacencyMatrix.length-1; i++){
            int currentSource = getMinCostNode(minCost, DijkstraSet, adjacencyMatrix.length);
            // Add currentSource to DijstraSet (we are visiting now)

            DijkstraSet[currentSource] = true;
            // find the neighbors:

            //currentSource represents where we already are.

            for (int a =0;a<adjacencyMatrix.length; a++){
                // checks if:
                // edge is present, checking to see if vertex j is not included in the shortest path, it's a neighbor, have we visited it yet, and if the edgeweight is less than thr min cost
                // an edge weight is just the cost to get to one node from another. 
                if(adjacencyMatrix[currentSource][a]>0 && !DijkstraSet[a] && minCost[currentSource] != Integer.MAX_VALUE && minCost[currentSource]+adjacencyMatrix[currentSource][a]<minCost[a]){
                    minCost[a] = minCost[currentSource] + adjacencyMatrix[currentSource][a];
                    max = minCost[a];

                } // makes sure there is a connection by putting 
            }
            


            // // Relax all the nodes adjacent to the node we are visiting (currentSource).

            // //for all currentSource neighbors:

            // // need a 3x3 grid to see the neighbors:
            // int count = 0;
            // int rows1[] = new int[3];
            // int columns1[] = new int[3];
            // rows1[1] = energyCost; // 1 is the middle so row is the position.
            // columns1[1] = energyCost; //1 is the middle so col is the position.
            // if (energyCost==0){ // if at first row
            // rows1[0] =adjacencyMatrix.length-1;
            // }
            // else{
            //     rows1[0]= energyCost-1;
            // }
            // if (energyCost==0){ // if at first col
            //     columns1[0]=adjacencyMatrix[i].length-1;
            // }
            // else{
            //     columns1[0] = energyCost-1;
            // }
            // if (energyCost==adjacencyMatrix.length-1){ // if at last row
            //     rows1[2]=0;
            // }
            // else{
            //     rows1[2]=energyCost+1;
    
            // }
            // if (energyCost == adjacencyMatrix[i].length-1){ // if at last col
            //     columns1[2]=0;
            // }
            // else{
            //     columns1[2] = energyCost+1;
                
            // }
            // // int currentCost = minCost;
            // int currentCost[] = new int[adjacencyMatrix.length];
            // for (int b =0; b<minCost.length; b++){
            //     currentCost[b] = minCost[b];
            // }
            // // think of gameOfLife
            // for (int a = 0; a<rows1.length; a++){
            //     if (i!=1 && currentSource!= Integer.MAX_VALUE && Math.abs(0-minCost[a])<energyCost ){

            //         minCost[currentSource] = minCost[currentSource]+ Math.abs(currentSource-(minCost[a]));
            //     }

            // }




        }
        StdOut.setFile(LocateTitanOutputFile);
        StdOut.print( minCost[g-1]);
        // int a = 0; 
        // for (int i =0; i<adjacencyMatrix.length; i++){
        //     a += minCost[i];
        //     StdOut.print(minCost[a] + " ");
        // }
    }
    private static int getMinCostNode (int [] minCost, boolean [] DijkstraSet, int numNodes){
        int min = Integer.MAX_VALUE;
        int vertex = 0;
        for (int i =0; i<numNodes; i++){
            if (DijkstraSet[i]==false && minCost[i]<min){
                vertex = i;
                min = minCost[i];
            }
        }
        return vertex;
        // for (int i =0; i<minCost.length; i++){
        //     if (minCost[i]==0){
        //         // if node is 0
        //     return 0;
        //     }
            
        //         // minCost[i] = Integer.MAX_VALUE; // sets minCost value to infinity
        // }
        // return Integer.MAX_VALUE;
    }
}
