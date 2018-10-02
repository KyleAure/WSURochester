//
//  node.cpp
//  Project2
//
//  Created by Aure, Kyle J on 10/2/18.
//  Copyright © 2018 Aure, Kyle J. All rights reserved.
//

//#include "node.hpp"
class TreeNode {
private:
    //data members
    int data;
    TreeNode *parent;
    TreeNode *leftChild;
    TreeNode *rightChild;
    
public:
    //constructor
    TreeNode(int tempData){
        data = tempData;
    }
    
    //setters and getters
    int getData() {
        return data;
    }

    void setData(int _data) {
        data = _data;
    }

    TreeNode getParent() {
        return *parent;
    }
    
    void setParent(TreeNode _parent) {
        *parent = _parent;
    }
    
    TreeNode getLeftChild() {
        return *leftChild;
    }
    
    void setLeftChild(TreeNode _leftChild) {
        *leftChild = _leftChild;
    }

    TreeNode getRightChild() {
        return *rightChild;
    }

    void setRightChild(TreeNode _rightChild) {
        *rightChild = _rightChild;
    }

    //insert a new tree node to the tree
    void insert(int _data, TreeNode *node){
        if(data < node->getData()){ //turn left
            if(leftChild == 0){
                leftChild = new TreeNode(data);
                leftChild->setParent(*node);
                node->setLeftChild(*leftChild);
            }else{
                leftChild->insert(data, node->getLeftChild());
            }
        }else{ //turn right
            if(rightChild == 0){
                rightChild = new TreeNode(data);
                rightChild->setParent(*node);
                node->setRightChild(*rightChild);
            }else{
                rightChild->insert(data, node->getRightChild());
            }
        }
    }
};

