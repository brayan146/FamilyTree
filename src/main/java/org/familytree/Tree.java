package org.familytree;

public class Tree {
    private Node root;

    public Tree() {
        this.root = null;
    }
    public Node getRoot() {
        return root;
    }

    Node result = null;
    public Node search(String dad){
        result = null;
        return search(root, dad);
    }

    private Node search(Node node, String d){
        if(node == null){
            result = null;
        }
        assert node != null;
        System.out.println("Search current node " + node.getData() + " node father " + d);
        if(d.equals(node.getData())){
            result = node;
        }

        for(Node child : node.getChildren()) {
            if(result != null) {
                break;
            }
            else {
                search(child, d);
            }
        }
        return result;
    }

    public void insertNode(String child, String father) {
        if(getRoot() == null) {
            root = new Node(child);
        } else {
            Node nodeFather = search(father);
            if(nodeFather != null) {
                System.out.println("Node father: " + nodeFather.getData());
                nodeFather.addChild(new Node(child));
            } else {
                root.getChildren().add(new Node(child));
            }
        }
    }
}
