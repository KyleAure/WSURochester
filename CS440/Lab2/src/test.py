#!/usr/bin/env python3

# Name: Kyle Aure
# Course: CS440
# Date: 07/31/2019 
#####  DESCRIPTION  #####
# This class tests our weighted directed graph class and functions

import unittest
from WeightedDirectedGraph import WeightedDirectedGraph, printResults
#tag::test[]
class testWeightedDirectedGraph (unittest.TestCase):
    def testAlwaysTrue(self):
        self.assertTrue(True, "This should always be true")

    def testDistances(self):
        graph = WeightedDirectedGraph(2)
        graph.addEdge(0,1,5)
        self.assertEqual(5, graph.weights[0][1], "Distance from 0 to 1 should be 5")
        self.assertEqual(-1, graph.weights[1][0], "Distance from 1 to 0 should be -1")

    def testDijkstra(self):
        graph = WeightedDirectedGraph(5)
        graph.weights = [
            [-1,3,-1,4,-1],     #0 --(3)--> 1, 0 --(4)--> 3
            [-1,-1,3,-1,-1],    #1 --(3)--> 2
            [-1,-1,-1,-1,3],    #2 --(3)--> 4
            [-1,-1,-1,-1,4],    #3 --(4)--> 4
            [-1,-1,-1,-1,-1]    #Path [0,1,2,4] = 9, [0,3,4] = 8  
        ]
        dist, path = graph.dijkstra(0)
        
        distance = graph.weights[0][1] + graph.weights[1][2] + graph.weights[2][4]
        self.assertEqual(9, distance, "Path [0,1,2,4] should have a distance of 9")

        distance = graph.weights[0][3] + graph.weights[3][4]
        self.assertEqual(8, distance, "Path [0,3,4] should have a distance of 8")

        self.assertEqual(8, dist[4], "Shortest path from 0 to 4 should be of distance 8")
        self.assertEqual([0, 3, 4], path[4], "Shortest path from 0 to 4 should be [0, 3, 4]")
#end::test[]
# Unit test runner
if __name__ == '__main__':
    unittest.main(verbosity=2)