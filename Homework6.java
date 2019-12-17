/******************************************************************************
 *  Compilation:  javac Homework6.java
 *  Execution:    java Homework
 *
 *  Takes in a file of cargo and orders it based on destination, then cargo type
 *  within that. Then displays everything. Then takes in a destinations file
 *  and offloads any cargo per destination and displays what remains on train.
 *
 *  Jackson Evans
 ******************************************************************************/

import java.util.Scanner;
import java.io.File;

public class Homework6{
  public static void main(String[]args){
    //main function
    DoublyLinkedList list = new DoublyLinkedList();
    //our primary list
    int id_count = 1;
    File file = new File("Cargo.txt");
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
      //add to linked list
      FreightCar f = new FreightCar(id_count, arr);
      DoublyLinkedList.LinkedList_Insert(list, f);
      id_count++;
      //add to ID for the attribute of freightcar
      }
    DoublyLinkedList.printList(list);
    //displaying all of our cargo, sorted

    //destinations
    File file2 = new File("Destinations.txt");
    Scanner sc2;
    try{
      //in case there is issue with file
      sc2 = new Scanner(file2);
    }
    catch(Exception e){
      System.out.print(e);
      return;//exit
    }
    while(sc2.hasNextLine()){
      //while we have more destinations to unload at
      String str = sc2.nextLine();
      System.out.printf("\n\nNow arriving at %s. Unloading cargo.", str);
      DoublyLinkedList.deleteKey(list, str);
      System.out.println("\n");
      //deleting the key which prints out what is unloaded
    }
    System.out.println("\nWhat's left on the train?");
    DoublyLinkedList.printList(list);
  }
}

class DoublyLinkedList{
  public Node head, tail;

  static class Node{
    FreightCar data;
    Node next, prev;

    Node(FreightCar d){
      data = d;
      next = null;
      prev = null;
    }
  }

  public DoublyLinkedList(){
    //constructor
    head = null;
    tail = null;
  }

  public static void printList(DoublyLinkedList list){
    //method to print list
    Node currnode = list.head;
    while(currnode != null){
      //while there exists an element
      System.out.printf("\n%d: %s\t%s", currnode.data.ID, currnode.data.dest, currnode.data.c_type); //formatting
      currnode = currnode.next;
      //iterating
    }
  }
  public static DoublyLinkedList LinkedList_Insert(DoublyLinkedList list, FreightCar data){
    //places in order
    Node currnode = list.head;
    Node newnode = new Node(data);
    Node prevnode = null;
    while(true){
      //while loop so we can break out of it an return every time we insert
    if(currnode == null){
      //empty
      list.head = newnode;
      list.tail = newnode;
      //insert
      break;
      //therefore return
    }
    if(currnode.next==null){
        //if last element
        currnode.next = newnode;
        //assigning new node to end
        newnode.prev = currnode;
        //old tail
        list.tail=newnode;
      //tail updated
      break;
      }
    while(currnode.next!=null && currnode.data.dest.equals(data.dest)==false){
        //while currnode not the last elt and different destination
          prevnode=currnode;
          currnode=currnode.next;
          //going to next elt
    }//exiting means we know there is not a next value or that destinations are equal
    if(currnode.next==null){
        //if last element
      currnode.next = newnode;
      //assigning new node to end
      newnode.prev = currnode;
      //old tail
      list.tail=newnode;
      //tail updated
      break;
      }
      while(currnode.data.dest.equals(data.dest) && currnode.data.c_type.equals(data.c_type)==false){
        //while destinations are equal but NOT the cargo type
        prevnode = currnode;
        currnode = currnode.next;
      }//exiting means either destinations are different, so we want to place previous
      //or that the type has changed, so we want to place previous
      // or that there is a different destination and type, so place previous
    if(currnode.data.dest.equals(data.dest) && currnode.data.c_type.equals(data.c_type)){
      //means that what broke the last while loop was the types being the same,
      //so we add after
      //with all conditions filled, we can safely add something in order
      newnode.next = currnode.next;
      //assigning the next value
      currnode.next=newnode;
      //placing newnode in after current
      newnode.prev=currnode;
      //assigning new node's previous to be current
      break;
      //finished with this element
    }else{
      //comment this
      newnode.prev=prevnode;
      currnode.prev = newnode;
      prevnode.next=newnode;
      newnode.next=currnode;
      break;
    }
  }
    return list;
}
   public static DoublyLinkedList deleteKey(DoublyLinkedList list, String dest){
     Node prevnode = null, currnode = list.head;
     while(currnode.next!=null){
       //traversing through whole list
       while(currnode != null && currnode.data.dest.equals(dest)){
         //not empty and destinations the same
         System.out.printf("\nUnloading %d: %s\t%s.",currnode.data.ID,currnode.data.dest,currnode.data.c_type);

         if(currnode.prev==null){
           //in case currnode is head
           list.head = currnode.next;
           list.head.prev = null;

           //iterate
           prevnode=currnode;
           currnode=currnode.next;

         }else{
           //don't need to worry about case if currnode is tail as it won't enter the
           //first while loop prevnode.next=currnode.next;
           if(currnode.next!=null){
           prevnode.next = currnode.next;
           currnode.next.prev = prevnode;
           currnode = prevnode.next;

            }else{
              //end of list
              prevnode.next=null; //delete item
              break;
            }
           }
         }
       while(currnode.next != null && !currnode.data.dest.equals(dest)){
         //while not empty and destinations not the same we traverse
         prevnode = currnode;
         currnode = currnode.next;
         }
     }
     return list;
   }
}


class FreightCar{
  //the object freight car that we build

  //attributes
  public int ID;
  public String c_type;
  public String dest;

  public FreightCar(int id, String[] arr){
    //constructor
    ID = id;
    c_type = arr[1];
    dest = arr[0];
  }
}
