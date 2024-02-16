import org.familytree.Node;
import org.familytree.Tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class GenealogyTests {
    Tree tree = new Tree();
    @Test
    public void tests(){
        Assertions.assertEquals(1,1);
    }

    @Test
    public void testNode(){
        Node node = new Node("Test");
        Assertions.assertEquals("Test", node.getData());
    }

    @Test
    public void testNodeParent(){
        Node node = new Node("Test");
        Node parent = new Node("Parent");
        node.setParent(parent);
        Assertions.assertEquals(parent, node.getParent());
    }
    @Test
    public void testNodeMother(){
        Node node = new Node("Test");
        Node mother = new Node("Mother");
        node.setMother(mother);
        Assertions.assertEquals(mother, node.getMother());
    }
    @Test
    public void testNodeChildren(){
        Node node = new Node("Test");
        Node child = new Node("Child");
        node.addChild(child);
        Assertions.assertTrue(node.isParentOf(child));
    }

    @Test
    public void testNodeToString() {
        Node node = new Node("Test");
        Assertions.assertEquals("Test", node.toString());
    }
    @Test
    public void testNodeChildrenList() {
        Node node = new Node("Test");
        Node child = new Node("Child");
        node.addChild(child);
        Assertions.assertEquals(1, node.getChildren().size());
    }
    @Test
    public void testNodeParentList() {
        Node node = new Node("Test");
        Node parent = new Node("Parent");
        node.setParent(parent);
        Assertions.assertEquals(parent, node.getParent());
    }
    @Test
    public void testNodeMotherList() {
        Node node = new Node("Test");
        Node mother = new Node("Mother");
        node.setMother(mother);
        Assertions.assertEquals(mother, node.getMother());
    }

    @Test
    public void testNodeToStringList() {
        Node node = new Node("Test");
        Assertions.assertEquals("Test", node.toString());
    }
    @Test
    public void parentalRelationship() {
        Node parent = new Node("Parent");
        Node child = new Node("Child");
        child.setParent(parent);
        Assertions.assertEquals(parent, child.getParent());
    }
    @Test
    public void maternalRelationship() {
        Node mother = new Node("Mother");
        Node child = new Node("Child");
        child.setMother(mother);
        Assertions.assertEquals(mother, child.getMother());
    }



}
