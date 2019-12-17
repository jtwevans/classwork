/******************************************************************************
 *  Compilation:  javac Homework7.java
 *  Execution:    java Homework7
 *
 *  Takes in a file of operations and orders them based on priority.
 *  Using two CPU's, we dequeue elements of the list based on priority and the
 *  time they take.
 *
 *  Jackson Evans
 ******************************************************************************/

import java.util.Scanner;
import java.io.File;


// A linked list contains a pointer to the first Node, often
// referred to as the head node. A node contains data, and a
// pointer to the next object in the list.
class LinkedList{
  //class linked list used for basis for our queue
  public Node head;
  public Node tail;

  static class Node{
    //class node

    Node next;
    Node prev;
    Process process;

    Node(Process p){
      //constructor for node
      process = p;
      next = null;
      prev = null;
    }
  }

  public LinkedList(){
    //constructor for linkedlist
    head = null;
    tail = null;
  }

  // Inserts a node into the linked list, sorted
  public void insert(Process process){
    Node currnode = head;
    Node newnode = new Node(process);
    Node prevnode = null;
    while(true){
      //while loop so we can break out of it an return every time we insert
      if(currnode == null){
        //empty
        head = newnode;
        tail = newnode;
        //insert
        break;
        //therefore return
      }
      if(currnode.prev==null && currnode.process.priority>newnode.process.priority){
        //if currnode is head and newnode's priority is less than currnode's
        head = newnode;
        newnode.next = currnode;
        break;
      }
      if(currnode.next==null && currnode.process.priority<newnode.process.priority){
          //if last element and currnode's priority less than newnode's
          currnode.next = newnode;
          //assigning new node to end
          newnode.prev = currnode;
          //old tail
          tail=newnode;
        //tail updated
        break;
      }else if(currnode.next==null && currnode.process.priority>newnode.process.priority){
        //if last element but new node is less
        newnode.prev=prevnode;
        currnode.prev = newnode;
        prevnode.next=newnode;
        newnode.next=currnode;
        break;
      }
      while(currnode.next!=null && currnode.process.priority<newnode.process.priority){
        //when not tail and currnode has a lowe priority than newnode
            prevnode=currnode;
            currnode=currnode.next;
            //going to next elt
      }//exiting means we know there is not a next value or that newnode's priority is lower
      if(currnode.next==null){
          //if last element
        currnode.next = newnode;
        //assigning new node to end
        newnode.prev = currnode;
        //old tail
        tail=newnode;
        //tail updated
        break;
      }else if(newnode.process.priority<currnode.process.priority){
        newnode.prev=prevnode;
        currnode.prev = newnode;
        prevnode.next=newnode;
        newnode.next=currnode;
        break;
      }
        prevnode=currnode;
        currnode=currnode.next;
        //continue through list
    }
}

  // This function uses a while loop to print the contents of
  // the linked list.
  public void printList(){
    Node currnode = head;

    while(currnode != null){
      System.out.printf("PID: %2d | %20s | %6d | Priority: %d\n",currnode.process.ID, currnode.process.name, currnode.process.time, currnode.process.priority);

      currnode = currnode.next;
    }
  }
}


class PriorityQueue extends LinkedList{
  //priority queue class
  public int size;
  //size

  public PriorityQueue(){
    //priorityQueue constructor
    size = 0;
  }

  public void addQueue(Process p){
    //addQueue method uses our linkedlist insert function to place in order
    insert(p);
    size++;
    }

  public Process removeQueue(){
    //remove queue method removes a value from the beginning and returns the object
    Process p;

    if (size == 0){
      // The list is empty. Throw an exception
      throw new NullPointerException("Queue is empty.");
    }

    else if (size == 1){
      p = head.process;
      head = null;
      tail = null;
      size--;
    }

    else{

      p = head.process;
      head = head.next;
      head.prev = null;
      size--;

    }
    return p;
  }

  public int sizeQueue(){
    //size method returns size
    return size;
  }
}

class Process{
  //process class
  int ID;
  String name;
  int time;
  int priority;

  Process(String[] arr, int id){
    //process class constructor takes array of information from file and an ID
    //number as a counter
    ID = id;
    name = arr[0];

    //parse through strings to convert to int
    time = Integer.parseInt(arr[1]);
    priority = Integer.parseInt(arr[2]);
  }
}

public class Homework7{
  //class that holds main function
    public static void main(String[]args){
      //main function
      PriorityQueue list = new PriorityQueue();
      //our primary list
      int id_count = 0;
      File file = new File("Processes.txt");
      Scanner sc;
      try{
        //in case there is an issue with file
        sc = new Scanner(file);
      }
      catch(Exception e){
        System.out.println(e);
        return;//exit
      }
      System.out.println("Queued Jobs.");
      while(sc.hasNextLine()){
        String str = sc.nextLine();
        if(str.split(",").length==3){
          //when we have the priority
        String[] arr = str.split(",");
        Process p = new Process(arr, id_count);
        list.addQueue(p); //adding
        }else{
          //if we dont, assign third value to be 100000
          String[] arr = new String[3];
          //creating array
          arr[0] = str.split(",")[0];
          arr[1] = str.split(",")[1];
          arr[2]="100000";
          Process p = new Process(arr, id_count);
          //creating process object to add to linkedlist
          list.addQueue(p); //adding
        }
        id_count++;
        //print and edit insert to be in order
        //list.printList(); check
        }
      list.printList();

      //dequeueing
      System.out.println("\n\nExecuting Jobs.");

      //variables used to count
      int CPU1 = 0;
      int CPU2 = 0;


      while(list.size>0){
        //main while loop to process things
        Process x = list.removeQueue();
        //this removes and allows us to store information in a variable
        if(CPU1<=CPU2){
          //we need to load CPU1
          CPU1+=x.time;
          System.out.printf("CPU1: Processing PID: %2d | %20s | %d\n", x.ID, x.name, x.time);
        }else{
          //need to load CPU2
          CPU2+=x.time;
          System.out.printf("CPU2: Processing PID: %2d | %20s | %d\n", x.ID, x.name, x.time);
        }
      }
      System.out.println("Finished.");
    }
}
