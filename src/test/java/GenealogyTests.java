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
    @Test
    public void testTree() {
        Tree tree = new Tree();
        Assertions.assertNotNull(tree);
    }
    @Test
    public void testInsertNode() {
        tree.insertNode("Father", "");
        Assertions.assertEquals("Father", tree.search("Father").getData());
    }
    @Test
    public void testInsertNodeWithParent() {
        tree.insertNode("Father", "");
        tree.insertNode("Child", "Father");
        Assertions.assertEquals("Child", tree.search("Child").getData());
    }
    @Test
    public void testInsertNodeWithParentNotFound() {
        tree.insertNode("Father", "");
        tree.insertNode("Child", "Mother");
        Assertions.assertEquals("Child", tree.search("Child").getData());
    }
    @Test
    public void testInsertNodeWithParentNotFoundAndRoot() {
        tree.insertNode("Father", "");
        tree.insertNode("Child", "Mother");
        tree.insertNode("Child", "");
        Assertions.assertEquals("Child", tree.search("Child").getData());
    }
    @Test
    public void getPathFromRootToNode() {
        tree.insertNode("Father", "");
        tree.insertNode("Child", "Father");
        Assertions.assertEquals("Father", tree.getPathFromRootToNode("Father").get(0).getData());
        Assertions.assertEquals("Child", tree.getPathFromRootToNode("Child").get(1).getData());
    }
    @Test
    public void getFather() {
        tree.insertNode("Father", "");
        tree.insertNode("Child", "Father");
        Assertions.assertEquals("Father", tree.getFather("Child").getData());
    }

    @Test
    public void getRelationship() {
        tree.insertNode("Father", "");
        tree.insertNode("Child", "Father");
        Assertions.assertEquals("Father", tree.getRelationship("Father", "Child"));
    }
    @Test
    public void getRelationshipSon() {
        tree.insertNode("Father", "");
        tree.insertNode("Child", "Father");
        Assertions.assertEquals("Son", tree.getRelationship("Child", "Father"));
    }
    @Test
    public void getRelationshipGrandFather() {
        tree.insertNode("Father", "");
        tree.insertNode("Child", "Father");
        tree.insertNode("GrandChild", "Child");
        Assertions.assertEquals("Grandfather", tree.getRelationship("Father", "GrandChild"));

    }




}
