# A printed list of the 10 most frequent words in the file in descending order of frequency along with each word’s count
# A log-log plot of word count versus word rank
#
# CS 150 Spring 2019 Lab 9
#
# Name: Jackson Evans, Tai Nguyen
# Section: B
#
# Creativity:
# gg plot implemented (end of the program)
# file name added to title of ggplot

# Import required R packages (suppressing conflict messages)
library(plyr, warn.conflicts=FALSE)
library(stringr, warn.conflicts=FALSE)
library(ggplot2, warn.conflicts=FALSE)


# Split strings on word boundaries, removing any punctuation
# Args:
#   strings: A vector of strings
# Returns a vector of strings (the words)
split_and_strip <- function(strings) {
  # str_subset removes any empty strings, or strings that contain only whitespace.
  # \S is the character class for non white-space characters
  unlist(str_split(str_subset(strings, "\\S+"), boundary("word")))
}


# Read file into a vector of cleaned and normalized words
# Args:
#   filename: Filename to analyze as a string
# Returns a vector of cleaned and normalized words
file_to_words <- function(filename) {
  string <- readLines(filename,warn=FALSE)
  string <- tolower(string)
  split_and_strip(string)
}

# Create a ranked data frame of words and their counts
# Args:
#   words: Vector of cleaned words
# Returns a data.frame of words and counts in descending order of count
count_and_rank <- function(word_list) {
  df <- plyr::count(data.frame(word_list))
  colnames(df) <- c("Word","Count")
  sorted <- df[order(df$Count,decreasing=TRUE),]
}

# Prompt the user for a file name and construct ranks data.frame
filename <- readline(prompt="Enter a filename: ")
word_list <- file_to_words(filename)
counts <- count_and_rank(word_list)

# Print 10 most common words and generate a log-log plot count vs. rank
print(head(counts, 10), row.names=FALSE)

#--- 
plot_graph <- function(filename) {
  plot(
    1:nrow(counts), counts$Count, log='xy', type='p',
    
    main = 'Log-log plot of Count vs Rank of words in text corpus',
    xlab ='Rank',
    ylab ='Frequency'
  )
}

plot_graph(filename)

#--- CREATIVITY

ggplot_graph <- function(filename) {
  plt <- ggplot(counts, aes(x=(1:nrow(counts)), y=counts$Count)) + geom_point() + 
    ggtitle('Log-log plot of Count vs Rank of words in text corpus | File used:', filename)+
    xlab("Rank") + ylab("Frequency")+
    scale_x_log10() +
    scale_y_log10()
  print(plt)
}
