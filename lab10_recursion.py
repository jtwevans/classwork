"""
Contains programs that:
recursively determines length of list
recursively determine the maximum in a list
draws a recursive H
draws stairs that vary in color with length
when run, draws stairs with base length of 128

CS150 Spring 2019 Lab 8

Name: Jackson Evans

Creativity:

rec_count function that recursively counts how many times an object occurs in a list

color added to recursive_H function

number of squares added to main function

color that varies with x and y coordinates for the stairs function
"""

import turtle as t
t.speed(0)

def length(list):
    """
Takes a list and returns length of list using recursion

Args:
    list
    
Returns:
    length

    """
    if list == []:
        return 0
    elif list[0:] == list[0]:
        return 1
    else:
        return length(list[1:]) + 1 # calls list from second value to the end to cycle through
    
def rec_max(list):
    """
Takes a list and returns the maximum value

Args:
    homogenous list
    
Returns:
    maximum value

    """
    if len(list) == 1: # base case
        return list[0]
    elif list[0] >= list[1]: # if first value is greater or equal to second
        return list[0]
        rec_max(list[1:])
    else: # if first value is less than second
        x = rec_max(list[1:])
        if x > list[0]:
            return x
        else:
            return list[0]
        
#CREATIVITY
def rec_count(list, object):
    """
Takes a list and object and recursively determines how many times the object occurs in the list

Args:
    list
    object
    
Returns:
    number of times object occurs in list
"""
    if len(list)==0:
        return 0
    elif list[0] == object:
        return 1 + rec_count(list[1:], object)
    else:
        return 0 + rec_count(list[1:], object)
       
       

def recursive_H(length, levels):
    """
Draws an H shape

Args:
    length of vertical bars
    levels: number of recursive levels to draw
    
Returns:
    H shape

    """
    t.speed(0)
    if levels == 0: # base case of a dot
        t.dot()
    else:
        t.pencolor(0,length/(length + 30),1) #color creativity
        t.forward(length)
        t.left(90)
        t.forward(length/2)
        t.right(90)
        recursive_H(length/2,levels-1) #recursive call on one corner
        t.pencolor(0,length/(length + 30),1) #color
        t.right(90)
        t.forward(length)
        t.left(90)
        recursive_H(length/2,levels-1)
        t.pencolor(0,length/(length + 30),1) #color
        t.right(90)
        t.backward(length/2)
        t.left(90)
        t.backward(length * 2)
        t.left(90)
        t.forward(length/2)
        t.right(90)
        recursive_H(length/2, levels-1)
        t.pencolor(0,length/(length + 30),1) #color
        t.left(90)
        t.backward(length)
        t.right(90)
        recursive_H(length/2,levels-1)
        t.pencolor(0,length/(length + 30),1) #color
        t.right(90)
        t.backward(length/2)
        t.left(90)
        t.forward(length)

def stairs(length):
    """
Takes a length as a parameter and draws a set of stairs where the largest square has length length
Returns amount of squares

Args:
    length of largest square
    
Returns:
    amount of squares

    """
    if length <= 3: # base case, doesn't draw anything
        return 0
    else:
        
        t.fillcolor(0,length/(length + 15),1) #15 chosen as it looks good
        
        # Creativity: this changes the color depending on position, making different
        # shades of blue and red, where each varies with x and y coordinate (remove hashtag)
        # t.fillcolor((t.pos()[0])/350,0,(t.pos()[1])/350)
        t.begin_fill()
        for i in range(4):
            t.forward(length)
            t.left(90)
        t.end_fill()
        count = 1
        
        t.forward(length)
        count += stairs(length/2) # adds to count and uses recursion to draw stairs
        t.backward(length)
        t.left(90)
        t.forward(length)
        t.right(90)
        count += stairs(length/2)
        t.left(90)
        t.backward(length)
        t.right(90)
    
    return count



if __name__ == '__main__':
    # Creativity: writing the amount of stairs
    t.write("Stairs with " + str(stairs(128)) + " squares")
