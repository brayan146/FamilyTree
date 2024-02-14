package org.familytree;

import org.familytree.design.ArbolBinarioGrafico;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new ArbolBinarioGrafico();
                frame.setSize(400, 400);
                frame.setVisible(true);
            }
        });
    }
}