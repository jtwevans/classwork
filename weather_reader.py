"""
Module: Function get_temperature finds the temperature at a zip code
and get_field will get information based on a field as an input.

CS150 Spring 2019 Lab 7

Name: Jackson Evans, Tai Nguyen
Section: B

Creativity:
Checks for valid zip code
general get_field function
"""

import urllib.request
import json
import sys

BASE_URL = 'http://api.openweathermap.org/data/2.5/weather?zip='
API_KEY = ',us&appid=aeba43d686780260ea34ba68532d0c77&units=imperial'


def get_temperature(zip):
    """
Pulls temperature from API and displays it
    args:
        5 digit zip code
    returns:
        temperature as a float
    """
    return get_field('temp', zip)

def get_field(field, zip):
    """
CREATIVITY: Pulls information from API and displays it
    args:
        field:
            type of data to be displayed, for example pressure, humidity, temp_min or temp_max
        zip:
            5 digit zip code
    returns:
        information as a float
    """
    url =  '{0}{1}{2}'.format(BASE_URL, zip, API_KEY)
    with urllib.request.urlopen(url) as webpage:
        contents = webpage.read().decode('utf-8')
    d = json.loads(contents)
    data = d['main'][field]
    #main can be substituted for another set of information like wind to display other information
    return data
    

def print_usage():
    """Print the usage of the program"""
    print("python3 weather_reader.py <zip_code>")

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print_usage()
    elif len(sys.argv[1])!= 5:
        print_usage()
        print("Please enter a valid zip code") #Checks if zip code is 5 digits
    else:
        print(get_temperature(sys.argv[1]))