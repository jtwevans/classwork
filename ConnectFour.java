/******************************************************************************
 *  Compilation:  javac ConnectFour.java
 *  Execution:    java ConnectFour
 *
 *  Plays the game connect four. Uses two classes to allow two players
 *  Players asked for inputs, where player 1 gets x's and player 2 gets o's
 *  Game ends when one player gets 4 in a row or all spaces are occupied.
 *
 *  Jackson Evans
 ******************************************************************************/

import java.util.Random;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Vector;

public class ConnectFour{
    public String[][] game_board;
    public Player p1 = new Player("x");
    public Player p2 = new Player("o");
    public int moves = 0;
    //used for the while loop that plays the game
    public int winner = 0;
    //this is used later to determine who won

    public ConnectFour(String[][] g_board){
      //constructor for ConnectFour class
      game_board = g_board;
    }

    public void DisplayBoard(String[][] game_board){
      //method that displays a board based on the array passed through
      for (int i = 0; i< 6; i++){
        for (int j = 0; j< 7; j++){
            System.out.print(game_board[i][j] + "\t");
            //displaying the contents of the array
          }
        System.out.println();
        //printing new line
        }
      System.out.println("0\t1\t2\t3\t4\t5\t6");
      //separating each by a tab
      }

    public boolean win_state(Vector<Integer[]> v){
      //method to determine if the game has been won
      boolean win = false;
      //assume the game hasn't been won yet
      Iterator<Integer[]> iter = v.iterator();
        //initializes iterator iter to iterate through vector
      while(iter.hasNext()){
        Integer[] curelement = iter.next();
        //initializing current element as a given coordinate within player vector

      while(curelement[1]-3>=0){
        //checking for horizontal left
        //don't want out of bounds, so while loop is only entered when three
        //spaces to the left of the point are in bounds
        if(game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]][curelement[1]-1]) &&
          //first is checking if the first element has the same value as the next
           game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]][curelement[1]-2]) &&
           game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]][curelement[1]-3])){
             win = true;
             //game has been won
             return win;
        }else{
          break;
          //breaks out of this while loop, as one point may satisfy many possible
          //win conditions, so if game isn't won, it needs to exit to check
          //other possibilities
        }
      }
      while(curelement[1]-3>=0 && curelement[0]-3>=0){
        //diagonal up left
        //only entered when three spaces up and left are in bounds
        if(game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]-1][curelement[1]-1]) &&
           game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]-2][curelement[1]-2]) &&
           game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]-3][curelement[1]-3])){
                   win = true;
                   return win;
        }else{
          break;
        }
      }
      while(curelement[1]-3>=0 && curelement[0]+3<=5){
        //diagonal down left
        //only entered when three spaces down and left are in bounds
        if(game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]+1][curelement[1]-1]) &&
           game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]+2][curelement[1]-2]) &&
           game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]+3][curelement[1]-3])){
                   win = true;
                   return win;
         }
         else{
           break;
         }
       }
       while(curelement[1]+3<=6){
         //horizontal right
         //only entered when three spaces to the right are in bounds
         if(game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]][curelement[1]+1]) &&
            game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]][curelement[1]+2]) &&
            game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]][curelement[1]+3])){
              win = true;
              return win;
        }
        else{
          break;
        }
      }
        while(curelement[1]+3<=6 && curelement[0]-3>=0){
          //diagonal up right
          //only entered when three spaces up and to the right are in bounds
          if(game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]-1][curelement[1]+1]) &&
             game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]-2][curelement[1]+2]) &&
             game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]-3][curelement[1]+3])){
                   win = true;
                   return win;
          }
          else{
            break;
          }
        }
        while(curelement[1]+3<=6 && curelement[0]+3<=5){
          //diagonal down right
          //only entered when three spaces down and right are in bounds
          if(game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]+1][curelement[1]+1]) &&
             game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]+2][curelement[1]+2]) &&
             game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]+3][curelement[1]+3])){
                  win = true;
                  return win;
          }
          else{
            break;
          }
        }
        while(curelement[0]-3>=0){
          //vertical up
          //only entered when three spaces up are in bounds
          if(game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]-1][curelement[1]]) &&
             game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]-2][curelement[1]]) &&
             game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]-3][curelement[1]])){
               win = true;
               return win;
          }
          else{
            break;
          }
        }
        while(curelement[0]+3<=5){
          //vertical down
          //only entered when three spaces down are in bounds
          if(game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]+1][curelement[1]]) &&
             game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]+2][curelement[1]]) &&
             game_board[curelement[0]][curelement[1]].equals(game_board[curelement[0]+3][curelement[1]])){
               win = true;
               return win;
          }
          else{
            break;
          }
        }
      }
      return win;
      //this is basically just for a loss, as win will = false if this is reached
    }

    public static void main(String[]args){
        String[][] game_board = new String [6][7];
        //initializing game board
        for (int i = 0; i<6; i++){
          for (int j = 0; j<7; j++){
            game_board[i][j]= "-";
            //filling with dashes for empty
          }
        }
        ConnectFour c = new ConnectFour(game_board);
        //constructor called to make object c
        System.out.println("Let's play connect four!");
        System.out.println("Player 1's moves are denoted by x's and Player 2's");
        System.out.println("are o's. Good luck!");
        while(c.moves<42){
          //main game while loop
          //only runs when there have been less than 42 turns
          c.DisplayBoard(game_board);
          //displays board
          System.out.println("Player 1's turn!");
          c.p1.getMove(game_board);
          //uses method in player class to get a valid move
          c.moves++;
          if (c.win_state(c.p1.v)==true){
            //checks for win
            break;
            //exits if player has won
          }
          c.DisplayBoard(game_board);
          System.out.println("Player 2's turn!");
          c.p2.getMove(game_board);
          c.moves++;
          if (c.win_state(c.p2.v)==true){
            c.winner += 1;
            //this means player 2 has won
            break;
          }
        }
        c.DisplayBoard(game_board);
        //displays winning board or tie board
        if(c.moves==42){
          System.out.println("The game is a tie!");
        }else if(c.winner == 0){
          //uses winner attribute
          System.out.println("Player 1 wins!");
        }else{
          System.out.println("Player 2 wins!");
        }
      }

public class Player{
  //second class
  public String marker;
  //attribute with either x or o
  public Vector<Integer[]> v = new Vector<Integer[]>();
  //attribute of vector
  //contains a list of previous moves in a Vector
  public Player(String mark){
    //constructor
    marker = mark;
    //takes x or o as argument
  }


  public void getMove(String[][] game_board){
    //method that takes game board as argument and gets move from player
    //asks for column
    boolean check = false;
    //this determines if a valid move has been made
    Scanner sc = new Scanner(System.in);
    while (check == false){
      System.out.println("Enter a column: ");
      int column = sc.nextInt();
      if (valid_move(column, game_board)<6){
        //as long as row is <6
        //if the first valid row is 6, eg. not possible, they will be asked for
        //a different column
        int row  =  valid_move(column, game_board);
        //assigning row to equal the first valid row in the column
        game_board[row][column] = marker;
        //game baord updated
        Integer[] coord = new Integer[2];
        //initializing coord to save coordinates
        coord[0] = row;
        coord[1] = column;
        v.add(coord);
        //adding to vector
        check = true;
        //exits while  loop
      }else{
        System.out.println("Please enter a different column: ");
      }
    }
  }

  public int valid_move(int column, String[][] game_board){
    //method that will take column and game board as arguments
    //returns the first valid row in that column
    for(int row = 0;row<6;row++){
      //looping through a given column
      if (game_board[5-row][column].equals("-")){
        //if the space is empty, return that row
        //using 5-row, as when printing game board, the top is printed before
        //the bottom
        return(5-row);
      }
    }return(6);
    //means only available would be too high
  }
}
}
