package avengers;
import java.util.*;
/**
 * Given a Set of Edges representing Vision's Neural Network, identify all of the 
 * vertices that connect to the Mind Stone. 
 * List the names of these neurons in the output file.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * MindStoneNeighborNeuronsInputFile name is passed through the command line as args[0]
 * Read from the MindStoneNeighborNeuronsInputFile with the format:
 *    1. v (int): number of neurons (vertices in the graph)
 *    2. v lines, each with a String referring to a neuron's name (vertex name)
 *    3. e (int): number of synapses (edges in the graph)
 *    4. e lines, each line refers to an edge, each line has 2 (two) Strings: from to
 * 
 * Step 2:
 * MindStoneNeighborNeuronsOutputFile name is passed through the command line as args[1]
 * Identify the vertices that connect to the Mind Stone vertex. 
 * Output these vertices, one per line, to the output file.
 * 
 * Note 1: The Mind Stone vertex has out degree 0 (zero), meaning that there are 
 * no edges leaving the vertex.
 * 
 * Note 2: If a vertex v connects to the Mind Stone vertex m then the graph has
 * an edge v -> m
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
 *     //Call StdOut.print() for EVERY neuron (vertex) neighboring the Mind Stone neuron (vertex)
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/MindStoneNeighborNeurons mindstoneneighborneurons.in mindstoneneighborneurons.out
 *
 * @author Yashas Ravi
 * 
 */


public class MindStoneNeighborNeurons {
    
    public static void main (String [] args) {
        
    	if ( args.length < 2 ) {
            StdOut.println("Execute: java MindStoneNeighborNeurons <INput file> <OUTput file>");
            return;
        }
    	
    	// WRITE YOUR CODE HERE

        //STEP 1:

        //reads file name from command line:
        String MindStoneNeighborNeuronsInputFile = args[0];
        String MindStoneNeighborNeuronsOutputFile = args[1]; // part of step 2

        // Sets the input file:
        StdIn.setFile(MindStoneNeighborNeuronsInputFile);
        StdOut.setFile(MindStoneNeighborNeuronsOutputFile);






        int v = StdIn.readInt(); // number of neurons or vertices in the graph. (17 for the first input file)

        // String [] firstArray = new String[v];

        // USE A HASHMAP

        HashMap<String, String> values = new HashMap<>();

        String neurons = "";
        String anything = "";

        for (int i =0; i<v; i++){
            neurons = StdIn.readString();
            values.put(neurons, anything);
        }
        // 3. e (int): number of synapses (edges in the graph)



        // next 16 lines:
        int e = StdIn.readInt(); // reads number of edges in graph


        // HashMap<String, Integer> values2 = new HashMap<>();
        String edge = "";
        

        for (int i =0; i<e; i++){
            edge = StdIn.readString();
            String edgeItsConnectedTo = StdIn.readString();
            values.put(edge, edgeItsConnectedTo);
        }
        
        
        String saver = "";
        for (String key : values.keySet()){
            if (values.get(key).equals("")){
                saver = key;


            }
        }

        for (String key : values.keySet()){
            if (values.get(key).equals(saver)){
                StdOut.println(key);
            }
        }
        //e x 2 array
        // 4. e lines, each line refers to an edge, each line has 2 (two) Strings: from to

        // need an array:

        // 16 by 2 lines in the first input file:

        // String secondArray [][] = new String[e][2];


        // for (int i=0; i<e; i++){
        //     secondArray[i][0] = StdIn.readString(); // reads a , b , c , d, ect.
        //     secondArray[i][1] = StdIn.readString(); // reads d , d , d , x, ect.

        //     // need to find a way to loop through each element of the first column, then loop through all elements of the second column,
        //     // then read the element where the element only exists in the second column and not the first, and designate that as the mindstone
        //     // key.

        //     //reads the first and second String from each line.
        // }


        // String first = firstArray[0];

        // for (int i =1; i<firstArray.length-1; i++){
        //     if (secondArray[i][1]!=firstArray[i]){
        //         StdOut.print(secondArray[i][0]);
        //     }
        // }

        // for (int i =0; i<firstArray.length; i++){
        //     for (int j =0; j<secondArray.length; j++){
        //         if (secondArray[j][1]!=firstArray[i]){
        //         }
        //     }
        // }
        // count for e
        // based on e
        // int mindstone;
        // StdOut.setFile(MindStoneNeighborNeuronsOutputFile);
        // for (int i =0; i <array.length; i++){
        //     for (int j =0; j<array[i].length; j++){
        //         if (array[i][1] != array[j][0]){
        //             mindstone = i;
        //             StdOut.print(array[mindstone][0]);
        //         }
        //     }
        // }

        // Step 2:

        // Identify the vertices that connect to the Mind Stone vertex. 
        // * Output these vertices, one per line, to the output file. * 

        // find mindStone vertex:
        // Out-degree of a vertex is the number edges which are coming out from the vertex.
        // so the mindstone is not pointing to anything (x in the first input files case):

        // use for loop to loop through the edges again and then check if edge variable != edgeItsConnectedToToFormSynapses and use that variable
        // as the mindStone variable because that means it has an outdegree of zero.


        
    }
    // private static String getNeuronName(int v){
    //     String neuronName = "";
    //     for (int i =0; i<v; i++){
    //         // 2. v lines, each with a String referring to a neuron's name (vertex name)
    //         neuronName = StdIn.readString();   
    //     }
    //     return neuronName;

    // }
}
