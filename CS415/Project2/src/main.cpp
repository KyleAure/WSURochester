//
//  main.cpp
//  Project2fix
//
//  Created by Aure, Kyle J on 10/4/18.
//  Copyright © 2018 Aure, Kyle J. All rights reserved.
//

#include <iostream>
#include <algorithm>

template <class T>
struct Node {
    // ##data memebers##
    T value;
    Node *left;
    Node *right;
    
    // ##constructors##
    Node(T val) {
        this->value = val;
        this->left = NULL;
        this->right = NULL;
    }
    
    Node(T val, Node<T> left, Node<T> right) {
        this->value = val;
        this->left = left;
        this->right = right;
    }
};

template <class T>
class BST {
private:
    // ## data members ##
    Node<T> *root;
    
    // ## private access methods ##
    
    //add a new node starting at root
    void addHelper(Node<T> *root, T val) {
        if (root->value > val) {                //turn left
            if (root->left == NULL) {           //if nothing there create new node
                root->left = new Node<T>(val);
            } else {                            //else contiue searching
                addHelper(root->left, val);
            }
        } else {                                //turn right
            if (root->right == NULL) {          //if nothing there create new node
                root->right = new Node<T>(val);
            } else {                            //else continue searching
                addHelper(root->right, val);
            }
        }
    }
    
    //print tree using In-Order traversal
    //left - parent - right
    void inOrderPrint(Node<T> *root) {
        if (!root) return;
        inOrderPrint(root->left);
        std::cout << root->value << ' ';
        inOrderPrint(root->right);
    }
    
    //print tree using Post-Order traversal
    //parent - left - right
    void postOrderPrint(Node<T> *root) {
        if (!root) return;
        std::cout << root->value << ' ';
        postOrderPrint(root->left);
        postOrderPrint(root->right);
    }
    
    //count nodes in tree
    int nodesCountHelper(Node<T> *root) {
        if (!root) return 0;
        else return 1 + nodesCountHelper(root->left) + nodesCountHelper(root->right);
    }
    
    //get height of tree
    int heightHelper(Node<T> *root) {
        if (!root) return 0;
        else return 1 + std::max(heightHelper(root->left), heightHelper(root->right));
    }
    
    //return longest path
    void printMaxPathHelper(Node<T> *root) {
        if (!root) return;
        std::cout<<root->value<<' ';
        if (heightHelper(root->left) > heightHelper(root->right)) {
            printMaxPathHelper(root->left);
        } else {
            printMaxPathHelper(root->right);
        }
    }
    
    //delete a value from true by providing NULL, ROOT, VAL
    //traverse tree, remove node, and fix pointers
    //return false if value is not found
    bool deleteValueHelper(Node<T>* parent, Node<T>* current, T value) {
        if (!current) return false;
        if (current->value == value) {
            if (current->left == NULL || current->right == NULL) {
                Node<T>* temp = current->left;
                if (current->right) temp = current->right;
                if (parent) {
                    if (parent->left == current) {
                        parent->left = temp;
                    } else {
                        parent->right = temp;
                    }
                } else {
                    this->root = temp;
                }
            } else {
                Node<T>* validSubs = current->right;
                while (validSubs->left) {
                    validSubs = validSubs->left;
                }
                T temp = current->value;
                current->value = validSubs->value;
                validSubs->value = temp;
                return deleteValueHelper(current, current->right, temp);
            }
            delete current;
            return true;
        }
        return deleteValueHelper(current, current->left, value) ||
        deleteValueHelper(current, current->right, value);
    }
    
public:
    //Public access methods
    //Use default constructor
    
    //add value to tree
    void add(T val) {
        if (root) {
            this->addHelper(root, val);
        } else {
            root = new Node<T>(val);
        }
    }
    
    
    void printInOrder() {
        std::cout << "In Order Print: ";
        inOrderPrint(root);
        std::cout << std::endl;
    }
    
    void printPostOrder() {
        std::cout << "Post Order Print: ";
        postOrderPrint(root);
        std::cout << std::endl;
    }
    
    int nodesCount() {
        return nodesCountHelper(root);
    }
    
    void printNodeCount() {
        std::cout << "Node count: " << nodesCount() << std::endl;
    }
    
    int height() {
        return heightHelper(root);
    }
    
    void printHeight() {
        std::cout << "Height: " << height() << std::endl;
    }
    
    void printMaxPath() {
        std::cout << "Max path: ";
        printMaxPathHelper(root);
        std::cout << std::endl;
    }
    
    bool deleteValue(T value) {
        return this->deleteValueHelper(NULL, root, value);
    }
};

int main() {
    //create binary search tree
    BST<int> *bst = new BST<int>();
    
    //array of values to add to tree
    int vals [6] = {11, 1, 6, -1, -10, 100};
    
    //add values
    for(const int &val : vals) {
        bst->add(val);
    }
    
    bst->printInOrder();
    bst->printPostOrder();
    bst->printNodeCount();
    bst->printHeight();
    bst->printMaxPath();
    
    //remove values
    for(const int &val : vals) {
        bst->deleteValue(val);
        std::cout << val << " removed: ";
        bst->printInOrder();
    }
    return 0;
}
