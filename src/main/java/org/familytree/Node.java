package org.familytree;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String data;
    private Node parent;
    private Node mother;
    private String gender;
    private List<Node> children = new ArrayList<>();

    public Node(String data) {
        this.data = data;
        this.gender=gender;
    }

    public String getData() {
        return data;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(){
        this.gender= gender;
    }

    public void addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
    }

    public List<Node> getChildren(){
        return this.children;
    }

    public boolean isParentOf(Node node) {
        return this.children.contains(node);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getMother() { // Add this method
        return mother;
    }

    public void setMother(Node mother) { // Add this method
        this.mother = mother;
    }

    public String toString() {
        return data;
    }
}
