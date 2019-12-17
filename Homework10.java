/******************************************************************************
 *  Compilation:  javac Homework10.java HashTable.java Player.java LinkedList.java
 *  Execution:    java Homework10 HashTable Player LinkedList
 *
 *  Reads in file of information. Iterates through file and adds to hash table.
 *  Allows user to search for a player, which returns the player object and
 *  prints how many traversals made in the linked list.
 *
 *  Jackson Evans
 ******************************************************************************/

import java.util.Scanner;
import java.io.File;

public class Homework10{
  //homework 10 class
  public static void main(String[]args){
    //main function
    System.out.println("Reading in player data from NBA.txt");
    HashTable ht = new HashTable();
    //creating hashtable object
    File file = new File("NBA.txt");
    //reading file
    Scanner sc;
    //scanner object
    try{
      //in case there is an issue with file
      sc = new Scanner(file);
    }
    catch(Exception e){
      System.out.println(e);
      return;//exit
    }
    while(sc.hasNextLine()){
      //iterate through file
      String str = sc.nextLine();
      String[] arr2 = str.split(",");
      //creating array for input
      Boolean wrong = false;
      for(int i=0;i<arr2.length;i++){
        if(arr2[i].equals("")){
          //making sure we don't have any issues with null
          wrong = true;
        }
      }
      if(wrong == false){
        //want to add the player
        Player p = new Player(arr2);
        //player class
        String lower_name = p.name.toLowerCase();
        //lowering the name to make hash code
        long h = ht.hashCode(lower_name);
        //using hash code method
        int x = ht.code_to_index(h);
        //modding that to make an index <=500
        ht.hash_insert(p,x);
        //inserting
      }else{
        System.out.println("Invalid field for " + arr2[1].split("\\\\")[0]);
      }

    }
    System.out.println("\nWelcome to the NBA Reference Database for 2018-2019.");
    Scanner sc2 = new Scanner(System.in);

    while (true){
      //main loop for the user
      System.out.println("Enter a player's name (type \\exit to quit): "); //\exit issue
      String x = sc2.nextLine();
      //taking the entire input
      String input = x.toLowerCase();
      long input_code = ht.hashCode(input);
      int input_index = ht.code_to_index(input_code);

      if(ht.array[input_index]!=null){
        //if the user  enters something that is in hash table
        try{
          Player t = ht.array[input_index].search(input);
        }
        catch(Exception e){
          System.out.println("Player not found");
        }

      }else if(input.equals("\\exit")){
        //if they want to exit
        break;
      }else{
        System.out.println("Player not in the hash table.");
        //not in hash table, go b ack to start of loop
      }

    }
  }
}
