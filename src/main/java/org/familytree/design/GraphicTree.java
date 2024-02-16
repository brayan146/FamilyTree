package org.familytree.design;

import org.familytree.Node;
import org.familytree.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GraphicTree extends JFrame {
    private boolean treeBuilt = false;
    private JPanel ventana;
    Tree arbol = new Tree();
    private boolean raizInsertada = false;

    public GraphicTree() {
        super("Genealogy Tree");
    }

    public void showDrawnTree() {
        if (arbol.getRoot() == null) {

            arbol.insertNode("A", "");

            arbol.insertNode("B", "A");
            arbol.insertNode("C", "A");

            arbol.insertNode("D", "B");
            arbol.insertNode("E", "B");
            arbol.insertNode("F", "B");

            arbol.insertNode("G", "C");

            arbol.insertNode("H", "D");
            arbol.insertNode("I", "D");

            arbol.insertNode("J", "E");

            arbol.insertNode("K", "F");
            arbol.insertNode("L", "F");
            arbol.insertNode("M", "F");
            arbol.insertNode("N", "F");

            arbol.insertNode("O", "K");
            arbol.insertNode("P", "K");
            arbol.insertNode("Q", "K");

            arbol.insertNode("R", "L");
            arbol.insertNode("S", "L");

            arbol.insertNode("T", "N");
            arbol.insertNode("U", "N");
            arbol.insertNode("V", "N");

            arbol.insertNode("W", "O");
            arbol.insertNode("X", "O");

            arbol.insertNode("Y", "Q");
            arbol.insertNode("Z", "Q");

            arbol.insertNode("A1", "R");

            arbol.insertNode("A2", "T");
            arbol.insertNode("A3", "T");

            arbol.insertNode("A4", "V");
            arbol.insertNode("A5", "V");
            arbol.insertNode("A6", "V");

        }

        JFrame frame = new JFrame("Genealogy Drawn Tree");
        frame.setSize(1800, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTree(g, arbol.getRoot(), 0, getWidth() - 25, 100);
                //drawTree(g, arbol.getRoot(), getWidth()/2, 100, 40, 60 );
            }
        };

        JButton compareButton = new JButton("Compare and Determine Relationship");
        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                determineRelationship();
            }
        });

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(compareButton, BorderLayout.SOUTH);
        frame.add(backButton, BorderLayout.NORTH);

        compareButton.setEnabled(true);

        frame.setResizable(false);
        frame.setVisible(true);

    }

    public void determineRelationship() {
        String person1 = JOptionPane.showInputDialog("Enter the name of the first person: ");
        String person2 = JOptionPane.showInputDialog("Enter the name of the second person: ");
        if (person1 != null && person2 != null && !person1.isEmpty() && !person2.isEmpty()) {
            Node person1Node = arbol.search(person1);
            Node person2Node = arbol.search(person2);
            if (person1Node == null || person2Node == null) {
                JOptionPane.showMessageDialog(null, "One or both of the persons do not exist in the tree. Please enter valid names.");
                return;
            }
            //try {
                String relationship = arbol.getRelationship(person1, person2);
                JOptionPane.showMessageDialog(null, "The relationship between " + person1 + " and " + person2 + " is: " + relationship);
           // } catch (Exception e) {
            //    JOptionPane.showMessageDialog(null, e.getMessage(), "ALERT", JOptionPane.ERROR_MESSAGE);
           // }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid names. Please enter valid names.");
        }
    }

    public void insertRoot() {
        if (/*raizInsertada*/ arbol.getRoot() == null) {
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
    }

    public void insertChildren() {

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

    public void exitTree() {
        int decision = JOptionPane.showConfirmDialog(null, "Do you want to exit the Genealogy Tree?", "Exit", JOptionPane.YES_NO_OPTION);
        if (decision == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void pintarArbol() {
        if (arbol.getRoot() != null) {
            JFrame frame = new JFrame("Árbol Genealógico");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    drawTree(g, arbol.getRoot(), 0, getWidth() - 25, 100 );
                    //drawTree(g, arbol.getRoot(), getWidth(), 100, 40, 60 );
                }
            };

            frame.add(panel);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No hay nodos para pintar el árbol. Inserte al menos un nodo.");
        }
    }

    public int drawTree(Graphics g, Node x, int x0, int x1, int y) {
        int m = (x0 + x1) / 2;
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(m-40, y, 60, 60);
        g.setColor(Color.BLACK);
        g.setFont(new Font("ARIAL", Font.BOLD, 12));
        String t = String.valueOf(x.getData());
        g.drawString(t, m -5, y + 35);

        List<Node> children = x.getChildren();
        int totalWidth = m - 60 * (children.size() - 1);

        for (Node child : children) {
            int x2 = drawTree(g, child, totalWidth, totalWidth + 60, y + 120);
            g.drawLine(m, y + 60, x2 + 30, y + 100);
            totalWidth += 120;
        }
        return m;
    }

}









/*package org.familytree.design;


import org.familytree.Node;

import org.familytree.Tree;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GraphicTree extends JFrame {
    private JPanel ventana;

    Tree arbol = new Tree();
    boolean bool = true;
    private boolean raizInsertada = false;

    public GraphicTree() {
        super("Genealogy Tree");
        buildTree();
    }

    public void run() {

    }

    public void buildTree() {
        while (bool) {
            String[] opciones = {"1 - Insert Node Root", "2 - Insert Node Child", "3 - Draw Tree","4.-Determine Relationship", "6 - Show Drawn tree","5 - Exit"};
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
                    determineRelationship();
                    break;
                case 4:
                    showDrawnTree();
                    break;
                case 5:
                    exitTree();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private void drawPredefinedTree(Graphics g) {
        int x0 = 100, x1 = 700, y = 50;

        // Inserta los nodos predefinidos
        arbol.insertNode("Elio", "");
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
        arbol.insertNode("Gael", "Abigael");

        // Dibuja el árbol
        drawTree(g, arbol.getRoot(), x0, x1, y);
    }

    public void showDrawnTree(){
        if (arbol.getRoot() == null) {
            JFrame frame = new JFrame("Genealogy Drawn Tree");
            frame.setSize(1000, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    drawPredefinedTree(g);
                }
            };

            JButton compareButton = new JButton("Compare and Determine Relationship");
            compareButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    determineRelationship();
                }
            });

            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.add(compareButton, BorderLayout.SOUTH);

            // Deshabilitar la opción de insertar hijos o comparar relaciones
            compareButton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe un nodo raíz en el árbol predefinido. No se pueden agregar más nodos raíz ni hijos.");
        }
    }

    public void determineRelationship() {
        String person1 = JOptionPane.showInputDialog("Enter the name of the first person: ");
        String person2 = JOptionPane.showInputDialog("Enter the name of the second person: ");
        if (person1 != null && person2 != null && !person1.isEmpty() && !person2.isEmpty()) {
            Node person1Node = arbol.search(person1);
            Node person2Node = arbol.search(person2);
            if (person1Node == null || person2Node == null) {
                JOptionPane.showMessageDialog(null, "One or both of the persons do not exist in the tree. Please enter valid names.");
                return;
            }
            try {
                String relationship = arbol.getRelationship(person1, person2);
                JOptionPane.showMessageDialog(null, "The relationship between " + person1 + " and " + person2 + " is: " + relationship);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e.getMessage(),"ALERT",JOptionPane.ERROR_MESSAGE);
            }
        }else {
            JOptionPane.showMessageDialog(null, "Invalid names. Please enter valid names.");
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
        arbol.insertNode(root, "");
    }

    private void insertChildren() {

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
            System.exit(0);
        }
    }

    private void pintarArbol() {
        if (arbol.getRoot() != null) {
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
        g.drawString(t, m + 20, y + 30);

        List<Node> children = x.getChildren();
        int totalWidth = m - 50 * (children.size() - 1);

        for (Node child : children) {
            int x2 = drawTree(g, child, totalWidth, totalWidth + 50, y + 80);
            g.drawLine(m + 25, y + 40, x2 + 25, y + 80);
            totalWidth += 150;
        }
        return m;

    }

    public void paint(Graphics g) {
        super.paint(g);
        drawTree(g, arbol.getRoot(), 0, this.getWidth() - 25, 100);
    }

}*/
