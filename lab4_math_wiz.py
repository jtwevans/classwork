"""
CSCI 150 Spring 2019 Lab 4

Name: Jackson Evans
Section: B

Creativity:
higher/lower prompts if user  gets question wrong more than 2 times
difficulty level based on operators
countdown as an integer to let user know how much time is left
squared operator for extra challenge

"""

ops = '+-*'
harder_ops = ['+','-','*','**']

from random import randint
import time


def random_equation(operators):
    """
    Produces an equation with random operators of plus, minus and times,
    with an optional power of 2 function that has random integers as values.
    
    args:
        amount of operators in the equation
        
    returns:
        random equation as a string
    """
    index = 0
    equation = str(randint(1,10))
    while index < operators:
        equation += ops[randint(1,2)] + str(randint(1,10))
        index += 1
        if operators == 11:
#CREATIVITY: if the user wants an extra challenge, which they are prompted to do when playing game,
#they can add an operator that asks them to square a number
            x = harder_ops[randint(0,3)]
            if x == '**':
                equation += x + str(randint(1,3)) #Don't want user to need a calculator
            else:
                equation += x + str(randint(1,10))
    return str(equation)

def query_equation(equation):
    """
    Prompts user for an input as the answer to the equation given.
    Prints either hints or correct as the answer is submitted.
    Evaluates equation given.
    
    args:
        equation as a string
    """
    x = eval(equation)
    correct = False
    index = 0
    while not correct:
        answer = int(input(str(equation) + " = "))
        if answer == x:
            print("Correct!")
            correct = True
        elif -2 <= x - answer <= 2:
            print("Close. Try again.")
        elif index >= 2:
# CREATIVITY, after the user has gotten answer wrong twice, will help more with
# saying higher or lower based on their answer.
            if x - answer < -2:
                print("Try lower!")
            elif x - answer > 2:
                print("Try higher!")
        else:
            print("Keep trying")
            index += 1

def play_game(duration, operators):
    """
    Promts user to play game using previously defined functions to create and ask for input
    on a random equation
    
    args:
        duration user wants to play
        number of operators in the equation
        
    Displays game summary of total duration and number of questions correct when finished.
    """
    start_time = time.time()
    number_correct = 0
    while time.time() - start_time < duration:
        problem = query_equation(random_equation(operators))
        number_correct += 1
        if time.time() - start_time < duration: # CREATIVITY this is so time left doesn't print after the last question is answered.
#Displays how much time user has left before game ends rounded down to the nearest integer.
            print('You have about ' + str((duration - (time.time() - start_time))//1) + ' seconds remaining!')
    end_time = time.time()
    time_elapsed = end_time - start_time
    print('You got ' + str(number_correct) + ' correct in ' + str(time_elapsed) + ' seconds')
    
if __name__ == '__main__':
    start = input("Do you want to play a game[yes/no]? ").lower()
    if start == "yes":
        duration = int(input("How long do you want to play for [seconds]? "))
        operators = int(input("What difficulty? [1-10] (try 11 for a crazy challenge!) "))
#CREATIVITY: prompt for difficulty based on number of operators
        play_game(duration, operators)
    else:
        print("Maybe some other time!")