/******************************************************************************
 *  Compilation:  javac BST.java
 *  Execution:    java BST
 *
 *  Creates a binary search tree for books in a library. Add function takes in
 *  Information and creates a book object, and adds it to the library in order.
 *  There are methods to print the whole library, check the size and check out.
 *
 *  Jackson Evans
 ******************************************************************************/

public class BST{
  //BST class
  private int size;
  public Node root;

  static class Node{
    Book data;
    Node parent;
    Node left, right;

    public Node(double ddn, String author, String title){
      //node constructor
      //create a book object
      //assign the attributes
      //rather than in main
      Book b = new Book(ddn, author, title);
      data = b;
      left = null;
      right = null;
      parent = null;

    }
  }

  public BST(){
    // BST constructor
    size = 0;
    root = null;
  }


  public static void traverseLeft(Node curr){
    //helper function to print every entry in order
    if(curr==null){
      //base case of empty node
      return; //exits
    }
    traverseLeft(curr.left);
    System.out.println(curr.data.title);
    traverseLeft(curr.right);
  }


  public void print(){
   //print function
   Node curr = root;
   traverseLeft(curr);
   //using helper function

  }

  public int size(){
    //method to return size
    return(size);
  }


  public Node addHelper(Node curr, Node n){
    //recursive helper function for add
    if(curr==null){
      //empty
      size++;
      return n;
    }
    if(curr.data.ddn>n.data.ddn){
      curr.left = addHelper(curr.left, n);
      addHelper(curr.left, n).parent = curr;
    }else if(curr.data.ddn<n.data.ddn){
      curr.right = addHelper(curr.right, n);
      addHelper(curr.right, n).parent = curr;
      }

    return (curr);
  }


  public void add(double ddn, String author, String title){
    //add method creates a book object then uses helper function to insert
    //check if null first
    //recurse
    Node n = new Node(ddn, author, title);
    //creating a node that creates a book
    root = addHelper(root,n);
  }

  public Book remove(double ddn){
    //remove method
    //current will never be null, as we are only removing books in the library
    //that also means we don't have to worry about edge cases of curr.left or
    //curr.right being null
    //need to find node first
    Node curr = root;
    //use this node to iterate through

    while(curr!=null){
      if(curr.data.ddn == ddn){
        //need to delete this node

        if(curr.right==null && curr.left==null){
          //no children so we delete and don't replace
          if(curr.parent == null){
            //if curr is root and has no children
            root=null;
            size--;
            return(curr.data);
            //just return the current node's book

          }else{
            //has parent node and no children
            if(curr.parent.data.ddn>curr.data.ddn){
              //if curr is a left child
              curr.parent.left=null;
            }else{
              //if curr is a right child
              curr.parent.right=null;
            }
            size--;
            return(curr.data);
            //return the node's book
          }

        }else if(curr.right==null){
          //has only a left child
          //curr.left replaces curr as there is no right child
          if(curr.parent == null){
            //if curr is root
            root = root.left;
            size--;
            return(curr.data);
            //just return the current node's book
          }else{
            //has parent node
            if(curr.parent.data.ddn>curr.data.ddn){
              //if curr is a left child
              curr.parent.left=curr.left;
            }else{
              //if curr is a right child
              curr.parent.right=curr.left;
            }
            size--;
            return(curr.data);
            //return the node's book
          }

        }else if(curr.left==null){
          //has only a right child
          //curr.right replaces null as there is no left child
          if(curr.parent == null){
            //if curr is head and no left child
            root = root.right;
            //root.right becomes new root
            size--;
            return(curr.data);
          }else{
            //not root
            if(curr.parent.data.ddn>curr.data.ddn){
              //if curr is a left child
              curr.parent.left = curr.right;
              //no left subtree so assign to right
            }else{
              //if curr is a right child
              curr.parent.right = curr.right;
            }
            size--;
            return(curr.data);
            //return the node
          }
        }else{
          //curr has both right and left children
          //need to find the largest value of the left child
          Node largest = curr.left;
          Node largestparent = curr;

          //we know this exists
          while(largest.right!=null){
            largestparent = largest;
            largest = largest.right;
          }
          //find if left or right child
          if(largest.data.ddn<largestparent.data.ddn){
            //left child
            largestparent.left=null;
          }else{
            largestparent.right=null;
          }

          //breaking out of this loop means we have found the largest value less
          //than curr

          if(curr.parent == null){
            //if curr is root
            //largest doesn't have a right child
            //also know that root has two children
            curr.left.parent = largest;
            curr.right.parent = largest;
            largest.right = curr.right;
            largest.left = curr.left;
            root = largest;
            //reassign root
            largestparent.right = null;
            //need to cut off former parent from child :(
            size--;
            return(curr.data);
          }else{
            //if curr has a parent
            largest.parent=curr.parent;
            if(curr.parent.data.ddn>curr.data.ddn){
              //if curr is left child
              curr.parent.left = largest;
              if(curr.right!=null){
                largest.right = curr.right;
                curr.right.parent = largest;
              }
              if(curr.left!=null){
                curr.left.parent=largest;
                largest.left=curr.left;
            }

              size--;
              return curr.data;
            }else{
              //if curr is right child
              curr.parent.right = largest;
              if(curr.right!=null){
                //edge case
                largest.right = curr.right;
                curr.right.parent=largest;
              }
              if(curr.left!=null){
                //edge case
                curr.left.parent=largest;
                largest.left=curr.left;
            }
              size--;
              return curr.data;
            }
          }
        }

      }else{
        //keep going down the list
        if(curr.data.ddn>ddn){
          curr=curr.left;
          //don't worry about null because we only check out things in the lib
        }else{
          curr=curr.right;
        }
      }
    }
    return(curr.data);
  }

  public boolean isAvailable(double ddn){
    //method to see if a value is in the library
    //non-recursive
    Node curr = root;
    while(true){
      if(curr.data.ddn==ddn){
        return true;
      }else if(curr.data.ddn>ddn){
        if(curr.left!=null){
          curr=curr.left;
        }else{
          break;
        }
      }else{
        // if curr.data.ddn<ddn
        if(curr.right!=null){
          curr=curr.right;
        }else{
          break;
        }
      }
    }
    return false;
  }
}

class Book{
  //book class
  public double ddn; // Dewey Decimal Number
  public String author;
  public String title;

  public Book(double _ddn, String _author, String _title){
    //book constructor
    ddn= _ddn;
    author = _author;
    title = _title;
  }
}
