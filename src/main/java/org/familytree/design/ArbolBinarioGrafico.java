package org.familytree.design;


import org.familytree.Node;

import org.familytree.Tree;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinarioGrafico extends JFrame{
    private JPanel ventana;

    Tree arbol = new Tree();
    boolean bool = true;
    private boolean raizInsertada = false;


    public ArbolBinarioGrafico() {
        super("Genealogy Tree");
        buildTree();
        /*arbol.insertNode("Elio", "");
        arbol.insertNode("Milena", "Elio");
        arbol.insertNode("Karina", "Elio");
        arbol.insertNode("Quique", "Elio");
        arbol.insertNode("Abigael", "Elio");
        arbol.insertNode("Daniela", "Milena");
        arbol.insertNode("Samuel", "Milena");
        arbol.insertNode("Jasiel", "Milena");
        arbol.insertNode("Magdiel", "Quique");
        arbol.insertNode("Aylin", "Quique");
        arbol.insertNode("Yurem", "Abigael");
        arbol.insertNode("Ariadne", "Abigael");
        arbol.insertNode("Gael", "Abigael");*/

    }

    public void buildTree() {
        while (bool){
            String[] opciones = {"1 - Insert Node Root", "2 - Insert Node Child", "3 - Draw Tree", "4 - Exit"};
            int seleccion = JOptionPane.showOptionDialog(null, "Choose an Option", "Menu Genealogy Tree",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    insertRoot();
                    break;
                case 1:
                    insertChildren();
                    break;
                case 2:
                    pintarArbol();
                    break;
                case 3:
                    exitTree();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private void insertRoot() {
        if (!raizInsertada) {
            String root = JOptionPane.showInputDialog("Enter the root's name: ");
            if (root != null && !root.isEmpty()) {
                arbol.insertNode(root, "");
                raizInsertada = true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid root name. Please enter a valid name.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Root node already inserted. You cannot insert another root.");
        }


        /*String root = JOptionPane.showInputDialog("Enter the root's name: ");
        arbol.insertNode(root, "");*/
    }

    private void insertChildren() {
        /*String father = JOptionPane.showInputDialog("Enter father's name:");

        if (father == null || father.isEmpty()) {
            return;
        }

        int number = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of child nodes for " + father + ":"));

        List<String> children = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            String child = JOptionPane.showInputDialog("Enter the name of the child node " + (i + 1) + " for " + father + ":");
            children.add(child);
        }

        for (String ch : children) {
            if (!ch.equals(ch.toUpperCase())) {
                ch = ch.substring(0, 1).toUpperCase() + ch.substring(1).toLowerCase();
            }
            arbol.insertNode(ch, father);
        }*/


        if (!raizInsertada) {
            JOptionPane.showMessageDialog(null, "Please insert the root node first.");
            return;
        }

        String parent = JOptionPane.showInputDialog("Enter the name of the parent:");

        if (parent == null || parent.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid parent name. Please enter a valid name.");
            return;
        }

        Node parentNode = arbol.search(parent);

        if (parentNode == null) {
            JOptionPane.showMessageDialog(null, "Parent node not found. Make sure the parent exists.");
            return;
        }

        int numberOfChildren = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of child nodes for " + parent + ":"));

        List<String> children = new ArrayList<>();
        for (int i = 0; i < numberOfChildren; i++) {
            String child = JOptionPane.showInputDialog("Enter the name of child node " + (i + 1) + " for " + parent + ":");
            children.add(child);
        }

        for (String child : children) {
            if (!child.equals(child.toUpperCase())) {
                child = child.substring(0, 1).toUpperCase() + child.substring(1).toLowerCase();
            }
            arbol.insertNode(child, parent);
        }
    }

    private void exitTree() {
        int decision = JOptionPane.showConfirmDialog(null, "Do you want to exit the Genealogy Tree?", "Exit", JOptionPane.YES_NO_OPTION);
        if (decision == JOptionPane.YES_OPTION) {
            bool = false;
        }
    }

    private void pintarArbol() {
        if (arbol.getRoot() != null) {
            // Solo pinta el árbol si hay al menos un nodo
            JFrame frame = new JFrame("Árbol Genealógico");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    drawTree(g, arbol.getRoot(), 0, getWidth() - 25, 100);
                }
            };

            frame.add(panel);
        } else {
            JOptionPane.showMessageDialog(null, "No hay nodos para pintar el árbol. Inserte al menos un nodo.");
        }
    }


    public int drawTree(Graphics g, Node x, int x0, int x1, int y) {
        int m = (x0 + x1) / 2;
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(m, y, 40, 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("ARIAL", Font.BOLD, 13));
        String t = String.valueOf(x.getData());
        g.drawString(t, m+20, y+30);

        List<Node> children = x.getChildren();
        int totalWidth = m - 50 * (children.size() - 1);

        for(Node child : children) {
            int x2 = drawTree(g, child, totalWidth,totalWidth+50,y+80);
            g.drawLine(m+25,y+40,x2+25,y+80);
            totalWidth += 150;
        }
        return m;

    }
    /*
    public void paint(Graphics g) {
        super.paint(g);
        drawTree(g, arbol.getRoot(), 0, this.getWidth() - 25, 100);
    }*/

}
