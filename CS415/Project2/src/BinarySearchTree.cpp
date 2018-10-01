struct Node {
private:
	int data;
public:
	Node(int tempData){
		data = tempData;
	}
	int* getData() {
		return data;
	}
	void setData(int thisData) {
		data = thisData;
	}
};

class TreeNode {
private:
	Node thisNode;
	Node parent;
	Node leftChild;
	Node rightChild;

public:
	TreeNode(int data) {
		thisNode = Node(data);
	}

	int getData() {
		return data;
	}

	TreeNode getParent() {
		return parent;
	}
	TreeNode getLeftChild() {
		return leftChild;
	}
	TreeNode getRightChild() {
		return rightChild;
	}

	void setData(int thisData){
		data = thisData;
	}
	void setParent(TreeNode thisParent) {
		parent = thisParent;
	}
	void setLeftChild(TreeNode thisLeftChild){
		leftChild = thisLeftChild;
	}
	void setRightChild(TreeNode thisRightChild){
		rightChild = thisRightChild;
	}

	void insert(int thisData, TreeNode node) {
		if(thisData < node.getData()) {
			if(leftChild == 0) {
				leftChild = new TreeNode(thisData);
				leftChild.setParent(node);
				node.setLeftChild(leftChild);
			} else {
				leftChild.insert(data, node.getLeftChild());
			}
		}else {
			if(rightChild == 0) {
				rightChild = new TreeNode(data);
				rightChild.setParent(node);
				node.setRightChild(rightChild);
			}else {
				rightChild.insert(data, node.getRightChild());
			}
		}
	}
};
