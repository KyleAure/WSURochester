#!/usr/bin/env python3

# Name: Kyle Aure
# Course: CS440
# Date: 07/31/2019 
#####  DESCRIPTION  #####
# This class tests our weighted directed graph class and functions

import unittest
from WeightedUndirectedGraph import *
from MinimumSpanningTree import MST, printMST

#tag::test[]
class testMST (unittest.TestCase):
    def testAlwaysTrue(self):
        self.assertTrue(True, "This should always be true")

    def testDistances(self):
        graph = WeightedUndirectedGraph(3)
        graph.addEdge(0,1,5)
        _, _, weight = graph.graph[0]
        
        # Make sure weight is set correctly.
        self.assertEqual(5, weight, "Weight from 0 to 1 should be 5")

        # Make sure we cannot create a wight of 0
        with self.assertRaises(IllegalWeightException):
            graph.addEdge(1,2,0)
        
        # Make sure we cannot create a negative weight
        with self.assertRaises(IllegalWeightException):
            graph.addEdge(2,3,-4)
            
    def testFind(self):
        parent = [1, 2, 3, 3]

        result = find(parent, 0)
        self.assertEqual(3, result, "Root-parent of node 0 should be node 3 but was {}".format(result))

        result = find(parent, 1)
        self.assertEqual(3, result, "Root-parent of node 1 should be node 3 but was {}".format(result))

        result = find(parent, 3)
        self.assertEqual(3, result, "Root-parent of node 3 should be node 3 but was {}".format(result))

    def testUnion(self):
        # Parent: 0-1-2 and 3-4-5-6
        parent = [1, 2, 2, 4, 5, 6, 6]
        height = [0, 1, 2, 0, 1, 2, 3]
        
        # Union 0, 3       
        union(parent, height, 0, 3)

        # Parent: 0-1-2-6 and 3-4-5-6
        self.assertEqual(parent, [1, 2, 6, 4, 5, 6, 6], "Parent after union did not produce expected result.")

        # Height: [0, 1, 2, 0, 1, 2, 3]
        self.assertEqual(height, [0, 1, 2, 0, 1, 2, 3], "Height after union did not produce expected result.")

    def testMST(self):
        # Weighted Undirected Graph
        #  0 --(1)-- 1
        #  | \       |
        #  |  \      |
        # (4)  (3)  (2)
        #  |     \   |
        #  |      \  |
        #  2 --(5)-- 3
        graph = WeightedUndirectedGraph(4)
        graph.addEdge(0, 1, 1)
        graph.addEdge(0, 2, 4) 
        graph.addEdge(0, 3, 3)
        graph.addEdge(1, 3, 2)
        graph.addEdge(2, 3, 5)

        # Minimum Spanning Tree
        # 0 ---( 1)---> 1
        # 1 ---( 2)---> 3
        # 0 ---( 4)---> 2
        result = MST(graph)

        _, _, weight = result[0]
        self.assertEqual(1, weight, "First edge in MST should have weight of 1 but was {}".format(weight))

        _, _, weight = result[1]
        self.assertEqual(2, weight, "First edge in MST should have weight of 2 but was {}".format(weight))

        _, _, weight = result[2]
        self.assertEqual(4, weight, "First edge in MST should have weight of 4 but was {}".format(weight))

        #Should not have more than 3 results
        with self.assertRaises(Exception):
            _, _, weight = result[3]
# end::test[]
# Unit test runner
if __name__ == '__main__':
    unittest.main(verbosity=2)