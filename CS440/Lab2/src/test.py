#!/usr/bin/env python3

# Name: Kyle Aure
# Course: CS440
# Date: 07/23/2019 
#####  DESCRIPTION  #####
# This class tests our directed graph class and functions

import unittest
from graphs import *
from DirectedGraph import DirectedGraph

class testDirectedGraph (unittest.TestCase):
    def testAlwaysTrue(self):
        self.assertTrue(True, "This should always be true")

    def testEmptyGraph(self):
        result, cycle = emptyGraph.evaluateAsDAG()

        self.assertIsNone(result, "Empty graph should not be a DAG")
        self.assertIsNone(cycle, "Empty graph should not have initialized a cycle")
        self.assertIsNone(emptyGraph.isDAG(), "Empty graph should not be a DAG")
        with self.assertRaises(Exception):
            emptyGraph.getTopologicalOrdering()

    def testCyclicGraph(self):
        with self.assertRaises(Exception):
            cyclicGraph.getTopologicalOrdering()
        result, cycle = cyclicGraph.evaluateAsDAG()
        expectedCycle = [2,3,1,0]

        self.assertFalse(result, "Cyclic graph should not be a DAG")
        self.assertIsNotNone(cycle, "Cyclic graph should have cycle")
        self.assertEqual(expectedCycle, cycle, "Cyclic graph did not return correct cycle")
        self.assertFalse(cyclicGraph.isDAG(), "Cyclic graph should not be a DAG")
        with self.assertRaises(Exception):
            cyclicGraph.getTopologicalOrdering()

    def testNonCyclicGraph(self):
        with self.assertRaises(Exception):
            nonCyclicGraph.getTopologicalOrdering()
        result, cycle = nonCyclicGraph.evaluateAsDAG()
        expectedCycle = []
        expectedTopOrder = [8, 7, 2, 3, 0, 1, 6, 9, 11, 12, 10, 5, 4]

        self.assertTrue(result, "NonCyclic graph should be a DAG")
        self.assertIsNotNone(cycle, "NonCyclic graph should have empty cycle")
        self.assertEqual(expectedCycle, cycle, "NonCyclic graph did not return empty cycle")
        self.assertTrue(nonCyclicGraph.isDAG(), "NonCyclic graph should be a DAG")
        self.assertEqual(expectedTopOrder, nonCyclicGraph.getTopologicalOrdering(), "NonCyclic graph did not return expected topological ordering.")
        
# Unit test runner
if __name__ == '__main__':
    unittest.main(verbosity=2)