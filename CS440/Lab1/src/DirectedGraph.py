#!/usr/bin/env python3

# Name: Kyle Aure
# Course: CS440
# Date: 07/23/2019 
#####  DESCRIPTION  #####
# This class represents a directed graph
# it uses a dictionary as the underlying 
# data structure to keep track of nodes
# and edges
# Reference Material: https://www.geeksforgeeks.org/detect-cycle-in-a-graph/
from collections import defaultdict
import graphs
import json

#tag::class[]
class DirectedGraph:
    # Constructor
    def __init__(self, nodes): 
        self.graph = defaultdict(list)  # Initialize the graph itself
        self.m = nodes                  # Initialize m to number of nodes
        self.cycle = None               # This will hold array with cycle if one is found

    def __str__(self):
        return self.graph.__str__
  
    # Function to add an edge
    def addEdge(self, parent, child):
        self.graph[parent].append(child)

    # definition of function 
    def generateEdges(self): 
        edges = [] 
        # for each node in graph 
        for node in self.graph: 
            # for each neighbor node of a single node 
            for neighbor in self.graph[node]: 
                # if edge exists then append 
                edges.append((node, neighbor)) 
        return edges 

    #tag::findCycle[]
    # Function returns true if graph has been evaluated as a DAG
    # returns false if graph has a cycle
    # returns None if graph has not been evaluated
    def isDAG(self):
        if(self.cycle == None):
            return None
        return len(self.cycle) == 0

    # Function returns true if graph is DAG else false 
    def evaluateAsDAG(self):
        if self.m == 0:
            return None, self.cycle

        # Initialize visited and stack arrays 
        visited = [False] * self.m      # Keeps track of which node(s) have been visited
        stack = [False] * self.m        # Keeps track of which node(s) are on the stack
        self.cycle = []

        # Iterate through each node once
        for node in range(self.m): 
            if visited[node] == False: 
                # Does this node lead to a cycle?
                if self.findCycle(node,visited,stack):
                    # If so end function and return false
                    return False, self.cycle
        
        # If we have visited every node, and have not found a cycle, return true
        return True, self.cycle

    # helper function to find cycle
    def findCycle(self, node, visited, stack): 
        # Mark current node as visited and put it on the stack
        visited[node] = True
        stack[node] = True
  
        # Recur for all neighbors
        for neighbor in self.graph[node]:
            # If neighbor has not been visited yet, go there
            if not visited[neighbor]:
                # If neighbor(s) close cycle, return true from current node
                if self.findCycle(neighbor, visited, stack):
                    self.cycle.append(node)
                    return True
            # If neighbor has been visited AND it is already on the stack,
            # then this node closes the cycle. return true.
            elif stack[neighbor] == True: 
                self.cycle.append(node)
                return True
  
        # Once we have visited all neighbors
        # if we have not found a cycle remove this node from stack
        # and return false
        self.cycle = []
        stack[node] = False
        return False
    #end::findCycle[]
    #tag::getTopologicalOrdering[]
    # function to get topological ordering
    def getTopologicalOrdering(self):
        # Make sure we are working with a DAG
        if self.isDAG() == None:
            raise Exception("This directed graph needs to be evaluated as a Directed Acyclic Graph (DAG) before a topological ordering can be determined")
        if not self.isDAG():
            raise Exception("This directed graph is not a Directed Acyclic Graph (DAG) and therefore does not have a toplogical ordering")
        
        # Initialize visited and stack arrays 
        visited = [False] * self.m         # Keeps track of which node(s) have been visited
        stack = []                         # Keeps topological order as stack
  
        # Iterate through each node once
        for node in range(self.m): 
            if not visited[node]: 
                # call recursive function which will add nodes to 
                # the stack in topological order
                self.topologicalOrderHelper(node, visited, stack) 
  
        # return resulting stack
        return stack
        
    # helper function for topological ordering recursion
    def topologicalOrderHelper(self, node, visited, stack):
        # Mark the current node as visited. 
        visited[node] = True
  
        # Recur for all neighbors
        for neighbor in self.graph[node]: 
            if visited[neighbor] == False: 
                self.topologicalOrderHelper(neighbor, visited, stack) 
  
        # Push current vertex to stack which stores result 
        stack.insert(0,node) 
    #end::getTopologicalOrdering[]
#end::class[]
# main runs and prints example graphs
def main():
    nonCyclicResult, nonCyclicCycle = graphs.nonCyclicGraph.evaluateAsDAG()
    cyclicResults, cyclicCycle = graphs.cyclicGraph.evaluateAsDAG()
    nonCyclicTop = nonCyclicTopE = cyclicTop = cyclicTopE = None

    try:
        nonCyclicTop = graphs.nonCyclicGraph.getTopologicalOrdering()
    except Exception as e:
        nonCyclicTopE = str(e)

    try:
        cyclicTop = graphs.cyclicGraph.getTopologicalOrdering()
    except Exception as e:
        cyclicTopE = str(e)

    output = {
    "Acyclic Graph":[
        {
            "Properties": [
            {
                "Nodes": "{}".format(graphs.nonCyclicGraph.graph),
                "Edges": "{}".format(graphs.nonCyclicGraph.generateEdges())
            }],
            "Evaluation": [
            {
                "isDAG":nonCyclicResult,
                "cycle":str(nonCyclicCycle)
            }],
            "Topological Ordering": str(nonCyclicTop),
            "Topological Exception": nonCyclicTopE
        }],
    "Cyclic Graph":[
        {
            "Properties": [
            {
                "Nodes": "{}".format(graphs.cyclicGraph.graph),
                "Edges": "{}".format(graphs.cyclicGraph.generateEdges())
            }],
            "Evaluation": [
            {
                "isDAG":cyclicResults,
                "cycle":str(cyclicCycle)
            }],
            "Topological Ordering": str(cyclicTop),
            "Topological Exception": cyclicTopE
        }]
    }

    print(json.dumps(output, indent=1, sort_keys=True))
if __name__ == '__main__':
    main()
