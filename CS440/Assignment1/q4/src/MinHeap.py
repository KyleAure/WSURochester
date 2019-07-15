#!/usr/bin/env python
# Name: Kyle Aure
# Course: CS440
# Date: 07/14/2019 
# Description:
    # This class will be used to represent a min-heap, since the algorithm in this problem
    # is to be used for determining if x less than the k-th smallest element in the heap 
    # I will be using an implementation of min-heap (heapq) that already exists in python

import heapq

#Exceptions#
class Error(Exception):
    pass

class HeapTooSmallError(Error):
    pass

class OutOfBoundsError(Error):
    pass

#Utility Functions: Algorithm independent#
def isInputValid(k, n):
    """
    Determins if input is valid
    """
    if n < 1:
        raise HeapTooSmallError('The heap was too small, it had a size of {}'.format(n))
    
    if not 1 <= k <= n:
        raise OutOfBoundsError('k should be between 1 and {}, but the value of k was {}'.format(n, k))

#Main Class
class minheap:
    """
    Variables: 
    heap    -> the heap itself
    n       -> the size of the heap
    k       -> the k-th smallest value (not necessarily at index k)
    x       -> a value
    """

    def __init__(self, array):
        """
        Constructor: creates heap, sets variable n, and prints resulting heap.
        """
        self.heap = array #The heap itself
        self.n = len(self.heap) #The size of the heap

        heapq.heapify(self.heap)
        print('Generated Heap: {}'.format(list(self.heap)))

    def simpleAlgorithm(self, x, k):
        """
        Simplest algorithm that pop's the min off the heap 
        k times, then compares that value to x.
        returns bool and value of the k-th term
        O(nlogn)
        """
        #Check input is valid
        isInputValid(k, self.n)

        #Pop values k-1 times
        while k > 1:
            print('Iteration: {} | Pop Value: {}'.format(k, heapq.heappop(self.heap)))
            k -= 1

        #Get value and result
        value = heapq.heappop(self.heap)
        result = (x < value)

        #return
        return result, value