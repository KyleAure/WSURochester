#!/usr/bin/env python3

# Name: Kyle Aure
# Course: CS440
# Date: 08/05/2019 
#####  DESCRIPTION  #####
# This class represents a weighted undirected graph
# it uses a dictionary as the underlying data structure 
# to keep track of nodes, edges, and weights
# Reference Material: Course notes

# tag::helper[]
class IllegalWeightException(Exception):
    """ Raised when adding an edge with a weight <= 0 """
    pass

# Helper Function - finds the root-parent of a node
# tag::notes[]
# From Notes:
#   find(x)
#       if Parent[x] = -1
# 	        return x
#       else:
# 	        return find(Parent[x])
# end::notes[]
def find(parent, node):
    """
    Parent - array that keeps track of parents
    Node - Node who's parent we want to find.
    """
    if parent[node] == node:  # If node maps to itself then it is the root - return it
        return node
    # Recursively call function to find the parent of this nodes parent.
    return find(parent, parent[node]) 

# Helper Function - Creates a union of two subsets x and y
# tag::notes[]
# From Notes:
#     union(x, y)
#         rootx = find(x)
#         rooty = find(y)
#         if(h[rootx] < h[rooty])
#             A[rootx] = rooty
#         else
#             A[rooty] = rootx
#         if h[rootx] == h[rooty]
#             h[rootx]++
#             h[rooty]++
# end::notes[]
def union(parent, height, x, y):
    """
    parent[node] returns the parent of this node
    height[node] returns number of children
    """
    # Find parent of ROOTx and ROOTy
    rootx = find(parent, x)
    rooty = find(parent, y)

    # Attach smaller height tree under root of  
    # high height tree
    if height[rootx] < height[rooty]: 
        parent[rootx] = rooty 
    
    elif height[rootx] > height[rooty]: 
        parent[rooty] = rootx

    # If heights are same, then make one as root  
    # and increment its height by one 
    else : 
        parent[rooty] = rootx 
        height[rootx] += 1
# end::helper[]
# tag::class[]
class WeightedUndirectedGraph:
    def __init__(self, nodes):
        self.n = nodes      #Number of nodes in graph
        self.graph = []     #The graph itself

    # Function to add an edge to the graph
    def addEdge(self, u, v, weight):
        if weight <= 0:
            raise IllegalWeightException("Weight cannot be less than or equal to 0")
        self.graph.append([u,v,weight])

# end::class[]
