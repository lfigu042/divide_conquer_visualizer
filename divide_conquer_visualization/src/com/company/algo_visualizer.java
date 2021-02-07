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

    public void startTest(int sizeArray){
        int[] array = new int[sizeArray];
        System.out.println("Array with SMALL blocks of repeated keys: ");
        fillArraySmallBlocks(array, sizeArray);
        Arrays.sort(array);
        printArray(array, sizeArray);
        System.out.println("Array with BIG blocks of repeated keys: ");
        fillArrayBigBlocks(array, sizeArray);
        Arrays.sort(array);
        printArray(array, sizeArray);
    }

    public algo_visualizer (){
        startTest(20);
    }

    public static void main(String[] args) {
        System.out.print("It’s Not Whether You Get Knocked Down, It’s Whether You Get Up. <3");
        new algo_visualizer();
    }
}
