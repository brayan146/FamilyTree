package org.familytree;

import java.util.ArrayList;
import java.util.List;

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
    public List<Node> getPathFromRootToNode(String data) {
        List<Node> path = new ArrayList<>();
        if(!findPath(root, data, path)) {
            path.clear();
        }
        return path;
    }

    private boolean findPath(Node node, String data , List<Node> path) {
        if(node == null) {
            return false;
        }
        path.add(node);
        if(node.getData().equals(data)) {
            return true;
        }
        for(Node child : node.getChildren()) {
            if(findPath(child, data, path)) {
                return true;
            }
        }
        path.remove(node);
        return false;
    }


    public Node getFather(String person) {
        List<Node> path = getPathFromRootToNode(person);
        if(path.size() > 1) {
            return path.get(path.size() - 2);
        }
        return null;
    }



    public List<Node> getFullSiblings(String person) {
        Node father = getFather(person);
        if(father != null) {
            List<Node> fullSiblings = new ArrayList<>();
            for(Node child : father.getChildren()) {
                if(!child.getData().equals(person)) {
                    fullSiblings.add(child);
                }
            }
            return fullSiblings;
        }
        return new ArrayList<>();
    }

    public List<Node> getUnclesAndAunts(String person) {
        Node father = getFather(person);
        if(father != null) {
            List<Node> unclesAndAunts = new ArrayList<>();
            Node grandFather = getFather(father.getData());
            if(grandFather != null) {
                for(Node child : grandFather.getChildren()) {
                    if(!child.getData().equals(father.getData())) {
                        unclesAndAunts.add(child);
                    }
                }
            }
            return unclesAndAunts;
        }
        return new ArrayList<>();
    }
    public List<Node> getFirstCousins(String person) {
        List<Node> unclesAndAunts = getUnclesAndAunts(person);
        List<Node> firstCousins = new ArrayList<>();
        for(Node uncleOrAunt : unclesAndAunts) {
            firstCousins.addAll(uncleOrAunt.getChildren());
        }
        return firstCousins;
    }
    public List<Node> getSecondCousins(String person) {
        List<Node> firstCousins = getFirstCousins(person);
        List<Node> secondCousins = new ArrayList<>();
        for(Node firstCousin : firstCousins) {
            secondCousins.addAll(firstCousin.getChildren());
        }
        return secondCousins;
    }
    public List<Node> getPaternalGrandparents(String person) {
        Node father = getFather(person);
        if(father != null) {
            List<Node> paternalGrandparents = new ArrayList<>();
            Node grandFather = getFather(father.getData());
            if(grandFather != null) {
                paternalGrandparents.add(grandFather);
                paternalGrandparents.addAll(grandFather.getChildren());
            }
            return paternalGrandparents;
        }
        return new ArrayList<>();
    }

    public List<Node> getSon(String person) {
        Node node = search(person);
        if(node != null) {
            return node.getChildren();
        }
        return new ArrayList<>();
    }




    public String getRelationship(String person1, String person2)  {

        Node node1 = search(person1);
        Node node2 = search(person2);
        if (person1.equals(person2)) {
            return "Same person";
        }

        Node fatherOfPerson2 = getFather(person2);
        if (fatherOfPerson2 != null && fatherOfPerson2.getData().equals(person1)) {
            return "Father";
        }
        if (fatherOfPerson2 != null ) {
            Node grandFatherOfPerson2 = getFather(fatherOfPerson2.getData());
            if (grandFatherOfPerson2 != null && grandFatherOfPerson2.getData().equals(person1)) {
                return "Grandfather";
            }
            if (grandFatherOfPerson2 != null) {
                Node greatGrandFatherOfPerson2 = getFather(grandFatherOfPerson2.getData());
                if (greatGrandFatherOfPerson2 != null && greatGrandFatherOfPerson2.getData().equals(person1)) {
                    return "Great Grandfather";
                }
                if (greatGrandFatherOfPerson2 != null) {
                    Node greatGreatGrandFatherOfPerson2 = getFather(greatGrandFatherOfPerson2.getData());
                    if (greatGreatGrandFatherOfPerson2 != null && greatGreatGrandFatherOfPerson2.getData().equals(person1)) {
                        return "Great Great Grandfather";
                    }
                }
            }
        }

        List<Node> sonsOfPerson2 = getSon(person2);
        for (Node son : sonsOfPerson2) {
            if (son.getData().equals(person1)) {
                return "Son";
            }
            List<Node> grandsonsOfSon = getSon(son.getData());
            for (Node grandson : grandsonsOfSon) {
                if (grandson.getData().equals(person1)) {
                    return "Grandson";
                }
                List<Node> greatGrandsonsOfGrandson = getSon(grandson.getData());
                for (Node greatGrandson : greatGrandsonsOfGrandson) {
                    if (greatGrandson.getData().equals(person1)) {
                        return "Great Grandson";
                    }
                    List<Node> greatGreatGrandsonsOfGreatGrandson = getSon(greatGrandson.getData());
                    for (Node greatGreatGrandson : greatGreatGrandsonsOfGreatGrandson) {
                        if (greatGreatGrandson.getData().equals(person1)) {
                            return "Great Great Grandson";
                        }
                    }
                }
            }

        }




        List<Node> fullSiblingsOfPerson2 = getFullSiblings(person2);
        for (Node sibling : fullSiblingsOfPerson2) {
            if (sibling.getData().equals(person1)) {
                return "Siblings";
            }
        }


        List<Node> unclesAndAuntsOfPerson2 = getUnclesAndAunts(person2);
        for (Node uncleOrAunt : unclesAndAuntsOfPerson2) {
            if (uncleOrAunt.getData().equals(person1)) {
                return "Uncle or Aunt";
            }
        }

        List<Node> firstCousinsOfPerson2 = getFirstCousins(person2);
        for (Node cousin : firstCousinsOfPerson2) {
            if (cousin.getData().equals(person1)) {
                return "First Cousin";
            }
        }

        List<Node> secondCousinsOfPerson2 = getSecondCousins(person2);
        for (Node cousin : secondCousinsOfPerson2) {
            if (cousin.getData().equals(person1)) {
                return "Second Cousin";
            }
        }

        List<Node> paternalGrandparentsOfPerson2 = getPaternalGrandparents(person2);
        for (Node grandparent : paternalGrandparentsOfPerson2) {
            if (grandparent.getData().equals(person1)) {
                return "Paternal Grandparent";
            }
        }
        Node fatherOfPerson1 = getFather(person1);
        if (fatherOfPerson1!=null){
            List<Node> siblingsOfFatherOfPerson1 = getFullSiblings(fatherOfPerson1.getData());
            for (Node sibling : siblingsOfFatherOfPerson1) {
                if (sibling.getData().equals(person2)) {
                    return "Nephew or Niece";
                }
            }
        }



        return "No Exist in the Familytree";
    }

}