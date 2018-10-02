//
//  node.hpp
//  Project2
//
//  Created by Aure, Kyle J on 10/2/18.
//  Copyright © 2018 Aure, Kyle J. All rights reserved.
//

#ifndef node_hpp
#define node_hpp



class TreeNode{
private:
    //data members
    int data;
    int *parent;
    int *leftChild;
    int *rightChild;

public:
    TreeNode(int tempData);
    TreeNode();
    int getData();
    void setData(int _data);
    TreeNode getParent();
    void setParent(TreeNode _parent);
    TreeNode getLeftChild();
    void setLeftChild(TreeNode _leftChild);
    TreeNode getRightChild();
    void setRightChild(TreeNode _rightChild);
    void insert(int _data, TreeNode node);
};

#endif /* node_hpp */
