/******************************************************************************
 *  Compilation:  javac LinkedList.java
 *  Execution:    java LinkedList
 *
 *  Code to create a linkedlist. Methods to insert and search for nodes.
 *
 *  Jackson Evans
 ******************************************************************************/

class LinkedList {
  //linkedlist class

  //attributes
  public Node head;
  public Node tail;

  static class Node {
    //node class
    Player data;
    Node next;
    Node prev;

    Node(Player d){
      //node constructor
      data = d;
      next = null;
      prev = null;
    }
  }

  public LinkedList(){
    //linked list constructor
    head = null;
    tail = null;
  }

  // Inserts a node into the end of the linked list.
  public void insert(Player data){

    // Create a new node for the data
    Node newnode = new Node(data);
    // Check to see if the head node is null. If it is, place
    // the new node there. Also, the tail is set to the new node
    // since it is the only item in the list.
    if (head == null) {
      head = newnode;
      tail = newnode;
    }
    else{

      tail.next = newnode;
      newnode.prev = tail;
      tail = newnode;
    }
  }

  public Player search(String name){
    //search method
    Node curr = head;
    //start at head
    if(curr==null){
      //if it is empty
      System.out.println("head is null");
      return null;
    }
    int traverse=1;
    //traverse counter
    while(true){
      //loop to traverse through the list
      if(curr.data.name.toLowerCase().equals(name)){
        //make toLowerCase
        //if we find what we want
        System.out.println(curr.data.toString());
        //print the tostring
        break;
        //break out of while loop
      }else{
        if(curr.next!=null){
          curr=curr.next;
          //this should always exist
          //otherwise iterate
          traverse++;
          //add to traverse
        }else{
          System.out.println("Player not found");
          return null;
        }
      }
    }
    System.out.println("Traversed " + traverse + " element(s).");
    return curr.data;
  }
}
