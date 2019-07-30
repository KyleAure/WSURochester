#!/usr/bin/env python3

# Name: Kyle Aure
# Course: CS440
# Date: 07/24/2019 
#####  DESCRIPTION  #####
# This class represents a directed weighted 
# graph it uses a 2-D array as the underlying 
# data structre to keep track of nodes, edges, and weights
from collections import defaultdict
import graphs
import json

#tag::class[]
class WeightedDirectedGraph:
    # Constructor
    def __init__(self, nodes):
        self.m = nodes # Initialize m to number of nodes          
        
        # Initialize 2-D array of size m*m
        # If weights[u][v] > 0 then 
        # there exits an edge (u,v) 
        # and it's weight is val(weights[u][v])
        self.weights = [[0 for columns in range(nodes)] for rows in range(nodes)]
  
    # Function to add an edge
    def addEdge(self, parent, child, weight):
        self.weights[parent][child] = weight

    def minDistance(self, )

#end::class[]
# main runs and prints example graphs
def main():
    pass
if __name__ == '__main__':
    main()
