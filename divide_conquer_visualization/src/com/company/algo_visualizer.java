package com.company;
import java.util.Random;    //Library used to randomly generate numbers
import java.util.Arrays;    //Library used to sort the arrays
public class algo_visualizer {

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

        // Get
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
     *   2 -> O(m + log n) approach – find a location loc of k with binary-search and then count the number
     *  of occurrences of k to the left and right of loc (m is the total number of occurrences)
     *   3 -> O(log n) approach – locate the boundaries of the block of occurrences of k in a.
     */
    public int sequential_search(int[] array, int k){
        int count = 0;      // We just need it for testing // ********* Remove this line later
        int comparisons = 0;    // Check the number of comparisons made to find the number

        for(int i = 0 ; i < array.length; i++){ //loop array
            comparisons++;
            if(array[i] <= k){ //array is sorted, if iterator gets bigger than 'k', then 'k' wasnt found
                if(array[i] == k){
                    while(array[i] == k){ 
                        count++; 
                        comparisons++;
                        i++;
                    }
                    System.out.println(k + " was found and counted, ending 'for loop'...");
                    break;
                }
            }else{ //end the search as soon as iterator becomes bigger than 'k'
                System.out.println(k + " was not found in the array");
                return comparisons;
            }
        }
        System.out.println(k + " appears " + count + " times and was compared " + comparisons + " times");
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

        public void startTest(int sizeArray){
            int[] array = new int[sizeArray];

            System.out.print("Array with SMALL blocks of repeated keys: ");
            int k = fillArraySmallBlocks(array, sizeArray); // k is a random number
            Arrays.sort(array);
            printArray(array, sizeArray);
            
            //testing sequential search
            // int comparisons = sequential_search(array, k);
            // System.out.println("The number of comparisons made in O(n) approach is: " + comparisons);

            //testing binary search
            int comparisons = binary_search(array, k);
            System.out.println("The number of comparisons made O(m + log n) approach is: " + comparisons);

            System.out.print("\nArray with BIG blocks of repeated keys: ");
            int k2 = fillArrayBigBlocks(array, sizeArray);
            Arrays.sort(array);
            printArray(array, sizeArray);

            // testing sequential search
            // int comparisons2 = sequential_search(array, k2);
            // System.out.println("The number of comparisons made in O(n) approach is: " + comparisons2);
            
            //testing binary search
            int comparisons2 = binary_search(array, k2);
            System.out.println("The number of comparisons made for O(m+log n) approach is: " + comparisons2);
    

    }

    public algo_visualizer (){
        startTest(20);
    }

    public static void main(String[] args) {
        System.out.println("\n It’s Not Whether You Get Knocked Down, It’s Whether You Get Up. <3 \n");
        new algo_visualizer();
    }
}
