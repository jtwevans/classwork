/******************************************************************************
 *  Compilation:  javac Battleship.java
 *  Execution:    java Battleship
 *
 *  Plays the game battlesip. Will ask user for a row and column for inputs
 *  then will update the board with an x or an o to denote hits and misses
 *  once the player has guessed all 17 spots, the game ends and displays the
 *  amount of attempts needed.
 *
 *  Jackson Evans
 ******************************************************************************/

import java.util.Random;
import java.util.Scanner;

public class Battleship{
  public int attempts;
  public int remaining;
  public String[][] game_board;
  public String[][] player_board;


  public Battleship(String[][] g_board, String[][] p_board){
    //constructor method that takes the player board and game board as inputs
    attempts = 0;
    remaining = 17;
    game_board = g_board;
    player_board = p_board;

  }

  public int numattempts(){
    //function used to display the number of attempts when the game finishes
    return(attempts);
}


  public void make_g_board(){
    //adding ships of lengths as per the game rules
    add_ship(5);
    add_ship(4);
    add_ship(3);
    add_ship(3);
    add_ship(2);
  }


  public void add_ship(int length){
    boolean overlap = true;
      //boolean used to determine if there is overlap
      //starts as true to enter the while loop
    Random rand = new Random();
    int horiz;

    horiz = rand.nextInt(2);

    if (horiz == 1){
      //this half of the method initialized when horizontal is 1
      //so this is the horizontal part
      while(overlap == true){
        //true assumed to initialize method
        int row_num = rand.nextInt(10);
        //since horizontal, row stays constant
        int column_start = rand.nextInt(10);
        //this refers to the start location of the column
        boolean checker = false;
        if ((column_start + length - 1) < 10){
          //if the ship would not go out of bounds by increasing index
          for(int i=0; i<length; i++){
            if (game_board[row_num][column_start + i].equals("x")){
              //checking to see if there  is already a ship there
              checker = true;

            }
          }
          if(checker){
            continue;
            //restart, can't put ship here
          }
          //if code gets here, then there is no overlap, so we place the ship
          for(int i=0; i<length; i++){
            game_board[row_num][column_start + i] = "x";
            //places ship
            overlap = false;
            //overlap = false so we can exit the while loop
          }

        }else{
          //this is when the ship would go out of bounds by increasing index
          //so we decrease index

          for(int j=0; j<length; j++){
            if (game_board[row_num][column_start - j].equals("x")){
              //checking to see if there is already a ship here
              checker = true;

            }
          }
          if(checker){
            continue;
            //restart, can't place ship here
          }
          //if code gets here, then there is no overlap, so we place ship
          for(int i=0; i<length; i++){
            //places ship
            game_board[row_num][column_start-i] = "x";
            overlap = false;
            // overlap = false so we can exit the while loop
          }
        }
      }

      }else{
        //this is essentially the same code  as before, except we are going
        //vertical, so now the column is fixes and we alter the row
        while(overlap == true){
          int row_start = rand.nextInt(10);
          int column_num = rand.nextInt(10);
          boolean checker = false;
          if ((row_start + length - 1) < 10){
            for(int i=0; i < length; i++){
              if (game_board[row_start + i][column_num].equals("x")){
                checker  = true; //restart, can't place ship here
              }
            }
            if(checker){
              continue;
            }
            // place ship
            for(int i = 0; i<length; i++){
              game_board[row_start + i][column_num]="x";
              overlap = false;
            }

          }else{

            for(int j=0; j<length; j++){
              if (game_board[row_start - 1][column_num].equals("x")){
                checker = true;
                //restart, can't place ship here
              }
            }
            if(checker){
              continue;
            }
          //place ship
            for(int j=0; j<length; j++){
              game_board[row_start - j][column_num] = "x";
              overlap = false;
            }
          }
        }
    }
}


  public void getcoord(){
    //this method is asking user for row and column and updating the number
    //of attempts
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter a row: ");
    int introw = sc.nextInt();
    System.out.println("Enter a column: ");
    int intcolumn = sc.nextInt();
    if (game_board[introw][intcolumn] == "x"){
        //here we change game board so hits don't count twice
        game_board[introw][intcolumn] = "o";
        remaining = remaining - 1;
        //decrease remaining for win condition
        player_board[introw][intcolumn] = "x";
        //change the player board that is displayed for hit
      }else{
        player_board[introw][intcolumn] = "o";
        //change the player board that is displayed for miss
    }
    attempts = attempts + 1;
    //update attempts to display after the game is over
  }

  public void DisplayBoard(String[][] player_board){
    //method that displays a board based on the array passed through
    //here it is used to display the player_board, but the game board could also
    //be passed through
    for (int i = 0; i< 10; i++){
      if(i == 0){
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        //this is displaying the header
      }
      for (int j = 0; j< 10; j++){
        if (j==0){
          System.out.print(i + " ");
          // this is displaying the rows on the side
        }
          System.out.print(player_board[i][j] + " ");
          //displaying the contents of the array
        }
        System.out.println();
        //printing new line
      }
  }

  public static void main(String[]args){
    //main function employing constructor
    //initializing the game and player boards
    String[][] game_board = new String [10][10];
    String[][] player_board = new String [10][10];
    for (int i = 0; i<10; i++){
      for (int j = 0; j<10; j++){
        player_board[i][j]= "-";
        game_board[i][j]= "-";
      }
    }
    System.out.println("This is a single player version of Battleship. There are 5 ships with lengths 5, 4, 3, 3, and 2. Your hits are displayed after each round.  Enter a row (0-9) followed by a column (0-9). An o denotes a miss, whereas an x denotes a hit. The game will end when all the ships are sunk. Good luck!");
    //really long string sorry
    Battleship g = new Battleship(game_board, player_board);
    g.make_g_board();
    //makes the game board
    while (g.remaining > 0){
      // while loop that gets the coordinates and displays the board
      //  therefore game ends when remaining = 0
      g.DisplayBoard(player_board);
      //displays player board
      g.getcoord();
      //asks user for coordinates
      }
    System.out.println("Great job! You have won. It took " + g.attempts + " attempts.");
    }
  }
