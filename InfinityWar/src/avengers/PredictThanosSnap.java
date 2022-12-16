package avengers;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes. 
 * Then, write into the output file a boolean (true or false) indicating if 
 * the graph is still connected.
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 *    1. seed (long): a seed for the random number generator
 *    2. p (int): number of people (vertices in the graph)
 *    2. p lines, each with p edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency matrix for
 * an undirected graph. 
 * 
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 * 
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) { 
 *     if (StdRandom.uniform() <= 0.5) { 
 *          delete vertex;
 *     }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 * 
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 * 
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, isConnected is true if the graph is connected,
 *   false otherwise):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(isConnected);
 * 
 * @author Yashas Ravi
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in predictthanossnap.out
*/

public class PredictThanosSnap {
	 
    public static void main (String[] args) {
 
        if ( args.length < 2 ) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }

        String PredictThanosSnapInputFile = args[0];
        String PredictThanosSnapOutputFile = args[1];

        StdIn.setFile(PredictThanosSnapInputFile);

        long seed = StdIn.readLong(); // a seed for the random number generator
        int p = StdIn.readInt(); // number of people (vertices in the graph).

        int [][] adjacencyMatrix = new int[p][p];

        // populate adjacency matrix area:


        for (int i =0; i<adjacencyMatrix.length; i++){
            for (int j =0; j<adjacencyMatrix[i].length; j++){
                adjacencyMatrix[i][j] = StdIn.readInt();
            }
        }

        boolean [] visitedMatrixToBeChecked = new boolean[p];
        boolean [] deletedMatrixToBeChecked = new boolean[p];

        // Step 2: find a way to delete the nodes by replacing each value we are looking at with 0

        // basically replace the 1's with 0's:

        StdRandom.setSeed(seed);

        boolean isConnected = true;

        for (int i = 0; i< p; i++){
            if (StdRandom.uniform()<=0.5){
            for (int j =0; j<adjacencyMatrix[i].length; j++){
                
                    // If StdRandom.uniform() is less than OR equal to 0.5, then delete the vertex.
                    // deletes connection between vertex and deletes vertex
                    adjacencyMatrix[i][j] =0;
                    adjacencyMatrix[j][i] =0;
                    deletedMatrixToBeChecked[i] = true;

                    //this checks if the nodes are connected (might need to do this after we delete the nodes):

                    // isConnected = returnTrueOrFalse(adjacencyMatrix, i, j);
                }
            }
        }

        for (int i =0; i< deletedMatrixToBeChecked.length; i++){
            if (deletedMatrixToBeChecked[i]== false){
                depthFirstSearch(i, adjacencyMatrix, visitedMatrixToBeChecked);
                break;
            }
        }

        for (int i =0; i<visitedMatrixToBeChecked.length; i++){
            if (deletedMatrixToBeChecked[i]==false && visitedMatrixToBeChecked[i]==false){
                isConnected = false;
                break;

            }
        }
        //delete nodes using depth first search to traverse graph:


    
        // int current =0;
        // boolean [] currentSpotInAdjacencyMatrix = new boolean[adjacencyMatrix.length];
        // for (int i =0; i < adjacencyMatrix.length; i++){
        //     for (int j=0; j<adjacencyMatrix[i].length; j++){
        //         current=adjacencyMatrix[i][j];
        //         currentSpotInAdjacencyMatrix[i] = false;
        //         DFS(current, currentSpotInAdjacencyMatrix,adjacencyMatrix);
        //     }
        // }
        StdOut.setFile(PredictThanosSnapOutputFile);
        StdOut.print(isConnected);



        // StdOut.setFile(PredictThanosSnapOutputFile);
        // StdOut.println(trueOrFalse);

        // for loop
        // for loop

        

        
    	// WRITE YOUR CODE HERE

    }


    private static void depthFirstSearch(int placeOfNode,int[][] adjacencyMatrix, boolean [] visitedMatrixToBeChecked){
        visitedMatrixToBeChecked[placeOfNode] = true;
        for (int i = 0; i< adjacencyMatrix[0].length; i++){
            if ((!visitedMatrixToBeChecked[i]) && adjacencyMatrix[placeOfNode][i] == 1){
                depthFirstSearch(i, adjacencyMatrix, visitedMatrixToBeChecked);
            }
        }
    }
    // private static boolean returnTrueOrFalse(int [][] adjacencyMatrixPosition , int i, int j){
    //     if (adjacencyMatrixPosition[i][j]==0 && adjacencyMatrixPosition[j][i]==0){
    //         return adjacencyMatrixPosition[i][j] ==0 && adjacencyMatrixPosition[j][i]==1;
    //     }

    //     return (adjacencyMatrixPosition[i][j] ==1 && adjacencyMatrixPosition[j][i]==1);
    // }

    // private static void DFS(int current, boolean[] isVisited, int [][] adjacencyMatrix){
    //     isVisited[current] = true;
    //     for (int dest : adjacencyMatrix[adjacencyMatrix.length]){
    //         if (!isVisited[dest]){
    //             DFS(current, isVisited, adjacencyMatrix);
    //         }
    //     }


    // }
}
