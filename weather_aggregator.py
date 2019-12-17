"""
Module: Function aggregate_weather collects weather data if a file exists, or creates a new one.

CS150 Spring 2019 Lab 7

Name: Jackson Evans, Tai Nguyen
Section: B

Creativity:
Includes zip code into file and adds information based on date, time and zipcode.
Checks to see if zip code is valid
"""

import sys
import weather_reader
import datetime
import os

def get_hour():
    """
    records the year, month, day and time to the millisecond
    and returns the current hour as a string
    """
    now = datetime.datetime.now()
    return str(now.hour)
   
def get_date():
    """
    records the year, month, day and time to the millisecond and
    returns the month, day and year as a string with a hyphen inbetween
    """
    now = datetime.datetime.now()
    return str(now.month) + "-" + str(now.day) + "-" + str(now.year)

def aggregate_weather(filename, zip_code):
    """
    If the desired file is new, program creates file and adds data.
    If desired file exists, new information is appended
    Args:
        filename:
            desired name of file
        zip_code:
            zipe code of location
    Returns:
        either writes new line of file including zip code
        or creates new file and adds information to that
    """
    date = str(get_date())
    hour = str(get_hour())
    
    if not os.path.exists(filename):
        with open(filename, "w") as file:
            file.write("\t".join([date, get_hour(), str(weather_reader.get_temperature(zip_code)), str(zip_code) + '\n']))
    else:
        with open(filename, "r") as file:
            for line in file:
                if line.startswith(str(date)+'\t'+str(hour)) and str(zip_code) in line:
                    break
            else:
                with open(filename, "a") as file:
                    file.write("\t".join([date, get_hour(), str(weather_reader.get_temperature(zip_code)), str(zip_code) + '\n']))

def print_usage():
    """Print the usage of the program"""
    print("python3 weather_aggregator.py <file> <zip_code>")

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print_usage()
    elif len(sys.argv[2])!= 5:
        print_usage()
        print("Please enter a valid zip code") #Checks if zip code is 5 digits
    else:
        aggregate_weather(sys.argv[1],sys.argv[2])
        