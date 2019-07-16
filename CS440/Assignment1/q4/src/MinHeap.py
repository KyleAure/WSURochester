#!/usr/bin/env python3
# Name: Kyle Aure
# Course: CS440
# Date: 07/14/2019 
#####  DESCRIPTION  #####
# This class will be used to represent a min-heap, 
# since the algorithm in this problem is to be used 
# for determining if x less than the k-th smallest 
# element in the heap I will be using an implementation 
# of min-heap (heapq) that already exists in python

####  IMPORTS  ####
import heapq
import math

####  CUSTOM EXCEPTIONS  ####
class Error(Exception):
    pass
class HeapTooSmallError(Error):
    pass
class OutOfBoundsError(Error):
    pass

####  UTILITY FUNCTIONS ####
def isInputValid(k, n):
    # Determins if input is valid

    if n < 1:
        raise HeapTooSmallError('The heap was too small, it had a size of {}'.format(n))
    if not 1 <= k <= n:
        raise OutOfBoundsError('k should be between 1 and {}, but the value of k was {}'.format(n, k))

####  MAIN CLASS ####
class minheap:
    # heap -> the heap itself
    # n    -> the size of the heap
    # k    -> the k-th smallest value (not necessarily at index k)
    # x    -> a value
    # log  -> logger for debugging

    def __init__(self, array):
        # Constructor: creates heap and sets variable n
        self.heap = array       #The heap itself
        self.n = len(self.heap) #The size of the heap
        self.log = "\n\n"       #Log start with new line

        #Construct heap
        heapq.heapify(self.heap)

        #Log results
        self.logger('Generated Heap: {}\n'.format(list(self.heap)))

    def getLog(self):
        return self.log

    def logger(self, s):
        self.log += s

    def simpleAlgorithm(self, x, k):
        # Simplest algorithm 
        # Pops the min element off the heap 
        # k times, then compares that value to x.
        # Returns bool and value for the k'th term
        # Complexity: O(klogn)
        self.logger('-> Simple Algorithm x:{} k:{} n:{}\n'.format(x, k, self.n))

        #Check input is valid
        self.logger('-> isInputValid\n')
        isInputValid(k, self.n)
        self.logger('<- isInputValid\n')

        #Main part of algorithm
        self.logger('-> algorithm\n')
        while k > 1:                       #complexity O(k)
            out = heapq.heappop(self.heap) #complexity O(logn)
            self.logger('Iteration: {} | Pop Value: {}\n'.format(k, out))
            k -= 1
        self.logger('<- algorithm\n')

        #Get value and result
        value = heapq.heappop(self.heap)
        result = (value < x)
        self.logger('value:{} < x:{} ? result:{}\n'.format(value, x, result))

        #return
        self.logger('<- Simple Algorithm')
        return result, value

    def efficientAlgorithm(self, x, k):
        # By definition the k'th term must be a node at height=floor(logk).
        # ie:: If k = 5, the k'th term must be an element at height 2.
        # There are 2^height elements in this subset.
        # By the definition of a min-heap all 2^height-1 nodes that came before 
        # the subset are less each node in the subset.
        # Therefore, if the count of nodes in the subset, that are 
        # less than x, plus all the parent nodes are >= k, 
        # then the k'th term must be less than x: else it is not.
        self.logger('-> Efficient Algorithm x:{} k:{} n:{}\n'.format(x, k, self.n))

        #Check input is valid
        self.logger('-> isInputValid\n')
        isInputValid(k, self.n)
        self.logger('<- isInputValid\n')

        #Height in heap where the k'th element exists
        height = self.getHeight(k)

        #Slice heap so we only have the elements at the correct height
        startIndex, endIndex = self.getIndexes(height)
        slicedHeap = self.heap[startIndex:endIndex]
        self.logger("Resulting Heap: {}\n".format(slicedHeap))

        #Iterate through slicedHeap
        count = 0
        for node in slicedHeap: #O(2^height) = O(2^(logk)) = O(k)
            if(node < x):
                count += 1

        result = count + 2**(height)-1 >= k 
        self.logger("count:{} elements in subset + parents:{} >= k:{} ? result:{}\n"
            .format(count, 2**(height)-1, k, result))

        self.logger('<- Efficient Algorithm\n')
        return result

    ####  HELPER FUNCTIONS FOR EFFICIENT ALGORITHM ####
    def getHeight(self, k):
        height = math.floor(math.log(k, 2))
        self.logger("Inspection height is: {}\n".format(height))
        return height

    def getIndexes(self, height):
        startIndex = 2**(height) - 1 #-1 index starts at 0
        lastIndex = 2**(height+1) - 1 #-1 index starts at 0
        #Last row could have empty nodes
        if(lastIndex > self.n):  
            endIndex = self.n
        else:
            endIndex = lastIndex
        self.logger("Start index: {}\nEnd index (exclusive): {}\n".format(startIndex, endIndex))
        return startIndex, endIndex




