#!/usr/bin/env python3

# Name: Kyle Aure
# Course: CS440
# Date: 08/05/2019 
#####  DESCRIPTION  #####
# This file defines functions to be used
# on a weighted undirected connected graph.
# It is important this the graph is connected.
# Otherwise, a minimum spanning tree cannot
# necessarily be created.

# tag::MST[]
from WeightedUndirectedGraph import WeightedUndirectedGraph, find, union

# Helper function: takes in an edge and returns it's weight
# Used by the default sorting function in Python so that 
# we can sort edges in ascending order.
def sortBy(edge):
	return edge[2]

def printMST(result):
	print("\n\n-----Minimum Spanning Tree-----")
	print("Example: u ---(weight)---> v ")
	print("-------------------------------")
	for entry in result:
		print("{0} ---({2:2d})---> {1}".format(entry[0], entry[1], entry[2]))

# The main function to construct MST using Kruskal's algorithm
# tag::notes[]
# From Notes:
# 	Graph: G
# 	Minimum Spanning Tree: T
# 	Edge: _e_ from (x,y)
# 	Sort all edges _e_ in G (shortest to longest)
# 	T = {}
# 	For all _e_ in G
# 		If T UNION {e} has no cycle		//if find(x) != find(y)
# 			T = T Union {e}				//using Union Find union(x,y)
# end::notes[]
def MST(graph): 

	result =[] 		# Array - Stores the final MST structure
	parent = [] 	# Array - parent[node] returns the parent of this node
	height = [] 	# Array - height[node] returns number of childen of this

	nextEdge = 0 	# An index variable, used for sorted edges 
	edges = 0 		# An index variable, used for result[] size

	# Step 1: Sort all the edges by weight from shortest to longest.
	# Use default sorted method in Python sorted(array, rule)
	# Here the rule uses the sortBy helper function to sort by weight 
	graph.graph = sorted(graph.graph,key=sortBy) 

	# Initialize parent and height arrays 
	for node in range(graph.n): 
		parent.append(node)	# Each node is it's own parent to begin with
		height.append(0) 	# Each node has height of 0

	# Iterate through edges until n-1 edges have been added to MST
	while edges < graph.n -1 : 
		# Step 2.1: Pick the smallest edge. 

		# Get edge (u,v,weight)
		u, v, weight = graph.graph[nextEdge] 

		# Find root-parent of u = x and v = y
		x = find(parent, u) 
		y = find(parent ,v) 

		# Step 2.2: Check if it forms a cycle with the spanning tree formed so far. 
		if x != y:
			# Step 2.3: If cycle is not formed, include this edge. 
			edges += 1	
			result.append([u, v, weight]) 
			union(parent, height, x, y)			 
		# Step 2.4: Else, discard it.

		# Increment to next smallest edge
		nextEdge += 1

	return result

# end::MST[]
# tag::main[]
# Entry point if we want to run sample code
if __name__ == '__main__':
	g = WeightedUndirectedGraph(4)
	# Weighted Undirected Graph
	#  0 --(1)-- 1
	#  | \       |
	#  |  \      |
	# (4)  (3)  (2)
	#  |     \   |
	#  |      \  |
	#  2 --(5)-- 3
	# Minimum Spanning Tree
	# 0 ---( 1)---> 1
	# 1 ---( 2)---> 3
	# 0 ---( 4)---> 2

	g.addEdge(0, 1, 1)
	g.addEdge(0, 2, 4) 
	g.addEdge(0, 3, 3)
	g.addEdge(1, 3, 2)
	g.addEdge(2, 3, 5)

	printMST(MST(g))
# end::main[]

