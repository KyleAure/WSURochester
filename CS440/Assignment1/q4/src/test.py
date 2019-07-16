#!/usr/bin/env python3
# This is the entry point for this program
from MinHeap import minheap
import MinHeap
import unittest

# Arrays used for testing
emptyArray = []
sortedArray = [2, 18, 26, 55, 79, 85, 92, 98]
unSortedArray = [15, 52, 17, 48, 23, 82, 41, 87]

class testSimpleAlgorithm (unittest.TestCase):
    ###  TEST EXCEPTIONS  ###
    def testEmptyArray(self):
        _array = list(emptyArray)
        _heap = minheap(_array)
        self.assertRaises(MinHeap.HeapTooSmallError, _heap.simpleAlgorithm, 5, 5)
        self.assertRaises(MinHeap.HeapTooSmallError, _heap.efficientAlgorithm, 5, 5)

    def testOutOfBounds(self):
        _array = list(sortedArray)
        _heap = minheap(_array)
        self.assertRaises(MinHeap.OutOfBoundsError, _heap.simpleAlgorithm, 0, len(_array) + 1)
        self.assertRaises(MinHeap.OutOfBoundsError, _heap.simpleAlgorithm, 0, 0)
        self.assertRaises(MinHeap.OutOfBoundsError, _heap.efficientAlgorithm, 0, len(_array) + 1)
        self.assertRaises(MinHeap.OutOfBoundsError, _heap.efficientAlgorithm, 0, 0)

    ###  TEST SIMPLE ALGORITHM  ###
    def testSortedArraySimple(self):
        expected = 79

        _array = list(sortedArray)
        _heap = minheap(_array)
        result, value = _heap.simpleAlgorithm(0,5)
        #print(_heap.getLog())
        self.assertFalse(result, "Simple Algorithm should have returned false as 0 will always be less")
        self.assertEqual(value, expected)

        _array = list(sortedArray)
        _heap = minheap(_array)
        result, value = _heap.simpleAlgorithm(100,5)
        #print(_heap.getLog())
        self.assertTrue(result, "Simple Algorithm should have returned true as 100 will always be more")
        self.assertEqual(value, expected)
    
    def testUnSortedArraySimple(self):
        expected = 48

        _array = list(unSortedArray)
        _heap = minheap(_array)
        result, value = _heap.simpleAlgorithm(0,5)
        #print(_heap.getLog())
        self.assertFalse(result, "Simple Algorithm should have returned false as 0 will always be less")
        self.assertEqual(value, expected)

        _array = list(unSortedArray)
        _heap = minheap(_array)
        result, value = _heap.simpleAlgorithm(100,5)
        #print(_heap.getLog())
        self.assertTrue(result, "Simple Algorithm should have returned true as 100 will always be more")
        self.assertEqual(value, expected)

    ###  TEST HELPER METHODS ###
    def testGetHeight(self):
        _array = [8,8,8,8,8,8,8,8]
        _heap = minheap(_array)
        self.assertEqual(_heap.getHeight(1), 0, "Height when k = 1 should be 0")
        self.assertEqual(_heap.getHeight(3), 1, "Height when k = 3 should be 1")
        self.assertEqual(_heap.getHeight(5), 2, "Height when k = 5 should be 2")
        self.assertEqual(_heap.getHeight(8), 3, "Height when k = 8 should be 3") 

    def testGetIndexes(self):
        _array = [8,8,8,8,8,8,8,8]
        _heap = minheap(_array)
        start, end = _heap.getIndexes(0)
        self.assertEqual(start, 0, "When height is 0, index should start at 0")
        self.assertEqual(end, 1, "When height is 0, index should end at 1")

        start, end = _heap.getIndexes(1)
        self.assertEqual(start, 1, "When height is 1, index should start at 1")
        self.assertEqual(end, 3, "When height is 1, index should end at 3")

        start, end = _heap.getIndexes(2)
        self.assertEqual(start, 3, "When height is 2, index should start at 3")
        self.assertEqual(end, 7, "When height is 2, index should end at 7")

        start, end = _heap.getIndexes(3)
        #print(_heap.getLog())
        self.assertEqual(start, 7, "When height is 3, index should start at 7")
        self.assertEqual(end, 8, "When height is 3, index should end at 8") #only 8 elements in array
 

    ###  TEST EFFICIENT ALOGRITHM  ###
    def testSortedArrayEfficient(self):
        _array = list(sortedArray)
        _heap = minheap(_array)
        result = _heap.efficientAlgorithm(0,5)
        print(_heap.getLog())
        self.assertFalse(result, "Efficient Algorithm should have returned false as 0 will always be less")

        _array = list(sortedArray)
        _heap = minheap(_array)
        result = _heap.efficientAlgorithm(100,5)
        #print(_heap.getLog())
        self.assertTrue(result, "Efficient Algorithm should have returned true as 100 will always be more")

    def testUnSortedArrayEfficient(self):
        _array = list(unSortedArray)
        _heap = minheap(_array)
        result = _heap.efficientAlgorithm(0,5)
        #print(_heap.getLog())
        self.assertFalse(result, "Efficient Algorithm should have returned false as 0 will always be less")

        _array = list(sortedArray)
        _heap = minheap(_array)
        result = _heap.efficientAlgorithm(100,5)
        #print(_heap.getLog())
        self.assertTrue(result, "Efficient Algorithm should have returned true as 100 will always be more")

if __name__ == '__main__':
    unittest.main()