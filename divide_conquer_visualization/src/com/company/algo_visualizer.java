package com.company;
import java.util.Random;    //Library used to randomly generate numbers
import java.util.Arrays;    //Library used to sort the arrays
public class algo_visualizer {

    /**
     * This method populates the array randomly while maintaining small blocks of duplicated keys.
     * This is archived by producing random numbers in a range from 0 to a number bigger than the size of the array.
     */
    public void  fillArraySmallBlocks(int[] array, int sizeArray){
        Random rnd = new Random();
        for(int i = 0; i < sizeArray; i++){
            // r.nextInt((max - min) + 1) + min;
            array[i] = rnd.nextInt(sizeArray + 5); // [0...sizeArray+10]
        }
    }

    public void fillArrayBigBlocks(int[] array, int sizeArray){
        Random rnd = new Random();
        int maxRange = sizeArray / 2;
        for(int i = 0; i < sizeArray; i++){
            // r.nextInt((max - min) + 1) + min;
            array[i] = rnd.nextInt(maxRange); // [0...(sizeArray/2)]
        }
    }


    /**
     * printArray() simply prints an array
     */
    public void printArray(int[] array, int sizeArray){
        System.out.print("\n[");
        for (int i=0; i< sizeArray; i++){
            System.out.print(array[i] + ",");
        }
        System.out.println("]");
    }
    /**
     * Three methods for finding appearances of element:
     *   1 -> O(n) approach – sequentially test each element
     *   2 -> O(m + log n) approach – find a location loc of k with binary-search and then count the number
     * of occurrences of k to the left and right of loc (m is the total number of occurrences)
     *   3 -> O(log n) approach – locate the boundaries of the block of occurrences of k in a.
     */
    public int sequential_search(int[] array, int k){
        int count = 0;
        for(int i = 0 ; i< array.length; i++){
            if( array[i] == k){
                count++;
            }
        }
        System.out.println(k + " appears " + count + " times \n");
        return count;
    }
    public int binary_search(int[] array, int k) {
        return 0;
    }
    public int divide_conquer_search(int[] array, int k) {
        return 0;
    }

        public void startTest(int sizeArray){
        int[] array = new int[sizeArray];

        System.out.print("Array with SMALL blocks of repeated keys: ");
        fillArraySmallBlocks(array, sizeArray);
        Arrays.sort(array);
        printArray(array, sizeArray);
//        testing sequential search
        sequential_search(array, 1);

        System.out.print("Array with BIG blocks of repeated keys: ");
        fillArrayBigBlocks(array, sizeArray);
        Arrays.sort(array);
        printArray(array, sizeArray);
//        testing sequential search
        sequential_search(array, 1);
    }

    public algo_visualizer (){
        startTest(20);
    }

    public static void main(String[] args) {
        System.out.println("It’s Not Whether You Get Knocked Down, It’s Whether You Get Up. <3 \n");
        new algo_visualizer();
    }
}
