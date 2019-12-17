"""
This program reads a file and prints the top 10 words with the highest frequency
and displays a log-log plot of the count versus the rank of works in the file.

CS150 Spring 2019 Lab 8

Name: Jackson Evans
Creativity:
Takes filename as an extra parameter in the display_data function to display it on the graph
Displays a fixed line of zipf's law as a reference (approaches 0)
Runs from command line and takes filename as an optional parameter
Checks for correct number of inputs and prints usage if not

"""
import numpy as np
import pandas as pd
import matplotlib
matplotlib.use('TkAgg')
import random
import matplotlib.pyplot as plt
import string
import sys

all_punctuation = '!"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~\n'

def read_corpus(filename):
    """
Takes a file and returns a list of all of the individual words

Args:
    name of file
    
Returns:
    list of words

    """
    n = []
    with open(filename, "r") as file:
        contents = file.read().split()
        for i in range(len(contents)):
            n.append((contents[i]).strip(all_punctuation).lower())
    return n
                


def count_and_rank(list):
    """
Takes a list of words counts how many times the word occurs in the list

Args:
    list of words

Returns:
    tuple of the word and the frequency of the word in the list
    """
    counts = {}
    for item in list:
        if item in counts:
            counts[item] += 1
        else:
            counts[item] = 1
    #print(counts)
    counts_tuples = counts.items()
    counts_sorted = sorted(counts_tuples, key=lambda counts: counts[1], reverse = True)
    word_list = []
    num_list = []
    for i in range(len(counts_sorted)):
        word_list.append(counts_sorted[i][0])
        num_list.append(counts_sorted[i][1])
    counts_sorted_tuple = (word_list, num_list)
    return counts_sorted_tuple

def display_data(tuple_1, filename):
    """
Takes a tuple of words and frequencies of that word and prints the top 10 frequencies in a table
and displays the rank of each word versus the frequency in a log-log plot
Also displays filename as title
Also shows zipf's law as a reference point

Args:
    tuple of word data
    
Returns:
    table of the top 10 words with their count
    log-log plot of count vs rank of words in text
    """
    #use pandas for creativity
    print("Word", "Count", sep="\t")    
    for i in range(10):
        print(tuple_1[0][i], tuple_1[1][i], sep="\t")
    
    rank = list(range(1,len(tuple_1[0])+1)) #building rank
    
    tuple_2 = (rank, tuple_1[1])
    
    
    plt.plot(tuple_2[0], tuple_2[1], 'bs')
    plt.xscale('log') #creating log axes
    plt.yscale('log')
    plt.xlabel("Rank")
    plt.ylabel("Count")
    plt.title(filename) #creativity
    
    #creativity: Zipf's law as a fixed line
    zipf_count = [tuple_1[1][0]]
    for i in range(1, len(tuple_1[1])): #how many elements
        if (zipf_count[i-1]/(i + 1)) < 1: #word use shouldn't dip below 1
            zipf_count.append(1)
        else:
            zipf_count.append(zipf_count[i-1]/(i + 1)) #nth most common frequency is highest/n as per zipf's law
    
    zipf_tuple = (rank, zipf_count)
    plt.plot(zipf_tuple[0], zipf_tuple[1], 'g^')
    
    plt.show()
    
def print_usage():
    """Print the usage of the program"""
    print("python3 lab8_zipf_law.py <file>")


if __name__ == '__main__':
    if len(sys.argv) != 2: #creativity
        print_usage()
    else:
        display_data(count_and_rank(read_corpus(sys.argv[1])), sys.argv[1])

  
# main program:
# check to see if program is run or imported 
# get filename from command-line arguments
# read_corpus(filename)
# count_and_rank(read_corpus(filename))
# display_data(count_and_rank(read_corpus(filename)), filename)