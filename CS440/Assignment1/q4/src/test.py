# This is the entry point for this program
from MinHeap import minheap
import MinHeap
import unittest

# Arrays used for testing
emptyArray = []
sortedArray = [2, 18, 26, 55, 79, 85, 92, 150]
unSortedArray = [15, 52, 17, 48, 23, 82, 41, 87]

class testSimpleAlgorithm (unittest.TestCase):

    def testEmptyArray(self):
        _array = list(emptyArray)
        _heap = minheap(_array)
        self.assertRaises(MinHeap.HeapTooSmallError, _heap.simpleAlgorithm, 5, 5)

    def testOutOfBounds(self):
        _array = list(sortedArray)
        _heap = minheap(_array)
        self.assertRaises(MinHeap.OutOfBoundsError, _heap.simpleAlgorithm, 0, len(_array) + 1)


    def testSortedArray(self):
        expected = 79

        _array = list(sortedArray)
        _heap = minheap(_array)
        result, value = _heap.simpleAlgorithm(0,5)
        self.assertTrue(result, "Simple Algorithm should have returned true as 0 will always be less")
        self.assertEqual(value, expected)

        _array = list(sortedArray)
        _heap = minheap(_array)
        result, value = _heap.simpleAlgorithm(100,5)
        self.assertFalse(result, "Simple Algorithm should have returned false as 100 will always be more")
        self.assertEqual(value, expected)



if __name__ == '__main__':
    unittest.main()