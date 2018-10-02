//
//  bst.cpp
//  Project2
//
//  Created by Aure, Kyle J on 10/2/18.
//  Copyright © 2018 Aure, Kyle J. All rights reserved.
//

#include "bst.hpp"
#include "node.hpp"
#include <iostream>

class BinarySearchTree {
private:
    //data members
    TreeNode root;
    
public:
    //constructor
    BinarySearchTree() {
        root = 0;
    }
    
    //setter and getter
    TreeNode getRoot() {
        return root;
    }
    
    void setRoot(TreeNode _root) {
        root = _root;
    }
    
    //this method will build the tree
    void insertNode(int data) {
        if (root == 0) {
            root = *new TreeNode(data);
        } else {
            root.insert(data, root);
        }
    }
    
    
    //Pre-Order Traverse
    //Parent - Left - Right
    void preOrderTraverse() {
        preOrderHelper(root);
    }
    
    void preOrderHelper(TreeNode node) {
        if (node == 0) {
            return;
        }
        std::cout << node.getData() << " " << std::endl;
        preOrderHelper(node.getLeftChild());
        preOrderHelper(node.getRightChild());
    }
    

    
    TreeNode search(int key, TreeNode node) {
        TreeNode temp = searchHelper(key, node);
        
        return temp;
    }
    
    TreeNode searchHelper(int key, TreeNode node) {
        if (key < node.getData()) {
            return searchHelper(key, node.getLeftChild());
        } else if (key < node.getData()) {
            return searchHelper(key, node.getRightChild());
        } else {
            return node;
        }
    }
};
