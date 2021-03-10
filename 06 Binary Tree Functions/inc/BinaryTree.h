#ifndef BINARY_TREE_H
#define BINARY_TREE_H
#include <iostream>
using namespace std;
template <class Type>
struct Node {
    Type item;
    Node<Type> *left;
    Node<Type> *right;
};

template <class Type>
class BinaryTree
{
    public:
        BinaryTree();
		BinaryTree(const BinaryTree& other);
		//BinaryTree& operator=(BinaryTree<Type>& other);
        ~BinaryTree();
        void insert(Type item);
        void preOrder();
        void inOrder();
        void postOrder();
        int nodeCount();
        Node<Type>*find(Type item);
        Node<Type>*findRightMostNode(Node<Type> *find);
        void remove(Type item);
        int  height();
        int leavesCount();


    protected:
        Node<Type> *root;

    private:
        void destroy(Node<Type> * curr);
        void insert(Type item, Node<Type> * curr);
        void preOrder(Node<Type> *curr);
        void postOrder(Node<Type> *curr);
        void inOrder(Node<Type> * curr);
        int nodeCount(Node<Type> * curr);
        int leavesCount(Node<Type> * curr);
        Node<Type>*find(Type item, Node<Type> *curr);
        Node<Type>* remove(Type item, Node<Type>*curr);
        int height(int level, Node<Type>*curr);
		void copyNode(Node<Type>*& copy, const Node<Type>* orig);
};

template <class Type>
BinaryTree<Type>::BinaryTree(){
	root = nullptr;
}

template <class Type>
BinaryTree<Type>::BinaryTree(const BinaryTree<Type>& other) {
	if (other.root == nullptr) {
		root = nullptr;
	}
	else {
		copyNode(root, other.root);
	}
}
//template <class Type>
//BinaryTree<Type>& BinaryTree<Type>::operator=(BinaryTree& other) {
//	if (this != &other) {
//		delete root;
//		root = new Node;
//		if (other.root == nullptr) {
//			root = nullptr;
//		}
//		else {
//			copyNode(root, other.root);
//		}
//	}
//	return *this;
//}

template <class Type>
void BinaryTree<Type>::copyNode(Node<Type>*& copy, const Node<Type>* orig) {
	copy = new Node<Type>;
	copy->item = orig->item;
	copy->left = nullptr;
	copy->right = nullptr;
	if (orig->left != nullptr) {
		copy->left = new Node<Type>;
		copyNode(copy->left, orig->left);
	}

	if (orig->right != nullptr) {
		copy->right = new Node<Type>;
		copyNode(copy->right, orig->right);
	}
}

template <class Type>
BinaryTree<Type>::~BinaryTree(){		//Everything until leavesCount is the same as the learning activity
	destroy(root);
}

template <class Type>
void BinaryTree<Type>::destroy(Node<Type> * curr){
	if (curr != nullptr) {
		destroy(curr->left);
		destroy(curr->right);
		delete curr;
	}
}

template <class Type>
void BinaryTree<Type>::insert(Type item){
	if (root == nullptr) {
		root = new Node<Type>;
		root->item = item;
		root->left = nullptr;
		root->right = nullptr;
	}
	else {
		insert(item, root);
	}

}

template <class Type>
void BinaryTree<Type>::insert(Type item, Node<Type> * curr){
	if (item < curr->item) {
		if (curr->left == nullptr) {
			auto tmp = new Node<Type>;
			tmp->item = item;
			tmp->right = nullptr;
			tmp->left = nullptr;
			curr->left = tmp;
		}
		else {
			insert(item, curr->left);
		}
	}
	else {
		if (curr->right == nullptr) {
			auto tmp = new Node<Type>;
			tmp->item = item;
			tmp->right = nullptr;
			tmp->left = nullptr;
			curr->right = tmp;
		}
		else {
			insert(item, curr->right);
		}
	}
}


template <class Type>
void BinaryTree<Type>::preOrder(){
	std::cout << "Pre Order: ";
	preOrder(root);
}
template <class Type>
void BinaryTree<Type>::preOrder(Node<Type> *curr){
	if (curr != nullptr) {
		std::cout << curr->item << " ";
		preOrder(curr->left);
		preOrder(curr->right);
	}
}

template <class Type>
void BinaryTree<Type>::inOrder(){
	std::cout << "In Order: ";
	inOrder(root);
}
template <class Type>
void BinaryTree<Type>::inOrder(Node<Type> *curr){
	if (curr != nullptr) {

		inOrder(curr->left);
		std::cout << curr->item << " ";
		inOrder(curr->right);
	}

}

template <class Type>
void BinaryTree<Type>::postOrder(){
	std::cout << "Post Order: ";
	postOrder(root);
}
template <class Type>
void BinaryTree<Type>::postOrder(Node<Type> *curr){
	if (curr != nullptr) {

		postOrder(curr->left);
		postOrder(curr->right);
		std::cout << curr->item << " ";
	}
}


template <class Type>
int BinaryTree<Type>::nodeCount(){
	return nodeCount(root);
}

template <class Type>
int BinaryTree<Type>::nodeCount(Node<Type> *curr){
	if (curr != nullptr) {
		return 1 + nodeCount(curr->left) + nodeCount(curr->right);
	}
	return 0;
}


template <class Type>
int BinaryTree<Type>::leavesCount(){
	if (root == nullptr) {	//if there is nothing there just return 0
		return 0;
	}
	else if(!root->left && !root->right){
		return 1;							//If there is something there but nothing below return 1
	}
	return leavesCount(root->left) + leavesCount(root->right);		//add the leaves of the left and right trees
	
	
}

template <class Type>
int BinaryTree<Type>::leavesCount(Node<Type> *curr){
	if (curr == nullptr) {
		return 0;
	}
	else if (!curr->left && !curr->right) {		//same logic as above
		return 1;
	}
	else {
		return leavesCount(curr->left) + leavesCount(curr->right);
	}
}


template <class Type>
Node<Type>*BinaryTree<Type>::find(Type item){
	if (root != nullptr) {				
		if (root->item == item) {	//if looking for root
			return root;
		}
		else {
			return find(item, root);		//recusively find the item
		}
	}
	return nullptr;
}


template <class Type>
Node<Type>*BinaryTree<Type>::find(Type item, Node<Type>*curr){
	if (curr) {
		if (curr->item == item) {
			return curr;		//if you find it return it
		}

		if (curr->item > item) {
		return find(item, curr->left);		//its bigger go left
	}

		if (curr->item < item) {
			return find(item, curr->right);		//its smaller go right
		}

	}
	else
	return nullptr;		// cant find it then its not there

	
}


template <class Type>
Node<Type>*BinaryTree<Type>::findRightMostNode(Node<Type> *curr){

	if (curr->right) {
		return findRightMostNode(curr->right);			//as long as there is a right, keep going right
	}
	return curr;
}

template <class Type>
void BinaryTree<Type>::remove(Type item){
	auto curr = find(item);		//find the item
	if (curr->left == nullptr) {
		
		auto temp = curr->right;		//replace the node with the right branch if there is no left branch
		curr->item = temp->item;
		temp = nullptr;
	}
	else if (curr->right == nullptr) {
		auto temp = curr->left;			//same for if there is no right branch
		curr->item = temp->item;
		temp = nullptr;

	}

	else {
		
		auto temp = findRightMostNode(curr->left);			//find that rightMostNode and then replace curr with it
		curr->item = temp->item;
		temp = nullptr;
	}
}


template <class Type>
Node<Type>* BinaryTree<Type>::remove(Type item, Node<Type>* curr){		//Didn't need this

    return nullptr;
}

template <class Type>
int BinaryTree<Type>::height(){		//there was some logic I found online that helped with this
	int level = 0;
	if (root == nullptr)
	{					//if there is nothing there return 0
		return 0;
	}
	else {
		int lDepth = height(level, root->left);		//sum up the left
		int rDepth = height(level, root->right);	//sum up the right

		if (lDepth > rDepth)		//see which one is bigger and return the one that is
			return (lDepth + 1);
		else {
			return (rDepth + 1);
		}
	}
}
template <class Type>
int BinaryTree<Type>::height(int level, Node<Type>*curr){
	if (curr) {
		
		if (curr->right) {			//if there is a right +1 to level
		level++;
			height(level, curr->right);
		}
		if (curr->left) {				//if left +1 to level
			level++;
			height(level, curr->left);
		}
		else {
			return level;		//return the total level of this branch
		}
	}
}

#endif // BINARY_TREE_H
