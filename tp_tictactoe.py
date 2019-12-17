"""
Plays the game tic tac toe, asking for names of players.

CSCI 150 Spring 2019 Test Project

Name: Jackson Evans
Section: B


Creativity:

Help functionality
Player names added as inputs
Times the game and lets the player know of how long it took
"""

import time

def game_state(list):
    """
    Description: Takes a list as an input and displays a tic tac toe board
                 with the list entries in the middle of each square in the board
    
    args: list of values to be displayed on the board
        
    prints: board

    """
    vertical = "     |     |"
    bridge = "_____|_____|_____"
    print(vertical)
    print("  " +str(list[0])+ "  |  "+ str(list[1])+ "  |  " + str(list[2]) +" ")
    print(bridge)
    print(vertical)
    print("  " +str(list[3])+ "  |  "+ str(list[4])+ "  |  " + str(list[5]) +" ")
    print(bridge)
    print(vertical)
    print("  " +str(list[6])+ "  |  "+ str(list[7])+ "  |  " + str(list[8]) +" ")
    print(vertical)

def alter_state(dictionary):
    """
    Description: takes a dictionary of inputted placements and displays the current game state
    
    args: dictionary of numbers
        
    returns: new list of X's or 0's to display on game board

    """
    base_list = [' ',' ',' ',' ',' ',' ',' ',' ',' ']
    for entry in dictionary:
        base_list[int(entry)-1] = dictionary[entry] #entry converted to integer to subtract one from all board spaces
    return base_list    
    
def win_states(dictionary):
    """
    Description: checks to see if the game has been won
    
    args: dictionary of numbers and if they were chosen by player 1 or 2
        
    returns: either a list of True and the winning symbol (X or 0) or a list of False

    """
    #checks for all 3 in a row to be the same and not blank
    if (dictionary['1'] == dictionary['2'] == dictionary['3']) and dictionary['1'] != ' ':
        return [True, dictionary['1']]
    elif (dictionary['1'] == dictionary['4'] == dictionary['7']) and dictionary['1'] != ' ':
        return [True, dictionary['1']]
    elif (dictionary['1'] == dictionary['5'] == dictionary['9']) and dictionary['1'] != ' ':
        return [True, dictionary['1']]
    elif (dictionary['4'] == dictionary['5'] == dictionary['6']) and dictionary['4'] != ' ':
        return [True, dictionary['4']]
    elif (dictionary['7'] == dictionary['8'] == dictionary['9']) and dictionary['7'] != ' ':
        return [True, dictionary['7']]
    elif (dictionary['2'] == dictionary['5'] == dictionary['8']) and dictionary['2'] != ' ':
        return [True, dictionary['2']]
    elif (dictionary['3'] == dictionary['6'] == dictionary['9']) and dictionary['3'] != ' ':
        return [True, dictionary['3']]
    elif (dictionary['3'] == dictionary['5'] == dictionary['7']) and dictionary['3'] != ' ':
        return [True, dictionary['3']]
    else:
        return [False]

def play_game():
    """
    Description: plays tic tac toe
    """
    
    # Intro sequence
    print("Welcome to tic-tac-toe!",'\n')
    p1 = str(input("Name of player 1 (playing X's)? ")) #CREATIVITY
    p2 = str(input("Name of player 2 (playing 0's)? "))
    print("Board squares are numbered 1..9 as seen here:")
    game_state([1,2,3,4,5,6,7,8,9]) # with numbers
    print("At your turn, enter a number 1..9, or ? for this help message")
    game_state([' ',' ',' ',' ',' ',' ',' ',' ',' ']) #blank
    
    #CREATIVITY: Time
    start_time = time.time()
    
    dictionary = {'1': ' ','2': ' ','3': ' ','4': ' ','5': ' ','6': ' ','7': ' ','8': ' ','9': ' ',}
    number_list = ["1","2","3","4","5","6","7","8","9"]
    index = 1
    
    while index < 10: # only 9 squares
        if index%2 != 0: #odd numbers
            place = str(input(p1 + "'s move [1..9 or ?]: "))
            while place == '?': #CREATIVITY: help function
                print("Board squares are numbered 1..9 as seen here:")
                game_state([1,2,3,4,5,6,7,8,9])
                print("At your turn, enter a number 1..9, or ? for this help message")
                game_state(alter_state(dictionary))
                place = str(input(p1 + "'s move [1..9]: "))
            while (place in number_list) == False: #checks to make sure input is a number 1-9
                place = str(input("Invalid move. Enter 1..9: "))
            while dictionary[place] != ' ': #if the dictionary value is not the base case of a blank space
                print("That square is already taken.")
                place = str(input("Enter a blank square among 1..9: "))
            
            dictionary[place] = 'X' #adds X to the value for that space
                
            
        else: #even numbers
            place = str(input(p2 + "'s move [1..9 or ?]: "))
            while place == '?':
                print("Board squares are numbered 1..9 as seen here:")
                game_state([1,2,3,4,5,6,7,8,9])
                print("At your turn, enter a number 1..9, or ? for this help message")
                game_state(alter_state(dictionary))
                place = str(input(p2 + "'s move [1..9]: "))
            while (place in number_list) == False:
                place = str(input("Invalid move. Enter 1..9: "))
            while dictionary[place] != ' ':
                print("That square is already taken.")
                place = str(input("Enter a blank square among 1..9: "))
                if (place in number_list) == False:
                    place = str(input("Invalid move. Enter 1..9: "))
            
            dictionary[place] = '0' #adds 0 to the value for that space
            
        
        index += 1
        game_state(alter_state(dictionary)) #displays new game state after the turn
        
        if (win_states(dictionary))[0] == True: #if someone has won
            break
        
        
    if win_states(dictionary)[0] == True: #win conditions met
        if win_states(dictionary)[1] == 'X': #checks to see which player won where X corresponds to player 1
            print(p1 + ' wins!', '\n')
            print("Thanks for playing, " + p1 + ' and ' + p2 + '!') #names included
        else:
            print(p2 + ' wins!', '\n')
            print("Thanks for playing, " + p1 + ' and ' + p2 + '!') #names added to the end
    else: #tie if 9 turns have passed and there is no win state
        print("It's a tie!")
        print("Thanks for playing, " + p1 + ' and ' + p2 + '!')
        
    end_time = time.time()
    time_elapsed = end_time - start_time
    print("Your game took: " + str(time_elapsed) + " seconds.")



if __name__ == "__main__": # plays when run, not imported
    play_game()

