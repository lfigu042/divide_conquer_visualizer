/*
 * ASSIGNMENT #01
 *
 * Laura Figeroa
 * PantherID:
 *
 * Samara Ruiz Sandoval
 * PantherID: 6090384
 */

package com.company;
import java.util.Random;    //Library used to randomly generate numbers
import java.util.Arrays;    //Library used to sort the arrays
// Libraries used to save information into a file
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *  The objective of this program is to compare three different algorithm approaches that aim to determine how
 *  many times a given value k occurs in an array sorted in a non-decreasing order.
 */
public class algo_visualizer {
    //Values for testing
    final int SMALLEST_ARRAY_SIZE = 5;
    final int LARGEST_ARRAY_SIZE = 500;
    final int INCREMENTS = 5;

    public static void main(String[] args) {
        new algo_visualizer();
    }

    public algo_visualizer (){
        PrintWriter output = null;
        boolean testSmallBlocks = false;     // *** CHANGE TO "FALSE" TO TEST LARGE BLOCKS OF REPEATED KEYS ***
        String outputFilename;

        if(testSmallBlocks)
            outputFilename = "Asg1-SmallBlocks.csv";
        else
            outputFilename = "Asg1-LargeBlocks.csv";

        //open output stream
        try {
            output = new PrintWriter(new FileWriter(outputFilename));
        } catch (IOException ex) {
            System.exit(1);
        }

        output.println("Array size , O(n) , O[m + log(n)] , O[log(n)]");

        for(int i = SMALLEST_ARRAY_SIZE; i <= LARGEST_ARRAY_SIZE ; i= i+INCREMENTS){
            int[] comparisonData = startTest(i, testSmallBlocks);
            output.println(i + "," + comparisonData[0] + "," + comparisonData[1] + ","+ comparisonData[2]);
        }
        //close output stream
        output.close();
    }

    public int[] startTest(int sizeArray, boolean testSmallBlocks){
        // Array that will store the number of comparisons each method requires to get the number of k repeated keys
        int[] comparisonsData = new int[3];
        int[] array = new int[sizeArray];

        if(testSmallBlocks){
            int k = fillArraySmallBlocks(array, sizeArray);         // Creation of the array with small blocks
            Arrays.sort(array);
            printArray(array, sizeArray);               // REMOVE: Just for testing purposes
            System.out.println(k+ "\n");                // REMOVE: Just for testing purposes
            comparisonsData[0] = sequential_search(array, k);       // testing O(n)
            comparisonsData[1] = binary_search(array, k);           // testing O(m + log(n))
            comparisonsData[2] = divide_conquer_search(array,k);    // testing O(log(n))
        } else {
            int k2 = fillArrayBigBlocks(array, sizeArray);          // Creation of the array with large blocks
            Arrays.sort(array);
            printArray(array, sizeArray);               // REMOVE: Just for testing purposes
            System.out.println(k2 + "\n");                // REMOVE: Just for testing purposes
            comparisonsData[0] = sequential_search(array, k2);       // testing O(n)
            comparisonsData[1] = binary_search(array, k2);           // testing O(m + log(n))
            comparisonsData[2] = divide_conquer_search(array,k2);    // testing O(log(n))
        }

        return comparisonsData;
    }

    /**
     * This method populates the array randomly while maintaining SMALL blocks of duplicated keys.
     * This is archived by producing random numbers in a range from 0 to a number bigger than the size of the array.
     */
    public int  fillArraySmallBlocks(int[] array, int sizeArray){
        Random rnd = new Random();
        for(int i = 0; i < sizeArray; i++){
            array[i] = rnd.nextInt(sizeArray + 5);          // [0...sizeArray+10]
        }

        int k = rnd.nextInt(sizeArray + 5);                 // Get k
        return k;
    }

    /**
     * This method populates the array randomly while maintaining BIG blocks of duplicated keys.
     * This is archived by producing random numbers in a range from 0 to a number less than the size of the array.
     */
    public int  fillArrayBigBlocks(int[] array, int sizeArray){
        Random rnd = new Random();
        int maxRange = sizeArray / 2;
        for(int i = 0; i < sizeArray; i++){
            array[i] = rnd.nextInt(maxRange);                       // [0...(sizeArray/2)]
        }

        int k = rnd.nextInt(maxRange);
        return k;
    }

    /**
     * printArray() simply prints an array
     */
    public void printArray(int[] array, int sizeArray){
        System.out.print("[");
        for (int i=0; i< sizeArray; i++){
            System.out.print(array[i] + ",");
        }
        System.out.println("]");
    }

    /**
     * Method for finding appearances of element:
     *   1 -> O(n) approach – sequentially test each element
     */
    public int sequential_search(int[] array, int k){
        int count = 0;          // We just need it for testing // ********* Remove this line later
        int comparisons = 0;    // Check the number of comparisons made to find the number

        for(int i = 0 ; i < array.length; i++){ //loop array
            comparisons++;      // comparison for the while loop and if statement
            if(array[i] > k){      //end search as soon as iterator becomes bigger than 'k' since the array is sorted
                break;
            }
            if(array[i] == k){
                count++;
            }
        }
        return comparisons;
    }

    /**
     * Method for finding appearances of element:
     *   2 -> O(m + log n) approach – find a location loc of k with binary-search and then count the number
     *  of occurrences of k to the left and right of loc (m is the total number of occurrences)
     */
    public int binary_search(int[] array, int k) {
        int count = 0;          // Number of k occurrences
        int comparisons = 0;    // Check the number of comparisons made to find the number

        // Pointers used for the binary search method
        int left = 0;
        int right = array.length - 1;
        int loc = -1;             // loc is initialized to -1  in case k is not found

        // We used the Iterative implementation of Binary Search to keep track of the number of comparisons performed
        while(left <= right){
            int middle = (left + right) /2;
            comparisons = comparisons + 2;  // comparison for the while loop and if statement
            if (array[middle] == k) {       // K was present in the middle
                loc = middle;
                break;
            } else if(array[middle] > k){   // If x smaller, ignore right half
                right = middle - 1;
            }else{
                left  = middle + 1;         // If x is bigger, ignore left half
            }
        }

        comparisons++;
        if(loc == -1){                      // k was not found
            return comparisons;
        } else{
            count++;                        // k was found at least once using binary search

            int previous = loc - 1;
            comparisons++;                  // comparison for the while statement
            while(previous >= 0 && array[previous] == k){
                comparisons ++;
                count++;
                previous--;
            }

            int next = loc + 1;
            comparisons++;
            while(next < array.length && array[next] == k){
                comparisons ++;
                count++;
                next++;
            }
        }
        return comparisons;
      }

    /**
     * Method for finding appearances of element:
     *   3 -> O(log n) approach – locate the boundaries of the block of occurrences of k in a.
     */
    public int divide_conquer_search(int[] array, int k) {
        int[] result =locateLeftEnd(array, k);
        int totalComparisons;

        int leftEnd = result[0];
        int comparisons = result[1];
        comparisons++;
        if ( leftEnd >= array.length-1 || array[leftEnd] != k){
            return comparisons;
        }else{
            int[] result2 = locateRightEnd(array, leftEnd, k);
            int rightEnd = result2[0];
            int comparisons2 = result2[1];
            int count = rightEnd - leftEnd + 1;
            totalComparisons = comparisons + comparisons2;
        }
        return totalComparisons;
    }

    /**
     * Helper method of the divide and conquer approach that finds the beginning of the block of k's repeated elements.
     * We used as reference, the slides that professor Hernandez provided
     */
    public int[] locateLeftEnd(int[] array, int k){
        int comparisons = 0;
        int first = 0;
        int last = array.length - 1;

        while(first <= last){
            int middle = (first + last) /2;
            comparisons++;   // We do two comparisons, with the while loop and with the if statement
            if (k <= array[middle]) {
                last = middle -1;
            }else{
                first  = middle + 1;
            }

        }
        int loc = first;
        return new int[]{loc, comparisons};     // Returning both the location and the num of comparisons
    }

    /**
     * Helper method of the divide and conquer approach that finds the beginning of the block of k's repeated elements.
     * We used as reference, the slides that professor Hernandez provided
     */
    public int[] locateRightEnd(int[] array, int first, int k){
        // First will be the location of the left end since we already know the right end can't be before that point.
        int comparisons = 0;
        int last = array.length - 1;

        while(first <= last){
            int middle = (first + last) /2;
            comparisons++;    // We do two comparisons, with the while loop and with the if statement
            if (k >= array[middle]) {
                first  = middle + 1;
            }else{
                last = middle -1;
            }
        }
        int loc = last;
        return new int[]{loc, comparisons};
    }

}
