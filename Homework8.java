/******************************************************************************
 *  Compilation:  javac Homework8.java BST.java
 *  Execution:    java Homework8
 *
 *  Main method and Homework 8 class to test functionality
 *
 *  Jackson Evans
 ******************************************************************************/


//read books from file, add books to BST, check to see if available, remove
import java.io.File;
import java.util.Scanner;

public class Homework8{
  public static void main(String[]args){
    BST bst = new BST();
    File file = new File("library.txt");
    Scanner sc;
    try{
      //in case there is an issue with file
      sc = new Scanner(file);
    }
    catch(Exception e){
      System.out.println(e);
      return;//exit
    }
    while(sc.hasNextLine()){
      String str = sc.nextLine();
      String[] arr = str.split(",");
      double d = Double.parseDouble(arr[0]);

      bst.add(d,arr[1],arr[2]);
      //add creates the node
    }
    System.out.println("Here are the titles in the library:\n");
    bst.print();
    //printing the library

    System.out.println("\nsize is: " + bst.size());

    System.out.println("\nusing ddn of 501, we see that the book is not available: " + bst.isAvailable(501));
    System.out.println("using ddn of 500, we see that the book is available: " + bst.isAvailable(500));

    //remove
    System.out.println("deleting " + bst.remove(001.942).title + "\n");
    //this works
    //bst.print();

    System.out.println("deleting " + bst.remove(133.8).title + "\n");
    //this works
    //bst.print();

    System.out.println("deleting " + bst.remove(289.73).title + "\n");
    //this works
    //bst.print();

    System.out.println("deleting " + bst.remove(701.1).title + "\n");
    //this works
    //bst.print();

    System.out.println("deleting " + bst.remove(500).title + "\n");
    //this works
    bst.print();
    System.out.println("\nsize is: " + bst.size());
  }
}
