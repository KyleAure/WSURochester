#!/usr/bin/env python3

# Name: Kyle Aure
# Course: CS440
# Date: 07/31/2019 
#####  DESCRIPTION  #####
# This class represents a directed weighted 
# graph it uses a 2-D array as the underlying 
# data structure to keep track of nodes, edges, and weights
import sys

# tag::class[]
class WeightedDirectedGraph:
    # Constructor
    def __init__(self, nodes):
        self.n = nodes # Initialize n to number of nodes          
        
        # Initialize 2-D array of size n*n
        # If weights[u][v] > -1 then 
        # there exits an edge (u,v) 
        # and it's weight is val(weights[u][v])
        self.weights = [[-1 for columns in range(nodes)] for rows in range(nodes)]
  
    # Function to add an edge
    def addEdge(self, parent, child, weight):
        self.weights[parent][child] = weight

    # tag::helper[]
    # A helper function to find the next node,
    # which has the shortest distance,
    # but has not yet been put on the shortest path list.
    def minDistance(self, dist, onPath):
        # Init min to infinity
        min = sys.maxsize

        # Search for the nearest node not
        # already in the shortest path list.
        for node in range(self.n):
            if dist[node] < min and onPath[node] == False:
                min = dist[node]
                index = node

        return index
    # end::helper[]
    # tag::dijkstra[]
    def dijkstra(self, source):
        # Variable needed for dijkstra
        dist = [sys.maxsize] * self.n       # dist[x] is the distance from source to x
        dist[source] = 0                    # Initialize distance from source to source = 0
        onPath = [False] * self.n           # onPath[x] is true if x has been found to be on the shortest path
        path = [[] for i in range(self.n)]  # path[x] will give path from source to x in order
        path[source].append(source)         # Initialize the path from source to source

        for _ in range(self.n):
            # Use our helper function to find next node
            # to be processed.  That is the node with the least distance
            # that is not already on the shortest distance path.
            u = self.minDistance(dist, onPath)

            # Put this on the shortest path 
            onPath[u] = True

            # Go through each node
            for v in range(self.n):
                # If we have found a connecting node
                # and it is not yet on the shortest path
                # and the current distance of v is more than the distance of u plus this path
                # Then we have found a shorter path
                if (self.weights[u][v] > -1) and (onPath[v] == False) and (dist[v] > dist[u] + self.weights[u][v]):
                    # Update the distance of the shortest path
                    dist[v] = dist[u] + self.weights[u][v]
                    # Update the actual path array
                    path[v] = path[u] + [v]
        
        return dist, path
    # end::dijkstra[]
# end::class[]
# tag::main[]
# Helper function to print results
def printResults(graph, dist, path):
    print("\n----Graph----\n")
    for row in range(graph.n):
        print("Node: {}\tMap: {}".format(row,graph.weights[row]))

    print("\n----Shortest Path----\n")
    for row in range(graph.n):
        print("Node: {}\tPath: {}".format(row, path[row]))

    print("\n----Minimum Distance----\n")
    for row in range(graph.n):
        print("Node: {}\tDistance: {}".format(row, dist[row]))

# main runs and prints example graphs
def main():
    size = 9
    source = 0
    graph = WeightedDirectedGraph(size) 
    graph.weights = [
        [-1, 4, -1, -1, -1, -1, -1, 8, -1], 
        [4, -1, 8, -1, -1, -1, -1, 11, -1], 
        [-1, 8, -1, 7, -1, 4, -1, -1, 2], 
        [-1, -1, 7, -1, 9, 14, -1, -1, -1], 
        [-1, -1, -1, 9, -1, 1-1, -1, -1, -1], 
        [-1, -1, 4, 14, 1-1, -1, 2, -1, -1], 
        [-1, -1, -1, -1, -1, 2, -1, 1, 6], 
        [8, 11, -1, -1, -1, -1, 1, -1, 7], 
        [-1, -1, 2, -1, -1, -1, 6, 7, -1] 
        ]
    
    dist, path = graph.dijkstra(source) 
    printResults(graph, dist, path)
# end::main[]
if __name__ == '__main__':
    main()
