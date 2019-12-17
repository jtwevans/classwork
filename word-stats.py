# CS150 class example: files, lists

def sentence_stats(sentence):
    """
    Prints out various statistics about a sentence
    
    Args:
        sentence: string to be analyzed
    """
    words = sentence.split()
    print_stats(words)
    
def print_stats(words):
    """
    Print out various statistics about the input collection of strings
    
    Args:
        words: Collection of strings
    """
    print("Number of words: " + str(len(words)))
    if len(words) > 0:
        print("Longest word: " + longest_word(words))
        print("Shortest word: " + shortest_word(words))
        print("Avg. word length: " + str(average_word_length(words)))

# NOTE: if you want to try the examples from class you'll need to
# download the file of English words, "english.txt"

def file_stats(filename):
    """
    Prints out various statistics about the words in the
    file, assuming one word per line.
    
    Args:
        filename: Path to file
    """
    words = read_file(filename)
    print_stats(words)

def read_file(filename):
    """
    Read file into list of words assuming one word per line

    Args:
        filename: Path to file
        
    Returns:
        list of words
    """
    with open(filename, "r") as word_file:
        words = []
        
        for line in word_file:
            # Assuming one word per line (remember to strip newline from
            # the end of the line)
            words.append(line.strip())    
        
        return words
    
def average_word_length(words):
    """
    Compute the average length in character of collection of strings
    
    Args:
        words: Collection of strings
        
    Returns:
        float of average word length
    """
    length_sum = 0
    
    for word in words:
        length_sum += len(word)
    
    return length_sum / len(words)
        
def longest_word(words):
    """
    Find longest string in collection of strings

    Args:
        words: Non-empty collection of strings
        
    Returns:
        longest string
    """
    longest = words[0]
    
    for word in words:
        if len(word) > len(longest):
            longest = word
    
    return longest

def shortest_word(words):
    """
    Find shortest string in collection of strings
    
    Args:
        words: Non-empty collection of strings
    
    Returns:
        shortest string
    """
    
    shortest = words[0]
    
    for word in words:
        if len(word) <  len(shortest):
            shortest = word
      
    return shortest
