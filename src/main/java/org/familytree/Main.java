package org.familytree;

import org.familytree.design.GraphicTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Genealogy Tree");
                frame.setSize(700, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JMenuBar menuBar = new JMenuBar();
                JMenu menu = new JMenu("Options");

                JMenuItem insertRootItem = new JMenuItem("Insert Node Root");
                JMenuItem insertChildItem = new JMenuItem("Insert Node Child");
                JMenuItem drawTreeItem = new JMenuItem("Draw Tree");
                JMenuItem determineRelationshipItem = new JMenuItem("Determine Relationship");
                JMenuItem showDrawnTreeItem = new JMenuItem("Show Drawn Tree");
                JMenuItem exitItem = new JMenuItem("Exit");

                GraphicTree graphicTree = new GraphicTree();

                insertRootItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graphicTree.insertRoot();
                    }
                });

                insertChildItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graphicTree.insertChildren();
                    }
                });

                drawTreeItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graphicTree.pintarArbol();
                    }
                });

                determineRelationshipItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graphicTree.determineRelationship();
                    }
                });

                showDrawnTreeItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graphicTree.showDrawnTree();
                    }
                });

                exitItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        graphicTree.exitTree();
                    }
                });

                menu.add(insertRootItem);
                menu.add(insertChildItem);
                menu.add(drawTreeItem);
                menu.add(determineRelationshipItem);
                menu.add(showDrawnTreeItem);
                menu.add(exitItem);

                menuBar.add(menu);
                frame.setJMenuBar(menuBar);

                frame.setVisible(true);
            }
        });
    }




    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new GraphicTree();
                frame.setSize(700, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }*/
}