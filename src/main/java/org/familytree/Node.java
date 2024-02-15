package org.familytree;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private String data;
    private List<Node> children = new ArrayList<>();

    public Node(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
    public void addChild(Node child) {
        this.children.add(child);
    }

    public List<Node> getChildren(){
        return this.children;
    }

    public String toString() {
        return data;
    }
}
