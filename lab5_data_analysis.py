"""
CSCI 150 Spring 2019 Lab 5

Names: Tai Nguyen, Jackson Evans
Section: B

Creativity:
Mode function added at the end
Extra data analyses on 95 census about income and age. Combined together with conclusions about them all.

"""

"""
Enter file to analyze: jumanji.txt
File contained  701  entries
Max:  5.0
Min:  1.0
Average:  3.20114122681883
Median:  3.0
Std. dev:  0.9831720435251046

Enter file to analyze: grumpier_old_men.txt
File contained  478  entries
Max:  5.0
Min:  1.0
Average:  3.01673640167364
Median:  3.0
Std. dev:  1.071711838975401

Analysis:
This data was floats in the range 1.0 to 5.0. Where the movie Jumanji had an average
rating of 3.2, Grumpier Old Men had an average of 3. Furthermore, Jumanji had more reviews
which consolidates the fact that it's average is more reliable. It's standard deviation
was slightly lower, meaning that there was slightly less range in difference of opinion, meaning
it was consistently better than Grumpier Old Men by critics.
"""

"""
Enter file to analyze: 95kids.txt
File contained  35326  entries
Max:  9.0
Min:  0.0
Average:  0.43285398856366414
Median:  0.0
Std. dev:  0.8975902261262514

Analysis:
This data was floats in the range 0.0 to 9.0. The average household had around 0.4 children
meaning that about half of households had 0 chilren, and the rest had up to 9. The standard
deviation was about 0.9 meaning that on average, households with children had between 1.0 to 2.0
"""

"""
Enter file to analyze: 95age.txt
File contained  35326  entries
Max:  90.0
Min:  0.0
Average:  35.73141595425466
Median:  34.0
Std. dev:  22.54500613160551

Analysis:
This data was floats in the range 0.0 to 90.0. The average and median are very close at
around 36 and 34 respectively. The standard deviation means that around 70% of people
in this region are between 13 and 57.
"""

"""
Enter file to analyze: 95income.txt
File contained  35326  entries
Max:  304998.0
Min:  -13411.0
Average:  16974.277925607203
Median:  8929.5
Std. dev:  22838.780363565795

Analysis:
This data was floats in the range -13411.0 to 304998.0. The average income was 16974 but the median
was only 8929.5. There was a huge wealth inequality, shown by the large standard deviation.

Through all of the analyses of the 95 census, we can see that the average household has
0.43 children, parents that are around 36 years old each of whome make 17000 as income.

"""


import math as m

def read_file(filename):
    """
    Takes a file and converts it to a list
    
    args:
        name of file
    
    returns:
        list based on the entries in the file
    """
    with open(filename, "r") as file:
        file_list = []
        for item in file:
            # with one name per line
            file_list.append(float(item.strip()))
    return file_list


def number_entries(file_list):
    """
    Displays number of entries in the list
    
    args:
        list based on file
    
    returns:
        number  of entries in file
    """
    return len(file_list)

def largest_value(file_list):
    """
    Displays largest value in the list
    
    args:
        list based on file
    
    returns:
        largest value
    """
    largest = file_list[0]
    for value in file_list:
        if value > largest:
            largest = value
    return largest

def smallest_value(file_list):
    """
    Displays smallest value in the list
    
    args:
        list based on file
    
    returns:
        smallest value
    """
    smallest = file_list[0]
    for value in file_list:
        if value < smallest:
            smallest = value
    return smallest

def average_value(file_list):
    """
    Displays mean (average) value of list
    
    args:
        list  based on file
    
    returns:
        mean
    """
    return sum(file_list)/len(file_list)

def median_value(file_list):
    """
    Displays median value in the list
    
    args:
        list based on file
    
    returns:
        median value in list
    """
    file_list.sort()
    if len(file_list) %2 == 0:
        smaller_middle = file_list[len(file_list)//2 - 1]
        larger_middle = file_list[len(file_list)//2]
        average = (smaller_middle + larger_middle)/2
        return average
    else:
        return file_list[len(file_list)//2]

def standard_deviation(file_list):
    """
    Displays standard deviation of a list
    
    args:
        list based on file
    
    returns:
        standard deviation
    """
    average = average_value(file_list)
    sum_sqdf = 0
    for i in range(len(file_list)):
        sum_sqdf += (file_list[i] - average)**2
    return m.sqrt(1/(len(file_list)-1)*sum_sqdf)

def data_analysis():
    """
    Displays statistics about a file
    
    args:
        file name
    
    returns:
        number of entries, largest value, smallest value, average value, median value, standard deviation
        works with zero entries
    """
    file_list = read_file(input("Type the file name here: "))
    print('File contained ', number_entries(file_list), ' entries')
    if len(file_list) > 0:
        print('Max: ', largest_value(file_list))
        print('Min: ',  smallest_value(file_list))
        print('Average: ',  average_value(file_list))
        print('Median: ',  median_value(file_list))
        print('Std. dev: ',  standard_deviation(file_list))

def frequencies(data):
    """
    Attempts to print the frequency of each item in the list data
    
    Args:
        data: List of "sortable" data items
    Prints:
        frequencies of all numbers
        mode
    """
    data.sort()
    
    count = 0
    previous = data[0]
    
    print("data\tfrequency") # '\t' is the TAB character
    
    #add ' ' at the end of the list. 
    data.append(' ')

    for d in data:
        if d == previous:
            # Same as the previous, increment the count for the run
            count += 1
        else:
            # We've found a different item so print out the old and reset the count
            print(str(previous) + "\t" + str(count))
            count = 1           
        previous = d
        

def mode(data):
    """
    Prints a list of the modes of a data set
    
    Args:
        data set
    Prints:
        list of the mode(s)
    """
    data.sort()
    
    count = 0
    previous = data[0]
    data_list = []      
    frequency_list = [] 
    
    
    #add ' ' at the end of the list. 
    data.append(' ')

    for d in data:
        if d == previous:
            # Same as the previous, increment the count for the run
            count += 1
        else:
            # We've found a different item so reset the count
            data_list.append(previous) # A sorted list of all different values of the data set.
            frequency_list.append(count) # A list of all corresponding frequencies to the list of values above.
            count = 1           
        previous = d
 
    largest = frequency_list [0] # Assume that the largest frequency is the first one in the frequency_list.
    mode_list = []   # This is the list of all the modes. 
    for i in range(len(frequency_list)):
        if frequency_list[i] > largest: #if there is a value in the data_list whose frequency is greater than largest, empty the mode_list, add that value into the mode_list.
            mode_list.clear()
            largest = frequency_list[i]
            mode_list.append(data_list[i])   
        elif frequency_list[i] == largest: #if there is a value in the data_list whose frequency is equal to largest, add that value to the mode_list
            mode = frequency_list[i]
            mode_list.append(data_list[i])
    print("Mode:", mode_list)

if __name__ == '__main__':
        data_analysis()