#!/usr/bin/env python3

# Name: Kyle Aure
# Course: CS440
# Date: 07/23/2019 
#####  DESCRIPTION  #####
# This file hosts graphs for
# examples and testing

from DirectedGraph import DirectedGraph

emptyGraph = DirectedGraph(0)
cyclicGraph = DirectedGraph(7)
nonCyclicGraph = DirectedGraph(13)
cyclicGraph.graph = {
    0:[1,2,6],
    1:[3],
    2:[1],
    3:[2,4,5],
    4:[],
    5:[0,4],
    6:[4]
}
nonCyclicGraph.graph = {
    0:[5,6,1],
    1:[],
    2:[0,3],
    3:[5],
    4:[],
    5:[4],
    6:[4,9],
    7:[6],
    8:[7],
    9:[10,11,12],
    10:[],
    11:[12],
    12:[],
}