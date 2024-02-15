package org.familytree;

import java.util.Arrays;

public class ArbolBinario implements IArbolBinario{

    public Nodo root;

    public ArbolBinario() {
        root = null;
    }

    public ArbolBinario(String data) {
        root = new Nodo(data);
    }

    @Override
    public void insert(String parentNode, String newNode) {
        Nodo parent = getNode(parentNode);
        if (parent == null) {
            System.out.println("El nodo padre no existe en el árbol");
            return;
        }
        if (search(newNode)) {
            System.out.println("El nodo ya existe en el árbol");
            return;
        }
        if (parent.left == null) {
            parent.left = new Nodo(newNode);
        } else if (parent.right == null) {
            parent.right = new Nodo(newNode);
        } else {
            System.out.println("El nodo padre ya tiene dos hijos");
        }
    }

    @Override
    public void insert(String data) {
        root = insert1(root, data);
    }

    @Override
    public boolean search(String data) {
        return search(root, data);
    }

    @Override
    public Nodo getRoot() {
        return root;
    }

    @Override
    public Nodo getNode(String data) {
        return getNode(root, data);
    }

    @Override
    public void delete(String data) {
        root = delete(root, data);
    }

    @Override
    public String[] inOrder() {
        int treeSize = size();
        String[] result = new String[treeSize];
        inOrder(root, result,0);
        System.out.println(Arrays.toString(result));
        return result;
    }

    @Override
    public String[] preOrder() {
        int treeSize = size();
        String[] result = new String[treeSize];
        preOrder(root, result,0);
        System.out.println(Arrays.toString(result));
        return result;
    }

    @Override
    public String[] postOrder() {
        int treeSize = size();
        String[] result = new String[treeSize];
        postOrder(root, result,0);
        System.out.println(Arrays.toString(result));
        return result;
    }

    @Override
    public int height() {
        return height(root);
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public void print() {

    }

    private static Nodo delete(Nodo root, String data) {
        if(root == null) {
            return null;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = delete(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = delete(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = delete(root.right, root.data);
        }
        return root;
    }

    private static String minValue(Nodo node) {
        String minv = node.data;
        while (node.left != null) {
            minv = node.left.data;
            node = node.left;
        }
        return minv;
    }

    private static int preOrder(Nodo root, String[] result, int index) {
        if(root == null) {
            return index;
        }

        result[index++] = root.data;
        index = preOrder(root.left, result, index);
        index = preOrder(root.right, result, index);

        return index;

    }

    private static int inOrder(Nodo root, String[] result, int index) {
        if(root == null) {
            return index;
        }


        index = inOrder(root.left,result,index);
        result[index++] = root.data;
        index = inOrder(root.right,result,index);

        return index;
    }

    private static int postOrder(Nodo root, String[] result, int index) {
        if(root == null) {
            return index;
        }

        index = postOrder(root.left,result,index);
        index = postOrder(root.right,result,index);
        result[index++] = root.data;

        return index;
    }

    private static boolean search(Nodo root, String data) {
        if(root == null) {
            return false;
        }

        if(root.data.equals(data)) {
            return true;
        }

        return search(root.left, data) || search(root.right, data);
    }

    private static Nodo getNode(Nodo root, String data) {
        if (root == null) {
            return null;
        }

        if (root.data.equals(data)) {
            return root;
        }

        Nodo leftSearch = getNode(root.left, data);
        if (leftSearch != null) {
            return leftSearch;
        }

        return getNode(root.right, data);
    }




    private static int size(Nodo root) {
        if(root == null) {
            return 0;
        }

        return 1 + size(root.left) + size(root.right);
    }

    private static int height(Nodo root) {
        if(root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.left), height(root.right));
    }
    public static Nodo insert1(Nodo root, String data) {
        if (root == null) {
            return new Nodo(data);
        }
        if (data.compareTo(root.data) < 0) {
            root.left = insert1(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insert1(root.right, data);
        }
        return root;
    }





}
