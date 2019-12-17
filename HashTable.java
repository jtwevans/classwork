/******************************************************************************
 *  Compilation:  javac HashTable.java
 *  Execution:    java HashTable
 *
 *  Creates an array of linked lists. Has functions to convert inputs to a
 *  hash code and then reduce that number to within the length of the array:500
 *  Then has an insert method to put a player object into the table
 *
 *  Jackson Evans
 ******************************************************************************/

class HashTable{
  //hashtable class
  LinkedList[] array = new LinkedList[500];
  //array of linked lists

  public long hashCode(String s){
    //method for creating a hash hash code

    long hash = 0;
      for(int i = 0; i<s.length(); i++){
        hash = hash * 31 + s.charAt(i);
      }
      if(hash<0){
        //deals with negatives
        hash = -1*hash;
      }
      return hash;
  }

  public int code_to_index(long l){
    //method to get it within 500
    long x = l%500;
    return (int) x;
  }

  public void hash_insert(Player p, int index){
  //insert function takes player and index, look for index, at index see if null, if null create new linked list then insert player
  if(array[index]==null){
    array[index] = new LinkedList();
  }
  array[index].insert(p);
  }
}
