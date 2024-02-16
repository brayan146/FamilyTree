
public class Test {
    @org.junit.jupiter.api.Test
    public void test() {
        org.familytree.Nodo nodo = new org.familytree.Nodo("data");
        org.junit.jupiter.api.Assertions.assertEquals("data", nodo.data);
        org.junit.jupiter.api.Assertions.assertNull(nodo.left);
        org.junit.jupiter.api.Assertions.assertNull(nodo.right);
    }
    @org.junit.jupiter.api.Test
    public void insertTest() {
        org.familytree.Nodo nodo = new org.familytree.Nodo("data");
        nodo.insert("data2");
        org.junit.jupiter.api.Assertions.assertEquals("data2", nodo.right.data);{

    }

}
