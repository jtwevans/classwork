/******************************************************************************
 *  Compilation:  javac Sort.java
 *  Execution:    java Sort
 *
 *  Part 1 asks for an array length, max value and number of trials and tells the
 *  user the average number of switches for selection and bubble sorts with a
 *  random array with specifications entered by the user.
 *  Part 2 uses selection and bubble sort to sort 2d arrays with specifications
 *  from user.
 *  Part 3 asks for rows, cols, max value and trials and tells the user on average
 *  How many switches bubble sort and selection sort took for a random 2d array.
 *
 *  This code does not employ seperate a method for creating an array of Random
 *  integers as it would skew the average results. Therefore this happens in the
 *  main.
 *
 *  Jackson Evans
 ******************************************************************************/

import java.util.Random;
import java.util.Scanner;

public class Sort{
  public static int bubble_switches = 0;
  public static int selection_switches = 0;
  //attributes static so I can use them for part 1 and 3

  public static void bubbleSort(int[] arr){
    int size = arr.length;
    int numsorted = 0;
    int temp = 0;

    for(int i = 1; i < size; i++){
      for (int j = 1; j < (size - numsorted); j++){

    // if the number on the left arr[j-1] is greater than the
    // number on the right arr[j], switch the two
        if(arr[j-1] > arr[j]){
          temp = arr[j];
          arr[j] = arr[j-1];
          arr[j-1] = temp;
          bubble_switches ++;
        }
      }
  // For each pass of the loop, at least one more number is sorted
  numsorted++;
  }
}

  public static void selectionSort(int[] arr){
    int pivot = arr.length - 1;
    int maxvalue = arr[pivot];
    int maxindex = pivot;

    for(int i = 1; i < arr.length; i++){
      for (int j = 0; j < pivot; j++){
        // if the value at arr[j] is greater than our pivot
        // switch the value with the pivots value.
        if(arr[j] > maxvalue){
          maxindex = j;
          maxvalue = arr[j];
          selection_switches++;
        }
      }
      int temp = arr[pivot];
      arr[pivot] = maxvalue;
      arr[maxindex] = temp;
      pivot--;
      maxindex = pivot;
      maxvalue = arr[pivot];
    }
  }

  public static void twoDbubbleSort(int[][] arr ){
    //this method takes a 2d array and sorts it by column and row
/*
    int size = arr.length;
    int width = arr[0].length;
    int temp = 0;

    for(int i = 0; i < size; i++){
      for (int j = 0; j < width; j++){
        for(int p = 1; p < width; p++){

        if(arr[i][p-1] > arr[i][p]){
          temp = arr[i][p];
          arr[i][p] = arr[i][p-1];
          arr[i][p-1] = temp;
        }
        }

      }
    }
    for(int i = 0; i < size; i++){
      for (int j = 0; j < width; j++){
        for(int p = 1; p < size; p++){

        if(arr[p-1][i] > arr[p][i]){
          temp = arr[p][i];
          arr[p][i] = arr[p-1][i];
          arr[p-1][i] = temp;
        }
        }

      }
    }
    */
    //Above, you can see me reinventing the wheel.

    for(int i = 0; i < arr.length; i++){
      bubbleSort(arr[i]);
      //sorting each row
    }
    int[] column_array = new int[arr.length];
    //making an array of just a column
    for(int j = 0; j< arr[0].length; j++){
      for(int k=0; k<arr.length; k++){
        column_array[k] = arr[k][j];
      }
      bubbleSort(column_array);
      //sort that column
      for(int p=0; p<arr.length; p++){
        arr[p][j] = column_array[p];
        //reassign values
      }
    }
  }

  public static void twoDselectionSort(int[][] arr){
    //this method takes a 2d array and sorts it by column and row
    for(int i = 0; i < arr.length; i++){
      selectionSort(arr[i]);
      //sorting the each row
    }
    int[] column_array = new int[arr.length];
    for(int j = 0; j< arr[0].length; j++){
      for(int k=0; k<arr.length; k++){
        //creating an array of columns
        column_array[k] = arr[k][j];
      }
      selectionSort(column_array);
      //sorting the columns
      for(int p=0; p<arr.length; p++){
        arr[p][j] = column_array[p];
        //reassigning values
      }
    }
  }

  public static void printArray(int[][] arr){
    //prints 2d array
    for (int i = 0; i< arr.length; i++){
      for (int j = 0; j< arr[0].length; j++){
        System.out.print(arr[i][j] + "\t");
            //displaying the contents of the array
          }
        System.out.println();
        //printing new line
        }
  }

  public static void main(String[]args){
    //main function
    Scanner sc = new Scanner(System.in);
    System.out.println("Part 1");
    System.out.println("Enter an array length: ");
    int length = sc.nextInt();
    System.out.println("Enter the maximum value: ");
    int maxval = sc.nextInt();
    System.out.println("Enter the number of trials: ");
    int num_trials = sc.nextInt();
    for(int i = 0; i<num_trials; i++){
      //creating random array
      int[] arr = new int[length];
      Random rand = new Random();
      for(int j = 0; j<length; j++){
        arr[j] = rand.nextInt(maxval);
      }
      int[] arr2 = arr.clone();
      bubbleSort(arr);
      selectionSort(arr2);
    }
    System.out.println("Bubble Sort Avg: " + bubble_switches/num_trials);
    System.out.println("Selection Sort Avg: " + selection_switches/num_trials +  "\n");

    System.out.println("Part 2");
    System.out.println("Enter an array height (rows): ");
    int rows = sc.nextInt();
    System.out.println("Enter an array width (columns): ");
    int columns = sc.nextInt();
    System.out.println("Enter the maximum value: ");
    maxval = sc.nextInt();
    int[][] arr3 = new int[rows][columns];
    for(int i = 0; i<rows; i++){
      for(int j = 0; j<columns; j++){
        //creating 2d array with random ints
        Random rand2 = new Random();
        arr3[i][j] = rand2.nextInt(maxval);
      }
    }
    System.out.println("Unsorted:");
    printArray(arr3);

    twoDbubbleSort(arr3);
    System.out.println("Bubble Sorted:");
    printArray(arr3);

    int[][] arr4 = new int[rows][columns];
    for(int i = 0; i<rows; i++){
      for(int j = 0; j<columns; j++){
        Random rand3 = new Random();
        arr4[i][j] = rand3.nextInt(maxval);
        }
      }

    System.out.println("Unsorted:");
    printArray(arr4);

    twoDselectionSort(arr4);
    System.out.println("Selection Sorted:");
    printArray(arr4);

    System.out.println();
    System.out.println("Part 3");
    selection_switches = 0;
    bubble_switches = 0;
    //resetting static attributes for part 3
    System.out.println("Enter an array height (rows): ");
    rows = sc.nextInt();
    System.out.println("Enter an array width (columns): ");
    columns = sc.nextInt();
    System.out.println("Enter the maximum value: ");
    maxval = sc.nextInt();
    System.out.println("Enter the number of trials: ");
    num_trials = sc.nextInt();
    int[][] arr5 = new int[rows][columns];
    int[][] arr6  = new int[rows][columns];

    for(int i = 0; i<num_trials; i++){
      Random rand3 = new Random();
      for(int j = 0; j<arr5.length; j++){
        for(int k = 0; k<arr5[0].length; k++){
          int x = rand3.nextInt(maxval);
          arr5[j][k] = x;
          arr6[j][k] = x;
        }
      }
      twoDbubbleSort(arr5);
      twoDselectionSort(arr6);
    }
    System.out.println("Bubble Sort Avg: " + bubble_switches/num_trials);
    System.out.println("Selection Sort Avg: " + selection_switches/num_trials);
  }
}
