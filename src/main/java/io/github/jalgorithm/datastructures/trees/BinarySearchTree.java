package io.github.jalgorithm.datastructures.trees;

import java.util.ArrayList;

/**
 * @author Muhammad Karbalaee
 * @param <T> Any object which implements the Comparable interface is accepted
 */
public class BinarySearchTree<T extends Comparable<T>>{
  private BinarySearchNode<T> rootNode;
  private final ArrayList<T> sortedData;
  private final ArrayList<T> preorderWalkResult;
  private final ArrayList<T> postorderWalkResult;

  /**
   * constructor for BinarySearchTree class.
   */
  public BinarySearchTree() {
    this.rootNode = null;
    this.sortedData = new ArrayList<>();
    this.preorderWalkResult = new ArrayList<>();
    this.postorderWalkResult = new ArrayList<>();
  }

  /**
   * by calling this method the sorted data will be stored in the
   * @param node the node of the subtree to print. if you want it to print the whole tree,
   *             pass the root node to it.
   *@param shouldClear if true, the ArrayList containing the result of inorder walk gets cleared. if false, the result is kept
   *                and concatenated with the result of the previous call.
   */
  public void inorderTreeWalk(BinarySearchNode<T> node,boolean shouldClear) {
    if (shouldClear) {
      this.sortedData.clear();
    }
    if (node != null) {
      this.inorderTreeWalk(node.getLeftChild(),false);
      this.sortedData.add(sortedData.size(),node.getData());
      this.inorderTreeWalk(node.getRightChild(),false);
    }
  }

  /**
   * by calling this method all the data in this tree will be walked through
   * @param node the node of the subtree to print. if you want it to print the whole tree,
   *             pass the root node to it.
   *
   * @param shouldClear if true, the ArrayList containing the result of preorder walk gets cleared. if false, the result is kept
   *                   and concatenated with the result of the previous call.
   */
  public void preorderTreeWalk(BinarySearchNode<T> node,boolean shouldClear) {
    if (node == this.rootNode) {
      this.preorderWalkResult.clear();
    }
    if (node != null) {
      this.preorderTreeWalk(node.getLeftChild(),false);
      this.preorderWalkResult.add(preorderWalkResult.size(),node.getData());
      this.preorderTreeWalk(node.getRightChild(),false);
    }
  }

  /**
   * by calling this method all the data in this tree will be walked through
   * @param node the node of the subtree to print. if you want it to print the whole tree,
   *             pass the root node to it.
   * @param shouldClear if true, the ArrayList containing the result of postorder walk gets cleared. if false, the result is kept
   *                    and concatenated with the result of the previous call.
   */
  public void postorderTreeWalk(BinarySearchNode<T> node,boolean shouldClear) {
    if (shouldClear) {
      this.postorderWalkResult.clear();
    }
    if (node != null) {
      this.postorderTreeWalk(node.getLeftChild(),false);
      this.postorderWalkResult.add(postorderWalkResult.size(),node.getData());
      this.postorderTreeWalk(node.getRightChild(),false);
    }
  }

  /**
   *
   * @return the root node of the tree
   */
  public BinarySearchNode<T> getRootNode() {
    return rootNode;
  }

  /**
   *
   * @return An ArrayList containing all the data inserted in the tree so far in a sorted order
   */
  public ArrayList<T> getSortedData() {
    this.inorderTreeWalk(this.rootNode,true);
    return this.sortedData;
  }

  /**
   *
   * @return data in the order presented by preorder tree walk
   */
  public ArrayList<T> getPreorderWalkResult() {
    this.preorderTreeWalk(this.rootNode,true);
    return this.preorderWalkResult;
  }

  /**
   *
   * @return data in the order presented by postorder tree walk
   */
  public ArrayList<T> getPostorderWalkResult() {
    this.postorderTreeWalk(this.rootNode,true);
    return this.postorderWalkResult;
  }

  /**
   *
   * @return data in the order presented by inorder tree walk
   */
  public ArrayList<T> getInorderWalkResult() {
    return this.sortedData;
  }

  /**
   * to insert a new node with new data into the tree
   * @param newData data of type T which is the same type as the type specified when creating BinarySearchTree class
   */
  public void insert(T newData) {
    BinarySearchNode<T> newNode = new BinarySearchNode<>(null,null,null,newData);
    BinarySearchNode<T> parentOfNewNode = null;
    BinarySearchNode<T> temporaryNode = this.rootNode;
    while (temporaryNode != null) {
      parentOfNewNode = temporaryNode;
      int comparisonResult = newNode.getData().compareTo(temporaryNode.getData());
      if (comparisonResult < 0) {
        temporaryNode = temporaryNode.getLeftChild();
      } else {
        temporaryNode = temporaryNode.getRightChild();
      }
    }
    newNode.setParent(parentOfNewNode);
    int parentChildDataComparisonResult = 0;
    if (parentOfNewNode != null) {
      parentChildDataComparisonResult = newNode.getData().compareTo(parentOfNewNode.getData());
    }
    if (parentOfNewNode == null) {
      this.rootNode = newNode;
    } else if (parentChildDataComparisonResult < 0) {
      parentOfNewNode.setLeftChild(newNode);
    } else {
      parentOfNewNode.setRightChild(newNode);
    }
  }

  /**
   *
   * @param pivotNode the node that its value is compared to searchingValue parameter
   * @param searchingValue the value to search for in the tree
   * @return the node with the wanted value
   */
  public BinarySearchNode<T> treeSearch(BinarySearchNode<T> pivotNode,T searchingValue) {
    if (pivotNode == null || pivotNode.getData() == searchingValue) {
      return pivotNode;
    }
    int comparisonResult = pivotNode.getData().compareTo(searchingValue);
    if (comparisonResult > 0) {
      return this.treeSearch(pivotNode.getLeftChild(),searchingValue);
    } else {
      return this.treeSearch(pivotNode.getRightChild(),searchingValue);
    }
  }

  /**
   *
   * @param startingNode the node to start finding the minimum from.
   *                     if you want the minimum node of the whole tree, pass the root node to this
   *                     method;otherwise, it will find the minimum value in the subtree.
   * @return the node of the tree which has the minimum data value
   */
  public BinarySearchNode<T> treeMinimum(BinarySearchNode<T> startingNode) {
    while (startingNode.getLeftChild() != null) {
      startingNode = startingNode.getLeftChild();
    }
    return startingNode;
  }

  /**
   *
   * @param startingNode the node to start finding the maximum from.
   *                     if you want the minimum node of the whole tree, pass the root node to this
   *                     method;otherwise, it will find the maximum value in the subtree.
   * @return the node of the tree which has the maximum data value
   */
  public BinarySearchNode<T> treeMaximum(BinarySearchNode<T> startingNode) {
    while (startingNode.getRightChild() != null) {
      startingNode = startingNode.getRightChild();
    }
    return startingNode;
  }

  /**
   *
   * @param node the node which we want its successor
   * @return the node which comes exactly after the node in sorted order
   */
  public BinarySearchNode<T> treeSuccessor(BinarySearchNode<T> node) {
    if (node.getRightChild() != null) {
      return this.treeMinimum(node.getRightChild());
    }
    BinarySearchNode<T> temporaryNode = node.getParent();
    while (temporaryNode != null && node == temporaryNode.getRightChild()) {
      node = temporaryNode;
      temporaryNode = temporaryNode.getParent();
    }
    return temporaryNode;
  }

  /**
   *
   * @param node the node which we want its predecessor
   * @return the node which comes exactly before the node in sorted order
   */
  public BinarySearchNode<T> treePredecessor(BinarySearchNode<T> node) {
    if (node.getLeftChild() != null) {
      return this.treeMaximum(node.getLeftChild());
    }
    BinarySearchNode<T> temporaryNode = node.getParent();
    while (temporaryNode != null && node == temporaryNode.getLeftChild()) {
      node = temporaryNode;
      temporaryNode = temporaryNode.getParent();
    }
    return temporaryNode;
  }

  /**
   * this method replaces one subtree as a child of its parent
   * with another subtree.
   * @param firstSubtreeRoot the root of first subtree
   * @param secondSubtreeRoot the root of second subtree
   */
  public void transplant(BinarySearchNode<T> firstSubtreeRoot,
                         BinarySearchNode<T> secondSubtreeRoot) {
    if (firstSubtreeRoot.getParent() == null) {
      this.rootNode = secondSubtreeRoot;
    } else if (firstSubtreeRoot == firstSubtreeRoot.getParent().getLeftChild()) {
      firstSubtreeRoot.getParent().setLeftChild(secondSubtreeRoot);
    } else {
      firstSubtreeRoot.getParent().setRightChild(secondSubtreeRoot);
    }
    if (secondSubtreeRoot != null) {
      secondSubtreeRoot.setParent(firstSubtreeRoot.getParent());
    }
  }

  /**
   * deletes the specified node from the tree
   * @param nodeToDelete the node that you want to delete from the tree
   */
  public void delete(BinarySearchNode<T> nodeToDelete) {
    if (nodeToDelete.getLeftChild() == null) {
      this.transplant(nodeToDelete,nodeToDelete.getRightChild());
    } else if (nodeToDelete.getRightChild() == null) {
      this.transplant(nodeToDelete,nodeToDelete.getLeftChild());
    } else {
      BinarySearchNode<T> temporaryNode = this.treeMinimum(nodeToDelete.getRightChild());
      if (temporaryNode.getParent() != nodeToDelete) {
        this.transplant(temporaryNode,temporaryNode.getRightChild());
        temporaryNode.setRightChild(nodeToDelete.getRightChild());
        temporaryNode.getRightChild().setParent(temporaryNode);
      }
      this.transplant(nodeToDelete,temporaryNode);
      temporaryNode.setLeftChild(nodeToDelete.getLeftChild());
      temporaryNode.getLeftChild().setParent(temporaryNode);
    }
  }

  /**
   *
   * @return the string representation of a BinarySearchTree object
   */
  @Override
  public String toString() {
    return "BinarySearchTree{" + "rootNode=" + rootNode.toString() + '}';
  }
}
