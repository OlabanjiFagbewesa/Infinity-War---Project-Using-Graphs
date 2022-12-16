package avengers;

/**
 * Given a starting event and an Adjacency Matrix representing a graph of all possible 
 * events once Thanos arrives on Titan, determine the total possible number of timelines 
 * that could occur AND the number of timelines with a total Expected Utility (EU) at 
 * least the threshold value.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * UseTimeStoneInputFile name is passed through the command line as args[0]
 * Read from UseTimeStoneInputFile with the format:
 *    1. t (int): expected utility (EU) threshold
 *    2. v (int): number of events (vertices in the graph)
 *    3. v lines, each with 2 values: (int) event number and (int) EU value
 *    4. v lines, each with v (int) edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note 1: the last v lines of the UseTimeStoneInputFile is an ajacency matrix for a directed
 * graph. 
 * The rows represent the "from" vertex and the columns represent the "to" vertex.
 * 
 * The matrix below has only two edges: (1) from vertex 1 to vertex 3 and, (2) from vertex 2 to vertex 0
 * 0 0 0 0
 * 0 0 0 1
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * UseTimeStoneOutputFile name is passed through the command line as args[1]
 * Assume the starting event is vertex 0 (zero)
 * Compute all the possible timelines, output this number to the output file.
 * Compute all the posssible timelines with Expected Utility higher than the EU threshold,
 * output this number to the output file.
 * 
 * Note 2: output these number the in above order, one per line.
 * 
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut:
 *     StdOut.setFile(outputfilename);
 *     //Call StdOut.print() for total number of timelines
 *     //Call StdOut.print() for number of timelines with EU >= threshold EU 
 * 
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/UseTimeStone usetimestone.in usetimestone.out
 * 
 * @author Yashas Ravi
 * 
 */

public class UseTimeStone {

    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java UseTimeStone <INput file> <OUTput file>");
            return;
        }
        String UseTimeStoneInputFile = args[0];
        StdIn.setFile(UseTimeStoneInputFile);

        // 1. t (int): expected utility (EU) threshold:
        int t = StdIn.readInt();
        
        //2. v (int): number of events (vertices in the graph)

        int v = StdIn.readInt();

        int eventNumber = 0;
        int associatedEUValue=0;

        int [] valuesArray = new int [v];
        for (int i =0; i<v; i++){
            //3. v lines, each with 2 values: (int) event number and (int) EU value
            eventNumber = StdIn.readInt();
            associatedEUValue = StdIn.readInt();
            valuesArray[eventNumber] = associatedEUValue;
        }

        // 4. v lines, each with v (int) edges: 1 means there is a direct edge between two vertices, 0 no edge:
        int [][] adjacencyMatrix = new int [v][v];
        for (int i =0; i<v; i++){
            for (int j=0; j<v; j++){
                adjacencyMatrix[i][j] = StdIn.readInt();
            }
        }


        String UseTimeStoneOutputFile = args[1];

        // int allPossibleTimelines =0;
        // int EUAmount =0;
        // for (int i =0; i<adjacencyMatrix.length; i++){
        //     for (int j=0; j<adjacencyMatrix[i].length; j++){
        //         EUAmount = EUHigherThanThresholdOfFive(allPossibleTimelines, valuesArray, associatedEUValue, eventNumber);

        //     }
        // }
        int expectedUtilityHigherThanEUThreshhold =0;

        // Compute all the possible timelines, output this number to the output file.
        // Compute all the posssible timelines with Expected Utility higher than the EU threshold,
        // * output this number to the output file.
        int count =0;



    	// WRITE YOUR CODE HERE
        StdOut.setFile(UseTimeStoneOutputFile);
        StdOut.println(traverseAndCalculate(adjacencyMatrix,0, count));
        StdOut.println(EUHigherThanThresholdOfFive(adjacencyMatrix, valuesArray, 0, 0 ,0,t ));
        // StdOut.println(EUHigherThanThresholdOfFive(timelines,valuesArray, associatedEUValue,));
        
    }

    private static int traverseAndCalculate(int [][] adjacencyMatrix, int given, int count){
            int length = adjacencyMatrix[0].length;
            for (int j=0; j<length; j++){
                if (adjacencyMatrix[given][j]==1){
                    count = traverseAndCalculate(adjacencyMatrix,j, count);
                }
                
            }
            count+=1;
            return count;
    }

    private static int EUHigherThanThresholdOfFive(int[][] matrix, int[] euvals, int val, int s, int c, int eu){
        s += euvals[val];

        int j=0;
            while(j<matrix[val].length){

                if (matrix[val][j]==1){
                    c = EUHigherThanThresholdOfFive(matrix, euvals, j, s, c, eu);
                }
            j++;  
            }
            if (s >= eu){
                c+=1;}
            return c;
    }
}
