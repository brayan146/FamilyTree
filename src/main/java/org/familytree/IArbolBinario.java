package org.familytree;

public interface IArbolBinario {
    /**
     * Inserts a new node with the given data into the binary tree.
     *
     * @param data The data to be inserted.
     */
    void insert(int data);

    /**
     * Searches for a node with the given data in the binary tree.
     *
     * @param data The data to search for.
     * @return True if the data is found, false otherwise.
     */
    boolean search(int data);

    /**
     * Returns the root node of the binary tree.
     *
     * @return The root node of the binary tree.
     */
    Nodo getRoot();

    /**
     * Searches for a node with the given data in the binary tree and returns it if found.
     *
     * @param data The data to search for.
     * @return The node with the given data, or null if not found.
     */
    Nodo getNode(int data);

    /**
     * Deletes a node with the given data from the binary tree, if it exists.
     *
     * @param data The data of the node to delete.
     */
    void delete(int data);

    /**
     * Performs an in-order traversal of the binary tree and returns an array of node data.
     *
     * @return An array of node data in in-order traversal order.
     */
    int[] inOrder();

    /**
     * Performs a pre-order traversal of the binary tree and returns an array of node data.
     *
     * @return An array of node data in pre-order traversal order.
     */
    int[] preOrder();

    /**
     * Performs a post-order traversal of the binary tree and returns an array of node data.
     *
     * @return An array of node data in post-order traversal order.
     */
    int[] postOrder();

    /**
     * Calculates and returns the height of the binary tree.
     *
     * @return The height of the binary tree.
     */
    int height();

    /**
     * Calculates and returns the number of nodes in the binary tree.
     *
     * @return The number of nodes in the binary tree.
     */
    int size();

    /**
     * Prints the elements of the method traversals inorder, preorder, and postorder
     */
    void print();
}
