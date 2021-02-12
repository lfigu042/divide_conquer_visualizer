package com.company;
import java.util.Random;    //Library used to randomly generate numbers
import java.util.Arrays;    //Library used to sort the arrays
// Libraries used to save information into a file
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class algo_visualizer {
    /**
     *  The objective of this program is to compare three different algorithm approaches that aim to determine how
     *  many times a given value k occurs in an array sorted in a non-decreasing order.
     */
    public static void main(String[] args) {
        new algo_visualizer();
    }

    public algo_visualizer (){
        String outputFilename = "Asg1-MethodComparison.csv";
        PrintWriter output = null;

        //open output stream
        try {
            output = new PrintWriter(new FileWriter(outputFilename));
        } catch (IOException ex) {
            System.exit(1);
        }

        output.println("Testing methods with arrays containing small blocks of repeated keys , \n");
        output.println("Array size , O(n) , O[m + log(n)] , O[log(n)]");

        for(int i = 5; i < 20; i= i+5){
            System.out.println("Running test: " + (i - (i/5)*4));
            int[] comparisonData = startTest(i);
            output.println(i + "," + comparisonData[0] + "," + comparisonData[1] + ","+ comparisonData[2]);
            System.out.println(" ----------------------------------------------------- ");
        }
        //close output stream
        output.close();
    }

    public int[] startTest(int sizeArray){
        // Array that will store the number of comparisons each method requires to get the number of k repeated keys
        int[] comparisonsData = new int[3];

        // Creation of the array with random values
        int[] array = new int[sizeArray];
        System.out.print("Array with SMALL blocks of repeated keys: ");
        int k = fillArraySmallBlocks(array, sizeArray); // k is a random number
        Arrays.sort(array);
        printArray(array, sizeArray);

        //testing sequential search
        int comparisons = sequential_search(array, k);
        System.out.println("The number of comparisons made in O(n) approach is: " + comparisons);
        comparisonsData[0] = comparisons;

        //testing binary search
        comparisons = binary_search(array, k);
        System.out.println("The number of comparisons made O(m + log n) approach is: " + comparisons);
        comparisonsData[1] = comparisons;

        //testing O(log(n))
        comparisonsData[2] = 0;     //TO BE IMPLEMENTED

        System.out.print("\nArray with BIG blocks of repeated keys: ");
        int k2 = fillArrayBigBlocks(array, sizeArray);
        Arrays.sort(array);
        printArray(array, sizeArray);

        // testing sequential search
        int comparisons2 = sequential_search(array, k2);
        System.out.println("The number of comparisons made in O(n) approach is: " + comparisons2);


        //testing binary search
        comparisons2 = binary_search(array, k2);
        System.out.println("The number of comparisons made for O(m+log n) approach is: " + comparisons2);

        return comparisonsData;
    }

    /**
     * This method populates the array randomly while maintaining SMALL blocks of duplicated keys.
     * This is archived by producing random numbers in a range from 0 to a number bigger than the size of the array.
     */
    public int  fillArraySmallBlocks(int[] array, int sizeArray){
        Random rnd = new Random();
        for(int i = 0; i < sizeArray; i++){
            // r.nextInt((max - min) + 1) + min;
            array[i] = rnd.nextInt(sizeArray + 5); // [0...sizeArray+10]
        }

        // Get k
        int k = rnd.nextInt(sizeArray + 5);
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
            // r.nextInt((max - min) + 1) + min;
            array[i] = rnd.nextInt(maxRange); // [0...(sizeArray/2)]
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
            comparisons++;
            if(array[i] <= k){
                if(array[i] == k){
                    count++;          // We just need it for testing // ********* Remove this line later
                    comparisons++;
                }
            } else {                  //end the search as soon as iterator becomes bigger than 'k' since the array is sorted
                break;
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
        int count = 0;      // We just need it for testing // ********* Remove this line later
        int comparisons = 0;    // Check the number of comparisons made to find the number
        int left = 0;
        int right = array.length - 1;
       
        while(left <= right){
            int middle = (left + right) / 2;
            if (k < array[middle]){
                comparisons++;
                right = middle - 1;
            }else if (k > array[middle]){
                comparisons++;
                left = middle + 1;
            }else{ //'k' has been found
                //iterate to the left and right and find count of 'k'
                int i = middle - 1; //checks to the left 
                int j = middle + 1; //checks to the right
                
                while(i >= 0){
                    if(array[i] == k){
                        i--;
                        comparisons++;
                        count++;                        
                    } else break; 
                }
                while (j < array.length){
                    if (array[j] == k){
                        j++;
                        comparisons++;
                        count++;                       
                    } else break;
                } 
                count++;               
                System.out.println(k + " appears " + count + " times and was compared " + comparisons + " times");
                return comparisons;
            }
        }
        System.out.println(k + " not found");
        return comparisons;
      }

    /**
     * Method for finding appearances of element:
     *   3 -> O(log n) approach – locate the boundaries of the block of occurrences of k in a.
     */
    public int divide_conquer_search(int[] array, int k) {
        return 0;
    }
}
